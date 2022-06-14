package be.moorluck.cardgame.services.impl;

import be.moorluck.cardgame.mappers.UserMapper;
import be.moorluck.cardgame.models.dtos.UserDTO;
import be.moorluck.cardgame.models.entities.*;
import be.moorluck.cardgame.models.forms.DeckForm;
import be.moorluck.cardgame.models.forms.UserForm;
import be.moorluck.cardgame.repository.CardRepository;
import be.moorluck.cardgame.repository.DeckRepository;
import be.moorluck.cardgame.repository.RoleRepository;
import be.moorluck.cardgame.repository.UserRepository;
import be.moorluck.cardgame.services.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

// TODO question transactional
@Service
@RequiredArgsConstructor @Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CardRepository<Monster> monsterRepository;
    private final CardRepository<Magic> magicRepository;
    private final DeckRepository deckRepository;

    @Override
    public UserDTO getById(Long id) {
        return userMapper.toDto(userRepository.findById(id).orElse(null));
    }

    @Override
    public UserDTO getByLogin(String login) {
        return userMapper.toDto(userRepository.findByLogin(login));
    }

    @Override
    public Set<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public UserDTO insert(UserForm form) {
        User user = userMapper.toEntity(form);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDTO addRole(Long idUser, String role) {
        User user = userRepository.findById(idUser).orElse(null);
        Role roleToAdd = roleRepository.findByName(role);

        if (user == null || roleToAdd == null)
            return null;

        user.getRoles().add(roleToAdd);

        return userMapper.toDto(user);
    }

    @Override
    public UserDTO addCard(Long idUser, Long idCard) {
        User user = userRepository.findById(idUser).orElse(null);
        Card card = monsterRepository.findById(idCard).orElse(null);

        if (card == null)
            card = magicRepository.findById(idCard).orElse(null);

        if (card == null || user == null)
            return null;

        user.getCards().add(card);

        return userMapper.toDto(user);
    }

    @Override
    public UserDTO addCards(Long idUser, Set<Long> idCards) {
        User user = userRepository.findById(idUser).orElse(null);

        if (user == null)
            return null;

        for (Long id : idCards) {
            Card card = monsterRepository.findById(id).orElse(null);

            if (card == null)
                card = magicRepository.findById(id).orElse(null);

            if (card != null)
                user.getCards().add(card);
        }

        return userMapper.toDto(user);
    }

    @Override
    public UserDTO addDeck(Long idUser, DeckForm form) {
        User user = userRepository.findById(idUser).orElse(null);
        Deck deck = new Deck();


        if (user == null)
            return null;

        for (Long id : form.getCards()) {
            Card card = monsterRepository.findById(id).orElse(null);

            if (card == null)
                card = magicRepository.findById(id).orElse(null);

            if (card != null)
                deck.getCards().add(card);
        }

        deck.setName(form.getName());
        deck.setUser(user);

        deckRepository.save(deck);

        return userMapper.toDto(user);
    }

    @Override
    public UserDTO delete(Long id) {
        UserDTO dto = userMapper.toDto(userRepository.findById(id).orElse(null));

        if (dto == null)
            return null;

        userRepository.deleteById(id);

        return dto;
    }

    // TODO comment ça marche
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        if (user == null)
            throw new UsernameNotFoundException("Login not found in the database");

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(
                role -> authorities.add(new SimpleGrantedAuthority(role.getName()))
        );

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), authorities);
    }


}

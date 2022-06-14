package be.moorluck.cardgame.services.impl;

import be.moorluck.cardgame.mappers.DeckMapper;
import be.moorluck.cardgame.models.dtos.DeckDTO;
import be.moorluck.cardgame.models.dtos.UserDTO;
import be.moorluck.cardgame.models.entities.User;
import be.moorluck.cardgame.models.forms.DeckForm;
import be.moorluck.cardgame.repository.DeckRepository;
import be.moorluck.cardgame.repository.UserRepository;
import be.moorluck.cardgame.services.DeckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor @Transactional
public class DeckServiceImpl implements DeckService {
    private final DeckMapper deckMapper;
    private final DeckRepository deckRepository;
    private final UserRepository userRepository;

    @Override
    public DeckDTO getById(Long id) {
        return deckMapper.toDto(deckRepository.findById(id).orElse(null));
    }

    @Override
    public Set<DeckDTO> getByUserId(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null)
            return null;

        return deckRepository.findByUser(user).stream()
                .map(deckMapper::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<DeckDTO> getAll() {
        return deckRepository.findAll().stream()
                .map(deckMapper::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public DeckDTO delete(Long id) {
        DeckDTO dto = deckMapper.toDto(deckRepository.findById(id).orElse(null));

        if (dto == null)
            return null;

        deckRepository.deleteById(id);

        return dto;
    }
}

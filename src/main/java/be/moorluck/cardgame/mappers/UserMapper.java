package be.moorluck.cardgame.mappers;

import be.moorluck.cardgame.models.dtos.UserDTO;
import be.moorluck.cardgame.models.entities.User;
import be.moorluck.cardgame.models.forms.UserForm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class UserMapper {
    private final CardMapper cardMapper;
    private final RoleMapper roleMapper;

    public UserDTO toDto(User entity) {
        System.out.println(entity);
        if (entity == null)
            return null;

        return UserDTO.builder()
                .id(entity.getId())
                .login(entity.getLogin())
                .password(entity.getPassword())
                .pseudo(entity.getPseudo())
                .cards(
                        entity.getCards()
                        .stream()
                        .map(cardMapper::cardToDto)
                        .collect(Collectors.toList())
                )
                .roles(
                        entity.getRoles()
                        .stream()
                        .map(roleMapper::toDto)
                        .collect(Collectors.toSet())
                )
                .build();
    }

    public User toEntity(UserForm form) {
        if (form == null)
            return null;

        User entity = new User();
        entity.setLogin(form.getLogin());
        entity.setPassword(form.getPassword());
        entity.setPseudo(form.getPseudo());

        return entity;
    }
}

package be.moorluck.cardgame.mappers;

import be.moorluck.cardgame.models.dtos.DeckDTO;
import be.moorluck.cardgame.models.entities.Deck;
import be.moorluck.cardgame.models.forms.DeckForm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class DeckMapper {
    private final CardMapper cardMapper;
    private final UserMapper userMapper;

    public DeckDTO toDto(Deck entity) {
        if (entity == null)
            return null;

        return DeckDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .cards(
                        entity.getCards()
                        .stream()
                        .map(cardMapper::cardToDto)
                        .collect(Collectors.toList())
                )
                .user(userMapper.toDto(entity.getUser()))
                .build();
    }

    public Deck toEntity(DeckForm form) {
        if (form == null)
            return null;

        Deck entity = new Deck();
        entity.setName(form.getName());

        return entity;
    }
}

package be.moorluck.cardgame.mappers;

import be.moorluck.cardgame.models.dtos.CardDTO;
import be.moorluck.cardgame.models.dtos.MagicDTO;
import be.moorluck.cardgame.models.dtos.MonsterDTO;
import be.moorluck.cardgame.models.entities.Card;
import be.moorluck.cardgame.models.entities.Magic;
import be.moorluck.cardgame.models.entities.Monster;
import be.moorluck.cardgame.models.forms.CardForm;
import org.springframework.stereotype.Service;

@Service
public class CardMapper {

    // TO DTO

    public CardDTO cardToDto(Card entity) {
        if (entity == null)
            return null;

        if (entity instanceof Monster) {
            return MonsterDTO.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .effect(entity.getEffect())
                    .description(entity.getDescription())
                    .attack(((Monster)entity).getAttack())
                    .defense(((Monster)entity).getDefense())
                    .build();
        }

        if (entity instanceof Magic) {
            return MagicDTO.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .effect(entity.getEffect())
                    .description(entity.getDescription())
                    .cost(((Magic)entity).getCost())
                    .build();
        }

        return null;
    }

    // TO ENTITY
    public Card cardFormToEntity(CardForm form) {
        if (form == null)
            return null;

        if (form.getType().equals(CardForm.MAGIC_TYPE)) {
            Magic entity = new Magic();
            entity.setName(form.getName());
            entity.setEffect(form.getEffect());
            entity.setDescription(form.getDescription());
            entity.setCost(form.getCost());

            return entity;
        }

        if (form.getType().equals(CardForm.MONSTER_TYPE)) {
            Monster entity = new Monster();
            entity.setName(form.getName());
            entity.setEffect(form.getEffect());
            entity.setDescription(form.getDescription());
            entity.setAttack(form.getAttack());
            entity.setDefense(form.getDefense());

            return entity;
        }

        return null;
    }
}

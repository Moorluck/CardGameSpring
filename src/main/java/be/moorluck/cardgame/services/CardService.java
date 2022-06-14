package be.moorluck.cardgame.services;

import be.moorluck.cardgame.models.dtos.CardDTO;
import be.moorluck.cardgame.models.forms.CardForm;

import java.util.Set;

public interface CardService {
    CardDTO getById(Long id);
    Set<CardDTO> getAll();
    CardDTO insert(CardForm form);
    CardDTO delete(Long id);
}

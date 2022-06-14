package be.moorluck.cardgame.services;

import be.moorluck.cardgame.models.dtos.CardDTO;
import be.moorluck.cardgame.models.dtos.DeckDTO;
import be.moorluck.cardgame.models.forms.CardForm;
import be.moorluck.cardgame.models.forms.DeckForm;

import java.util.Set;

public interface DeckService {
    DeckDTO getById(Long id);
    Set<DeckDTO> getByUserId(Long id);
    Set<DeckDTO> getAll();
    DeckDTO delete(Long id);
}

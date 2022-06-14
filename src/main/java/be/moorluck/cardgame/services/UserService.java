package be.moorluck.cardgame.services;

import be.moorluck.cardgame.models.dtos.DeckDTO;
import be.moorluck.cardgame.models.dtos.UserDTO;
import be.moorluck.cardgame.models.forms.CardForm;
import be.moorluck.cardgame.models.forms.DeckForm;
import be.moorluck.cardgame.models.forms.UserForm;

import java.util.Set;

public interface UserService {
    UserDTO getById(Long id);
    UserDTO getByLogin(String login);
    Set<UserDTO> getAll();
    UserDTO insert(UserForm form);
    UserDTO addRole(Long idUser, String role);
    UserDTO addCard(Long idUser, Long idCard);
    UserDTO addCards(Long idUser, Set<Long> idCards);
    UserDTO addDeck(Long idUser, DeckForm form);
    UserDTO delete(Long id);
}

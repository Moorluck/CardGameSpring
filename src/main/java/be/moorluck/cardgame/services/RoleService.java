package be.moorluck.cardgame.services;

import be.moorluck.cardgame.models.dtos.CardDTO;
import be.moorluck.cardgame.models.dtos.RoleDTO;
import be.moorluck.cardgame.models.forms.CardForm;
import be.moorluck.cardgame.models.forms.RoleForm;

import java.util.Set;

public interface RoleService {
    RoleDTO getById(Long id);
    Set<RoleDTO> getAll();
    RoleDTO insert(RoleForm form);
    RoleDTO delete(Long id);
}

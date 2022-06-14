package be.moorluck.cardgame.mappers;

import be.moorluck.cardgame.models.dtos.RoleDTO;
import be.moorluck.cardgame.models.entities.Role;
import be.moorluck.cardgame.models.forms.RoleForm;
import org.springframework.stereotype.Service;

@Service
public class RoleMapper {
    public RoleDTO toDto(Role entity) {
        if (entity == null)
            return null;

        return RoleDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public Role toEntity(RoleForm form) {
        if (form == null)
            return null;

        Role entity = new Role();
        entity.setName(form.getName());

        return entity;
    }
}

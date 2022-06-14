package be.moorluck.cardgame.models.dtos;

import be.moorluck.cardgame.models.entities.User;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class RoleDTO {
    private Long id;
    private String name;
}

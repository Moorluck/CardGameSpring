package be.moorluck.cardgame.models.dtos;

import lombok.Builder;
import lombok.Data;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class UserDTO {
    private Long id;
    private String login;
    private String password;
    private String pseudo;
    private List<CardDTO> cards;
    private Set<RoleDTO> roles;
}

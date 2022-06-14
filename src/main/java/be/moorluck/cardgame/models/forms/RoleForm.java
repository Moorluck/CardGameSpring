package be.moorluck.cardgame.models.forms;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RoleForm {
    @NotNull
    private String name;
}

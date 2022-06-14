package be.moorluck.cardgame.models.forms;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class UserForm {
    @NotNull
    @Min(4) @Max(15)
    private String login;
    @NotNull
    @Min(4) @Max(15)
    private String password;
    @NotNull
    @Min(4) @Max(15)
    private String pseudo;
}

package be.moorluck.cardgame.models.forms;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class CardForm {

    public static String MAGIC_TYPE = "magic";
    public static String MONSTER_TYPE = "monster";

    @Pattern(regexp = "magic|monster")
    private String type;
    @NotNull
    private String name;
    private String effect;
    @NotNull
    private String description;
    private Integer cost;
    private Integer attack;
    private Integer defense;
}

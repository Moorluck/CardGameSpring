package be.moorluck.cardgame.models.forms;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class DeckForm {
    @NotNull
    private String name;
    private List<Long> cards = new ArrayList<>();
}

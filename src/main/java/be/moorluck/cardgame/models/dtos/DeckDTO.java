package be.moorluck.cardgame.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class DeckDTO {
    private Long id;
    private String name;
    @Getter(onMethod = @__(@JsonIgnore))
    private UserDTO user;
    private List<CardDTO> cards;
}

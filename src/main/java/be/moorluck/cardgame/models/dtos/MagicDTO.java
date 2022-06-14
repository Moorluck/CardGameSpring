package be.moorluck.cardgame.models.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class MagicDTO extends CardDTO {

    private Integer cost = 0;

    @Builder
    public MagicDTO(Long id, String name, String effect, String description, Integer cost) {
        super(id, name, effect, description);
        this.cost = cost;
    }
}

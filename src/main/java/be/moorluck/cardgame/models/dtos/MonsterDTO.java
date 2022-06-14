package be.moorluck.cardgame.models.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class MonsterDTO extends CardDTO {
    private Integer attack;
    private Integer defense;

    @Builder
    public MonsterDTO(Long id, String name, String effect, String description, Integer attack, Integer defense) {
        super(id, name, effect, description);
        this.attack = attack;
        this.defense = defense;
    }
}

package be.moorluck.cardgame.models.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Monster extends Card {
    @Column(nullable = false)
    private Integer attack;
    @Column(nullable = false)
    private Integer defense;
}

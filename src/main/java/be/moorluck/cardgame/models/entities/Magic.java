package be.moorluck.cardgame.models.entities;

import lombok.*;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Magic extends Card{
    private Integer cost = 0;
}

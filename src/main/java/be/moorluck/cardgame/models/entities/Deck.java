package be.moorluck.cardgame.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Deck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    @ManyToOne
    private User user;

    @ManyToMany
    @JoinTable(
            name = "deck_card",
            joinColumns = @JoinColumn(name = "fk_deck_id"),
            inverseJoinColumns = @JoinColumn(name = "fk_card_id")
    )
    private List<Card> cards = new ArrayList<>();
}

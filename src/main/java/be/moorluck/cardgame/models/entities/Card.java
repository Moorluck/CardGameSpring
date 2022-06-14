package be.moorluck.cardgame.models.entities;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String effect;
    @Column(nullable = false)
    private String description;

    @ManyToMany(mappedBy = "cards")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> users = new ArrayList<>();

    @ManyToMany(mappedBy = "cards")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Deck> decks = new ArrayList<>();
}

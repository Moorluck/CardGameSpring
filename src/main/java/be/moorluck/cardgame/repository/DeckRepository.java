package be.moorluck.cardgame.repository;

import be.moorluck.cardgame.models.entities.Deck;
import be.moorluck.cardgame.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {
    Set<Deck> findByUser(User user);
}

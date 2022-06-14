package be.moorluck.cardgame.repository;

import be.moorluck.cardgame.models.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository<T extends Card> extends JpaRepository<T, Long> {
}

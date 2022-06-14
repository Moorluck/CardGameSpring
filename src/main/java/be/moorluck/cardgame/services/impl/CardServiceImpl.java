package be.moorluck.cardgame.services.impl;

import be.moorluck.cardgame.mappers.CardMapper;
import be.moorluck.cardgame.models.dtos.CardDTO;
import be.moorluck.cardgame.models.entities.Card;
import be.moorluck.cardgame.models.entities.Magic;
import be.moorluck.cardgame.models.entities.Monster;
import be.moorluck.cardgame.models.forms.CardForm;
import be.moorluck.cardgame.repository.CardRepository;
import be.moorluck.cardgame.services.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor @Transactional
public class CardServiceImpl implements CardService {
    private final CardMapper cardMapper;
    private final CardRepository<Monster> monsterRepository;
    private final CardRepository<Magic> magicRepository;

    @Override
    public CardDTO getById(Long id) {
        Card card = monsterRepository.findById(id).orElse(null);

        if (card == null)
            card = magicRepository.findById(id).orElse(null);

        return cardMapper.cardToDto(card);
    }

    @Override
    public Set<CardDTO> getAll() {
        Set<Card> cards = new HashSet<>();
        List<Monster> monsters = monsterRepository.findAll();
        List<Magic> magics = magicRepository.findAll();
        cards.addAll(magics);
        cards.addAll(monsters);

        return cards.stream().map(cardMapper::cardToDto).collect(Collectors.toSet());
    }

    @Override
    public CardDTO insert(CardForm form) {
        Card newCard = cardMapper.cardFormToEntity(form);
        if (form.getType().equals(CardForm.MAGIC_TYPE)) {
            return cardMapper.cardToDto(magicRepository.save((Magic) newCard));
        }

        if (form.getType().equals(CardForm.MONSTER_TYPE)) {
            return cardMapper.cardToDto(monsterRepository.save((Monster) newCard));
        }

        return null;
    }

    @Override
    public CardDTO delete(Long id) {
        Card card = monsterRepository.findById(id).orElse(null);

        if (card == null)
            card = magicRepository.findById(id).orElse(null);

        if (card instanceof Monster) {
            monsterRepository.deleteById(id);
        }

        if (card instanceof Magic) {
            magicRepository.deleteById(id);
        }

        return cardMapper.cardToDto(card);
    }
}

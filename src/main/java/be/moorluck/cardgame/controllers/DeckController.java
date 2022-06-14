package be.moorluck.cardgame.controllers;

import be.moorluck.cardgame.models.dtos.DeckDTO;
import be.moorluck.cardgame.services.impl.DeckServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/deck")
public class DeckController {
    private final DeckServiceImpl deckService;

    @GetMapping("{id}")
    public Set<DeckDTO> getByUserId(@PathVariable Long id) {
        return deckService.getByUserId(id);
    }
}

package be.moorluck.cardgame.controllers;

import be.moorluck.cardgame.models.dtos.CardDTO;
import be.moorluck.cardgame.models.entities.Card;
import be.moorluck.cardgame.models.forms.CardForm;
import be.moorluck.cardgame.services.impl.CardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/card")
public class CardController {
    private final CardServiceImpl cardService;

    @GetMapping("{id}")
    public ResponseEntity<CardDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(cardService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Set<CardDTO>> getAll() {
        return ResponseEntity.ok().body(cardService.getAll());
    }

    @PostMapping
    public ResponseEntity<CardDTO> insert(@Valid @RequestBody CardForm form) {
        //TODO pas compris
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/card").toUriString());
        return ResponseEntity.created(uri).body(cardService.insert(form));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CardDTO> delete(@PathVariable Long id) {
        return ResponseEntity.ok().body(cardService.delete(id));
    }
}

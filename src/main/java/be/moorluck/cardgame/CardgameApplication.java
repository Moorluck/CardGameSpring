package be.moorluck.cardgame;

import be.moorluck.cardgame.models.forms.CardForm;
import be.moorluck.cardgame.models.forms.DeckForm;
import be.moorluck.cardgame.models.forms.RoleForm;
import be.moorluck.cardgame.models.forms.UserForm;
import be.moorluck.cardgame.services.impl.CardServiceImpl;
import be.moorluck.cardgame.services.impl.DeckServiceImpl;
import be.moorluck.cardgame.services.impl.RoleServiceImpl;
import be.moorluck.cardgame.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
@RequiredArgsConstructor
public class CardgameApplication implements CommandLineRunner {
	private final CardServiceImpl cardService;
	private final UserServiceImpl userService;
	private final RoleServiceImpl roleService;
	private final DeckServiceImpl deckService;

	public static void main(String[] args) {
		SpringApplication.run(CardgameApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		CardForm cardForm = new CardForm();
		cardForm.setType("magic");
		cardForm.setCost(5);
		cardForm.setName("Flip table");
		cardForm.setDescription("You enrage and flip the table");
		cardForm.setEffect("Flip the table instantly");

		cardService.insert(cardForm);

		CardForm cardForm2 = new CardForm();
		cardForm2.setType("monster");
		cardForm2.setName("Big Dragon");
		cardForm2.setDescription("He is big");
		cardForm2.setEffect("Kill the target because he is big");
		cardForm2.setAttack(10);
		cardForm2.setDefense(10);

		cardService.insert(cardForm2);

		UserForm userForm = new UserForm();
		userForm.setLogin("wgenard");
		userForm.setPassword("12345");
		userForm.setPseudo("Moorluck");

		userService.insert(userForm);

		RoleForm roleForm = new RoleForm();
		roleForm.setName("ADMIN");

		roleService.insert(roleForm);

		RoleForm roleForm1 = new RoleForm();
		roleForm1.setName("USER");

		roleService.insert(roleForm1);

		userService.addRole(1L, "ADMIN");

		userService.addCard(1L, 1L);

		userService.addCards(1L, new HashSet<>(Set.of(1L, 2L)));

		DeckForm deckForm = new DeckForm();
		deckForm.setName("First deck");
		deckForm.getCards().add(1L);
		deckForm.getCards().add(2L);

		userService.addDeck(1L, deckForm);


		System.out.println(cardService.getAll());
		System.out.println(roleService.getAll());
		System.out.println(userService.getAll());
		System.out.println(deckService.getByUserId(1L));
	}
}

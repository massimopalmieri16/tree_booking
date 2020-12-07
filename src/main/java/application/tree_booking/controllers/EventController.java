package application.tree_booking.controllers;

import application.tree_booking.entities.UserDB;
import application.tree_booking.services.AuthenticationService;
import application.tree_booking.services.EventService;
import application.tree_booking.views.EventView;
import application.tree_booking.views.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@RestController
public class EventController {
	@Autowired
	EventService eventService;
	@Autowired
	AuthenticationService authenticationService;

	@GetMapping("/events")
	public List<EventView> getActiveEvents(@CookieValue(value = "userID") String cookieString,
								 HttpServletResponse response) {

		System.out.println("Richiesta eventi | cookie: " + cookieString);

		UserDB userDB = authenticationService.validCookie(cookieString);
		if(userDB != null) {
			System.out.println("Cookie valido dell'user " + userDB.getUserdbUsername());
			response.setStatus(200);    // OK
			return eventService.getActiveEvents(userDB);
		}else{
			System.out.println("Cookie non valido");
			response.setStatus(400);
			return null;
		}
	}

	@GetMapping("/event/{eventid}")
	public EventView getEventDetails(@PathVariable(value = "eventid") UUID eventId,
									 @CookieValue(value = "userID") String cookieString,
									 HttpServletResponse response) {

		System.out.println("Richiesta dettagli evento | cookie: " + cookieString);

		UserDB userDB = authenticationService.validCookie(cookieString);
		if(userDB != null) {
			System.out.println("Cookie valido dell'user " + userDB.getUserdbUsername());
			response.setStatus(200);    // OK
			// TODO aggiungere chiamata a funzione eventService che dato in input eventId restituisce EventView
			return null;
		}else{
			System.out.println("Cookie non valido");
			response.setStatus(400);
			return null;
		}
	}

	@GetMapping("/user/events")
	public List<EventView> getUserEvents(@CookieValue(value = "userID") String cookieString,
										 HttpServletResponse response) {

		System.out.println("Richiesta eventi utente | cookie: " + cookieString);

		UserDB userDB = authenticationService.validCookie(cookieString);
		if(userDB != null) {
			System.out.println("Cookie valido dell'user " + userDB.getUserdbUsername());
			response.setStatus(200);    // OK
			// TODO aggiungere chiamata a funzione eventService che dato in input userDB restituisce i suoi EventView
			return null;
		}else{
			System.out.println("Cookie non valido");
			response.setStatus(400);
			return null;
		}
	}

	@PostMapping("/event")
	public EventView createEvent(/* TODO creare classe EventInputView @RequestBody EventInputView eventInputView,*/
								 @CookieValue(value = "userID") String cookieString,
								 HttpServletResponse response) {

		System.out.println("Richiesta creazione evento | cookie: " + cookieString);

		UserDB userDB = authenticationService.validCookie(cookieString);
		if(userDB != null) {
			System.out.println("Cookie valido dell'user " + userDB.getUserdbUsername());
			response.setStatus(200);    // OK
			// TODO aggiungere chiamata a funzione eventService che dato in input eventInputView lo crea e restituisce EventView
			return null;
		}else{
			System.out.println("Cookie non valido");
			response.setStatus(400);
			return null;
		}
	}

	@PostMapping("/join/{eventid}")
	public EventView joinEvent(@PathVariable(value = "eventid") UUID eventId,
							   @CookieValue(value = "userID") String cookieString,
							   HttpServletResponse response) {

		System.out.println("Richiesta join evento | cookie: " + cookieString);

		UserDB userDB = authenticationService.validCookie(cookieString);
		if(userDB != null) {
			System.out.println("Cookie valido dell'user " + userDB.getUserdbUsername());
			response.setStatus(200);    // OK
			// TODO aggiungere chiamata a funzione eventService che dato in input eventId lo joina e restituisce EventView
			return null;
		}else{
			System.out.println("Cookie non valido");
			response.setStatus(400);
			return null;
		}
	}

	@PostMapping("/unjoin/{eventid}")
	public EventView unjoinEvent(@PathVariable(value = "eventid") UUID eventId,
								 @CookieValue(value = "userID") String cookieString,
								 HttpServletResponse response) {

		System.out.println("Richiesta join evento | cookie: " + cookieString);

		UserDB userDB = authenticationService.validCookie(cookieString);
		if(userDB != null) {
			System.out.println("Cookie valido dell'user " + userDB.getUserdbUsername());
			response.setStatus(200);    // OK
			// TODO aggiungere chiamata a funzione eventService che dato in input eventId lo unjoina e restituisce EventView
			return null;
		}else{
			System.out.println("Cookie non valido");
			response.setStatus(400);
			return null;
		}
	}

	@DeleteMapping("/event/{eventid}")
	public EventView cancelEvent(@PathVariable(value = "eventid") UUID eventId,
								 @CookieValue(value = "userID") String cookieString,
								 HttpServletResponse response) {

		System.out.println("Richiesta cancellazione evento | cookie: " + cookieString);

		UserDB userDB = authenticationService.validCookie(cookieString);
		if(userDB != null) {
			System.out.println("Cookie valido dell'user " + userDB.getUserdbUsername());
			response.setStatus(200);    // OK
			// TODO aggiungere chiamata a funzione eventService che dato in input eventId lo cancella e restituisce EventView
			return null;
		}else{
			System.out.println("Cookie non valido");
			response.setStatus(400);
			return null;
		}
	}
}

package application.tree_booking.controller;

import application.tree_booking.service.EventService;
import application.tree_booking.view.EventView;
import application.tree_booking.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class EventController {
	@Autowired
	EventService eventService;

	@GetMapping("/events")
	public List<EventView> getActiveEvents(@CookieValue(value = "userID") String cookieString,
								 HttpServletResponse response) {

		System.out.println("Richiesta eventi cookie: " + cookieString);

		response.setStatus(200);	// OK

		return eventService.getActiveEvents();
	}
}

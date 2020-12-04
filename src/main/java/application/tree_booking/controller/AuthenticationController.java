package application.tree_booking.controller;

import application.tree_booking.service.AuthenticationService;
import application.tree_booking.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.UUID;

@RestController
public class AuthenticationController {
	@Autowired
	AuthenticationService authenticationService;

	@PostMapping("/user")
	public UserView signUpUser(@RequestBody UserView userView,
							   HttpServletResponse response) throws ParseException {
		System.out.println("UserView ricevuta dal client");
		System.out.println(userView.toString());

		UserView userViewOut = authenticationService.signUpUser(userView);
		if(userViewOut != null) {
			Cookie cookie = new Cookie("userID", UUID.randomUUID().toString());
			response.addCookie(cookie);
			response.setStatus(201);	// Created

			System.out.println("Registrazione effettuata correttamente cookie: " + cookie.getValue());
		}else{
			response.setStatus(409);	// Conflict
		}
		return userViewOut;
	}

	@GetMapping("/login")
	public UserView login(@RequestParam(value = "username") String username,
						  @RequestParam(value = "password") String password,
						  HttpServletResponse response) {

		System.out.println("Richiesta login " + username + " " + password);

		UserView userViewOut = authenticationService.login(username, password);
		if(userViewOut != null) {
			Cookie cookie = new Cookie("userID", UUID.randomUUID().toString());
			response.addCookie(cookie);
			response.setStatus(200);	// OK

			System.out.println("Login effettuato correttamente cookie: " + cookie.getValue());
		}else{
			response.setStatus(401);	// Unauthorized

			System.out.println("Login fallito");
		}
		return userViewOut;
	}
}

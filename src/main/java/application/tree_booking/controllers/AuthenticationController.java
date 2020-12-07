package application.tree_booking.controllers;

import application.tree_booking.services.AuthenticationService;
import application.tree_booking.services.UserDataAuth;
import application.tree_booking.views.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;

@RestController
public class AuthenticationController {
	@Autowired
	AuthenticationService authenticationService;

	@PostMapping("/user")
	public UserView signUpUser(@RequestBody UserView userView,
							   HttpServletResponse response) throws ParseException {
		System.out.println("UserView ricevuta dal client");
		System.out.println(userView.toString());

		UserDataAuth userDataAuth = authenticationService.signUpUser(userView);
		if(userDataAuth != null) {

			response.addCookie(userDataAuth.getCookie());
			response.setStatus(201);	// Created

			System.out.println("Registrazione effettuata correttamente | cookie: " + userDataAuth.getCookie().getValue());
		}else{
			response.setStatus(409);	// Conflict
		}
		return userView;
	}

	@GetMapping("/login")
	public UserView login(@RequestParam(value = "username") String username,
						  @RequestParam(value = "password") String password,
						  HttpServletResponse response) {

		System.out.println("Richiesta login " + username + " " + password);

		UserDataAuth userDataAuth = authenticationService.login(username, password);
		if(userDataAuth != null) {
			response.addCookie(userDataAuth.getCookie());
			response.setStatus(200);	// OK

			System.out.println("Login effettuato correttamente | cookie: " + userDataAuth.getCookie().getValue());
			return userDataAuth.getUserView();
		}else{
			response.setStatus(401);	// Unauthorized

			System.out.println("Login fallito");
			return null;
		}

	}
}

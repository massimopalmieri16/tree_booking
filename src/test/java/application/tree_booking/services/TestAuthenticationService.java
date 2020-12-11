package application.tree_booking.services;

import application.tree_booking.entities.CookieDBRepository;
import application.tree_booking.entities.Gender;
import application.tree_booking.entities.UserDB;
import application.tree_booking.entities.UserDBRepository;
import application.tree_booking.views.UserView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.time.LocalDate;

@SpringBootTest
public class TestAuthenticationService {
	@Autowired
	AuthenticationService authenticationService;
	@Autowired
	UserDBRepository userDBRepository;
	@Autowired
	CookieDBRepository cookieDBRepository;

	@Test
	void userAuthentication() throws ParseException {
		UserDataAuth userDataAuthSignUp = authenticationService.signUpUser(
				new UserView("username",
						"name",
						"surname",
						LocalDate.parse("2000-01-01"),
						Gender.OTHER,
						"123456"));

		assertThat(userDataAuthSignUp).isNotNull();

		UserDB userDB = userDataAuthSignUp.getUserDB();
		UserDataAuth userDataAuthLogin = authenticationService.login(userDB.getUserdbUsername(), "123456");

		assertThat(userDataAuthLogin).isNotNull();
		//assertThat(userDataAuthLogin.getUserDB()).isEqualTo(userDataAuthSignUp.getUserDB());

		cookieDBRepository.deleteAll();
		userDBRepository.delete(userDB);
	}
}

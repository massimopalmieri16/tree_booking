package application.tree_booking.services;

import application.tree_booking.entities.CookieDB;
import application.tree_booking.entities.CookieDBRepository;
import application.tree_booking.entities.UserDB;
import application.tree_booking.entities.UserDBRepository;
import application.tree_booking.views.UserView;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationService {
	@Autowired
	UserDBRepository userDBRepository;
	@Autowired
	CookieDBRepository cookieDBRepository;

	public UserDataAuth signUpUser(UserView userView) throws ParseException {
		// se username non presente lo salvo altrimenti ritorno null
		if(userDBRepository.findByUserdbUsername(userView.getUsername()).isEmpty()){
			UserDB userDB = new UserDB(
					userView.getUsername(),
					userView.getName(),
					userView.getSurname(),
					userView.getBirthDate(),
					userView.getGender(),
					BCrypt.hashpw(userView.getPassword(), BCrypt.gensalt()) // hash della password prima di metterla nel db
			);

			userDBRepository.save(userDB);
			return new UserDataAuth(userView, userDB, genCookie(userDB));
		}else{
			return null;
		}
	}

	public UserDataAuth login(String username, String password){
		// Wipe cookie nel db
		cookieDBRepository.wipeCookie();

		Optional<UserDB> optionalUserDB = userDBRepository.findByUserdbUsername(username);
		if(optionalUserDB.isPresent()){
			UserDB userDB = optionalUserDB.get();
			if(BCrypt.checkpw(password, userDB.getUserdbPassword())) {
				UserView userView = new UserView(userDB.getUserdbUsername(),
						userDB.getUserdbName(),
						userDB.getUserdbSurname(),
						userDB.getUserdbDatebirth(),
						userDB.getUserdbGender(),
						userDB.getUserdbPassword());
				return new UserDataAuth(userView, userDB, genCookie(userDB));
			}
		}

		// se nessuna corrispondenza ritorno null
		return null;
	}

	public UserDB validCookie(String cookieString){
		Optional<CookieDB> optionalCookieDB = cookieDBRepository.findByCookiedbUuidExp(UUID.fromString(cookieString));
		return optionalCookieDB.map(CookieDB::getCookiedbUserDB).orElse(null);
	}

	public void deleteCookieDB(){
		cookieDBRepository.deleteAll();
	}

	private Cookie genCookie(UserDB userDB){

		CookieDB cookieDB = new CookieDB(userDB);
		cookieDBRepository.save(cookieDB);
		return new Cookie("userID", cookieDB.getCookiedbUuid().toString());
	}
}

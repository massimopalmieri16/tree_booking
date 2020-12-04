package application.tree_booking.service;

import application.tree_booking.entities.UserDB;
import application.tree_booking.entities.UserDBRepository;
import application.tree_booking.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Optional;

@Service
public class AuthenticationService {
	@Autowired
	UserDBRepository userDBRepository;

	public UserView signUpUser(UserView userView) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ITALIAN);

		UserDB userDB = new UserDB(
				userView.getUsername(),
				userView.getName(),
				userView.getSurname(),
				userView.getBirthDate(),
				userView.getGender(),
				userView.getPassword()
		);

		// se username non presente lo salvo altrimenti ritorno null
		if(userDBRepository.findByUserdbUsername(userView.getUsername()).isEmpty()){
			userDBRepository.save(userDB);
			return userView;
		}else{
			return null;
		}
	}

	public UserView login(String username, String password){
		Optional<UserDB> optionalUserDB = userDBRepository.findByUserdbUsernameAndUserdbPassword(username, password);

		UserView userView = null;
		if(optionalUserDB.isPresent()){
			UserDB userDB = optionalUserDB.get();
			userView = new UserView(userDB.getUserdbUsername(),
									userDB.getUserdbName(),
									userDB.getUserdbSurname(),
									userDB.getUserdbDatebirth(),
									userDB.getUserdbGender(),
									userDB.getUserdbPassword());
		}

		return userView;
	}
}

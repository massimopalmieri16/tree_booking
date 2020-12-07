package application.tree_booking.services;

import application.tree_booking.entities.UserDB;
import application.tree_booking.views.UserView;

import javax.servlet.http.Cookie;


/** Contenitore di utilità per output funzioni di autenticazione
* 	contiene: UserView, UserDB e Cookie
*/
public class UserDataAuth {
	private UserView userView;
	private UserDB userDB;
	private Cookie cookie;

	public UserDataAuth(UserView userView, UserDB userDB, Cookie cookie) {
		this.userView = userView;
		this.userDB = userDB;
		this.cookie = cookie;
	}

	public UserView getUserView() {
		return userView;
	}

	public void setUserView(UserView userView) {
		this.userView = userView;
	}

	public UserDB getUserDB() {
		return userDB;
	}

	public void setUserDB(UserDB userDB) {
		this.userDB = userDB;
	}

	public Cookie getCookie() {
		return cookie;
	}

	public void setCookie(Cookie cookie) {
		this.cookie = cookie;
	}
}

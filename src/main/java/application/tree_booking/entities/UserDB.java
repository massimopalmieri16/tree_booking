package application.tree_booking.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class UserDB {
	@Id @GeneratedValue
	private int userdbPk;
	private String userdbUsername;
	private String userdbName;
	private String userdbSurname;
	private LocalDate userdbDatebirth;
	private Gender userdbGender;
	private String userdbPassword;
	@OneToMany(mappedBy = "cookiedbUserDB",
			orphanRemoval = true,
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL)
	private List<CookieDB> userdbCookie;
	@ManyToMany(mappedBy = "eventdbParticipants")
	private List<EventDB> userdbEventsJoined;

	public UserDB() {
	}

	public UserDB(String userdbUsername, String userdbName, String userdbSurname, LocalDate userdbDatebirth, Gender userdbGender, String userdbPassword) {
		this.userdbUsername = userdbUsername;
		this.userdbName = userdbName;
		this.userdbSurname = userdbSurname;
		this.userdbDatebirth = userdbDatebirth;
		this.userdbGender = userdbGender;
		this.userdbPassword = userdbPassword;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (Objects.isNull(o) || !(o instanceof UserDB)) return false;
		UserDB userDB = (UserDB) o;
		return getUserdbUsername().equals(userDB.getUserdbUsername()) &&
				getUserdbName().equals(userDB.getUserdbName()) &&
				getUserdbSurname().equals(userDB.getUserdbSurname()) &&
				getUserdbDatebirth().equals(userDB.getUserdbDatebirth()) &&
				getUserdbGender() == userDB.getUserdbGender() &&
				getUserdbPassword().equals(userDB.getUserdbPassword());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getUserdbUsername(), getUserdbName(), getUserdbSurname(), getUserdbDatebirth(), getUserdbGender(), getUserdbPassword());
	}

	// All getter and setter
	public int getUserdbPk() {
		return userdbPk;
	}

	public void setUserdbPk(int userdbPk) {
		this.userdbPk = userdbPk;
	}

	public String getUserdbUsername() {
		return userdbUsername;
	}

	public void setUserdbUsername(String userdbUsername) {
		this.userdbUsername = userdbUsername;
	}

	public String getUserdbName() {
		return userdbName;
	}

	public void setUserdbName(String userdbName) {
		this.userdbName = userdbName;
	}

	public String getUserdbSurname() {
		return userdbSurname;
	}

	public void setUserdbSurname(String userdbSurname) {
		this.userdbSurname = userdbSurname;
	}

	public LocalDate getUserdbDatebirth() {
		return userdbDatebirth;
	}

	public void setUserdbDatebirth(LocalDate userdbDatebirth) {
		this.userdbDatebirth = userdbDatebirth;
	}

	public Gender getUserdbGender() {
		return userdbGender;
	}

	public void setUserdbGender(Gender userdbGender) {
		this.userdbGender = userdbGender;
	}

	public String getUserdbPassword() {
		return userdbPassword;
	}

	public void setUserdbPassword(String userdbPassword) {
		this.userdbPassword = userdbPassword;
	}
}

package application.tree_booking.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class CookieDB {
	@Id	@GeneratedValue
	private int cookiedbPk;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cookiedbUserDB")
	private UserDB cookiedbUserDB;
	private UUID cookiedbUuid;
	private LocalDateTime cookiedbExpiration;

	public CookieDB() {
	}

	public CookieDB(UserDB cookiedbUserDB) {
		this.cookiedbUserDB = cookiedbUserDB;
		this.cookiedbUuid = UUID.randomUUID();
		this.cookiedbExpiration = LocalDateTime.now().plusHours(1);
	}

	// All getter and setter
	public UserDB getCookiedbUserDB() {
		return cookiedbUserDB;
	}

	public void setCookiedbUserDB(UserDB cookiedbUserDB) {
		this.cookiedbUserDB = cookiedbUserDB;
	}

	public int getCookiedbPk() {
		return cookiedbPk;
	}

	public void setCookiedbPk(int cookiedbPk) {
		this.cookiedbPk = cookiedbPk;
	}

	public UUID getCookiedbUuid() {
		return cookiedbUuid;
	}

	public void setCookiedbUuid(UUID cookiedbUuid) {
		this.cookiedbUuid = cookiedbUuid;
	}

	public LocalDateTime getCookiedbExpiration() {
		return cookiedbExpiration;
	}

	public void setCookiedbExpiration(LocalDateTime cookiedbExpiration) {
		this.cookiedbExpiration = cookiedbExpiration;
	}
}

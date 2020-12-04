package application.tree_booking.entities;

import org.apache.catalina.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class EventDB {
	@Id
	private int eventdbPk;
	private UUID eventdbUuid;
	private String eventdbName;
	private LocalDateTime eventdbDate;
	private String eventdbPlace;
	private int eventdbCapacity;
	@OneToOne
	private UserDB eventdbCreator;

	public EventDB() {
	}

	public EventDB(String eventdbName, LocalDateTime eventdbDate, String eventdbPlace, int eventdbCapacity, UserDB eventdbCreator) {
		this.eventdbUuid = UUID.randomUUID();
		this.eventdbName = eventdbName;
		this.eventdbDate = eventdbDate;
		this.eventdbPlace = eventdbPlace;
		this.eventdbCapacity = eventdbCapacity;
		this.eventdbCreator = eventdbCreator;
	}

	// All getter and setter
	public UserDB getEventdbCreator() {
		return eventdbCreator;
	}

	public void setEventdbCreator(UserDB eventdbCreator) {
		this.eventdbCreator = eventdbCreator;
	}

	public int getEventdbPk() {
		return eventdbPk;
	}

	public void setEventdbPk(int eventdbPk) {
		this.eventdbPk = eventdbPk;
	}

	public UUID getEventdbUuid() {
		return eventdbUuid;
	}

	public void setEventdbUuid(UUID eventdbUuid) {
		this.eventdbUuid = eventdbUuid;
	}

	public String getEventdbName() {
		return eventdbName;
	}

	public void setEventdbName(String eventdbName) {
		this.eventdbName = eventdbName;
	}

	public LocalDateTime getEventdbDate() {
		return eventdbDate;
	}

	public void setEventdbDate(LocalDateTime eventdbDate) {
		this.eventdbDate = eventdbDate;
	}

	public String getEventdbPlace() {
		return eventdbPlace;
	}

	public void setEventdbPlace(String eventdbPlace) {
		this.eventdbPlace = eventdbPlace;
	}

	public int getEventdbCapacity() {
		return eventdbCapacity;
	}

	public void setEventdbCapacity(int eventdbCapacity) {
		this.eventdbCapacity = eventdbCapacity;
	}
}

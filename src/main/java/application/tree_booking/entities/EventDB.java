package application.tree_booking.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class EventDB {
	@Id @GeneratedValue
	private int eventdbPk;
	private UUID eventdbUuid;
	private String eventdbName;
	private LocalDateTime eventdbDate;
	private String eventdbPlace;
	private int eventdbCapacity;
	@OneToOne(fetch = FetchType.EAGER,
			cascade = CascadeType.DETACH)
	private UserDB eventdbCreator;
	@ManyToMany(fetch = FetchType.EAGER,
			cascade = CascadeType.DETACH)
	@JoinTable
	private Set<UserDB> eventdbParticipants;

	public EventDB() {
	}

	public EventDB(String eventdbName, LocalDateTime eventdbDate, String eventdbPlace, int eventdbCapacity, UserDB eventdbCreator) {
		this.eventdbUuid = UUID.randomUUID();
		this.eventdbName = eventdbName;
		this.eventdbDate = eventdbDate;
		this.eventdbPlace = eventdbPlace;
		this.eventdbCapacity = eventdbCapacity;
		this.eventdbCreator = eventdbCreator;
		this.eventdbParticipants = new HashSet<>();
	}

	public boolean addPartecipants(UserDB userDB){
		if(this.eventdbParticipants.size() < this.eventdbCapacity && this.eventdbDate.isAfter(LocalDateTime.now())) {
			return this.eventdbParticipants.add(userDB);
		}
		return false;
	}

	public void removePartecipants(UserDB userDB){
		this.eventdbParticipants.remove(userDB);
	}

	// All getter and setter
	public Set<UserDB> getEventdbParticipants() {
		return eventdbParticipants;
	}

	public void setEventdbParticipants(Set<UserDB> eventdbParticipants) {
		this.eventdbParticipants = eventdbParticipants;
	}

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

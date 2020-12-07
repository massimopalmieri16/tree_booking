package application.tree_booking.views;

import java.time.LocalDateTime;
import java.util.UUID;

public class EventView {
	private UUID eventid;
	private boolean owned;
	private boolean joined;
	private String name;
	private LocalDateTime date;
	private String place;
	private Integer capacity;

	public EventView(UUID eventid, boolean owned, boolean joined, String name, LocalDateTime date, String place, Integer capacity) {
		this.eventid = eventid;
		this.owned = owned;
		this.joined = joined;
		this.name = name;
		this.date = date;
		this.place = place;
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "EventView{" +
				"eventid=" + eventid +
				", owned=" + owned +
				", joined=" + joined +
				", name='" + name + '\'' +
				", date=" + date +
				", place='" + place + '\'' +
				", capacity=" + capacity +
				'}';
	}

	// All getter and setter
	public boolean isJoined() {
		return joined;
	}

	public void setJoined(boolean joined) {
		this.joined = joined;
	}

	public UUID getEventid() {
		return eventid;
	}

	public void setEventid(UUID eventid) {
		this.eventid = eventid;
	}

	public boolean isOwned() {
		return owned;
	}

	public void setOwned(boolean owned) {
		this.owned = owned;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
}

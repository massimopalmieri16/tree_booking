package application.tree_booking.views;

import java.time.LocalDateTime;

public class EventInputView {
	private String name;
	private int capacity;
	private String place;
	private LocalDateTime date;

	public EventInputView(String name, int capacity, String place, LocalDateTime date) {
		this.name = name;
		this.capacity = capacity;
		this.place = place;
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
}

package application.tree_booking.views;

import application.tree_booking.entities.Gender;

import java.time.LocalDate;

public class UserView {
	private String username;
	private String name;
	private String surname;
	private LocalDate birthDate;
	private Gender gender;
	private String password;

	public UserView(String username, String name, String surname, LocalDate birthDate, Gender gender, String password) {
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.gender = gender;
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserView{" +
				"username='" + username + '\'' +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", birthDate='" + birthDate + '\'' +
				", gender=" + gender +
				", password='" + password + '\'' +
				'}';
	}

	// All getter and setter
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

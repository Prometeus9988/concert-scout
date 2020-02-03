package logic.bean;

import logic.entity.User;

public class UserBean extends GeneralUserBean{
	// TODO check this
	private static final long serialVersionUID = 42L;
	private String name;
	private String surname;
	
	public UserBean(String username, String password, String email,
			String firstName, String lastName, String profilePicture) {
		this.setUsername(username);
		this.setPassword(password);
		this.setName(firstName);
		this.setSurname(lastName);
		this.setEmail(email);
		this.setProfilePicture(profilePicture);
	}
	
	// TODO check this
	public UserBean(User u) {
		this.setUsername(u.getUsername());
		this.setName(u.getName());
		this.setSurname(u.getSurname());
		this.setProfilePicture(u.getProfilePicture());
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getSurname() {
		return this.surname;
	}
}

package logic.login;

public class UserBean extends GeneralUserBean{
	
	private String name;
	private String surname;
	
	public UserBean(String username, String password, String email, String firstName, String lastName) {
		this.setUsername(username);
		this.setPassword(password);
		this.setName(firstName);
		this.setSurname(lastName);
		this.setEmail(email);
	}
	
	public UserBean(User u) {
		this.setUsername(u.getUsername());
		this.setPassword(u.getPassword());
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
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

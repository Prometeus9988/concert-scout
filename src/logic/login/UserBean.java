package logic.login;

public class UserBean {
	private String username;
	private String password;
	private String name;
	private String surname;
	
	public UserBean() {
		this.username = "";
		this.password = "";
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
	
	public boolean validate() {
		if (this.username.equals("") || this.password.equals("")) {
			return false;
		}
		
		LoginController controller = LoginController.getInstance();
		User found = controller.login(this.username, this.password);
		return  (found != null);
	}
}

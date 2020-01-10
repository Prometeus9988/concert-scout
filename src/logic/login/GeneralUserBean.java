package logic.login;

public class GeneralUserBean {
	private String username;
	private String password;
	private String role;
	
	public GeneralUserBean() {
		this.username = "";
		this.password = "";
		this.role = "";
	}
	
	public GeneralUserBean(GeneralUser gu) {
		this.setUsername(gu.getUsername());
		this.setPassword(gu.getPassword());
		this.setRole(gu.getRole());
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
	
	public void setRole(String name) {
		this.role = name;
	}

	public String getRole() {
		return this.role;
	}
}

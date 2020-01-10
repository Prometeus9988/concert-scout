package logic.buyticket;

public class ArtistBean {
	private String username;
	private String name;
	private String profilePicture;
	
	public ArtistBean(Artist a) {
		this.setUsername(a.getUsername());
		this.setName(a.getName());
		this.setProfilePicture(a.getProfilePicture());
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	
	public String getProfilePicture() {
		return profilePicture;
	}
}

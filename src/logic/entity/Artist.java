package logic.entity;

public class Artist extends GeneralUser {
	private String bandName;
	private String profilePicture;
	
	public Artist(String username, String bandName, String profilePicture) {
		this.bandName = bandName;
		this.profilePicture = profilePicture;
		this.username = username;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getName() {
		return bandName;
	}
	
	public String getProfilePicture() {
		return profilePicture;
	}
}

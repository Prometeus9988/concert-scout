package logic.bean;

import logic.entity.Artist;

public class ArtistBean extends GeneralUserBean{
	private String bandName;
	private String profilePicture;
	
	public ArtistBean(Artist a) {
		this.setBandName(a.getName());
		this.setProfilePicture(a.getProfilePicture());
	}
	
	public ArtistBean(String username, String password, String bandName, String profilePicture, String email) {
		this.setUsername(username);
		this.setPassword(password);
		this.setBandName(bandName);
		this.setProfilePicture(profilePicture);
		this.setEmail(email);
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setBandName(String name) {
		this.bandName = name;
	}
	
	public String getBandName() {
		return bandName;
	}
	
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	
	public String getProfilePicture() {
		return profilePicture;
	}
}

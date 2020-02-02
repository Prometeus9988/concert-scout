package logic.bean;

import java.io.Serializable;

import logic.entity.Artist;

public class ArtistBean extends GeneralUserBean implements Serializable{
	static final long serialVersionUID = 42L;
	
	private String bandName;
	
	public ArtistBean(Artist a) {
		this.setUsername(a.getUsername());
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

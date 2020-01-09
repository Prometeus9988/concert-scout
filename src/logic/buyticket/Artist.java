package logic.buyticket;

public class Artist {
	private String id;
	private String name;
	private String profilePicture;
	
	public Artist(String id, String name, String profilePicture) {
		this.id = id;
		this.name = name;
		this.profilePicture = profilePicture;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getProfilePicture() {
		return profilePicture;
	}
}

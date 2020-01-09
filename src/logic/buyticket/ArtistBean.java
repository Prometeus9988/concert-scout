package logic.buyticket;

public class ArtistBean {
	private String id;
	private String name;
	private String profilePicture;
	
	public ArtistBean(Artist a) {
		this.setId(a.getId());
		this.setName(a.getName());
		this.setProfilePicture(a.getProfilePicture());
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
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

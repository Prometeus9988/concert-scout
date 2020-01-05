package logic.buyticket;

public class MusicEventBean {
	private String id;
	private String artistId;
	private String name;
	private String coverPath;
	private String location;
	
	public MusicEventBean(String id, String artistId, String name, String coverPath,  String location) {
		this.id  = id;
		this.artistId  = artistId;
		this.name = name;
		this.coverPath = coverPath;
		this.location  = location;
	}
	
	public MusicEventBean(MusicEvent me) {
		this.id  = me.getId();
		this.artistId  = me.getArtistId();
		this.name = me.getName();
		this.coverPath = me.getCoverPath();
		this.location  = me.getLocation();
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setArtistId(String id) {
		this.artistId = id;
	}
	
	public String getArtistId() {
		return artistId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}
	
	public String getCoverPath() {
		return coverPath;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getLocation() {
		return this.location;
	}
}

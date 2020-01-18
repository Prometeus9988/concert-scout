package logic.buyticket;

public class MusicEvent {
	private int id;
	private String artistUsername;
	private String name;
	private String coverPath;
	private String location;
	
	public MusicEvent(int id, String artistId, String name, String coverPath, String location) {
		this.id = id;
		this.artistUsername = artistId;
		this.name = name;
		this.coverPath = coverPath;
		this.location = location;
	}
	
	public int getId() {
		return id;
	}
	
	public String getArtistId() {
		return artistUsername;
	}

	public String getName() {
		return name;
	}
	
	public String getCoverPath() {
		return coverPath;
	}
	
	public String getLocation() {
		return this.location;
	}
}

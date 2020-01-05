package logic.buyticket;

public class MusicEvent {
	private String id;
	private String artistId;
	private String name;
	private String coverPath;
	private String location;
	
	public MusicEvent(String id, String artistId, String name, String coverPath, String location) {
		this.id = id;
		this.artistId = artistId;
		this.name = name;
		this.coverPath = coverPath;
		this.location = location;
	}
	
	public String getId() {
		return id;
	}
	
	public String getArtistId() {
		return artistId;
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

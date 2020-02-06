package logic.entity;

import java.time.LocalDateTime;

public class News {
	private String text;
	private String artistId;
	private LocalDateTime dateTime;
	private String picturePath;
	private String bandName;
	private String profilePath;
	private int id;
	
	public News(int id, String text, String artistId, LocalDateTime dateTime, String picturePath, String bandName, String profilePath) {
		this.text = text;
		this.artistId = artistId;
		this.dateTime = dateTime;
		this.picturePath = picturePath;
		this.bandName = bandName;
		this.profilePath = profilePath;
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getText(){
		return this.text;
	}
	
	public String getArtistId(){
		return this.artistId;
	}
	
	public LocalDateTime getDateTime(){
		return this.dateTime;
	}
	
	public String getPicturePath(){
		return this.picturePath;
	}
	
	public String getBandName(){
		return this.bandName;
	}
	
	public String getProfilePath() {
		return this.profilePath;
	}
}

package logic.bean;

import java.io.Serializable;

public class MusicEventBean implements Serializable {
	static final long serialVersionUID = 42L;
	private String id;
	private String artistId;
	private String name;
	private String coverPath;
	private String location;
	private String bandName;
	private String ticketone;
	private String date;
	private double latitude;
	private double longitude;
	
	public void setId(int id) {
		
		this.id = "" + id;
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
	
	public void setBandName(String bandName) {
		this.bandName = bandName;
	}
	
	public String getBandName() {
		return this.bandName;
	}
	
	public void setTicketone(String ticketone) {
		this.ticketone = ticketone;
	}
	
	public String getTicketone() {
		return this.ticketone;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double getLatitude() {
		return this.latitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public double getLongitude() {
		return this.longitude;
	}
	
}

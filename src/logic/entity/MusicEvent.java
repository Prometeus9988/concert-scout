package logic.entity;

import java.util.List;

import logic.bean.MusicEventBean;

public class MusicEvent {
	private int id;
	private String artistUsername;
	private String bandName;
	private String name;
	private String coverPath;
	private String location;
	private String ticketone;
	private List<Double> coordinates;
	private double distance;
	
	public MusicEvent(int id, String artistId, String name, String coverPath, String location, String bandName, String ticketone) {
		this.id = id;
		this.artistUsername = artistId;
		this.name = name;
		this.coverPath = coverPath;
		this.location = location;
		this.bandName = bandName;
		this.ticketone = ticketone;
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
	
	public String getBandName() {
		return this.bandName;
	}
	
	public String getTicketone() {
		return this.ticketone;
	}
	
	public void setCoordinates(List<Double> coordinates) {
		this.coordinates = coordinates;
	}
	
	public List<Double> getCoordinates(){
		return this.coordinates;
	}
	
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public double getDistance() {
		return this.distance;
	}
}

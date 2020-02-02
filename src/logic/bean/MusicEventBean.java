package logic.bean;

import java.io.Serializable;

import logic.entity.MusicEvent;

public class MusicEventBean implements Serializable {
	static final long serialVersionUID = 42L;
	private String id;
	private String artistId;
	private String name;
	private String coverPath;
	private String location;
	private String bandName;
	
	public MusicEventBean(int id, String artistId, String name, String coverPath,  String location, String bandName) {
		this.setId(id);
		this.setArtistId(artistId);
		this.setName(name);
		this.setCoverPath(coverPath);
		this.setLocation(location);
		this.setBandName(bandName);
	}
	
	public MusicEventBean(MusicEvent me) {
		this.setId(me.getId());
		this.setArtistId(me.getArtistId());
		this.setName(me.getName());
		this.setCoverPath(me.getCoverPath());
		this.setLocation(me.getLocation());
		this.setBandName(me.getBandName());
	}
	
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
}

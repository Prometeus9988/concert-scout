package logic.bean;

public class NewsBean {
	private String text;
	private String artistId;
	private String postedSince;
	private String picturePath;
	private String bandName;
	private String profilePath;
	private int id;
	
	public NewsBean() {
		this.text = "";
		this.artistId = "";
		this.postedSince = "";
		this.picturePath = "";
		this.bandName = "";
		this.profilePath = "";
		this.id = 0;
	}
	
	public String getText(){
		return this.text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getArtistId(){
		return this.artistId;
	}
	
	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}
	
	public String getPostedSince(){
		return this.postedSince;
	}
	
	public void setPostedSince(String posted) {
		this.postedSince = posted;
	}
	
	public String getPicturePath(){
		return this.picturePath;
	}
	
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	
	public String getBandName(){
		return this.bandName;
	}
	
	public void setBandName(String bandName) {
		this.bandName = bandName;
	}

	public void setProfilePath(String profilePath) {
		this.profilePath = profilePath;
	}
	
	public String getProfilePath() {
		return this.profilePath;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
}

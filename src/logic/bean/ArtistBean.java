package logic.bean;

import java.io.Serializable;

public class ArtistBean extends GeneralUserBean implements Serializable{
	static final long serialVersionUID = 42L;
	
	private String bandName;
	
	public void setBandName(String name) {
		this.bandName = name;
	}
	
	public String getBandName() {
		return bandName;
	}

}

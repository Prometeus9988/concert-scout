package logic.utils;

import java.util.ArrayList;
import java.util.List;

import logic.bean.ArtistBean;
import logic.bean.MusicEventBean;
import logic.entity.Artist;
import logic.entity.MusicEvent;

public abstract class Controller {
	protected MusicEventBean convert(MusicEvent me) {
		MusicEventBean meb = new MusicEventBean();
		meb.setId(me.getId());
		meb.setArtistId(me.getArtistId());
		meb.setName(me.getName());
		meb.setCoverPath(me.getCoverPath());
		meb.setLocation(me.getLocation());
		meb.setBandName(me.getBandName());
		meb.setTicketone(me.getTicketone());
		
		meb.setLatitude(me.getCoordinates().get(0));
		meb.setLongitude(me.getCoordinates().get(1));
		
		return meb;
	}
	
	protected List<MusicEventBean> convertMusicEventList(List<MusicEvent> l){
		List<MusicEventBean> lb = new ArrayList<>();
		for(int i = 0; i < l.size(); i++) {
			MusicEvent me = l.get(i);
			MusicEventBean meb = this.convert(me);
			lb.add(meb);
		}
		
		return lb;
	}
	
	protected ArtistBean convert(Artist a) {
		ArtistBean ab = new ArtistBean();
		ab.setUsername(a.getUsername());
		ab.setBandName(a.getName());
		ab.setProfilePicture(a.getProfilePicture());
		return ab;
	}
	
	
	protected List<ArtistBean> convertArtistList(List<Artist> l){
		List<ArtistBean> lb = new ArrayList<>();
		for(int i = 0; i < l.size(); i++) {
			Artist curr = l.get(i);
			ArtistBean ab = new ArtistBean();
			ab.setUsername(curr.getUsername());
			ab.setBandName(curr.getName());
			ab.setProfilePicture(curr.getProfilePicture());
			lb.add(ab);
		}
		
		return lb;
	}
}

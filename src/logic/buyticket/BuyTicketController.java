package logic.buyticket;

import java.util.ArrayList;
import java.util.List;

import logic.bean.ArtistBean;
import logic.bean.GeneralUserBean;
import logic.bean.MusicEventBean;
import logic.dao.ArtistDao;
import logic.dao.MusicEventDao;
import logic.entity.Artist;
import logic.entity.MusicEvent;

public class BuyTicketController {

	public List<MusicEventBean> getSuggestedEvents(String username) {
		MusicEventDao med = new MusicEventDao();

		List<MusicEvent> l = med.getSuggestedEvents(username);
		List<MusicEventBean> lb = new ArrayList<>();
		for(int i = 0; i < l.size(); i++) {
			MusicEvent me = l.get(i);
			MusicEventBean meb = new MusicEventBean();
			meb.setId(me.getId());
			meb.setArtistId(me.getArtistId());
			meb.setName(me.getName());
			meb.setCoverPath(me.getCoverPath());
			meb.setLocation(me.getLocation());
			meb.setBandName(me.getBandName());
			meb.setTicketone(me.getTicketone());
			lb.add(meb);
		}
		
		return lb;
	}
	
	public List<MusicEventBean> getSearchMusicEvent(String searchString) {
		MusicEventDao med = new MusicEventDao();

		List<MusicEvent> l = med.getSearchMusicEvent(searchString);
		List<MusicEventBean> lb = new ArrayList<>();
		for(int i = 0; i < l.size(); i++) {
			MusicEvent me = l.get(i);
			MusicEventBean meb = new MusicEventBean();
			meb.setId(me.getId());
			meb.setArtistId(me.getArtistId());
			meb.setName(me.getName());
			meb.setCoverPath(me.getCoverPath());
			meb.setLocation(me.getLocation());
			meb.setBandName(me.getBandName());
			meb.setTicketone(me.getTicketone());
			lb.add(meb);
		}
		
		return lb;
	}
	
	public List<ArtistBean> getSearchArtist(String searchString) {
		ArtistDao ad = new ArtistDao();

		List<Artist> l = ad.getSearchArtist(searchString);
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
	
	public List<ArtistBean> getSuggestedArtist(String username){
		ArtistDao ad = new ArtistDao();
		List<Artist> l = ad.getSuggestedArtist(username);
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
	
	public MusicEventBean getMusicEvent(String id, GeneralUserBean gu) {
		//TODO dovrebbe essere relativo al musicevent e non al buyticketcontroller?
		MusicEventDao med = new MusicEventDao();
		MusicEvent me = med.getMusicEvent(id, gu.getRole());
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
	
	public ArtistBean getArtist(String username) {
		ArtistDao ad = new ArtistDao();
		Artist a = ad.getArtist(username);
		ArtistBean ab = new ArtistBean();
		ab.setUsername(a.getUsername());
		ab.setBandName(a.getName());
		ab.setProfilePicture(a.getProfilePicture());
		return ab;
	}
	
	public void addParticipation(GeneralUserBean user, MusicEventBean meb) {
		AddParticipationController apc = new AddParticipationController();
		apc.addParticipation(user, meb);
	}
	
	public void removeParticipation(GeneralUserBean user, MusicEventBean meb) {
		AddParticipationController apc = new AddParticipationController();
		apc.removeParticipation(user, meb);
	}
	
	public boolean isParticipating(GeneralUserBean user, MusicEventBean meb) {
		AddParticipationController apc = new AddParticipationController();
		return apc.isParticipating(user, meb);
	}
	
	public void getAroundYou(double latitude, double longitude, int radius){
		System.out.println("lat: " + latitude + " lng: " + longitude + " radius " + radius);
	}
}

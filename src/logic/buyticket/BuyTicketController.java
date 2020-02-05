package logic.buyticket;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.bean.ArtistBean;
import logic.bean.GeneralUserBean;
import logic.bean.MusicEventBean;
import logic.bean.UserBean;
import logic.dao.ArtistDao;
import logic.dao.MusicEventDao;
import logic.entity.Artist;
import logic.entity.MusicEvent;
import logic.entity.User;

public class BuyTicketController {
	private static final Logger logger = Logger.getLogger(BuyTicketController.class.getName());

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
		//TODO dovrebbe essere relativo al musicevent e non al buyticketcontroller
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
		MusicEventDao med = new MusicEventDao();
		med.addParticipation(user.getUsername(), meb.getId());
	}
	
	public void removeParticipation(GeneralUserBean user, MusicEventBean meb) {
		MusicEventDao med = new MusicEventDao();
		med.removeParticipation(user.getUsername(), meb.getId());
	}
	
	public boolean isParticipating(GeneralUserBean user, MusicEventBean meb) {
		MusicEventDao med = new MusicEventDao();
		return med.isParticipating(user.getUsername(), meb.getId());
	}
}

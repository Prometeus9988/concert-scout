package logic.buyticket;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.login.GeneralUserBean;
import logic.login.User;
import logic.login.UserBean;

public class BuyTicketController {
	private static final Logger logger = Logger.getLogger(BuyTicketController.class.getName());
	private static BuyTicketController instance = null;
	
	public BuyTicketController() {

	}
	
	public  static BuyTicketController getInstance() {
		if(instance == null) {
			instance = new BuyTicketController();
		}
		return instance;
	}

	public List<MusicEventBean> getSuggestedEvents(String username) {
		MusicEventDao med = new MusicEventDao();
		//List<MusicEventBean> lb = null;
		//List<MusicEvent> l = med.getSuggestedEventsStub(username);
		List<MusicEvent> l = med.getSuggestedEvents(username);
		List<MusicEventBean> lb = new ArrayList<>();
		for(int i = 0; i < l.size(); i++) {
			lb.add(new MusicEventBean(l.get(i)));
		}
		
		return lb;
	}
	
	public List<MusicEventBean> getSearchMusicEvent(String searchString) {
		MusicEventDao med = new MusicEventDao();
		//List<MusicEventBean> lb = null;
		//List<MusicEvent> l = med.getSuggestedEventsStub(username);
		List<MusicEvent> l = med.getSearchMusicEvent(searchString);
		List<MusicEventBean> lb = new ArrayList<>();
		for(int i = 0; i < l.size(); i++) {
			lb.add(new MusicEventBean(l.get(i)));
		}
		
		return lb;
	}
	
	public List<ArtistBean> getSearchArtist(String searchString) {
		ArtistDao ad = new ArtistDao();
		//List<MusicEventBean> lb = null;
		//List<MusicEvent> l = med.getSuggestedEventsStub(username);
		List<Artist> l = ad.getSearchArtist(searchString);
		List<ArtistBean> lb = new ArrayList<>();
		for(int i = 0; i < l.size(); i++) {
			lb.add(new ArtistBean(l.get(i)));
		}
		
		return lb;
	}
	
	public List<ArtistBean> getSuggestedArtist(String username){
		ArtistDao ad = new ArtistDao();
		List<Artist> l = ad.getSuggestedArtist(username);
		List<ArtistBean> lb = new ArrayList<>();
		for(int i = 0; i < l.size(); i++) {
			lb.add(new ArtistBean(l.get(i)));
		}
		
		return lb;
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

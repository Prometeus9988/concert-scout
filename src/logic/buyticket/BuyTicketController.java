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
import logic.utils.Controller;

public class BuyTicketController extends Controller{

	public List<MusicEventBean> getSuggestedEvents(String username) {
		MusicEventDao med = new MusicEventDao();

		List<MusicEvent> l = med.getSuggestedEvents(username);
		return this.convertMusicEventList(l);
	}
	
	public List<MusicEventBean> getSearchMusicEvent(String searchString) {
		MusicEventDao med = new MusicEventDao();

		List<MusicEvent> l = med.getSearchMusicEvent(searchString);
		return this.convertMusicEventList(l);
	}
	
	public List<ArtistBean> getSearchArtist(String searchString) {
		ArtistDao ad = new ArtistDao();

		List<Artist> l = ad.getSearchArtist(searchString);
		return this.convertArtistList(l);
	}
	
	public List<ArtistBean> getSuggestedArtist(String username){
		ArtistDao ad = new ArtistDao();
		List<Artist> l = ad.getSuggestedArtist(username);
		return this.convertArtistList(l);
	}
	
	public MusicEventBean getMusicEvent(String id, GeneralUserBean gu) {
		MusicEventDao med = new MusicEventDao();
		MusicEvent me = med.getMusicEvent(id, gu.getRole());
		MusicEventBean meb = this.convert(me);
		
		meb.setLatitude(me.getCoordinates().get(0));
		meb.setLongitude(me.getCoordinates().get(1));
		return meb;
	}
	
	public ArtistBean getArtist(String username) {
		ArtistDao ad = new ArtistDao();
		Artist a = ad.getArtist(username);
		return this.convert(a);
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
	
	public List<MusicEventBean> getAroundYou(double latitude, double longitude, int radius){
		MusicEventDao med = new MusicEventDao();

		List<MusicEvent> l = med.getAroundYou(latitude, longitude, radius);
		List<MusicEventBean> lb = new ArrayList<>();
		for(int i = 0; i < l.size(); i++) {
			MusicEvent me = l.get(i);
			MusicEventBean meb = this.convert(me);
			meb.setDistance(me.getDistance());
			lb.add(meb);
		}
		
		return lb;
	}
}

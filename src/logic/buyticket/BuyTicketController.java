package logic.buyticket;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BuyTicketController {
	private static final Logger logger = Logger.getLogger(BuyTicketController.class.getName());
	private static BuyTicketController instance = null;
	
	private BuyTicketController() {
		
	}
	
	public  static BuyTicketController getInstance() {
		if(instance == null) {
			instance = new BuyTicketController();
		}
		return instance;
	}
	
	public static void main(String[] args) {
		logger.log(Level.INFO, "Hello World!");
	}
	
	public List<MusicEventBean> getSuggestedEvents(String username) {
		MusicEventDao med = new MusicEventDao();
		List<MusicEvent> l = med.getSuggestedEventsStub(username);
		List<MusicEventBean> lb = new ArrayList<>();
		for(int i = 0; i < l.size(); i++) {
			lb.add(new MusicEventBean(l.get(i)));
		}
		
		return lb;
	}
	
	public List<ArtistBean> getSuggestedArtist(String username){
		ArtistDao ad = new ArtistDao();
		List<Artist> l = ad.getSuggestedArtistStub(username);
		List<ArtistBean> lb = new ArrayList<>();
		for(int i = 0; i < l.size(); i++) {
			lb.add(new ArtistBean(l.get(i)));
		}
		
		return lb;
	}
}

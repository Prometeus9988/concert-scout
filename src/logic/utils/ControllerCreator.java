package logic.utils;

import logic.login.LoginController;
import logic.readnews.ReadNewsController;
import logic.addmusicevent.AddMusicEventController;
import logic.addnews.AddNewsController;
import logic.buyticket.AddParticipationController;
import logic.buyticket.BuyTicketController;
import logic.followartist.FollowArtistController;

public class ControllerCreator {
	private static ControllerCreator instance = null;
	
	private ControllerCreator() {
		
	}
	
	public static ControllerCreator getInstance() {
		if(instance == null) {
			instance = new ControllerCreator();
		}
		return instance;
	}
	
	public LoginController getLoginController() {
		return new LoginController();
	}
	
	public BuyTicketController getBuyTicketController() {
		return new BuyTicketController();
	}
	
	public AddMusicEventController getAddMusicEventController() {
		return new AddMusicEventController();
	}
	
	public AddNewsController getAddNewsController() {
		return new AddNewsController();
	}
	
	public FollowArtistController getFollowArtistController() {
		return new FollowArtistController();
	}
	
	public ReadNewsController getReadNewsController() {
		return new ReadNewsController();
	}
	
	public AddParticipationController getAddParticipationController() {
		return new AddParticipationController();
	}
}

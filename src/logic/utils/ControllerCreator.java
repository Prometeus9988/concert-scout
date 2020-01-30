package logic.utils;

import logic.login.LoginController;
import logic.addmusicevent.AddMusicEventController;
import logic.addnews.AddNewsController;
import logic.buyticket.BuyTicketController;

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
}

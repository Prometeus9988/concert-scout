package logic.buyticket;

import java.util.ArrayList;

public class BuyTicketBoundary {
	public static ArrayList<MusicEventBean> getFollowed(String username) {
		BuyTicketController btc = BuyTicketController.getInstance();
		return btc.getSuggestedEvents(username);
	}
}

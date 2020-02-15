package logic.fxmlview;

import javafx.event.ActionEvent;
import logic.bean.ArtistBean;
import logic.buyticket.BuyTicketController;

public class EventUserController extends EventController {

	UserGraphicChange ugc;

	public EventUserController() {
		this.ugc = UserGraphicChange.getInstance();
	}

	@Override
	public void openEvent(ActionEvent e){
		this.ugc.toEventDetails(this.artBtn.getScene(),this.myMusicEvent, this.from, this.searchString);
	}

	@Override
	public void openArtist(ActionEvent e){
		BuyTicketController btc = new BuyTicketController();
		ArtistBean ab = btc.getArtist(this.myMusicEvent.getArtistId());
		this.ugc.toArtistDetails(this.artBtn.getScene(), ab, this.from, this.searchString);
	}

}

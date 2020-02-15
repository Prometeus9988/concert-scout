package logic.fxmlview;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import logic.bean.*;
import logic.buyticket.BuyTicketController;

public class EventController extends EventControllerTemplate{
	

	private UserGraphicChange ugc;
	
	@FXML
	public void openEvent(ActionEvent e){
		
		this.ugc.toEventDetails(this.artBtn.getScene(),this.myMusicEvent, this.from, this.searchString);
	}
	
	@FXML
	public void openArtist(ActionEvent e){
		
		BuyTicketController btc = new BuyTicketController();
		ArtistBean ab = btc.getArtist(this.myMusicEvent.getArtistId());
		this.ugc.toArtistDetails(this.artBtn.getScene(), ab, this.from, this.searchString);
	}
	
	@Override
	public void init(MusicEventBean ev,String from,String searchString) {
		this.ugc=UserGraphicChange.getInstance();
		super.init(ev, from, searchString);
	}
}

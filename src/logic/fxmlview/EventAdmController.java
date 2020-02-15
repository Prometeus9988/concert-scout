package logic.fxmlview;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import logic.bean.*;
import logic.buyticket.BuyTicketController;

public class EventAdmController extends EventControllerTemplate{

	private AdminGraphicChange agc;
	
	@FXML
	public void openEvent(ActionEvent e){
		
		this.agc.toEventDetails(this.artBtn.getScene(),this.myMusicEvent, this.from, this.searchString);
		
	}
	
	@FXML
	public void openArtist(ActionEvent e){
		
		//open artist
		BuyTicketController btc = new BuyTicketController();
		ArtistBean ab = btc.getArtist(this.myMusicEvent.getArtistId());
		this.agc.toArtistDetails(this.imageBtn.getScene(), ab, this.from, this.searchString);
	}
	
	@Override
	public void init(MusicEventBean ev,String from,String searchString) {
		this.agc=AdminGraphicChange.getInstance();
		super.init(ev, from, searchString);
	}
}

package logic.fxmlview;

import javafx.event.ActionEvent;
import logic.bean.ArtistBean;
import logic.followartist.FollowArtistController;

public class EventAdminController extends EventController {

	private AdminGraphicChange agc;

	public EventAdminController() {
		this.agc = AdminGraphicChange.getInstance();
	}

	@Override
	public void openEvent(ActionEvent e){
		this.agc.toEventDetails(this.artBtn.getScene(),this.myMusicEvent, this.from, this.searchString);
	}

	@Override
	public void openArtist(ActionEvent e){
		//open artist
		FollowArtistController fac = new FollowArtistController();
		ArtistBean ab = fac.getArtist(this.myMusicEvent.getArtistId());
		this.agc.toArtistDetails(this.imageBtn.getScene(), ab, this.from, this.searchString);
	}

}

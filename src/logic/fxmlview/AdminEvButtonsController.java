package logic.fxmlview;

import javafx.event.ActionEvent;



import javafx.fxml.FXML;
import logic.bean.MusicEventBean;
import javafx.scene.control.Button;
import logic.addmusicevent.AddMusicEventController;

public class AdminEvButtonsController extends EvButtonController{
	
	@FXML
	private Button tkButton;
	private AddMusicEventController amec;
	private AdminGraphicChange agc;
	
	@FXML
	public void rejectAction(ActionEvent ev) {
		this.amec.rejectMusicEvent(this.myMusicEvent);
		this.agc.toHomepage(this.tkButton.getScene());
	}
	
	@FXML
	public void acceptAction(ActionEvent ev) {
		this.amec.acceptMusicEvent(this.myMusicEvent);
		this.agc.toHomepage(this.tkButton.getScene());
	}
	
	public void init(MusicEventBean myMusicEvent) {
		//INIT MUSIC EVENT VAR
		this.myMusicEvent=myMusicEvent;
		
		//INIT CONTROLLER
		this.amec = new AddMusicEventController();
		
		//INIT GRAPHIC CHANGE
		this.agc=AdminGraphicChange.getInstance();
	
	}
}

package logic.fxmlView;

import javafx.event.ActionEvent;
import java.net.URL;



import javafx.fxml.FXML;
import logic.utils.SessionUser;
import logic.buyticket.*;
import logic.bean.MusicEventBean;
import javafx.scene.control.Button;
import logic.bean.GeneralUserBean;
import logic.utils.OpenBrowser;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserEvButtonsController {
	
	@FXML
	private Button part;
	@FXML
	private Button tk;
	 
	private MusicEventBean myMusicEvent;
	
	private BuyTicketController controller;
	private GeneralUserBean sessionUser;
	private static final Logger logger=Logger.getLogger(UserEvButtonsController.class.getName());
	
	@FXML
	public void partAction(ActionEvent ev) {
		boolean isPart = controller.isParticipating(this.sessionUser, this.myMusicEvent);
		if(isPart){
			controller.removeParticipation(this.sessionUser, this.myMusicEvent);
			this.part.setText("Add Participation");
		} else {
			controller.addParticipation(this.sessionUser, this.myMusicEvent);
			this.part.setText("Remove Participation");
		}
	}
	
	@FXML
	public void tkAction(ActionEvent ev) {
		//MANAGE NULL LINK
		/*try {
			OpenBrowser.openWebpage(new URL(this.myMusicEvent.getTicketone()));
		}
		catch(IOException e) {
			logger.log(Level.WARNING, e.toString());
		}*/System.out.println("link "+this.myMusicEvent.getTicketone());
	}
	
	public void init(MusicEventBean myMusicEvent) {
		//INIT SESSION USER
		this.sessionUser = SessionUser.getInstance().getSession();
		//INIT BUYTICKETCONTROLLER
		this.controller = new BuyTicketController();
		
		//INIT MUSIC EVENT VAR
		this.myMusicEvent=myMusicEvent;
		
		//INIT BUTTON 
		boolean isPart=this.controller.isParticipating(this.sessionUser, this.myMusicEvent);
		if(isPart==false) {
			this.part.setText("Add Participation");
		}else {
			this.part.setText("Remove Participation");
		}
	}
}

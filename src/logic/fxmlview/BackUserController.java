package logic.fxmlview;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class BackUserController implements BackController{

	private String from;
	private String searchString;
	@FXML
	private Button buttonBack;

	private UserGraphicChange grCtrl;

	@FXML
	public void backAction(ActionEvent ev){

		if (this.from.equals("home")) {
			if(this.searchString.equals(""))
				this.grCtrl.toHomepage(this.buttonBack.getScene());
			else 
				this.grCtrl.toSearchEv(this.buttonBack.getScene(), this.searchString);
			}
		//from my friends
		else if(this.from.equals("friends")) {
			if(this.searchString.equals("")) {
				//go to friends section
				this.grCtrl.toFriendsSection(this.buttonBack.getScene());
			}else {
				//go to search user
				this.grCtrl.toSearchUsr(this.buttonBack.getScene(), searchString);
			}
		}

		//from my events
		else if(this.from.equals("myEvents")) {
			this.grCtrl.toMyEvents(this.buttonBack.getScene());
		}
		//from news
		else if(this.from.equals("news")) {
			this.grCtrl.toNews(this.buttonBack.getScene());
		}
	}

	@Override
	public void init(String from,String searchString) {
		this.grCtrl = UserGraphicChange.getInstance();
		this.from = from;
		this.searchString = searchString;
	}

}

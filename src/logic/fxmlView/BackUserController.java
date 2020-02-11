package logic.fxmlView;

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
		
		//FXMLLoader loader;
		
		if (this.from.equals("home")) {
			if(this.searchString.equals(""))
				this.grCtrl.toHomepage(this.buttonBack.getScene());
			else 
				this.grCtrl.toSearchEv(this.buttonBack.getScene(), this.searchString);
			}
		//from my friends
		
		//from search friends
		
		//from my events
		else if(this.from.equals("myEvents")) {
			this.grCtrl.toMyEvents(this.buttonBack.getScene());
		}
		//from news
		else if(this.from.equals("news")) {
			this.grCtrl.toNews(this.buttonBack.getScene());
		}
	}
	
	public void init(String from,String searchString) {
		this.grCtrl=UserGraphicChange.getInstance();
		this.from=from;
		this.searchString=searchString;
	}
}

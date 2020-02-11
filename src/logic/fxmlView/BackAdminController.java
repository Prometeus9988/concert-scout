package logic.fxmlView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class BackAdminController implements BackController {
		
	
	private String from;
	private String searchString;
	@FXML
	private Button buttonBack;
	
	private AdminGraphicChange agc;
	
	@FXML
	public void backAction(ActionEvent ev){
		
		if(this.from.equals("home")){
			this.agc.toHomepage(this.buttonBack.getScene());
		}else if(this.from.equals("news")) {
			//go to accept news
		}
		
		
	}
	
	public void init(String from,String searchString) {
		this.agc=AdminGraphicChange.getInstance();
		this.from=from;
		this.searchString=searchString;
	}
}

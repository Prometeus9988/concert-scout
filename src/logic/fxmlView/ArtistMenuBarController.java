package logic.fxmlView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import logic.utils.SessionUser;

public class ArtistMenuBarController {
		
	@FXML
	private Button addEventBtn;
	
	@FXML
	private Button addNewsBtn;
	
	@FXML
	private Button logoutButton;
	
	private ArtistGraphicChange agc;
	
	
	@FXML
	public void addEventAction(ActionEvent ev) {		
		this.agc.toHomepage(this.addNewsBtn.getScene());
	}
	
	@FXML
	public void addNewsAction(ActionEvent ev) {
		this.agc.toAddNews(this.addNewsBtn.getScene());
	}
	
	@FXML
	public void logoutButtonAction(ActionEvent ev) {
		SessionUser.getInstance().closeSession();	
		this.agc.toLogin(this.addEventBtn.getScene());
	}
	
	public void init(String selected){
		
		this.agc=ArtistGraphicChange.getInstance();
		
		String style="-fx-background-color: transparent; -fx-border: none; -fx-text-fill: rgba(245, 203, 92, 1); -fx-font-size: 16 ; -fx-font-weight: bold;";
		
		if(selected.equals("addEv")) {
			this.addEventBtn.setStyle(style);
		} else if(selected.equals("addNews")) {
			this.addNewsBtn.setStyle(style);
		}
		
	}
}

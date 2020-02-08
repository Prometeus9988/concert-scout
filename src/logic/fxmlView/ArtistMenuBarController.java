package logic.fxmlView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
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
	public void addEventAction(ActionEvent ev) throws IOException {		
		this.agc.toHomepage(this.addNewsBtn.getScene());
	}
	
	@FXML
	public void addNewsAction(ActionEvent ev) {}
	
	@FXML
	public void logoutButtonAction(ActionEvent ev) throws IOException {
		SessionUser.getInstance().closeSession();	
		this.agc.toLogin(this.addEventBtn.getScene());
	}
	
	public void init(String selected){
		
		this.agc=ArtistGraphicChange.getInstance();
		
		String style="-fx-background-color: transparent; -fx-border: none; -fx-text-fill: rgba(245, 203, 92, 1); -fx-font-size: 16 ; -fx-font-weight: bold;";
		switch(selected) {
			case "addEv":
				this.addEventBtn.setStyle(style);
				break;
			case "addNews":
				this.addNewsBtn.setStyle(style);
				break;
		}
		
	}
}

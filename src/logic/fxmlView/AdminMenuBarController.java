package logic.fxmlView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import logic.utils.SessionUser;

public class AdminMenuBarController {
	
	@FXML
	private Button homeBtn;
	@FXML
	private Button newsBtn;
	@FXML
	private Button logoutBtn;
	
	private AdminGraphicChange agc;
	
	@FXML
	public void homeButtonAction(ActionEvent e) {
		this.agc.toHomepage(this.newsBtn.getScene());
	}
	
	@FXML
	public void newsButtonAction(ActionEvent e) {
		this.agc.toNews(this.newsBtn.getScene());
	}
	
	@FXML
	public void logoutButtonAction(ActionEvent e) {
		SessionUser.getInstance().closeSession();
		this.agc.toLogin(this.newsBtn.getScene());
	}
	
	public void init(String selected) {
			
		this.agc=AdminGraphicChange.getInstance();
		
		String style="-fx-background-color: transparent; -fx-border: none; -fx-text-fill: rgba(245, 203, 92, 1); -fx-font-size: 16 ; -fx-font-weight: bold;";
		switch(selected) {
			case "home":
				this.homeBtn.setStyle(style);
				break;
			case "news":
				this.newsBtn.setStyle(style);
				break;
		}
		
	}
	
}

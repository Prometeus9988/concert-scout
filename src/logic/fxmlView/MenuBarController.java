package logic.fxmlView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import logic.utils.SessionUser;

public class MenuBarController {
	
	@FXML
	private Button homeButton;
	@FXML
	private Button newsButton;
	@FXML
	private Button favouritesButton;
	@FXML
	private Button friendsButton;
	@FXML
	private Button aroundYouButton;
	@FXML
	private Button myEventsButton;
	@FXML
	private Button logoutButton;
	
	private UserGraphicChange ugc;
	
	
	public MenuBarController() {}
	
	
	public void init(String selected) {
		
		this.ugc=UserGraphicChange.getInstance();
		
		String style="-fx-background-color: transparent; -fx-border: none; -fx-text-fill: rgba(245, 203, 92, 1); -fx-font-size: 16 ; -fx-font-weight: bold;";
		
		switch(selected) {
			case "home":
				this.homeButton.setStyle(style);
				break;
			case "news":
				this.newsButton.setStyle(style);
				break;
			case "favourites":
				this.favouritesButton.setStyle(style);
				break;
			case "friends":
				this.friendsButton.setStyle(style);
				break;
			case "around":
				this.aroundYouButton.setStyle(style);
				break;
			case "myEvents":
				this.myEventsButton.setStyle(style);
				break;
		
		}
		
	}
	
	
	@FXML
	public void homeButtonAction(ActionEvent e){
		this.ugc.toHomepage(this.homeButton.getScene());
	}
	
	@FXML
	public void newsButtonAction(ActionEvent e) {
		this.ugc.toNews(this.homeButton.getScene());
	}
	
	@FXML
	public void favouritesButtonAction(ActionEvent e) {
		System.out.println("Go to favourites");
	}
	
	@FXML
	public void friendsButtonAction(ActionEvent e) {
		System.out.println("Go to friends");
	}
	
	@FXML
	public void aroundYouButtonAction(ActionEvent e) {
		System.out.println("Go to around you");
	}
	@FXML
	public void myEventsButtonAction(ActionEvent e) {
		this.ugc.toMyEvents(this.homeButton.getScene());
	}
	
	@FXML
	public void logoutButtonAction(ActionEvent e){
		SessionUser.getInstance().closeSession();
		this.ugc.toLogin(this.homeButton.getScene());
	}
}

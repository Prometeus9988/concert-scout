package logic.fxmlView;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.io.IOException;
import javafx.scene.control.Button;
import logic.bean.GeneralUserBean;
import logic.login.*;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;

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
	
	public MenuBarController() {}
	
	
	public void init(String selected) {
		
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
	public void homeButtonAction(ActionEvent e) {
		System.out.println("Go to home");
	}
	
	@FXML
	public void newsButtonAction(ActionEvent e) {
		System.out.println("Go to news");
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
		System.out.println("Go to myEvents");
	}
	
	@FXML
	public void logoutButtonAction(ActionEvent e) {
		System.out.println("Logout");
	}
}

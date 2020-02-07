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
import logic.utils.SessionUser;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;

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
		/*HomepageArtistController hac=new HomepageArtistController();
		FXMLLoader loader=new FXMLLoader(getClass().getResource("HomepageArtist.fxml"));
		loader.setController(hac);
		this.addNewsBtn.getScene().setRoot(loader.load());
		hac.init();*/
		
		this.agc.toHomepage(this.addNewsBtn.getScene());
	}
	
	@FXML
	public void addNewsAction(ActionEvent ev) {}
	
	@FXML
	public void logoutButtonAction(ActionEvent ev) throws IOException {
		/*LoginViewController lvc=new LoginViewController();
		FXMLLoader loader=new FXMLLoader(getClass().getResource("Login.fxml"));
		loader.setController(lvc);
		this.addEventBtn.getScene().setRoot(loader.load());
		lvc.init();*/
		
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

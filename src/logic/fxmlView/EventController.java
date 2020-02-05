package logic.fxmlView;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.io.IOException;
import java.io.File;
import logic.bean.GeneralUserBean;
import logic.login.*;
import logic.utils.*;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import logic.bean.*;
import logic.utils.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Rectangle2D;
import logic.bean.*;

public class EventController {
	
	@FXML
	private Button eventBtn;
	@FXML
	private Button artBtn;
	@FXML
	private Button imageBtn;
	
	private MusicEventBean myMusicEvent;
	
	@FXML
	public void openEvent(ActionEvent e) {
		System.out.println("openEvent");
	}
	
	@FXML
	public void openArtist(ActionEvent e) {
		System.out.println("openArtist");
	}
	
	public void init(MusicEventBean ev) {
		
		this.myMusicEvent=ev;
		String path=System.getProperty("user.home")+ File.separator
				+ "Desktop" + File.separator + "LIVEtheMUSIC" + File.separator
				+ "trunk" + File.separator + "WebContent" + File.separator
				+ "img" + File.separator + "concertPictures"+File.separator+this.myMusicEvent.getCoverPath();
		
		File file = new File(path);
		Image image = new Image(file.toURI().toString());
		ImageView iv3 = new ImageView(image);
		iv3.setFitHeight(170);
        iv3.setFitWidth(110);
		this.imageBtn.setGraphic(iv3);
		
		
		
		
		
		this.artBtn.setText(this.myMusicEvent.getBandName());
		this.eventBtn.setText(this.myMusicEvent.getName());
	}
}

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
import java.io.InputStream;
import java.nio.file.Files;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.ComboBox;
import logic.login.*;
import logic.utils.*;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.stage.Stage;
import java.io.FileInputStream;
import logic.buyticket.*;
import java.util.List;
import logic.bean.ArtistBean;
import logic.bean.MusicEventBean;
import javafx.scene.control.Button;


public class BackUserController {
	
	private String from;
	private String searchString;
	@FXML
	private Button buttonBack;
	
	
	@FXML
	public void backAction(ActionEvent ev) throws IOException{
		
		FXMLLoader loader;
		
		if(this.from=="home") {
			HomepageUserController huc=new HomepageUserController();
			loader=new FXMLLoader(getClass().getResource("HomepageUser.fxml"));
			loader.setController(huc);
			this.buttonBack.getScene().setRoot(loader.load());
			huc.init();
		}else if(this.from=="search"){
			SearchEventsArtistsController evc=new SearchEventsArtistsController();
			loader=new FXMLLoader(getClass().getResource("SearchEventsArtists.fxml"));
			loader.setController(evc);
			this.buttonBack.getScene().setRoot(loader.load());
			evc.init(this.searchString);
			
		}
	}
	
	public void init(String from,String searchString) {
		this.from=from;
		this.searchString=searchString;
	}
}

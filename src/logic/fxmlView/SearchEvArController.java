package logic.fxmlView;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javafx.scene.control.ComboBox;

import logic.bean.ArtistBean;
import logic.bean.GeneralUserBean;
import logic.bean.UserBean;
import logic.login.*;
import logic.utils.*;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.stage.Stage;
import java.io.FileInputStream;

public class SearchEvArController implements SearchBarController {
	
	@FXML
	private TextField searchField;
	private UserGraphicChange ucg;
	
	@FXML
	public void searchAction(ActionEvent e){
		
		/*SearchEventsArtistsController evc=new SearchEventsArtistsController();
		FXMLLoader loader=new FXMLLoader(getClass().getResource("SearchEventsArtists.fxml"));
		loader.setController(evc);
		this.searchField.getScene().setRoot(loader.load());
		evc.init(this.searchField.getText());*/
		
		this.ucg.toSearchEv(this.searchField.getScene(), this.searchField.getText());
	}
	
	public void init() {
		this.ucg=UserGraphicChange.getInstance();
		this.searchField.setPromptText("Search artists or events...");
		
	}


}

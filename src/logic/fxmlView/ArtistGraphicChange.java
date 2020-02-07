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
import logic.view.SearchServlet;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.stage.Stage;
import java.io.FileInputStream;
import logic.buyticket.*;

import java.util.List;
import java.util.logging.Level;

import logic.bean.ArtistBean;
import logic.bean.MusicEventBean;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;
import javafx.scene.Parent;

public class ArtistGraphicChange {
	
	private ArtistGraphicChange() {}
	
	public static ArtistGraphicChange myInstance=null;
	private static final Logger logger = Logger.getLogger(ArtistGraphicChange.class.getName());
	
	public static ArtistGraphicChange getInstance() {
		
		if(myInstance==null) {
			myInstance=new ArtistGraphicChange();
		}
		return myInstance;
	}
	
	public void menuBar(VBox menu,String sel) {
		try {
			ArtistMenuBarController amc=new ArtistMenuBarController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("ArtistMenuBar.fxml"));			
			loader.setController(amc);
			menu.getChildren().add(loader.load());
			amc.init(sel);
		}catch(IOException e) {
			logger.log(Level.WARNING, e.toString());
		}
	}
	
	public void toHomepage(Scene scene) {
		try {
			HomepageArtistController hac=new HomepageArtistController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("HomepageArtist.fxml"));
			loader.setController(hac);
			scene.setRoot(loader.load());
			hac.init();
		}catch(IOException e) {
			logger.log(Level.WARNING, e.toString());
		}
	}
	
	public void toLogin(Scene scene) {
		try {
			LoginViewController lvc=new LoginViewController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("Login.fxml"));
			loader.setController(lvc);
			scene.setRoot(loader.load());
			lvc.init();
		}catch(IOException e) {
			logger.log(Level.WARNING, e.toString());
		}	
	}
	
}

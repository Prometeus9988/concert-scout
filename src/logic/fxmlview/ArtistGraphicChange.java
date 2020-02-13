package logic.fxmlview;

import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.layout.VBox;

import java.util.logging.Level;

import javafx.scene.Scene;
import java.util.logging.Logger;

public class ArtistGraphicChange {
	
	private ArtistGraphicChange() {}
	
	private static ArtistGraphicChange myInstance=null;
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
	
	public void toAddNews(Scene scene) {
		try {
			AddNewsArtistController anc=new AddNewsArtistController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("AddNewsArtist.fxml"));
			loader.setController(anc);
			scene.setRoot(loader.load());
			anc.init();
		}catch(IOException e) {
			logger.log(Level.WARNING,e.toString());
		}
	}
	
}

package logic.fxmlView;

import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import java.util.logging.Level;

import logic.bean.ArtistBean;
import logic.bean.MusicEventBean;
import javafx.scene.Scene;
import java.util.logging.Logger;
import javafx.scene.layout.AnchorPane;
import logic.bean.NewsBean;

public class UserGraphicChange {
	
	private static UserGraphicChange myInstance=null;
	private static final Logger logger = Logger.getLogger(UserGraphicChange.class.getName());
	
	private UserGraphicChange(){}
	
	public static UserGraphicChange getInstance(){
		if(myInstance==null) {
			myInstance=new UserGraphicChange();
		}
		return myInstance;
	}
	
	public void toHomepage(Scene scene) {
		try {
			HomepageUserController huc=new HomepageUserController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("HomepageUser.fxml"));
			loader.setController(huc);
			scene.setRoot(loader.load());
			huc.init();
		}catch(IOException e) {
			logger.log(Level.WARNING, e.toString());
		}
	}
	
	public void toSearchEv(Scene scene,String searchString) {
		try {
			SearchEventsArtistsController evc=new SearchEventsArtistsController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("SearchEventsArtists.fxml"));
			loader.setController(evc);
			scene.setRoot(loader.load());
			evc.init(searchString);
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
	
	public void menuBar(VBox pos,String sel) {
		try {
			MenuBarController mbc=new MenuBarController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("MenuBar.fxml"));
			loader.setController(mbc);
			pos.getChildren().add(loader.load());
			mbc.init(sel);
		}catch(IOException e) {
			logger.log(Level.WARNING,e.toString());
		}
	}
	
	public void searchBar(AnchorPane ap,int kind) {
		try {
			SearchBarController sc=SearchBarControllerFactory.getInstance().creator(kind);
			FXMLLoader loader=new FXMLLoader(getClass().getResource("SearchBar.fxml"));
			loader.setController(sc);
			ap.getChildren().add(loader.load());
			sc.init();
		}catch(IOException e) {
			logger.log(Level.WARNING,e.toString());
		}
	}
	
	public void eventPreview(HBox box,MusicEventBean event,String from,String searchString) {
		try {
			EventController ev=new EventController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("Event.fxml"));
			loader.setController(ev);
			box.getChildren().add(loader.load());
			ev.init(event, from, searchString);
		}catch(IOException e) {
			logger.log(Level.WARNING,e.toString());
		}
	}
	
	public void artistPreview(HBox box, ArtistBean ar,String from,String searchString) {
		try {	
			ArtistController ac=new ArtistController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("Artist.fxml"));
			loader.setController(ac);
			box.getChildren().add(loader.load());
			ac.init(ar, from, searchString);
			
		}catch(IOException e) {
			logger.log(Level.WARNING,e.toString());
		}
	}
	
	public void backButton(AnchorPane ap,String from,String searchString) {
		try {
			BackController bc= BackControllerFactory.getInstance().creator(1);
			FXMLLoader loader=new FXMLLoader(getClass().getResource("BackButton.fxml"));
			loader.setController(bc);
			ap.getChildren().add(loader.load());
			bc.init(from, searchString);
		}catch(IOException e) {
			logger.log(Level.WARNING,e.toString());
		}
	}
	
	public void evDetailsButtons(HBox box,MusicEventBean event) {
		try {
			UserEvButtonsController btc=new UserEvButtonsController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("UserEvDetButtons.fxml"));
			loader.setController(btc);
			box.getChildren().add(loader.load());
			btc.init(event);
		}catch(IOException e) {
			logger.log(Level.WARNING,e.toString());
		}
	}
	
	public void toArtistDetails(Scene scene,ArtistBean ar, String from,String searchString) {
		try {
			ArtDetailsController adc=new ArtDetailsController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("ArtDetails.fxml"));
			loader.setController(adc);
			scene.setRoot(loader.load());
			adc.init(ar,from,searchString);
			
		}catch(IOException e) {
			logger.log(Level.WARNING, e.toString());
		}
	}
	
	public void toEventDetails(Scene scene,MusicEventBean meb,String from,String searchString ) {
		try {
			EvDetailsController edc=new EvDetailsController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("EvDetails.fxml"));
			loader.setController(edc);
			scene.setRoot(loader.load());
			edc.init(meb, from, searchString);
		}catch(IOException e) {
			logger.log(Level.WARNING,e.toString());
		}
	}
	
	public void toMyEvents(Scene scene) {
		try {
			YourEventsController yec=new YourEventsController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("YourEventsSection.fxml"));
			loader.setController(yec);
			scene.setRoot(loader.load());
			yec.init();
		}catch(IOException e) {
			logger.log(Level.WARNING,e.toString());
		}
	}
	
	public void eventPreviewMyEvents(VBox box,MusicEventBean event,String from,String searchString) {
		try {
			EventController ev=new EventController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("Event.fxml"));
			loader.setController(ev);
			box.getChildren().add(loader.load());
			ev.init(event, from, searchString);
		}catch(IOException e) {
			logger.log(Level.WARNING,e.toString());
		}
	}
	
	public void toNews(Scene scene) {
		try {
			NewsUserSectionController controller=new NewsUserSectionController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("NewsSection.fxml"));
			loader.setController(controller);
			scene.setRoot(loader.load());
			controller.init();
		}catch(IOException e) {
			logger.log(Level.WARNING,e.toString());
		}
	}
	
	public void newsPrev(VBox anchor,NewsBean myNews) {
		try {
			NewsUserController controller=new NewsUserController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("News.fxml"));
			loader.setController(controller);
			anchor.getChildren().add(loader.load());
			controller.init(myNews);
		}catch(IOException e) {
			logger.log(Level.WARNING,e.toString());
		}
	}
}

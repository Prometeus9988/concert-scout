package logic.fxmlView;

import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.layout.VBox;

import java.util.logging.Level;

import javafx.scene.Scene;
import java.util.logging.Logger;
import logic.bean.MusicEventBean;
import logic.bean.ArtistBean;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;


public class AdminGraphicChange {
	
	public static AdminGraphicChange myInstance=null;
	private static final Logger logger=Logger.getLogger(AdminGraphicChange.class.getName());
	
	private AdminGraphicChange() {}
	
	public static AdminGraphicChange getInstance() {
		if(myInstance==null) {
			myInstance=new AdminGraphicChange();
		}
		return myInstance;
	}
	
	public void menuBar(VBox menu,String sel) {
		try {
			AdminMenuBarController amc=new AdminMenuBarController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("AdminMenuBar.fxml"));
			loader.setController(amc);
			menu.getChildren().add(loader.load());
			amc.init(sel);
		}catch(IOException e) {
			logger.log(Level.WARNING,e.toString());
		}
	}
	
	public void toHomepage(Scene scene) {
		try {
			HomepageAdminController hac=new HomepageAdminController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("AdminHomepage.fxml"));
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
	
	public void eventPreview(VBox box,MusicEventBean event,String from,String searchString){
		
		try {
			EventAdmController ev=new EventAdmController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("Event.fxml"));
			loader.setController(ev);
			box.getChildren().add(loader.load());
			ev.init(event, from, searchString);
		}catch(IOException e) {
			logger.log(Level.WARNING,e.toString());
		}
	}
	
	public void toArtistDetails(Scene scene,ArtistBean ar,String from,String searchString) {
		try {	
			AdminArtDetailsController adc=new AdminArtDetailsController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("ArtDetails.fxml"));
			loader.setController(adc);
			scene.setRoot(loader.load());
			adc.init(ar,from,searchString);			
		}catch(IOException e) {
			logger.log(Level.WARNING,e.toString());
		}
	}
	
	public void backButton(AnchorPane ap,String from,String searchString) {
		try {
			BackController bc= BackControllerFactory.getInstance().creator(2);
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
			AdminEvButtonsController controller=new AdminEvButtonsController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("AdminEvDetButtons.fxml"));
			loader.setController(controller);
			box.getChildren().add(loader.load());
			controller.init(event);
		}catch(IOException e) {
			logger.log(Level.WARNING,e.toString());
		}
		
	}
	
	public void toEventDetails(Scene scene,MusicEventBean meb,String from,String searchString) {
		try {
			AdminEvDetController controller=new AdminEvDetController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("EvDetails.fxml"));
			loader.setController(controller);
			scene.setRoot(loader.load());
			controller.init(meb,from,searchString);
		}catch(IOException e) {
			logger.log(Level.WARNING,e.toString());
		}
	}
	
	
}

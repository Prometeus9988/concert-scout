package logic.fxmlview;

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
import logic.bean.NewsBean;

public class AdminGraphicChange{
	
	private static AdminGraphicChange myInstance = null;
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
	
	public void newsPrev(VBox anchor,NewsBean myNews) {
		try {
			NewsAdminController controller=new NewsAdminController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("News.fxml"));
			loader.setController(controller);
			anchor.getChildren().add(loader.load());
			controller.init(myNews);
		}catch(IOException e) {
			logger.log(Level.WARNING,e.toString());
		}
	}
	
	public void toNews(Scene scene) {
		try {
			AdminNewsSectionController controller=new AdminNewsSectionController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("NewsSection.fxml"));
			loader.setController(controller);
			scene.setRoot(loader.load());
			controller.init();
		}catch(IOException e) {
			logger.log(Level.WARNING,e.toString());
		}
	}
	
	public void newsBtn(HBox box,NewsBean nb) {
		try {
			AdminNewsBtnController controller=new AdminNewsBtnController();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("AdminNewsBtn.fxml"));
			loader.setController(controller);
			box.getChildren().add(loader.load());
			controller.init(nb);
		}catch(IOException e) {
			logger.log(Level.WARNING,e.toString());
		}
	}
	
}

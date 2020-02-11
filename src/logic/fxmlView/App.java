package logic.fxmlView;

import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;


public class App extends Application {
	
	@Override
	public void start(Stage main) throws IOException {
		
		main.setTitle("LIVEtheMUSIC");
		
		LoginViewController lvc=new LoginViewController();
		FXMLLoader loader=new FXMLLoader(getClass().getResource("Login.fxml"));
		loader.setController(lvc);
		Scene scene=new Scene(loader.load());
		lvc.init();
		
		/*MenuBarController mbc=new MenuBarController();
		FXMLLoader loader=new FXMLLoader(getClass().getResource("MenuBar.fxml"));
		
		loader.setController(mbc);
		
		Scene scene=new Scene(loader.load());
		mbc.init("myEvents");*/
		
		/*TryController tc=new TryController();
		FXMLLoader loader=new FXMLLoader(getClass().getResource("Try.fxml"));
		
		loader.setController(tc);
		Scene scene=new Scene(loader.load());
		tc.init();*/
		
		/*
		HomepageUserController huc=new HomepageUserController();
		FXMLLoader loader=new FXMLLoader(getClass().getResource("HomepageUser.fxml"));
		loader.setController(huc);
		Scene scene=new Scene(loader.load());
		huc.init();*/
		
		/*FXMLLoader loader=new FXMLLoader(getClass().getResource("HomepageArtist.fxml"));
		Scene scene=new Scene(loader.load());
		*/
		
		/*EventController ev=new EventController();
		FXMLLoader loader=new FXMLLoader(getClass().getResource("Event.fxml"));
		loader.setController(ev);
		
		Scene scene=new Scene(loader.load());
		ev.init();*/
		
		
		/*EvDetailsController detC=new EvDetailsController();
		FXMLLoader loader=new FXMLLoader(getClass().getResource("EvDetails.fxml"));
		loader.setController(detC);
		Scene scene=new Scene(loader.load());
		*/
		/*
		ArtistMenuBarController mbc=new ArtistMenuBarController();
		FXMLLoader loader=new FXMLLoader(getClass().getResource("ArtistMenuBar.fxml"));
		
		loader.setController(mbc);
		
		Scene scene=new Scene(loader.load());
		mbc.init("addEv");
		
		HomepageAdminController ahc=new HomepageAdminController();
		FXMLLoader loader=new FXMLLoader (getClass().getResource("AdminHomepage.fxml"));
		Scene scene=new Scene(loader.load());
		ahc.init();*/
		
		/*AdminMenuBarController controller=new AdminMenuBarController();
		FXMLLoader loader=new FXMLLoader (getClass().getResource("AdminMenuBar.fxml"));
		loader.setController(controller);
	
		Scene scene=new Scene(loader.load());
		controller.init("news");*/
		/*
		VBox root=new VBox();
		root.setSpacing(20);
		Scene scene= new Scene(root);
	
		NewsUserController nc=new NewsUserController();
		FXMLLoader loader=new FXMLLoader(getClass().getResource("News.fxml"));
		loader.setController(nc);
		root.getChildren().add(loader.load());
		nc.init();
		nc=new NewsUserController();
		loader=new FXMLLoader(getClass().getResource("News.fxml"));
		loader.setController(nc);
		root.getChildren().add(loader.load());
		*/
		main.setScene(scene);
		//nc.init();
		main.setMaximized(true);
		main.show();
		
		

	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}	
	
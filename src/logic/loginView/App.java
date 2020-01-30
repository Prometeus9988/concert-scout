package logic.loginView;

import javafx.application.Application;
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
		main.setScene(scene);
		main.setMaximized(true);
		main.show();
		

	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}	
	
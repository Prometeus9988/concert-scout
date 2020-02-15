package logic.fxmlview;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public abstract class GraphicChangeTemplate {
	
	protected final Logger logger = Logger.getLogger("GraphicChange");
	
	public void catcher(GraphicChangeAction gca) {
		try {
			gca.act();
		} catch (IOException ie) {
			logger.log(Level.WARNING, ie.toString());
		}
	}

	public void toLogin(Scene scene) {
		this.catcher(() -> {
			LoginViewController lvc = new LoginViewController();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
			loader.setController(lvc);
			scene.setRoot(loader.load());
			lvc.init();
		});
	}
}

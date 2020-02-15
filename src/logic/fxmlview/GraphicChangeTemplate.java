package logic.fxmlview;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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
		this.catcher(new GraphicChangeAction() {
			@Override
			public void act() throws IOException {
				LoginViewController lvc = new LoginViewController();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
				loader.setController(lvc);
				scene.setRoot(loader.load());
				lvc.init();
			}
		});
	}

	public void addMap(AnchorPane pane, double latitude, double longitude) {
		this.catcher(new GraphicChangeAction() {
			@Override
			public void act() throws IOException {
				MapController controller = new MapController();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("Map.fxml"));
				loader.setController(controller);
				pane.getChildren().add(loader.load());
				controller.init(latitude,longitude); 
			}
		});
	}

	public abstract void toHomepage(Scene scene);

	public abstract void menuBar(VBox menu, String sel);

}

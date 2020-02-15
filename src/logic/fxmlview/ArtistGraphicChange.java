package logic.fxmlview;

import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.layout.VBox;

import javafx.scene.Scene;

public class ArtistGraphicChange extends GraphicChangeTemplate {

	private ArtistGraphicChange() {}

	private static ArtistGraphicChange myInstance=null;

	public static ArtistGraphicChange getInstance() {

		if(myInstance==null) {
			myInstance=new ArtistGraphicChange();
		}
		return myInstance;
	}

	@Override
	public void menuBar(VBox menu,String sel) {
		this.catcher(new GraphicChangeAction() {
			@Override
			public void act() throws IOException {
				ArtistMenuBarController amc = new ArtistMenuBarController();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("ArtistMenuBar.fxml"));			
				loader.setController(amc);
				menu.getChildren().add(loader.load());
				amc.init(sel);
			}
		});
	}

	@Override
	public void toHomepage(Scene scene) {
		this.catcher(new GraphicChangeAction() {
			@Override
			public void act() throws IOException {
				HomepageArtistController hac = new HomepageArtistController();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("HomepageArtist.fxml"));
				loader.setController(hac);
				scene.setRoot(loader.load());
				hac.init();
			}
		});
	}

	public void toAddNews(Scene scene) {
		this.catcher(new GraphicChangeAction() {
			@Override
			public void act() throws IOException {
				AddNewsArtistController anc = new AddNewsArtistController();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("AddNewsArtist.fxml"));
				loader.setController(anc);
				scene.setRoot(loader.load());
				anc.init();
			}
		});
	}

}

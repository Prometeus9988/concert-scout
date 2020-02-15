package logic.fxmlview;

import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.layout.VBox;

import javafx.scene.Scene;
import logic.bean.MusicEventBean;
import logic.bean.ArtistBean;
import javafx.scene.layout.HBox;
import logic.bean.NewsBean;
import logic.utils.Roles;

public class AdminGraphicChange extends GraphicChangeTemplate {

	private static AdminGraphicChange myInstance = null;

	private AdminGraphicChange() {
		whoAmI = Roles.ADMIN;
	}

	public static AdminGraphicChange getInstance() {
		if(myInstance==null) {
			myInstance=new AdminGraphicChange();
		}
		return myInstance;
	}

	@Override
	public void toHomepage(Scene scene) {
		this.catcher(new GraphicChangeAction() {
			@Override
			public void act() throws IOException {
				HomepageAdminController hac = new HomepageAdminController();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminHomepage.fxml"));
				loader.setController(hac);
				scene.setRoot(loader.load());
				hac.init();
			}
		});
	}

	public void eventPreview(VBox box, MusicEventBean event, String from, String searchString) {
		this.catcher(new GraphicChangeAction() {
			@Override
			public void act() throws IOException {
				EventAdminController ev = new EventAdminController();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("Event.fxml"));
				loader.setController(ev);
				box.getChildren().add(loader.load());
				ev.init(event, from, searchString);
			}
		});
	}

	public void toArtistDetails(Scene scene, ArtistBean ar, String from, String searchString) {
		this.catcher(new GraphicChangeAction() {
			@Override
			public void act() throws IOException {
				AdminArtDetailsController adc = new AdminArtDetailsController();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("ArtDetails.fxml"));
				loader.setController(adc);
				scene.setRoot(loader.load());
				adc.init(ar,from,searchString);			
			}
		});
	}

	public void evDetailsButtons(HBox box, MusicEventBean event) {
		this.catcher(new GraphicChangeAction() {
			@Override
			public void act() throws IOException {
				AdminEvButtonsController controller = new AdminEvButtonsController();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminEvDetButtons.fxml"));
				loader.setController(controller);
				box.getChildren().add(loader.load());
				controller.init(event);
			}
		});
	}

	public void toEventDetails(Scene scene, MusicEventBean meb, String from, String searchString) {
		this.catcher(new GraphicChangeAction() {
			@Override
			public void act() throws IOException {
				AdminEvDetController controller = new AdminEvDetController();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("EvDetails.fxml"));
				loader.setController(controller);
				scene.setRoot(loader.load());
				controller.init(meb,from,searchString);
			}
		});
	}

	public void newsPrev(VBox anchor, NewsBean myNews) {
		this.catcher(new GraphicChangeAction() {
			@Override
			public void act() throws IOException {
				NewsAdminController controller = new NewsAdminController();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("News.fxml"));
				loader.setController(controller);
				anchor.getChildren().add(loader.load());
				controller.init(myNews);
			}
		});
	}

	public void toNews(Scene scene) {
		this.catcher(new GraphicChangeAction() {
			@Override
			public void act() throws IOException {
				AdminNewsSectionController controller = new AdminNewsSectionController();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("NewsSection.fxml"));
				loader.setController(controller);
				scene.setRoot(loader.load());
				controller.init();
			}
		});
	}

	public void newsBtn(HBox box, NewsBean nb) {
		this.catcher(new GraphicChangeAction() {
			@Override
			public void act() throws IOException {
				AdminNewsBtnController controller = new AdminNewsBtnController();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminNewsBtn.fxml"));
				loader.setController(controller);
				box.getChildren().add(loader.load());
				controller.init(nb);
			}
		});
	}

}

package logic.fxmlview;

import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import logic.bean.ArtistBean;
import logic.bean.MusicEventBean;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import logic.bean.NewsBean;
import logic.bean.UserBean;
import logic.utils.Roles;

public class UserGraphicChange extends GraphicChangeTemplate {

	private static UserGraphicChange myInstance=null;

	private UserGraphicChange() {
		whoAmI = Roles.USER;
	}

	public static UserGraphicChange getInstance(){
		if(myInstance==null) {
			myInstance=new UserGraphicChange();
		}
		return myInstance;
	}

	@Override
	public void toHomepage(Scene scene) {
		this.catcher(new GraphicChangeAction() {
			@Override
			public void act() throws IOException {
				HomepageUserController huc = new HomepageUserController();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("HomepageUser.fxml"));
				loader.setController(huc);
				scene.setRoot(loader.load());
				huc.init();
			}
		});
	}

	public void toSearchEv(Scene scene, String searchString) {
		this.catcher(new GraphicChangeAction() {
			@Override
			public void act() throws IOException {
				SearchEventsArtistsController evc = new SearchEventsArtistsController();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchEventsArtists.fxml"));
				loader.setController(evc);
				scene.setRoot(loader.load());
				evc.init(searchString);
			}
		});
	}

	public void searchBar(AnchorPane ap, int kind) {
		this.catcher(new GraphicChangeAction() {
			@Override
			public void act() throws IOException {
				SearchBarController sc = SearchBarControllerFactory.getInstance().creator(kind);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchBar.fxml"));
				loader.setController(sc);
				ap.getChildren().add(loader.load());
				sc.init();
			}
		});
	}

	public void eventPreview(HBox box, MusicEventBean event, String from, String searchString) {
		this.catcher(new GraphicChangeAction() {
			@Override
			public void act() throws IOException {
				EventUserController ev = new EventUserController();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("Event.fxml"));
				loader.setController(ev);
				box.getChildren().add(loader.load());
				ev.init(event, from, searchString);
			}
		});
	}

	public void artistPreview(HBox box, ArtistBean ar, String from, String searchString) {
		this.catcher(new GraphicChangeAction() {
			@Override
			public void act() throws IOException {
				ArtistController ac = new ArtistController();
				FXMLLoader loader=new FXMLLoader(getClass().getResource("Artist.fxml"));
				loader.setController(ac);
				box.getChildren().add(loader.load());
				ac.init(ar, from, searchString);
			}
		});
	}

	public void evDetailsButtons(HBox box, MusicEventBean event) {
		this.catcher(new GraphicChangeAction() {
			@Override
			public void act() throws IOException {
				UserEvButtonsController btc = new UserEvButtonsController();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("UserEvDetButtons.fxml"));
				loader.setController(btc);
				box.getChildren().add(loader.load());
				btc.init(event);
			}
		});
	}

	public void toArtistDetails(Scene scene, ArtistBean ar, String from, String searchString) {
		this.catcher(new GraphicChangeAction() {
			@Override
			public void act() throws IOException {
				ArtDetailsController adc = new ArtDetailsController();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("ArtDetails.fxml"));
				loader.setController(adc);
				scene.setRoot(loader.load());
				adc.init(ar,from,searchString);
			}
		});
	}

	public void toEventDetails(Scene scene, MusicEventBean meb, String from, String searchString ) {
		this.catcher(new GraphicChangeAction() {
			@Override
			public void act() throws IOException {
				UserEvDetController edc = new UserEvDetController();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("EvDetails.fxml"));
				loader.setController(edc);
				scene.setRoot(loader.load());
				edc.init(meb, from, searchString);
			}
		});
	}

	public void toMyEvents(Scene scene) {
		this.catcher(new GraphicChangeAction() {
			@Override
			public void act() throws IOException {
				YourEventsController yec = new YourEventsController();
				FXMLLoader loader=new FXMLLoader(getClass().getResource("YourEventsSection.fxml"));
				loader.setController(yec);
				scene.setRoot(loader.load());
				yec.init();
			}
		});
	}

	public void eventPreviewMyEvents(VBox box, MusicEventBean event, String from, String searchString) {
		this.catcher(new GraphicChangeAction() {
			@Override
			public void act() throws IOException {
				EventUserController ev = new EventUserController();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("Event.fxml"));
				loader.setController(ev);
				box.getChildren().add(loader.load());
				ev.init(event, from, searchString);
			}
		});
	}

	public void toNews(Scene scene) {
		this.catcher(new GraphicChangeAction() {
			@Override
			public void act() throws IOException {
				NewsUserSectionController controller = new NewsUserSectionController();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("NewsSection.fxml"));
				loader.setController(controller);
				scene.setRoot(loader.load());
				controller.init();
			}
		});
	}

	public void newsPrev(VBox anchor, NewsBean myNews) {
		this.catcher(new GraphicChangeAction() {
			@Override
			public void act() throws IOException {
				NewsUserController controller = new NewsUserController();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("News.fxml"));
				loader.setController(controller);
				anchor.getChildren().add(loader.load());
				controller.init(myNews);
			}
		});
	}

	public void toFriendsSection(Scene scene) {
		this.catcher(new GraphicChangeAction() {
			@Override
			public void act() throws IOException {
				FriendsSectionController controller = new FriendsSectionController();
				FXMLLoader loader=new FXMLLoader(getClass().getResource("FriendsSection.fxml"));
				loader.setController(controller);
				scene.setRoot(loader.load());
				controller.init();
			}
		});
	}

	public void userPrev(HBox box, UserBean ub, String from, String searchString) {
		this.catcher(new GraphicChangeAction() {
			@Override
			public void act() throws IOException {
				UserPrevController controller = new UserPrevController();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
				loader.setController(controller);
				box.getChildren().add(loader.load());
				controller.init(ub, from, searchString);
			}
		});
	}

	public void usrSerachPrev(VBox box, UserBean ub, String from, String searchString) {
		this.catcher(new GraphicChangeAction() {
			@Override
			public void act() throws IOException {
				UserPrevController controller = new UserPrevController();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
				loader.setController(controller);
				box.getChildren().add(loader.load());
				controller.init(ub, from, searchString);
			}
		});
	}

	public void toSearchUsr(Scene scene, String searchString) {
		this.catcher(new GraphicChangeAction() {
			@Override
			public void act() throws IOException {
				UserSearchSecController controller = new UserSearchSecController();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchUsers.fxml"));
				loader.setController(controller);
				scene.setRoot(loader.load());
				controller.init(searchString);
			}
		});
	}

	public void toUserDetails(Scene scene, UserBean ub, String from, String searchString) {
		this.catcher(new GraphicChangeAction() {
			@Override
			public void act() throws IOException {
				UserDetailsController controller = new UserDetailsController();
				FXMLLoader loader=  new FXMLLoader(getClass().getResource("UserDetails.fxml"));
				loader.setController(controller);
				scene.setRoot(loader.load());
				controller.init(ub, from, searchString);
			}
		});
	}

}

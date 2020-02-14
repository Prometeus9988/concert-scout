package logic.fxmlview;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;

import logic.utils.SessionUser;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import logic.bean.ArtistBean;
import logic.bean.GeneralUserBean;
import logic.bean.MusicEventBean;
import logic.buyticket.*;
import java.util.List;

public class HomepageUserController {
	
	@FXML
	private VBox menuBar;
	@FXML
	private AnchorPane searchBar;
	@FXML
	private Label nameLabel;
	@FXML
	private VBox suggEvCol;
	@FXML
	private HBox suggEvRow;
	@FXML
	private VBox suggArtCol;
	@FXML
	private HBox suggArtRow;
	
	public void init(){
		
		UserGraphicChange ugc = UserGraphicChange.getInstance();
		
		//init menuBar
		
		ugc.menuBar(this.menuBar,"home");
		
		
		//init searchBar
		
		ugc.searchBar(this.searchBar,1);
		
		//init nameBar
		GeneralUserBean gub = SessionUser.getInstance().getSession();
		this.nameLabel.setText(gub.getUsername());
		
		//Scrollpane
		
		ScrollPane artScrollPane = new ScrollPane(this.suggArtRow);
		artScrollPane.setFitToHeight(true);
		this.suggArtCol.getChildren().add(artScrollPane);
		
		ScrollPane evScrollPane = new ScrollPane(this.suggEvRow);
		evScrollPane.setFitToHeight(true);
		this.suggEvCol.getChildren().add(evScrollPane);
		
		
		artScrollPane.setStyle("-fx-background-color: transparent; -fx-background:  #F5EDF0");
		evScrollPane.setStyle("-fx-background-color:  transparent;-fx-background:  #F5EDF0");
		
		BuyTicketController btc = new BuyTicketController();
		String username = gub.getUsername();
		
		List<MusicEventBean> musicEvents = btc.getSuggestedEvents(username);
		List<ArtistBean>artist = btc.getSuggestedArtist(username);
		
		int i;
		
		for(i = 0; i < musicEvents.size(); i++) {
			
			//EVENT PREVIEW
			
			ugc.eventPreview(this.suggEvRow, musicEvents.get(i),"home", "");
		}
		
		for(i = 0; i < artist.size(); i++) {
			
			//ARTIST PREVIEW
			
			ugc.artistPreview(this.suggArtRow, artist.get(i),"home", "");
		}
	}
	
}

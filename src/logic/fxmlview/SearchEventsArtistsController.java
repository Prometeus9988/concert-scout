package logic.fxmlview;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import logic.buyticket.*;
import logic.exceptions.NoArtistFoundException;
import logic.exceptions.NoMusicEventFoundException;

import java.util.ArrayList;
import java.util.List;
import logic.bean.ArtistBean;
import logic.bean.MusicEventBean;

public class SearchEventsArtistsController {

	@FXML
	private VBox menuBar;
	@FXML
	private Label searchStringLabel;
	@FXML
	private HBox artRow;
	@FXML
	private VBox artCol;
	@FXML
	private HBox evRow;
	@FXML
	private VBox evCol;

	public void init(String searchString){

		UserGraphicChange ugc = UserGraphicChange.getInstance();

		//menuBar	
		ugc.menuBar(this.menuBar, "home");

		//name bar
		this.searchStringLabel.setText("\"" + searchString + "\"");

		//scrollPane
		ScrollPane artScrollPane = new ScrollPane(this.artRow);
		artScrollPane.setFitToHeight(true);
		this.artCol.getChildren().add(artScrollPane);

		ScrollPane evScrollPane = new ScrollPane(this.evRow);
		evScrollPane.setFitToHeight(true);
		this.evCol.getChildren().add(evScrollPane);

		artScrollPane.setStyle("-fx-background-color: transparent; -fx-background:  #F5EDF0");
		evScrollPane.setStyle("-fx-background-color:  transparent;-fx-background:  #F5EDF0");

		//searched lists
		BuyTicketController btc = new BuyTicketController();
		List<MusicEventBean> musicEvents = null;
		
		try {
			musicEvents = btc.getSearchMusicEvent(searchString);
		} catch (NoMusicEventFoundException e) {
			musicEvents = new ArrayList<>();
		}
		
		List<ArtistBean> artists = null;
		
		try {
			artists = btc.getSearchArtist(searchString);
		} catch (NoArtistFoundException e) {
			artists = new ArrayList<>();
		}
		
		int i;

		for(i = 0; i < musicEvents.size(); i++) {
			ugc.eventPreview(this.evRow, musicEvents.get(i), "home", searchString);
		}

		for(i = 0; i < artists.size(); i++) {
			ugc.artistPreview(this.artRow, artists.get(i), "home", searchString);
		}

	}

}

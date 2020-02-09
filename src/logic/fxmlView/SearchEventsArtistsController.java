package logic.fxmlView;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import logic.buyticket.*;
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
	
	private String mySearchString;
	
	private UserGraphicChange ugc;
	
	public void init(String searchString){
		
		this.ugc=UserGraphicChange.getInstance();
		
		//menuBar		
		this.ugc.menuBar(this.menuBar, "home");
		
		//name bar
		this.mySearchString=searchString;
		this.searchStringLabel.setText("\""+this.mySearchString+"\"");
		
		
		//scrollPane
		ScrollPane artScrollPane=new ScrollPane(this.artRow);
		artScrollPane.setFitToHeight(true);
		this.artCol.getChildren().add(artScrollPane);
		
		ScrollPane evScrollPane=new ScrollPane(this.evRow);
		evScrollPane.setFitToHeight(true);
		this.evCol.getChildren().add(evScrollPane);
		
		
		artScrollPane.setStyle("-fx-background-color: transparent; -fx-background:  #F5EDF0");
		evScrollPane.setStyle("-fx-background-color:  transparent;-fx-background:  #F5EDF0");
		
		//searched lists
		BuyTicketController btc = new BuyTicketController();
		List<MusicEventBean> musicEvents=null;
		musicEvents=btc.getSearchMusicEvent(this.mySearchString);
		List<ArtistBean> artists=null;
		artists=btc.getSearchArtist(this.mySearchString);
		
		
		int i;
		
		for(i=0;i<musicEvents.size();i++) {
			this.ugc.eventPreview(this.evRow,musicEvents.get(i),"home",this.mySearchString);
			System.out.println(musicEvents.get(i).getTicketone());
		}
		
		for(i=0;i<artists.size();i++) {
			this.ugc.artistPreview(this.artRow,artists.get(i),"home",this.mySearchString);
		}
		
		
		
	}
	

}

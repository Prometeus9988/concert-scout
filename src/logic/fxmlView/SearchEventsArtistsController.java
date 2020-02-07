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
		/*MenuBarController mbc=new MenuBarController();
		FXMLLoader loader=new FXMLLoader(getClass().getResource("MenuBar.fxml"));
		loader.setController(mbc);
		this.menuBar.getChildren().add(loader.load());
		mbc.init("home");*/
		
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
		/*EventController ev;
		FXMLLoader loader2;
		ArtistController ac;*/
		
		for(i=0;i<musicEvents.size();i++) {
			/*ev=new EventController();
			loader2=new FXMLLoader(getClass().getResource("Event.fxml"));
			loader2.setController(ev);
			this.evRow.getChildren().add(loader2.load());
			ev.init(musicEvents.get(i),"search",this.mySearchString);*/
			
			this.ugc.eventPreview(this.evRow,musicEvents.get(i),"search",this.mySearchString);
		}
		
		for(i=0;i<artists.size();i++) {
			/*ac=new ArtistController();
			loader2=new FXMLLoader(getClass().getResource("Artist.fxml"));
			loader2.setController(ac);
			this.artRow.getChildren().add(loader2.load());
			ac.init(artists.get(i),"search",this.mySearchString);*/
			
			this.ugc.artistPreview(this.artRow,artists.get(i),"search",this.mySearchString);
		}
		
		
		
	}
	

}

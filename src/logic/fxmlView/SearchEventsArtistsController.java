package logic.fxmlView;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.ComboBox;
import logic.login.*;
import logic.utils.*;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.stage.Stage;
import java.io.FileInputStream;
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
	
	public void init(String searchString) throws IOException {
		
		//menuBar
		MenuBarController mbc=new MenuBarController();
		FXMLLoader loader=new FXMLLoader(getClass().getResource("MenuBar.fxml"));
		loader.setController(mbc);
		this.menuBar.getChildren().add(loader.load());
		mbc.init("home");
		
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
		BuyTicketController btc=ControllerCreator.getInstance().getBuyTicketController();
		List<MusicEventBean> musicEvents=null;
		musicEvents=btc.getSearchMusicEvent(this.mySearchString);
		List<ArtistBean> artists=null;
		artists=btc.getSearchArtist(this.mySearchString);
		
		
		int i;
		EventController ev;
		FXMLLoader loader2;
		ArtistController ac;
		
		for(i=0;i<musicEvents.size();i++) {
			ev=new EventController();
			loader2=new FXMLLoader(getClass().getResource("Event.fxml"));
			loader2.setController(ev);
			this.evRow.getChildren().add(loader2.load());
			ev.init(musicEvents.get(i),"search",this.mySearchString);
		}
		
		for(i=0;i<artists.size();i++) {
			ac=new ArtistController();
			loader2=new FXMLLoader(getClass().getResource("Artist.fxml"));
			loader2.setController(ac);
			this.artRow.getChildren().add(loader2.load());
			ac.init(artists.get(i),"search",this.mySearchString);
		}
		
		
		
	}
	

}

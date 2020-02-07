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

import logic.login.*;
import logic.utils.*;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import logic.bean.ArtistBean;
import logic.bean.GeneralUserBean;
import logic.bean.MusicEventBean;
import logic.utils.*;
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
	
	private GeneralUserBean gub;
	private UserGraphicChange ugc;
	
	
	public void init(){
		
		this.ugc=UserGraphicChange.getInstance();
		
		//init menuBar
		
		/*MenuBarController mbc=new MenuBarController();
		FXMLLoader loader=new FXMLLoader(getClass().getResource("MenuBar.fxml"));
		loader.setController(mbc);
		this.menuBar.getChildren().add(loader.load());
		mbc.init("home");*/
		
		this.ugc.menuBar(this.menuBar,"home");
		
		
		//init searchBar
		
		/*
		SearchBarController sc=SearchBarControllerFactory.getInstance().creator(1);
		FXMLLoader loader1=new FXMLLoader(getClass().getResource("SearchBar.fxml"));
		loader1.setController(sc);
		this.searchBar.getChildren().add(loader1.load());
		sc.init();*/
		
		this.ugc.searchBar(this.searchBar,1);
		
		//init nameBar
		this.gub=SessionUser.getInstance().getSession();
		this.nameLabel.setText(this.gub.getUsername());
		
		//Scrollpane
		
		ScrollPane artScrollPane=new ScrollPane(this.suggArtRow);
		artScrollPane.setFitToHeight(true);
		this.suggArtCol.getChildren().add(artScrollPane);
		
		ScrollPane evScrollPane=new ScrollPane(this.suggEvRow);
		evScrollPane.setFitToHeight(true);
		this.suggEvCol.getChildren().add(evScrollPane);
		
		
		artScrollPane.setStyle("-fx-background-color: transparent; -fx-background:  #F5EDF0");
		evScrollPane.setStyle("-fx-background-color:  transparent;-fx-background:  #F5EDF0");
		
		BuyTicketController btc=ControllerCreator.getInstance().getBuyTicketController();
		String username=this.gub.getUsername();
		
		List<MusicEventBean> musicEvents=btc.getSuggestedEvents(username);
		List<ArtistBean>artist=btc.getSuggestedArtist(username);
		
		int i;
		//EventController ev;
		//FXMLLoader loader2;
		//ArtistController ac;
		
		for(i=0;i<musicEvents.size();i++) {
			/*ev=new EventController();
			loader2=new FXMLLoader(getClass().getResource("Event.fxml"));
			loader2.setController(ev);
			this.suggEvRow.getChildren().add(loader2.load());
			ev.init(musicEvents.get(i),"home","");*/
			
			//EVENT PREVIEW
			
			this.ugc.eventPreview(this.suggEvRow, musicEvents.get(i),"home", "");
		}
		
		for(i=0;i<artist.size();i++) {
			/*ac=new ArtistController();
			loader2=new FXMLLoader(getClass().getResource("Artist.fxml"));
			loader2.setController(ac);
			this.suggArtRow.getChildren().add(loader2.load());
			ac.init(artist.get(i),"home","");*/
			
			//ARTIST PREVIEW
			
			this.ugc.artistPreview(this.suggArtRow, artist.get(i),"home", "");
		}
		
	
	}
	
}

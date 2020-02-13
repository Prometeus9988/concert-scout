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
import logic.friends.*;
import logic.bean.UserBean;


public class FriendsSectionController {
	
	@FXML
	private VBox menuBar;
	@FXML
	private AnchorPane searchBar;
	@FXML
	private VBox friendsCol;
	@FXML
	private HBox friendsRow;
	@FXML
	private VBox requestCol;
	@FXML
	private HBox requestsRow;
	
	
	public void init() {
		
		//set menubar
		UserGraphicChange ugc=UserGraphicChange.getInstance();
		ugc.menuBar(this.menuBar,"friends");
		
		//set searchBar
		ugc.searchBar(this.searchBar,2);
		
		//init controller
		FriendsController fc=new FriendsController();
		String username=SessionUser.getInstance().getSession().getUsername();
		List<UserBean> friend=fc.getFriends(username);
		List<UserBean> requests=fc.getRequests(username);
		
		//scrollpane
		ScrollPane friendsScroll = new ScrollPane(this.friendsRow);
		friendsScroll.setFitToHeight(true);
		this.friendsCol.getChildren().add(friendsScroll);
		
		ScrollPane requestsScroll = new ScrollPane(this.requestsRow);
		requestsScroll.setFitToHeight(true);
		this.requestCol.getChildren().add(requestsScroll);
		
		
		friendsScroll.setStyle("-fx-background-color: transparent; -fx-background:  #F5EDF0");
		requestsScroll.setStyle("-fx-background-color:  transparent;-fx-background:  #F5EDF0");
		
		int i;
		for(i=0;i<friend.size();i++) {
			ugc.userPrev(this.friendsRow, friend.get(i), "friends", "");
		}
		
		for(i=0;i<requests.size();i++) {
			ugc.userPrev(this.requestsRow, requests.get(i),"friends","");
		}
		
	}
	
	
}

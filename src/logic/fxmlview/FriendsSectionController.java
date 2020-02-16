package logic.fxmlview;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;

import logic.utils.SessionUser;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import java.util.List;
import logic.friends.*;
import logic.bean.UserBean;
import javafx.scene.control.Label;
import logic.exceptions.*;
import java.util.ArrayList;

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
	@FXML
	private Label frLabel;
	@FXML
	private Label reqLabel;
	
	private static final String FRIENDS = "friends";
	
	public void init() {
		
		//set menubar
		UserGraphicChange ugc=UserGraphicChange.getInstance();
		ugc.menuBar(this.menuBar, FRIENDS);
		
		//set searchBar
		ugc.searchBar(this.searchBar,2);
		
		//init controller
		FriendsController fc=new FriendsController();
		String username=SessionUser.getInstance().getSession().getUsername();
		List<UserBean> friend=null;
		List<UserBean> requests=null;
		try {
			friend=fc.getFriends(username);
		}catch(NoFriendException e) {
			friend=new ArrayList<>();
			this.frLabel.setText(e.getMessage());
		}
		try {
			requests=fc.getRequests(username);
		}catch(NoFriendRequestException e) {
			requests=new ArrayList<>();
			this.reqLabel.setText(e.getMessage());
		}
		
		
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
			ugc.userPrev(this.friendsRow, friend.get(i), FRIENDS, "");
		}
		
		for(i=0;i<requests.size();i++) {
			ugc.userPrev(this.requestsRow, requests.get(i), FRIENDS,"");
		}
		
	}
	
	
}

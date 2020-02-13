package logic.fxmlView;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import java.io.File;
import logic.bean.MusicEventBean;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import logic.bean.UserBean;
import javafx.scene.control.Button;
import logic.userevents.*;
import logic.utils.SessionUser;
import logic.bean.GeneralUserBean;
import java.util.List;
import javafx.scene.control.ScrollPane;
import logic.friends.*; 
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class UserDetailsController {
	
	@FXML
	private VBox menuBar;
	@FXML
	private ImageView profileImage;
	@FXML
	private AnchorPane backButton;
	@FXML
	private VBox verPane;
	@FXML
	private VBox evParent;
	@FXML
	private Label username;
	@FXML
	private Label usrName;
	@FXML
	private HBox btnAnchor;
	@FXML
	private Label isGoing;
	@FXML
	private HBox evAnchor;
	
	public void init(UserBean ub,String from,String searchString){
		
		//init controller
		UserGraphicChange ugc=UserGraphicChange.getInstance();
		UserEventsController uc=new UserEventsController();
		FriendsController fc=new FriendsController();
		//init menuBar
		ugc.menuBar(this.menuBar,"friends");
		
		//init labels
		this.username.setText(ub.getUsername());
		this.usrName.setText(ub.getName()+" "+ub.getSurname());
		this.isGoing.setText(ub.getUsername()+" is going to:");
		
		//init buttons
		GeneralUserBean gu=SessionUser.getInstance().getSession();
		boolean isFriend=fc.isFriend(gu, ub);
		String who=fc.whoSentRequest(gu, ub);		
		
		Button btn1=new Button();
		Button btn2=null;
		
		if(!isFriend) {
			if(who.equals("user")) {
				btn1.setText("Remove Friend Request");
				//set behavoiur
				btn1.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event) {
						fc.removeRequest(gu, ub);
						ugc.toUserDetails(isGoing.getScene(), ub, from, searchString);
					}
				});
			}else if(who.equals("target")) {
				btn1.setText("Accept Friend Request");
				btn2=new Button("Decline Friend Request");
				//set all behavoiurs
				btn1.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						fc.acceptRequest(gu, ub);
						ugc.toUserDetails(isGoing.getScene(), ub, from, searchString);
					}
				});
				
				btn2.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						fc.declineRequest(gu, ub);
						ugc.toUserDetails(isGoing.getScene(), ub, from, searchString);
					}
				});
				
			}else {
				btn1.setText("Add Friend");
				//set behaviour
				btn1.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						fc.requestFriend(gu, ub);
						ugc.toUserDetails(isGoing.getScene(), ub, from, searchString);
					}
				});
			}
		}else {
			btn1.setText("Remove Friend");
			//set behaviour
			btn1.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e){
					fc.unfriend(gu, ub);
					ugc.toUserDetails(isGoing.getScene(), ub, from, searchString);
				}
			});
		}
		
		this.btnAnchor.getChildren().add(btn1);
		if(btn2!=null) {
			this.btnAnchor.getChildren().add(btn2);
			
		}
		
		
		
		
		
		//init back button
		ugc.backButton(this.backButton, from, searchString);
		
		//INIT IMAGE
				String path=System.getProperty("user.home")+ File.separator
						+ "Desktop" + File.separator + "LIVEtheMUSIC" + File.separator
						+ "trunk" + File.separator + "WebContent" + File.separator
						+ "img" + File.separator + "concertPictures"+File.separator+ub.getProfilePicture();
				
				File file = new File(path);
				Image image = new Image(file.toURI().toString());
				this.profileImage.setImage(image);
				this.profileImage.setFitHeight(320);
				this.profileImage.setFitWidth(1200);
				
					
				
		//ScrollPane
		ScrollPane scroll2=new ScrollPane(this.evParent);
		scroll2.setFitToHeight(true);
		this.verPane.getChildren().add(scroll2);
		scroll2.setStyle("-fx-background-color: transparent; -fx-background:  #F5EDF0");		
		
		
		//events
		List<MusicEventBean> targetEvents=uc.getUserEvents(ub.getUsername());
		int i;
		
		for(i=0;i<targetEvents.size();i++) {
			ugc.eventPreview(this.evAnchor,targetEvents.get(i),from,searchString);
		}
		
		
		
	}
}

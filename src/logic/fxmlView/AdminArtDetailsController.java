package logic.fxmlView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import logic.utils.SessionUser;
import java.io.File;
import logic.bean.ArtistBean;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import logic.followartist.FollowArtistController;
import logic.bean.GeneralUserBean;

public class AdminArtDetailsController {
	
	@FXML
	private VBox menuBar;
	@FXML 
	private ImageView profileImage;
	@FXML
	private AnchorPane backButton;
	@FXML
	private Label artName;
	@FXML 
	private Button followBtn;
	
	private  ArtistBean myArtist;
	
	private AdminGraphicChange ucg;
	
	@FXML
	public void followAction(ActionEvent ev) {
		
	}
	
	public void init(ArtistBean ar,String from,String searchString) {
		this.ucg=AdminGraphicChange.getInstance();
		this.myArtist=ar;
		
		//DESTRY BACK BUTTON
		this.followBtn.setVisible(false);
		this.followBtn.setDisable(true);
		
		//INIT MENU BAR		
		this.ucg.menuBar(this.menuBar,from);
		
		//INIT LABEL NAMES
		this.artName.setText(this.myArtist.getBandName());
		
		//INIT BACK BUTTON	
		
		this.ucg.backButton(this.backButton, from, searchString);
		
		//INIT IMAGE
		String path=System.getProperty("user.home")+ File.separator
						+ "Desktop" + File.separator + "LIVEtheMUSIC" + File.separator
						+ "trunk" + File.separator + "WebContent" + File.separator
						+ "img" + File.separator + "profilePictures"+File.separator+this.myArtist.getProfilePicture();
				
		File file = new File(path);
		Image image = new Image(file.toURI().toString());
		this.profileImage.setImage(image);
		this.profileImage.setFitHeight(400);
		this.profileImage.setFitWidth(1200);
		
		
	}
	
}

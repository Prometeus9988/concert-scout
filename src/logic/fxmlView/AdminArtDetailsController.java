package logic.fxmlView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.io.File;
import logic.bean.ArtistBean;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;

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
	
	@FXML
	public void followAction(ActionEvent ev) {
		//Not used for the admin
	}
	
	public void init(ArtistBean ar,String from,String searchString) {
		ArtistBean myArtist;
		AdminGraphicChange ucg;
		
		ucg = AdminGraphicChange.getInstance();
		myArtist = ar;
		
		//DESTRY BACK BUTTON
		this.followBtn.setVisible(false);
		this.followBtn.setDisable(true);
		
		//INIT MENU BAR		
		ucg.menuBar(this.menuBar,from);
		
		//INIT LABEL NAMES
		this.artName.setText(myArtist.getBandName());
		
		//INIT BACK BUTTON	
		
		ucg.backButton(this.backButton, from, searchString);
		
		//INIT IMAGE
		String path=System.getProperty("user.home")+ File.separator
						+ "Desktop" + File.separator + "LIVEtheMUSIC" + File.separator
						+ "trunk" + File.separator + "WebContent" + File.separator
						+ "img" + File.separator + "profilePictures"+File.separator+myArtist.getProfilePicture();
				
		File file = new File(path);
		Image image = new Image(file.toURI().toString());
		this.profileImage.setImage(image);
		this.profileImage.setFitHeight(400);
		this.profileImage.setFitWidth(1200);
		
		
	}
	
}

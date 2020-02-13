package logic.fxmlview;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import java.io.File;
import logic.bean.MusicEventBean;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class AdminEvDetController {
	
	@FXML
	private VBox menuBar;
	@FXML
	private ImageView profileImage;
	@FXML
	private AnchorPane backButton;
	@FXML
	private Label conc;
	@FXML
	private Label loc;
	@FXML
	private Label arName;
	@FXML
	private HBox buttons;
	
	public void init(MusicEventBean myEventParam,String from,String searchString){
		AdminGraphicChange agc;
		MusicEventBean myEvent = myEventParam;
		agc = AdminGraphicChange.getInstance();
		
		//INIT MENU BAR
		agc.menuBar(this.menuBar, from);
		
		//INIT LABEL NAMES
		this.conc.setText(myEvent.getName());
		this.loc.setText(myEvent.getLocation());
		this.arName.setText(myEvent.getArtistId());
		
		//INIT BACK BUTTON
		agc.backButton(this.backButton, from, searchString);
		
		//INIT ROLE BUTTONS
		agc.evDetailsButtons(this.buttons, myEvent);
		
		//INIT IMAGE
		String path=System.getProperty("user.home")+ File.separator
					+ "Desktop" + File.separator + "LIVEtheMUSIC" + File.separator
					+ "trunk" + File.separator + "WebContent" + File.separator+ "img" + File.separator + "concertPictures"+File.separator + myEvent.getCoverPath();
				
		File file = new File(path);
		Image image = new Image(file.toURI().toString());
		this.profileImage.setImage(image);
		this.profileImage.setFitHeight(400);
		this.profileImage.setFitWidth(1200);
				
	}
}
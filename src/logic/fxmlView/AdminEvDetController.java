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

public class AdminEvDetController {
	
	private MusicEventBean myEvent;
	
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
	
	private AdminGraphicChange agc;
	
	public void init(MusicEventBean myEvent,String from,String searchString){
		
		this.myEvent=myEvent;
		this.agc=AdminGraphicChange.getInstance();
		
		//INIT MENU BAR
		this.agc.menuBar(this.menuBar, from);
		
		//INIT LABEL NAMES
		this.conc.setText(this.myEvent.getName());
		this.loc.setText(this.myEvent.getLocation());
		this.arName.setText(this.myEvent.getArtistId());
		
		//INIT BACK BUTTON
		this.agc.backButton(this.backButton, from, searchString);
		
		//INIT ROLE BUTTONS
		this.agc.evDetailsButtons(this.buttons, this.myEvent);
		
		//INIT IMAGE
		String path=System.getProperty("user.home")+ File.separator
					+ "Desktop" + File.separator + "LIVEtheMUSIC" + File.separator
					+ "trunk" + File.separator + "WebContent" + File.separator+ "img" + File.separator + "concertPictures"+File.separator+this.myEvent.getCoverPath();
				
		File file = new File(path);
		Image image = new Image(file.toURI().toString());
		this.profileImage.setImage(image);
		this.profileImage.setFitHeight(400);
		this.profileImage.setFitWidth(1200);
				
	}
}
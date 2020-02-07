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

public class EvDetailsController {
	
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
	
	private UserGraphicChange ucg;
	
	public void init(MusicEventBean myEvent,String from,String searchString){
		this.myEvent=myEvent;
		this.ucg=UserGraphicChange.getInstance();
		
		//INIT MENU BAR
		
		/*MenuBarController mbc=new MenuBarController();
		FXMLLoader loader=new FXMLLoader(getClass().getResource("MenuBar.fxml"));
		loader.setController(mbc);
		this.menuBar.getChildren().add(loader.load());
		mbc.init("home");*/
		
		this.ucg.menuBar(this.menuBar,"home");
		
		
		//INIT LABEL NAMES
		this.conc.setText(this.myEvent.getName());
		this.loc.setText(this.myEvent.getLocation());
		this.arName.setText(this.myEvent.getArtistId());
		
		//INIT BACK BUTTON
		
		/*BackUserController bc=new BackUserController();
		loader=new FXMLLoader(getClass().getResource("BackButton.fxml"));
		loader.setController(bc);
		bc.init(from,searchString);
		this.backButton.getChildren().add(loader.load());*/
		
		this.ucg.backButton(this.backButton, from, searchString);
		
		//INIT ROLE BUTTONS
		
		/*UserEvButtonsController btc=new UserEvButtonsController();
		loader=new FXMLLoader(getClass().getResource("UserEvDetButtons.fxml"));
		loader.setController(btc);
		this.buttons.getChildren().add(loader.load());
		btc.init(this.myEvent);*/
		
		this.ucg.evDeatilsButtons(this.buttons, this.myEvent);
		
		//INIT IMAGE
		String path=System.getProperty("user.home")+ File.separator
				+ "Desktop" + File.separator + "LIVEtheMUSIC" + File.separator
				+ "trunk" + File.separator + "WebContent" + File.separator
				+ "img" + File.separator + "concertPictures"+File.separator+this.myEvent.getCoverPath();
		
		File file = new File(path);
		Image image = new Image(file.toURI().toString());
		this.profileImage.setImage(image);
		this.profileImage.setFitHeight(400);
		this.profileImage.setFitWidth(1200);
		
	}
	

}

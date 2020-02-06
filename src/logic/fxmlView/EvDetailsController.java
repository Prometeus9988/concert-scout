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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

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
	
	public void init(MusicEventBean myEvent,String from,String searchString) throws IOException{
		this.myEvent=myEvent;
		
		//INIT MENU BAR
		MenuBarController mbc=new MenuBarController();
		FXMLLoader loader=new FXMLLoader(getClass().getResource("MenuBar.fxml"));
		loader.setController(mbc);
		this.menuBar.getChildren().add(loader.load());
		mbc.init("home");
		
		//INIT LABEL NAMES
		this.conc.setText(this.myEvent.getName());
		this.loc.setText(this.myEvent.getLocation());
		this.arName.setText(this.myEvent.getArtistId());
		
		//INIT BACK BUTTON
		
		BackUserController bc=new BackUserController();
		loader=new FXMLLoader(getClass().getResource("BackButton.fxml"));
		loader.setController(bc);
		bc.init(from,searchString);
		this.backButton.getChildren().add(loader.load());
		
		//INIT ROLE BUTTONS
		UserEvButtonsController btc=new UserEvButtonsController();
		loader=new FXMLLoader(getClass().getResource("UserEvDetButtons.fxml"));
		loader.setController(btc);
		this.buttons.getChildren().add(loader.load());
		btc.init(this.myEvent);
		
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

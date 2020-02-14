package logic.fxmlview;

import java.io.File;
import logic.bean.MusicEventBean;
import javafx.scene.image.Image;
import javafx.scene.control.*;

public class UserEvDetController extends EvDetailsController {

	@Override
	public void init(MusicEventBean myEvent, String from, String searchString){

		UserGraphicChange ucg=UserGraphicChange.getInstance();
		
		//INIT MENU BAR
		ucg.menuBar(this.menuBar, from);
		
		
		//INIT LABEL NAMES
		this.conc.setText(myEvent.getName());
		this.loc.setText(myEvent.getLocation());
		this.arName.setText(myEvent.getArtistId());
		
		//INIT BACK BUTTON
		
		ucg.backButton(this.backButton, from, searchString);
		
		//INIT ROLE BUTTONS
		
		ucg.evDetailsButtons(this.buttons, myEvent);
		
		//INIT IMAGE
		String path=System.getProperty("user.home")+ File.separator
				+ "Desktop" + File.separator + "LIVEtheMUSIC" + File.separator
				+ "trunk" + File.separator + "WebContent" + File.separator
				+ "img" + File.separator + "concertPictures" + File.separator + myEvent.getCoverPath();
		
		File file = new File(path);
		Image image = new Image(file.toURI().toString());
		this.profileImage.setImage(image);
		this.profileImage.setFitHeight(400);
		this.profileImage.setFitWidth(1200);
		
		//INIT SCROLL AND MAP
		ScrollPane scrollBar=new ScrollPane(scroll);
		scrollBar.setFitToHeight(true);
		scrollParent.getChildren().add(scrollBar);
		scrollBar.setStyle("-fx-background-color: transparent; -fx-background:  #F5EDF0");
		ucg.addMap(map,myEvent.getLatitude(),myEvent.getLongitude());
		
	}
	

}

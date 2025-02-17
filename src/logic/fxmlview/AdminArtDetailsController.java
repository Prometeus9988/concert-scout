package logic.fxmlview;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.File;
import logic.bean.ArtistBean;
import logic.utils.FileManager;
import javafx.scene.image.Image;

public class AdminArtDetailsController extends ArtDetailsController{
	
	@FXML
	@Override
	public void followAction(ActionEvent ev) {
		//Not used for the admin
	}
	
	@Override
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
		String path = FileManager.PROFILE + File.separator + myArtist.getProfilePicture();
				
		File file = new File(path);
		Image image = new Image(file.toURI().toString());
		this.profileImage.setImage(image);
		this.profileImage.setFitHeight(400);
		this.profileImage.setFitWidth(1200);

	}

}

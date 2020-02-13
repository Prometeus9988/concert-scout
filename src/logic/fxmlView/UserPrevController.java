package logic.fxmlView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.File;

import logic.bean.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UserPrevController {
	
	@FXML
	private Button imageBtn;
	@FXML
	private Button usrBtn;
	
	private UserBean myUser;
	private String from;
	private String searchString;
	
	private UserGraphicChange ucg;
	
	@FXML
	public void openUser(ActionEvent e) {
		//DUMP
		this.ucg.toUserDetails(this.imageBtn.getScene(), this.myUser, this.from, this.searchString);
	}
	
	public void init(UserBean ub,String from,String searchString) {
		
		this.from=from;
		this.searchString=searchString;
		
		this.ucg=UserGraphicChange.getInstance();
		this.myUser=ub;
		
		String path=System.getProperty("user.home")+ File.separator
				+ "Desktop" + File.separator + "LIVEtheMUSIC" + File.separator
				+ "trunk" + File.separator + "WebContent" + File.separator
				+ "img" + File.separator + "profilePictures"+File.separator+this.myUser.getProfilePicture();
		
		File file = new File(path);
		Image image = new Image(file.toURI().toString());
		ImageView iv3 = new ImageView(image);
		iv3.setFitHeight(170);
        iv3.setFitWidth(110);
		this.imageBtn.setGraphic(iv3);
		
		this.usrBtn.setText(this.myUser.getUsername());
		
		
		
	}
	

}

package logic.fxmlview;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.bean.MusicEventBean;

public abstract class EventControllerTemplate {
	//USER EVENT CONTROLLER
	@FXML
	protected Button eventBtn;
	@FXML
	protected Button artBtn;
	@FXML
	protected Button imageBtn;
	
	protected MusicEventBean myMusicEvent;
	
	protected String from;
	protected String searchString;
	
	protected void init(MusicEventBean ev, String from, String searchString) {
		
		this.myMusicEvent=ev;
		this.from=from;
		this.searchString=searchString;
		String path=System.getProperty("user.home")+ File.separator
				+ "Desktop" + File.separator + "LIVEtheMUSIC" + File.separator
				+ "trunk" + File.separator + "WebContent" + File.separator
				+ "img" + File.separator + "concertPictures"+File.separator+this.myMusicEvent.getCoverPath();
		
		File file = new File(path);
		Image image = new Image(file.toURI().toString());
		ImageView iv3 = new ImageView(image);
		iv3.setFitHeight(170);
        iv3.setFitWidth(110);
		this.imageBtn.setGraphic(iv3);

		this.artBtn.setText(this.myMusicEvent.getBandName());
		this.eventBtn.setText(this.myMusicEvent.getName());
	}
}

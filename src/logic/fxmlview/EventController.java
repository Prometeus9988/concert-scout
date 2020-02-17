package logic.fxmlview;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.File;
import logic.bean.MusicEventBean;
import logic.utils.FileManager;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class EventController {

	@FXML
	protected Button eventBtn;
	@FXML
	protected Button artBtn;
	@FXML
	protected Button imageBtn;

	protected MusicEventBean myMusicEvent;

	protected String from;
	protected String searchString;

	@FXML
	protected abstract void openEvent(ActionEvent e);

	@FXML
	protected abstract void openArtist(ActionEvent e);

	public void init(MusicEventBean ev, String from, String searchString) {
		this.myMusicEvent=ev;
		this.from=from;
		this.searchString=searchString;
		String path = FileManager.EVENT + File.separator + this.myMusicEvent.getCoverPath();

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

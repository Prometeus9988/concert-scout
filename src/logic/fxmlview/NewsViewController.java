package logic.fxmlview;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.fxml.FXML;

import java.io.File;

import javafx.scene.shape.Circle;
import logic.bean.*;
import logic.utils.FileManager;



public abstract class NewsViewController {
	@FXML
	protected ImageView image;
	@FXML
	protected Button artBtn;
	@FXML
	protected Label postTime;
	@FXML
	protected HBox admBtn;
	@FXML
	protected VBox imageAnchor;
	@FXML
	protected Text newsText;

	protected NewsBean myNews;
	
	protected void initPictures() {
		//INIT PROFILE IMAGE

		final Circle clip = new Circle(30, 25, 25);
		this.image.setClip(clip);

		String path = FileManager.PROFILE + File.separator + this.myNews.getProfilePath();

		File file = new File(path);
		Image img = new Image(file.toURI().toString());
		this.image.setImage(img);
		this.image.setFitHeight(60);
		this.image.setFitWidth(60);


		//INIT PICTURE IF IT EXISTS
		if (!this.myNews.getPicturePath().equals("")) {
			path = FileManager.NEWS + File.separator + this.myNews.getPicturePath();

			file = new File(path);
			img = new Image(file.toURI().toString());

			ImageView iv = new ImageView();
			iv.setPreserveRatio(false);
			iv.setImage(img);
			iv.setFitHeight(300);
			iv.setFitWidth(300);
			this.imageAnchor.getChildren().add(iv);
		}
	}
}

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

import javafx.event.ActionEvent;
import javafx.scene.shape.Circle;
import logic.bean.*;
import logic.buyticket.BuyTicketController;

public class NewsUserController {

	@FXML
	private ImageView image;
	@FXML
	private Button artBtn;
	@FXML
	private Label postTime;
	@FXML
	private HBox admBtn;
	@FXML
	private VBox imageAnchor;
	@FXML
	private Text newsText;

	private NewsBean myNews;

	@FXML
	public void openArtist(ActionEvent e) {
		BuyTicketController btc = new BuyTicketController();
		ArtistBean ab = btc.getArtist(this.myNews.getArtistId());
		UserGraphicChange.getInstance().toArtistDetails(this.newsText.getScene(), ab, "news", "");

	}

	public void init(NewsBean myNews) {

		//INIT LABELS
		this.myNews = myNews;
		this.artBtn.setText(this.myNews.getBandName());
		this.postTime.setText("Posted " + this.myNews.getPostedSince() + " ago");
		this.newsText.setText(this.myNews.getText());

		//INIT PROFILE IMAGE

		final Circle clip = new Circle(30, 25, 25);
        this.image.setClip(clip);

        String path=System.getProperty("user.home") + File.separator
				+ "Desktop" + File.separator + "LIVEtheMUSIC" + File.separator
				+ "trunk" + File.separator + "WebContent" + File.separator
				+ "img" + File.separator + "profilePictures" + File.separator + this.myNews.getProfilePath();

        File file = new File(path);
		Image img = new Image(file.toURI().toString());
		this.image.setImage(img);
		this.image.setFitHeight(60);
		this.image.setFitWidth(60);


		//INIT PICTURE IF IT EXISTS
		if (!this.myNews.getPicturePath().equals("")) {
			path=System.getProperty("user.home") + File.separator
					+ "Desktop" + File.separator + "LIVEtheMUSIC" + File.separator
					+ "trunk" + File.separator + "WebContent" + File.separator
					+ "img" + File.separator + "newsPictures" + File.separator + this.myNews.getPicturePath();
			
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

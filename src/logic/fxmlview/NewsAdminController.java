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

public class NewsAdminController extends NewsViewController{

	@FXML
	public void openArtist(ActionEvent e) {
		BuyTicketController btc = new BuyTicketController();
		ArtistBean ab = btc.getArtist(this.myNews.getArtistId());
		AdminGraphicChange.getInstance().toArtistDetails(this.newsText.getScene(), ab, "news", "");

	}

	public void init(NewsBean myNews) {

		//INIT LABELS
		this.myNews=myNews;
		this.artBtn.setText(this.myNews.getBandName());
		this.postTime.setText("Posted " + this.myNews.getPostedSince() + " ago");
		this.newsText.setText(this.myNews.getText());

		//INIT BUTTONS
		AdminGraphicChange.getInstance().newsBtn(this.admBtn, this.myNews);

		this.initPictures();

	}

}

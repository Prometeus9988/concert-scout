package logic.fxmlview;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import logic.bean.*;

import logic.followartist.FollowArtistController;

public class NewsUserController extends NewsViewController{

	@FXML
	public void openArtist(ActionEvent e) {
		FollowArtistController fac = new FollowArtistController();
		ArtistBean ab = fac.getArtist(this.myNews.getArtistId());
		UserGraphicChange.getInstance().toArtistDetails(this.newsText.getScene(), ab, "news", "");

	}

	public void init(NewsBean myNews) {

		//INIT LABELS
		this.myNews = myNews;
		this.artBtn.setText(this.myNews.getBandName());
		this.postTime.setText("Posted " + this.myNews.getPostedSince() + " ago");
		this.newsText.setText(this.myNews.getText());

		this.initPictures();

	}

}

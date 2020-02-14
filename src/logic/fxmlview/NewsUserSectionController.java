package logic.fxmlview;

import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import logic.readnews.*;
import logic.utils.*;
import logic.bean.*;
import java.util.List;
import javafx.scene.control.ScrollPane;

public class NewsUserSectionController {

	@FXML
	private VBox menuBar;
	@FXML
	private VBox secRoot;
	@FXML
	private VBox newsAnchor;

	public void init() {
		//init controllers
		UserGraphicChange ugc = UserGraphicChange.getInstance();

		ReadNewsController rnc = new ReadNewsController();

		//news list
		List<NewsBean> nb = rnc.getNewsUser(SessionUser.getInstance().getSession());

		//init scrollPane
		ScrollPane scroll = new ScrollPane(this.newsAnchor);
		scroll.setFitToHeight(true);
		this.secRoot.getChildren().add(scroll);
		scroll.setStyle("-fx-background-color: transparent; -fx-background:  #F5EDF0");

		//init menuBar
		ugc.menuBar(this.menuBar,"news");
		
		for(int i = 0; i<nb.size(); i++) {
			ugc.newsPrev(this.newsAnchor, nb.get(i));
		}
	}

}

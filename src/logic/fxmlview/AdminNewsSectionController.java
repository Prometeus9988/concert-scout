package logic.fxmlview;

import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import logic.bean.*;
import java.util.List;
import javafx.scene.control.ScrollPane;
import logic.addnews.*;

public class AdminNewsSectionController {
	
	@FXML
	private VBox menuBar;
	@FXML
	private VBox secRoot;
	@FXML
	private VBox newsAnchor;
	
	public void init() {
		//init controllers
		AdminGraphicChange adm;
		adm=AdminGraphicChange.getInstance();
		
		AddNewsController anc = new AddNewsController();
		List<NewsBean>nb=anc.getNews();
		
		//init scrollPane
		ScrollPane scroll=new ScrollPane(this.newsAnchor);
		scroll.setFitToHeight(true);
		this.secRoot.getChildren().add(scroll);
		scroll.setStyle("-fx-background-color: transparent; -fx-background:  #F5EDF0");
		//init menubar
		adm.menuBar(this.menuBar, "news");
		
		for(int i=0;i<nb.size();i++) {
			adm.newsPrev(this.newsAnchor,nb.get(i));
		}	
		
	}
	
}

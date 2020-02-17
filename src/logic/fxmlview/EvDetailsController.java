package logic.fxmlview;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.bean.MusicEventBean;
import logic.utils.FileManager;

public abstract class EvDetailsController {
	
	@FXML
	protected VBox menuBar;
	@FXML
	protected ImageView profileImage;
	@FXML
	protected AnchorPane backButton;
	@FXML
	protected Label conc;
	@FXML
	protected Label loc;
	@FXML
	protected Label arName;
	@FXML
	protected HBox buttons;
	@FXML
	protected VBox scrollParent;
	@FXML
	protected VBox scroll;
	@FXML
	protected AnchorPane map;
	
	public abstract void init(MusicEventBean myEvent, String from, String searchString);
	
	protected void initImage(MusicEventBean myEvent) {
		String path = FileManager.EVENT + File.separator + myEvent.getCoverPath();
			
	File file = new File(path);
	Image image = new Image(file.toURI().toString());
	this.profileImage.setImage(image);
	this.profileImage.setFitHeight(400);
	this.profileImage.setFitWidth(1200);
	}
	
	protected void initLabelNames(MusicEventBean myEvent) {
		this.conc.setText(myEvent.getName());
		this.loc.setText(myEvent.getLocation());
		this.arName.setText(myEvent.getArtistId());
	}
	
	protected void initScroll() {
		ScrollPane scrollBar=new ScrollPane(scroll);
		scrollBar.setFitToHeight(true);
		scrollParent.getChildren().add(scrollBar);
		scrollBar.setStyle("-fx-background-color: transparent; -fx-background:  #F5EDF0");
		scrollBar.setHbarPolicy(ScrollBarPolicy.NEVER);
	}

}

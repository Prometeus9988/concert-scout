package logic.fxmlview;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.bean.MusicEventBean;

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

}

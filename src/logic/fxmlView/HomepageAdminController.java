package logic.fxmlView;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import logic.bean.MusicEventBean;

import java.util.ArrayList;
import java.util.List;
import logic.addmusicevent.*;

public class HomepageAdminController {
	
	@FXML
	private VBox menuBar;
	@FXML
	private HBox scrPane;
	@FXML
	private VBox secRoot;
	@FXML
	private VBox col1;
	@FXML
	private VBox col2;
	@FXML
	private VBox col3;
	@FXML
	private VBox col4;
	@FXML
	private VBox col5;
	
	private AdminGraphicChange auc;
	
	public void init() {
		
		
		//LIST
		List<VBox> columns=new ArrayList<>();
		columns.add(this.col1);
		columns.add(this.col2);
		columns.add(this.col3);
		columns.add(this.col4);
		columns.add(this.col5);
		
		//init menu bar
		this.auc=AdminGraphicChange.getInstance();
		auc.menuBar(this.menuBar, "home");
		
		//scrollpane
		ScrollPane scroll=new ScrollPane(this.scrPane);
		scroll.setFitToWidth(true);
		this.secRoot.getChildren().add(scroll);
		scroll.setStyle("-fx-background-color: transparent; -fx-background:  #F5EDF0");
		
		
		//event's requests
		AddMusicEventController aec=new AddMusicEventController();
		List<MusicEventBean> musicEvents=aec.viewPendingEvents();
		
		int i,j;
		j=0;
		for(i=0;i<musicEvents.size();i++) {
			this.auc.eventPreview(columns.get(j), musicEvents.get(i), "home", "");
			j=(j+1)%5;
		}
		
	}
	
}

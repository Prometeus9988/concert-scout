package logic.fxmlview;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import logic.utils.SessionUser;
import logic.bean.GeneralUserBean;
import javafx.scene.control.ScrollPane;
import logic.userevents.UserEventsController;
import logic.bean.MusicEventBean;
import java.util.List;
import java.util.ArrayList;

public class YourEventsController {
	
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
	
	private UserGraphicChange ugc;
	
	public void init() {
		
		//LIST
		List<VBox> columns=new ArrayList<>();
		columns.add(this.col1);
		columns.add(this.col2);
		columns.add(this.col3);
		columns.add(this.col4);
		columns.add(this.col5);
		
		
		//init UGC
		this.ugc=UserGraphicChange.getInstance();
		
		//init menubar
		this.ugc.menuBar(this.menuBar, "myEvents");
		
		//scrollPane
		
		ScrollPane scroll=new ScrollPane(this.scrPane);
		scroll.setFitToWidth(true);
		this.secRoot.getChildren().add(scroll);
		
		scroll.setStyle("-fx-background-color: transparent; -fx-background:  #F5EDF0");
		
		//init controller
		UserEventsController ec=new UserEventsController();
		
		GeneralUserBean gu=SessionUser.getInstance().getSession();
		
		List<MusicEventBean> musicEventList= ec.getUserEvents(gu.getUsername());
		
		int i,j;
		j=0;
		for(i=0;i<musicEventList.size();i++) {
			
			this.ugc.eventPreviewMyEvents(columns.get(j), musicEventList.get(i), "myEvents", "");
			j=(j+1)%5;
		}
		
	}

}

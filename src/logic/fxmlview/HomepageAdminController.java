package logic.fxmlview;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import logic.bean.MusicEventBean;

import java.util.List;
import logic.addmusicevent.*;

public class HomepageAdminController extends EventListPage {

	@Override
	public void init() {

		//LIST
		List<VBox> columns = initList();
		
		AdminGraphicChange auc;
		//init menu bar
		auc = AdminGraphicChange.getInstance();
		auc.menuBar(this.menuBar, "home");
		
		//scrollpane
		ScrollPane scroll=new ScrollPane(this.scrPane);
		scroll.setFitToWidth(true);
		this.secRoot.getChildren().add(scroll);
		scroll.setStyle("-fx-background-color: transparent; -fx-background:  #F5EDF0");
		
		
		//event's requests
		AddMusicEventController aec=new AddMusicEventController();
		List<MusicEventBean> musicEvents=aec.viewPendingEvents();
		
		int i;
		int j = 0;
		for(i=0;i<musicEvents.size();i++) {
			auc.eventPreview(columns.get(j), musicEvents.get(i), "home", "");
			j=(j+1)%5;
		}
		
	}
	
}

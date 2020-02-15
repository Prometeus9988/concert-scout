package logic.fxmlview;

import javafx.scene.layout.VBox;
import logic.utils.SessionUser;
import logic.bean.GeneralUserBean;
import javafx.scene.control.ScrollPane;
import logic.userevents.UserEventsController;
import logic.bean.MusicEventBean;
import java.util.List;

public class YourEventsController extends EventListPage {

	@Override
	public void init() {

		//LIST
		List<VBox> columns = initList();
		//init UGC
		UserGraphicChange ugc = UserGraphicChange.getInstance();
		
		//init menubar
		ugc.menuBar(this.menuBar, "myEvents");

		//scrollPane

		ScrollPane scroll = new ScrollPane(this.scrPane);
		scroll.setFitToWidth(true);
		this.secRoot.getChildren().add(scroll);

		scroll.setStyle("-fx-background-color: transparent; -fx-background:  #F5EDF0");

		//init controller
		UserEventsController ec = new UserEventsController();

		GeneralUserBean gu = SessionUser.getInstance().getSession();

		List<MusicEventBean> musicEventList = ec.getUserEvents(gu.getUsername());

		int i;
		int j = 0;

		for(i = 0; i < musicEventList.size(); i++) {

			ugc.eventPreviewMyEvents(columns.get(j), musicEventList.get(i), "myEvents", "");
			j = (j + 1) % 5;
		}

	}

}

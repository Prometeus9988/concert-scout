package logic.fxmlview;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.List;
import logic.bean.UserBean;
import logic.friends.*;
import logic.utils.SessionUser;

public class UserSearchSecController extends SingleListPage {

	@FXML
	private Label searchStringLabel;
	@FXML
	private Label userLabel;
	
	public void init(String searchString) {

		UserGraphicChange ugc = UserGraphicChange.getInstance();

		//LIST
		List<VBox> columns = initList();
		//init menu
		ugc.menuBar(this.menuBar, "friends");
		//init label
		this.searchStringLabel.setText("\"" + searchString + "\"");

		//scrollPane
		ScrollPane scroll = new ScrollPane(this.scrPane);
		scroll.setFitToHeight(true);
		this.secRoot.getChildren().add(scroll);
		scroll.setStyle("-fx-background-color: transparent; -fx-background:  #F5EDF0");

		//init controller
		FriendsController fc = new FriendsController();

		int i;
		int j = 0;
		List<UserBean> users = fc.getSearchUser(searchString, SessionUser.getInstance().getSession().getUsername());

		for(i = 0; i < users.size(); i++) {

			ugc.usrSerachPrev(columns.get(j), users.get(i), "friends", searchString);
			j = (j + 1) % 5;
		}

	}

}

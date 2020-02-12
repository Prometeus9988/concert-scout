package logic.fxmlView;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import logic.buyticket.*;

import java.util.ArrayList;
import java.util.List;
import logic.bean.ArtistBean;
import logic.bean.MusicEventBean;
import logic.bean.UserBean;
import logic.friends.*;
import logic.utils.SessionUser;

public class UserSearchSecController {
	
	@FXML
	private VBox menuBar;
	@FXML
	private VBox usrCol;
	@FXML
	private HBox usrRow;
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
	@FXML
	private Label searchStringLabel;
	
	
	public void init(String searchString) {
		
		UserGraphicChange ugc=UserGraphicChange.getInstance();
		
		//LIST
		List<VBox> columns=new ArrayList<>();
		columns.add(this.col1);
		columns.add(this.col2);
		columns.add(this.col3);
		columns.add(this.col4);
		columns.add(this.col5);
		
		//init menu
		ugc.menuBar(this.menuBar, "friends");
		//init label
		this.searchStringLabel.setText("\""+searchString+"\"");
		
		//scrollPane
		ScrollPane scroll=new ScrollPane(this.usrRow);
		scroll.setFitToHeight(true);
		this.usrCol.getChildren().add(scroll);
		scroll.setStyle("-fx-background-color: transparent; -fx-background:  #F5EDF0");
		
		//init controller
		FriendsController fc=new FriendsController();
	
		int i,j;
		List<UserBean> users=fc.getSearchUser(searchString, SessionUser.getInstance().getSession().getUsername() );
		
		j=0;
		
		for(i=0;i<users.size();i++) {
			
			ugc.usrSerachPrev(columns.get(j), users.get(i), "friends", searchString);
			j=(j+1)%5;
		}
		
		
		
		
		
	}
}

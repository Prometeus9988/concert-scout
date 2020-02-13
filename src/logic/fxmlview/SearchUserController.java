package logic.fxmlview;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SearchUserController implements SearchBarController {
		
	@FXML
	private TextField searchField;
	private UserGraphicChange ucg;
	
	@FXML
	public void searchAction(ActionEvent e) {
		System.out.println("User");
		this.ucg.toSearchUsr(this.searchField.getScene(),this.searchField.getText());
	}
	
	public void init() {
		this.ucg=UserGraphicChange.getInstance();
		this.searchField.setPromptText("Search user...");
	}
	
	
}	

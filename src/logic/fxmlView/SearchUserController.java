package logic.fxmlView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SearchUserController implements SearchBarController {
		
	@FXML
	private TextField searchField;
	@FXML
	public void searchAction(ActionEvent e) {
		System.out.println("User");
	}
	
	public void init() {
		this.searchField.setPromptText("Search other users...");
	}
	
	
}	

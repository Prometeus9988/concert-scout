package logic.fxmlView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SearchEvArController implements SearchBarController {
	
	@FXML
	private TextField searchField;
	private UserGraphicChange ucg;
	
	@FXML
	public void searchAction(ActionEvent e){
		
		/*SearchEventsArtistsController evc=new SearchEventsArtistsController();
		FXMLLoader loader=new FXMLLoader(getClass().getResource("SearchEventsArtists.fxml"));
		loader.setController(evc);
		this.searchField.getScene().setRoot(loader.load());
		evc.init(this.searchField.getText());*/
		
		this.ucg.toSearchEv(this.searchField.getScene(), this.searchField.getText());
	}
	
	public void init() {
		this.ucg=UserGraphicChange.getInstance();
		this.searchField.setPromptText("Search artists or events...");
		
	}


}

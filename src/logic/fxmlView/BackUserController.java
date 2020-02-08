package logic.fxmlView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class BackUserController implements BackController{
	
	private String from;
	private String searchString;
	@FXML
	private Button buttonBack;
	
	private UserGraphicChange grCtrl;
	
	@FXML
	public void backAction(ActionEvent ev){
		
		//FXMLLoader loader;
		
		if (this.from.equals("home")) {
			/*HomepageUserController huc=new HomepageUserController();
			loader=new FXMLLoader(getClass().getResource("HomepageUser.fxml"));
			loader.setController(huc);
			this.buttonBack.getScene().setRoot(loader.load());
			huc.init();*/
			
			this.grCtrl.toHomepage(this.buttonBack.getScene());
			
		} else if (this.from.equals("search")){
			/*SearchEventsArtistsController evc=new SearchEventsArtistsController();
			loader=new FXMLLoader(getClass().getResource("SearchEventsArtists.fxml"));
			loader.setController(evc);
			this.buttonBack.getScene().setRoot(loader.load());
			evc.init(this.searchString);
			*/
			
			this.grCtrl.toSearchEv(this.buttonBack.getScene(), this.searchString);
		}
		
		//from my friends
		
		//from search friends
	}
	
	public void init(String from,String searchString) {
		this.grCtrl=UserGraphicChange.getInstance();
		this.from=from;
		this.searchString=searchString;
	}
}

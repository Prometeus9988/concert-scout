package logic.fxmlview;

import javafx.event.ActionEvent;



import javafx.fxml.FXML;
import logic.bean.MusicEventBean;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import logic.addmusicevent.AddMusicEventController;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class AdminEvButtonsController {
	
	@FXML
	private Button tkButton;
	private MusicEventBean myMusicEvent;
	private AddMusicEventController amec;
	private AdminGraphicChange agc;
	
	@FXML
	public void rejectAction(ActionEvent ev) {
		this.amec.rejectMusicEvent(this.myMusicEvent);
		this.agc.toHomepage(this.tkButton.getScene());
	}
	
	@FXML
	public void acceptAction(ActionEvent ev) {
		this.amec.acceptMusicEvent(this.myMusicEvent);
		this.agc.toHomepage(this.tkButton.getScene());
	}
	
	@FXML
	public void tkAction(ActionEvent ev) {
		//MANAGE NULL LINK
		
		Stage primaryStage=new Stage();
		primaryStage.setTitle("TicketOne");
		WebView webView = new WebView();

        webView.getEngine().load(this.myMusicEvent.getTicketone());

       VBox vBox = new VBox(webView);
       Scene scene = new Scene(vBox);
		        
       primaryStage.resizableProperty().setValue(Boolean.FALSE);
       primaryStage.setScene(scene);
       primaryStage.show();
	}
	
	public void init(MusicEventBean myMusicEvent) {
		//INIT MUSIC EVENT VAR
		this.myMusicEvent=myMusicEvent;
		
		//INIT CONTROLLER
		this.amec = new AddMusicEventController();
		
		//INIT GRAPHIC CHANGE
		this.agc=AdminGraphicChange.getInstance();
	
	}
}

package logic.fxmlview;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logic.bean.MusicEventBean;



public abstract class EvButtonController {
	
	protected MusicEventBean myMusicEvent;
	
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
}

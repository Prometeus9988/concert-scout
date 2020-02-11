package logic.fxmlView;

import javafx.event.ActionEvent;



import javafx.fxml.FXML;
import logic.utils.SessionUser;
import logic.buyticket.*;
import logic.bean.MusicEventBean;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import logic.bean.GeneralUserBean;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class UserEvButtonsController {
	
	@FXML
	private Button part;
	@FXML
	private Button tk;
	 
	private MusicEventBean myMusicEvent;
	
	private BuyTicketController controller;
	private GeneralUserBean sessionUser;

	
	@FXML
	public void partAction(ActionEvent ev) {
		boolean isPart = controller.isParticipating(this.sessionUser, this.myMusicEvent);
		if(isPart){
			controller.removeParticipation(this.sessionUser, this.myMusicEvent);
			this.part.setText("Add Participation");
		} else {
			controller.addParticipation(this.sessionUser, this.myMusicEvent);
			this.part.setText("Remove Participation");
		}
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
		//INIT SESSION USER
		this.sessionUser = SessionUser.getInstance().getSession();
		//INIT BUYTICKETCONTROLLER
		this.controller = new BuyTicketController();
		
		//INIT MUSIC EVENT VAR
		this.myMusicEvent=myMusicEvent;
		
		//INIT BUTTON 
		boolean isPart=this.controller.isParticipating(this.sessionUser, this.myMusicEvent);
		if(isPart==false) {
			this.part.setText("Add Participation");
		}else {
			this.part.setText("Remove Participation");
		}
	}
}

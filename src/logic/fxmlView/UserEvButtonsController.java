package logic.fxmlView;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.ComboBox;
import logic.login.*;
import logic.utils.*;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.stage.Stage;
import java.io.FileInputStream;
import logic.buyticket.*;
import java.util.List;
import logic.bean.ArtistBean;
import logic.bean.MusicEventBean;
import javafx.scene.control.Button;
import logic.bean.GeneralUserBean;

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
		//TO IMPLEMENT
		System.out.println("Tk pressed");
	}
	
	public void init(MusicEventBean myMusicEvent) {
		//INIT SESSION USER
		this.sessionUser=SessionUser.getInstance().getSession();
		//INIT BUYTICKETCONTROLLER
		this.controller=ControllerCreator.getInstance().getBuyTicketController();
		
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

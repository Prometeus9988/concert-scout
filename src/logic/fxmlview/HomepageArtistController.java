package logic.fxmlview;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.bean.GeneralUserBean;
import logic.bean.MusicEventBean;
import logic.utils.*;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.stage.Stage;
import java.io.FileInputStream;
import javafx.scene .layout.VBox;
import javafx.scene.control.DatePicker;
import logic.addmusicevent.AddMusicEventController;

public class HomepageArtistController {
	private static final Logger logger = Logger.getLogger(HomepageArtistController.class.getName());
	
	@FXML
	private VBox menuBar;
	@FXML
	private Label nameLabel;
	@FXML
	private Label headerLabel;
	@FXML
	private TextField nameField;
	@FXML
	private TextField locField;
	@FXML
	private TextField tkField;
	@FXML
	private DatePicker dateField;
	@FXML
	private Label imageLabel;
	
	private File imageFile=null;
	private GeneralUserBean gub;
	private AddMusicEventController controller;
	
	@FXML
	public void selectImage(ActionEvent ev) {
		final FileChooser fc=new FileChooser();
		fc.setTitle("Select image");
		fc.setInitialDirectory(new File(System.getProperty("user.home")));
		fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG","*.jpg"),
										new FileChooser.ExtensionFilter("PNG","*.png"));
		this.imageFile=fc.showOpenDialog(new Stage());
		if(this.imageFile!=null)this.imageLabel.setText(this.imageFile.getName());
	}
	
	@FXML
	public void createBtn(ActionEvent ev) {

		String name = this.nameField.getText();
		String location = this.locField.getText();
		String date = "";
		
		if(this.dateField.getValue() != null) {
			date = this.dateField.getValue().toString();
		}
		
		String ticketone = this.tkField.getText();
		String fileName = null;
		String newFileName = null;
		boolean result = false;
		
		if(this.imageFile == null) {
			fileName="";
			newFileName="";
		}else {
			fileName=this.imageFile.getName();
			newFileName=this.gub.getUsername() + name + fileName;
		}
		
		MusicEventBean meb=new MusicEventBean();
		meb.setArtistId(this.gub.getUsername());
		meb.setName(name);
		meb.setCoverPath(newFileName);
		meb.setLocation(location);
		meb.setTicketone(ticketone);
		meb.setDate(date);
		
		result=controller.addMusicEvent(meb);
		if(result)this.headerLabel.setText("Music Event Added");
		else this.headerLabel.setText("Failed to add music event");
		
		if(!fileName.contentEquals("") && result) {
			String path = System.getProperty("user.home") + File.separator
					+ "Desktop" + File.separator + "LIVEtheMUSIC" + File.separator
					+ "trunk" + File.separator + "WebContent" + File.separator
					+ "img" + File.separator + "concertPictures";
		    File file = new File(path, fileName);
		    File newFile = new File(path, newFileName);
		    try (InputStream input = new FileInputStream(this.imageFile)) {
		    		Files.copy(input, file.toPath());
		    } catch (Exception e) {
		    	logger.log(Level.WARNING, e.toString());
		    }
		    if(!file.renameTo(newFile)) {
		    	logger.log(Level.WARNING, "Unable to rename {0}: ", fileName);
		    }
		}
		
	}
	
	public void init(){
		
		//init controller
		this.controller = new AddMusicEventController();
		ArtistGraphicChange agc;
		agc = ArtistGraphicChange.getInstance();
		
		//init menuBar
		
		agc.menuBar(this.menuBar,"addEv");
		
		
		//init nameBar
		this.gub=SessionUser.getInstance().getSession();
		this.nameLabel.setText(this.gub.getUsername());
	}

}

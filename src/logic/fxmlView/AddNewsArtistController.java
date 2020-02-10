package logic.fxmlView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import java.io.InputStream;
import java.nio.file.Files;

import logic.bean.GeneralUserBean;
import logic.utils.*;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.stage.Stage;
import java.io.FileInputStream;
import javafx.scene .layout.VBox;
import javafx.scene.control.TextArea;

import logic.addnews.AddNewsController;
import java.util.Random;
import logic.bean.NewsBean;

public class AddNewsArtistController {
	
	@FXML
	private VBox menuBar;
	@FXML
	private Label nameLabel;
	@FXML
	private Label headerLabel;
	@FXML
	private Label imageLabel;
	@FXML
	private TextArea newsArea;
	@FXML
	private VBox scrBox;
	
	private AddNewsController controller;
	private ArtistGraphicChange agc;
	private GeneralUserBean gub;
	private File imageFile=null;
	private static final int MIN = 0;
	private static final int MAX = 10000;
	
	
	@FXML
	public void selectImage(ActionEvent e) {
		final FileChooser fc=new FileChooser();
		fc.setTitle("Select image");
		fc.setInitialDirectory(new File(System.getProperty("user.home")));
		fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG","*.jpg"),
										new FileChooser.ExtensionFilter("PNG","*.png"));
		this.imageFile=fc.showOpenDialog(new Stage());
		if(this.imageFile!=null)this.imageLabel.setText(this.imageFile.getName());
	}
	
	@FXML
	public void postBtn(ActionEvent e) {
		
		String fileName = "";
		String newFileName = null;
		String text=this.newsArea.getText();
		NewsBean nb=new NewsBean();
		Random r=new Random();
		
		if(this.imageFile==null) {
			fileName="";
			newFileName="";
		}else {
			int salt = r.nextInt((MAX - MIN) + 1) + MIN;
			fileName=this.imageFile.getName();
			newFileName=this.gub.getUsername() + salt + fileName;
		}
		
		nb.setText(text);
		nb.setArtistId(this.gub.getUsername());
		nb.setPicturePath(newFileName);
		
		boolean result=controller.addNews(nb);
		
		if(result) {
			this.headerLabel.setText("News posted");
		}else {
			this.headerLabel.setText("Failed to post the news");
		}
		
		if(!fileName.equals("")&& result) {
			String path = System.getProperty("user.home") + File.separator
					+ "Desktop" + File.separator + "LIVEtheMUSIC" + File.separator
					+ "trunk" + File.separator + "WebContent" + File.separator
					+ "img" + File.separator + "newsPictures";
		    File file = new File(path, fileName);
		    File newFile = new File(path, newFileName);
		    try (InputStream input = new FileInputStream(this.imageFile)) {
		    		Files.copy(input, file.toPath());
		    } catch (Exception ex) {
		    	ex.printStackTrace();
		    }
		    file.renameTo(newFile);
		}
		
		this.newsArea.setText(null);
		this.imageFile=null;
		this.imageLabel.setText("No image selected");
		
		
	}
	
	public void init() {
		//init controller
		this.controller=new AddNewsController();
		this.agc=ArtistGraphicChange.getInstance();
		
		//init menuBar
		this.agc.menuBar(this.menuBar, "addNews");
		
		//init scrollBar
		ScrollPane scroll =new ScrollPane(this.newsArea);
		scroll.setFitToHeight(true);
		this.scrBox.getChildren().add(scroll);
		scroll.setStyle("-fx-background-color: transparent; -fx-background:  #F5EDF0");
		
		//init nameBar
		this.gub=SessionUser.getInstance().getSession();
		this.nameLabel.setText(this.gub.getUsername());
	}
}

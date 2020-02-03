package logic.fxmlView;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.io.IOException;

import logic.bean.GeneralUserBean;
import logic.login.*;
import logic.utils.*;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class HomepageUserController {
	
	@FXML
	private VBox menuBar;
	@FXML
	private AnchorPane searchBar;
	
	
	public void init() throws IOException {
		
		//init menuBar
		MenuBarController mbc=new MenuBarController();
		FXMLLoader loader=new FXMLLoader(getClass().getResource("MenuBar.fxml"));
		loader.setController(mbc);
		this.menuBar.getChildren().add(loader.load());
		mbc.init("home");
		
		//init searchBar
		SearchController sc=new SearchController();
		FXMLLoader loader1=new FXMLLoader(getClass().getResource("SearchBar.fxml"));
		loader1.setController(sc);
		this.searchBar.getChildren().add(loader1.load());
		
		//init nameBar
		
	}
	
}

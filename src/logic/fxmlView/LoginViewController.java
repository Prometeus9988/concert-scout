package logic.fxmlView;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.io.IOException;
import javafx.scene.control.ComboBox;

import logic.bean.ArtistBean;
import logic.bean.GeneralUserBean;
import logic.bean.UserBean;
import logic.login.*;
import logic.utils.*;


public class LoginViewController {
	
	@FXML
	private TextField usernameTextField;
	@FXML
	private PasswordField passwordBox;
	@FXML
	private Label registerLabel;
	@FXML
	private TextField emailField;
	@FXML
	private TextField usernameRegField;
	@FXML
	private PasswordField passwordRegField;
	@FXML
	private TextField bandNameField;
	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private ComboBox<String> typeOfUserField;
	@FXML
	private Label errorLabel1;
	@FXML
	private Label errorLabel2;

	
	@FXML
	public void loginButtonAction(ActionEvent event) throws IOException {
		
		GeneralUserBean gub=new GeneralUserBean();
		gub.setUsername(this.usernameTextField.getText());
		gub.setPassword(this.passwordBox.getText());
		
		LoginController controller=ControllerCreator.getInstance().getLoginController();
    	GeneralUserBean gu=controller.login(gub);
    	if(gu==null) {
    		this.errorLabel1.setText("Wrong username");
    		this.errorLabel2.setText("or password");
    	}
    	else {
    		String role=gu.getRole();
    		FXMLLoader loader;
    		//SET SESSION GENERAL USER
    		
    		switch(role) {
    		case "user":
    			HomepageUserController huc=new HomepageUserController();
    			loader=new FXMLLoader(getClass().getResource("HomepageUser.fxml"));
    			loader.setController(huc);
    			this.usernameTextField.getScene().setRoot(loader.load());
    			huc.init();
    			break;
    		case "artist":
    			//set artist homepage controller
    			//DUMP
    			loader=new FXMLLoader(getClass().getResource("HomepageArtist.fxml"));
    			this.usernameTextField.getScene().setRoot(loader.load());
    			break;
    		case "admin":
    			//set admin controller
    			//DUMP
    			loader=new FXMLLoader(getClass().getResource("AdminHomepage.fxml"));
    			this.usernameTextField.getScene().setRoot(loader.load());
    			break;
    		}
    		
    		
    		
    	}
    	
	}
	
	@FXML
	public void registerButtonAction(ActionEvent event) {
		LoginController controller = ControllerCreator.getInstance().getLoginController();
		Boolean regResult=false;
		String email = "";
		String username = "";
		String password = "";
		String userType = "";
		
		email=emailField.getText();
		System.out.println("email "+email);
		
		username=this.usernameRegField.getText();
		System.out.println("username "+username);
		
		password=this.passwordRegField.getText();
		System.out.println("password "+password);
		
		userType=this.typeOfUserField.getValue();
		System.out.println("type "+userType);
		
		String fileName = null;
		//FOR PROFILE PICTURE
		//Part filePart = null;
		
		
		if(userType.equals("User")) {
			String firstName=this.firstNameField.getText();
			System.out.println("firstname "+firstName);
			
			String lastName=this.lastNameField.getText();
			System.out.println("lastname "+lastName);
			
			// TODO implement profile picture
			UserBean u = new UserBean(username, password, email, firstName, lastName, "");
			u.setProfilePicture(fileName);
			regResult = controller.createUser(u);	
		}else if(userType.contentEquals("Artist")) {
			String bandName=this.bandNameField.getText();
			System.out.println("band name "+bandName);
			
			ArtistBean a = new ArtistBean(username, password, bandName, fileName, email);
			regResult = controller.createArtist(a);
		}
		
		if(Boolean.TRUE.equals(regResult)) {
			this.registerLabel.setText("Registration successfull");
		}
		else {
			this.registerLabel.setText("Registration unsuccessfull");
		}
		
	}
	
	
	public void init() {
		String[] kinds= {"User","Artist"};
		this.typeOfUserField.setItems(FXCollections.observableArrayList(kinds));
		this.typeOfUserField.getSelectionModel().selectFirst();
	}

}

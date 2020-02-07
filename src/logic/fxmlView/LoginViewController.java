package logic.fxmlView;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.io.InputStream;
import java.nio.file.Files;

import javafx.scene.control.ComboBox;

import logic.bean.ArtistBean;
import logic.bean.GeneralUserBean;
import logic.bean.UserBean;
import logic.login.*;
import logic.utils.SessionUser;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.stage.Stage;
import java.io.FileInputStream;

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
	private Label imageLabel;
	
	
	private File imageFile=null;
	
	@FXML
	public void selectImage(ActionEvent event) {
		final FileChooser fc=new FileChooser();
		fc.setTitle("Select image");
		fc.setInitialDirectory(new File(System.getProperty("user.home")));
		fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG","*.jpg"),
										new FileChooser.ExtensionFilter("PNG","*.png"));
		this.imageFile=fc.showOpenDialog(new Stage());
		if(this.imageFile!=null)this.imageLabel.setText(this.imageFile.getName());
	}

	
	@FXML
	public void loginButtonAction(ActionEvent event){
		
		GeneralUserBean gub = new GeneralUserBean();
		gub.setUsername(this.usernameTextField.getText());
		gub.setPassword(this.passwordBox.getText());
		
		LoginController controller = new LoginController();
    	GeneralUserBean gu=controller.login(gub);
    	if(gu==null) {
    		this.errorLabel1.setText("Wrong username");
    		this.errorLabel2.setText("or password");
    	}
    	else {
    		String role=gu.getRole();
    		
    		//SET SESSION GENERAL USER
    		SessionUser su=SessionUser.getInstance();
    		su.setSession(gu);
    		
    		switch(role) {
    		case "user":
    			/*HomepageUserController huc=new HomepageUserController();
    			loader=new FXMLLoader(getClass().getResource("HomepageUser.fxml"));
    			loader.setController(huc);
    			this.usernameTextField.getScene().setRoot(loader.load());
    			huc.init();*/
    			UserGraphicChange.getInstance().toHomepage(this.usernameTextField.getScene());    			
    			break;
    		case "artist":
    			//set artist homepage controller
    			
    			/*HomepageArtistController hac=new HomepageArtistController();
    			loader=new FXMLLoader(getClass().getResource("HomepageArtist.fxml"));
    			loader.setController(hac);
    			this.usernameTextField.getScene().setRoot(loader.load());
    			hac.init();*/
    			
    			ArtistGraphicChange.getInstance().toHomepage(this.usernameTextField.getScene());
    			break;
    		case "admin":
    			//set admin controller
    			//DUMP
    			/*loader=new FXMLLoader(getClass().getResource("AdminHomepage.fxml"));
    			this.usernameTextField.getScene().setRoot(loader.load());*/
    			break;
    		}
    		
    		
    		
    	}
    	
	}
	
	@FXML
	public void registerButtonAction(ActionEvent event) {
		LoginController controller = new LoginController();
		Boolean regResult = false;
		String email = "";
		String username = "";
		String password = "";
		String userType = "";
		
		email=emailField.getText();
		
		username=this.usernameRegField.getText();
		
		password=this.passwordRegField.getText();
		
		userType=this.typeOfUserField.getValue();
		
		String fileName;
		String newFileName;
		
		if(this.imageFile==null) {
			fileName="";
			newFileName="";
		}else {
			fileName=this.imageFile.getName();
			newFileName=username+fileName;
		}
		
		
		if(userType.equals("User")) {
			String firstName=this.firstNameField.getText();
			String lastName=this.lastNameField.getText();
			UserBean u = new UserBean();
			u.setUsername(username);
			u.setPassword(password);
			u.setEmail(email);
			u.setName(firstName);
			u.setSurname(lastName);
			u.setProfilePicture(newFileName);
			regResult = controller.createUser(u);	
		}else if(userType.contentEquals("Artist")) {
			String bandName=this.bandNameField.getText();
			ArtistBean a = new ArtistBean();
			a.setUsername(username);
			a.setPassword(password);
			a.setBandName(bandName);
			a.setProfilePicture(newFileName);
			a.setEmail(email);
			regResult = controller.createArtist(a);
		}
		
		if(Boolean.TRUE.equals(regResult)) {
			this.registerLabel.setText("Registration successfull");
			if(this.imageFile!=null) {
				String path = System.getProperty("user.home") + File.separator
						+ "Desktop" + File.separator + "LIVEtheMUSIC" + File.separator
						+ "trunk" + File.separator + "WebContent" + File.separator
						+ "img" + File.separator + "profilePictures";
			    File file = new File(path, fileName);
			    File newFile = new File(path, newFileName);
			    try (InputStream input = new FileInputStream(this.imageFile)) {
			    		Files.copy(input, file.toPath());
			    } catch (Exception e) {
			    	e.printStackTrace();
			    }
			    file.renameTo(newFile);
			}
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

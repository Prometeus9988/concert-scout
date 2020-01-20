package logic.loginView;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.io.IOException;
import logic.login.*;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;

public class LoginViewController {
	
	@FXML
	private TextField usernameTextField;
	@FXML
	private PasswordField passwordBox;
	@FXML
	private Label titleLabel;
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
	public void loginButtonAction(ActionEvent event) throws IOException {
		
		GeneralUserBean gub=new GeneralUserBean();
		gub.setUsername(usernameTextField.getText());
		gub.setPassword(passwordBox.getText());
		
		LoginController controller=LoginController.getInstance();
    	GeneralUserBean gu=controller.login(gub);
    	if(gu==null) {
    		this.titleLabel.setText("DATA NOT FOUND");
    	}
    	else {
    		HomepageViewController hvc=new HomepageViewController();
    		/*FXMLLoader loader=new FXMLLoader(getClass().getResource("HomepageDump.fxml"));
    		loader.setController(hvc);
    		Scene main=titleLabel.getScene();
    		main.setRoot(loader.load());
    		hvc.init(gu);*/
    	}
	}
	
	@FXML
	public void registerButtonAction(ActionEvent event) {
			System.out.println("Register pressed");
	}
	
	public void init() {
		String[] kinds= {"User","Artist"};
		this.typeOfUserField.setItems(FXCollections.observableArrayList(kinds));
		this.typeOfUserField.getSelectionModel().selectFirst();
	}

}

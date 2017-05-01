package player.passwordManager;

import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import newengine.utils.Callback;
import player.App;
import player.helpers.UserCallback;
import user.UsersModel;
import utilities.PopUpMessage;


public class PasswordManager{

	public static final String LOCATION = "resources/password";
	
	public static final Writer writer = new PasswordStorage();

	public static final int LENGTH_OF_USER = 8;

	public static final int LENGTH_OF_PASSWORD = 6;
	private ResourceBundle myResources = ResourceBundle.getBundle(LOCATION);
	
	private  ResourceBundle resources = ResourceBundle.getBundle(App.RESOURCES_LOCATION);

	private String checkUser;
	private String checkPw;
	private String tempCheckUser = "";
	private String tempCheckPw = "";
	private TextField txtUserName;
	private Label lblMessage;
	private PasswordField pf;
	
	private UsersModel usersModel; 
	
	private Scene scene;
	private UserCallback onSuccess;

	public PasswordManager(UsersModel userModel, UserCallback onSuccess){
		txtUserName = new TextField();
		lblMessage = new Label();
		pf = new PasswordField();
		this.usersModel= userModel; 
		this.onSuccess = onSuccess;
		
		show();
	}
	
	//FIXME: refactor this method into smaller methods 
	private void show() {
		BorderPane bp = new BorderPane();
		bp.setPadding(new Insets(10,50,50,50));
		HBox hb = new HBox();
		hb.setPadding(new Insets(20,20,20,30));
		//Adding GridPane
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(20,20,20,20));
		gridPane.setHgap(5);
		gridPane.setVgap(5);   
		//Implementing Nodes for GridPane
		Label lblUserName = new Label("Username");		
		Label lblPassword = new Label("Password");
		Label lblLanguage = new Label("Language");	
		Button btnLogin = new Button("Login");
		Button btnReset = new Button("Reset");
		Button btnRegister = new Button("Register");
		btnLogin.setMaxWidth(Double.MAX_VALUE);
		btnReset.setMaxWidth(Double.MAX_VALUE);
		btnRegister.setMaxWidth(Double.MAX_VALUE);
		//Adding Nodes to GridPane layout
		gridPane.add(lblUserName, 0, 0);
		gridPane.add(txtUserName, 1, 0);
		gridPane.add(lblPassword, 0, 1);
		gridPane.add(pf, 1, 1);
		gridPane.add(btnLogin, 2, 0);
		gridPane.add(btnReset, 2, 1);
		gridPane.add(lblMessage, 1, 2);
		gridPane.add(btnRegister, 2, 2);
		gridPane.add(lblLanguage, 0, 3);
		gridPane.add(createComboBox(), 1 , 3);
		//Reflection for gridPane
		Reflection r = new Reflection();
		r.setFraction(0.7f);
		gridPane.setEffect(r);
		//DropShadow effect
		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(5);
		dropShadow.setOffsetY(5);
		//Adding text and DropShadow effect to it
		Text text = new Text("Game Login");
		text.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
		text.setEffect(dropShadow);
		//Adding text to HBox
		hb.getChildren().add(text);
		//Add ID's to Nodes
		bp.setId("bp");
		gridPane.setId("root");
		btnLogin.setId("btnLogin");
		btnReset.setId("btnReset");
		btnRegister.setId("btnReset");
		text.setId("text");
		//Add HBox and GridPane layout to BorderPane Layout
		bp.setTop(hb);
		bp.setCenter(gridPane); 
		
		
		//Adding BorderPane to the scene and loading CSS
		scene = new Scene(bp);
		scene.getStylesheets().setAll("/styleSheets/login.css");
		
		//Action for btnLogin
		btnLogin.setOnAction(e -> buttonLoginAction());

		//Action for btnReset
		btnReset.setOnAction(e -> buttonResetAction());
		
		//Action for btnRegister
		btnRegister.setOnAction(e -> buttonRegisterAction(new SuccessRegistration()));
				
				
		scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
	}
	
	public Scene getScene(){
		return scene;
	}
	
	
	private void buttonRegisterAction(PopUpMessage p) {
				
		tempCheckUser = txtUserName.getText().toString();
		tempCheckPw = pf.getText().toString();
		if(tempCheckUser.length() < LENGTH_OF_USER || tempCheckPw.length() < LENGTH_OF_PASSWORD ){
			UnsuccessRegistration unsuccess = new UnsuccessRegistration();
			unsuccess.show();
			tempCheckUser="";
			tempCheckPw = "";
			buttonResetAction();
			return;
		}
		usersModel.addUser(tempCheckUser, tempCheckPw);
		
		writer.write(tempCheckUser, tempCheckPw);
		p.show();
		buttonResetAction();	
	}
	
	
	
	private void handleKeyInput(KeyCode code) {
		if(code == KeyCode.ENTER ){
			buttonLoginAction();
		}

	}
	
	private void buttonLoginAction() {
		checkUser = txtUserName.getText().toString();
		checkPw = pf.getText().toString();
		if(checkUserAndPassword(checkUser, checkPw) ||((!tempCheckUser.equals("") && !tempCheckPw.equals("")) && checkUser.equals(tempCheckUser) && checkPw.equals(tempCheckPw) )){
			lblMessage.setText("Congratulations!");
			lblMessage.setTextFill(Color.GREEN);
			onSuccess.execute(checkUser);
		}
		else{
			lblMessage.setText("Incorrect user or pw.");
			lblMessage.setTextFill(Color.RED);
		}
		txtUserName.setText("");
		pf.setText("");
	}

	private void buttonResetAction() {
		txtUserName.clear();
		pf.clear();
	}

	private boolean checkUserAndPassword(String user, String pw){
		if(myResources.containsKey(user)){
			String password = myResources.getString(user);		
			return password.equals(pw);		
		}		
		return false;		
	}
	
	 private ComboBox createComboBox(){
		 final ComboBox languageComboBox = new ComboBox();
		 String number = resources.getString("languageNumber");
			int n = number.charAt(0)-'0';
			for(int i = 0 ; i < n; i++){
				languageComboBox.getItems().add(resources.getString("language" + (i + 1)));
				//System.out.println("The current language is: " + languageComboBox.getItems().get(i));
				//TODO: change the language setting in the backend
			}
			languageComboBox.setValue("English");
		 
		 return languageComboBox;
	 }
	
}

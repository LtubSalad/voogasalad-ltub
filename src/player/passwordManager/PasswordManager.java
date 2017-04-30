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
import javafx.stage.Stage;
import player.App;
import player.gameChoice.GameManager;
import player.levelChoice.LevelManager;
import user.UsersModel;
import utilities.PopUpMessage;


public class PasswordManager{

	public static final String LOCATION = "resources/password";
	
	public static final Writer writer = new PasswordStorage();
	private ResourceBundle myResources = ResourceBundle.getBundle(LOCATION);
	
	private  ResourceBundle resources = ResourceBundle.getBundle(App.RESOURCES_LOCATION);

	public static final String TITLE = "Login";
	private Stage primaryStage;
	String checkUser, checkPw;
	String tempCheckUser = "", tempCheckPw = "";
	TextField txtUserName;
	Label lblMessage;
	PasswordField pf;
	
	UsersModel model; 

	public PasswordManager(Stage primaryStage){
		this.primaryStage = primaryStage;
		txtUserName = new TextField();
		lblMessage = new Label();
		pf = new PasswordField();
	}
	public PasswordManager(Stage primaryStage2, UsersModel userModel) {
		this.model= userModel; 
	}
	//FIXME: refactor this method into smaller methods 
	public void show() {
		primaryStage.setTitle(TITLE);
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
		Scene scene = new Scene(bp);
		scene.getStylesheets().setAll("/styleSheets/login.css");
		primaryStage.setScene(scene);
		primaryStage.show();
		//Action for btnLogin
		btnLogin.setOnAction(e -> buttonLoginAction());

		//Action for btnReset
		btnReset.setOnAction(e -> buttonResetAction());
		
		//Action for btnRegister
		btnRegister.setOnAction(e -> buttonRegisterAction(new SuccessRegistration()));
				
				
		scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
	}
	
	
	private void buttonRegisterAction(PopUpMessage p) {
				
		tempCheckUser = txtUserName.getText().toString();
		tempCheckPw = pf.getText().toString();
		
		model.addUser(tempCheckUser, tempCheckPw);
		
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
			primaryStage.hide();
			LevelManager levelManager = new LevelManager(primaryStage);
			//levelManager.show();
			new GameManager(primaryStage);
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
				
			}
			languageComboBox.setValue("English");
		 
		 return languageComboBox;
	 }
	
}

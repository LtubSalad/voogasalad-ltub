package player.levelChoice;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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


public class PasswordManager{

	public static final String TITLE = "Login";
	String user = "LTUB";
	String pw = "123456";
	String checkUser, checkPw;
	TextField txtUserName;
	Label lblMessage;
	PasswordField pf;

	public PasswordManager(){
		txtUserName = new TextField();
		lblMessage = new Label();
		pf = new PasswordField();
	}
	public void show(Stage primaryStage) {
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
		Button btnLogin = new Button("Login");

		//Adding Nodes to GridPane layout

		gridPane.add(lblUserName, 0, 0);
		gridPane.add(txtUserName, 1, 0);
		gridPane.add(lblPassword, 0, 1);
		gridPane.add(pf, 1, 1);
		gridPane.add(btnLogin, 2, 1);
		gridPane.add(lblMessage, 1, 2);

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
		btnLogin.setOnAction(e -> buttonAction(primaryStage));
		
		scene.setOnKeyPressed(e -> handleKeyInput(e.getCode(), primaryStage));
	}

	private void handleKeyInput(KeyCode code, Stage primaryStage) {
		if(code == KeyCode.ENTER ){
			buttonAction(primaryStage);
		}
		
	}
	private void buttonAction(Stage primaryStage) {

		checkUser = txtUserName.getText().toString();
		checkPw = pf.getText().toString();
		if(checkUser.equals(user) && checkPw.equals(pw)){
			primaryStage.hide();
			 LevelManager levelManager = new LevelManager();
			 levelManager.show();
		}
		else{
			lblMessage.setText("Incorrect user or pw.");
			lblMessage.setTextFill(Color.RED);
		}
		txtUserName.setText("");
		pf.setText("");

	}
}

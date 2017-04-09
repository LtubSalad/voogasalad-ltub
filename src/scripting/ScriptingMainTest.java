package scripting;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ScriptingMainTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Test the Scripting");
		BorderPane root = new BorderPane();
		TextArea input = new TextArea();
		input.setPrefWidth(300);
		input.setPrefHeight(300);
		ScriptingExample scriptingExample = new ScriptingExample();
		Button executeButton = new Button("Execute");
		Button retrieveButton = new Button("Retrieve");
		executeButton.setOnAction(e -> scriptingExample.inputScript(input.getText()));
		retrieveButton.setOnAction(e ->scriptingExample.getTowersFromGroovy());
		root.setCenter(input);
		root.setBottom(executeButton);
		root.setRight(retrieveButton);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}

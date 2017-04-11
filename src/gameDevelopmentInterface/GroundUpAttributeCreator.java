package gameDevelopmentInterface;

import java.util.ArrayList;

import data.AttributeData;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Pair;
import utilities.XStreamHandler;

public class GroundUpAttributeCreator extends BorderPane{
	AttributeData data;
	AttributeCustomizerPane myPane;
	XStreamHandler dataHandler;
	
	public GroundUpAttributeCreator(){
		dataHandler=new XStreamHandler();
		data=new AttributeData("choose name",true,null);
		myPane=new AttributeCustomizerPane(data,true,true);
		Button update=new Button("Update attribute");
		update.setOnAction((click)->{
			myPane.updateAttribute();
		});
		Button save= new Button("Save Attributes");
		save.setOnAction((click)->{
			myPane.updateAttribute();
			dataHandler.saveToFile(data);
		});
		Button addVariable=new Button("Add variable");
		addVariable.setOnAction((click)->{
			data.getVariables().put("dummy_variable", "");
			myPane.instantiate();
		});
		Button addFunction= new Button("Add Function");
		addFunction.setOnAction((click)->{
			data.getScripts().put(new Pair<>("dummy",new ArrayList<>()), "Put scripts here");
			myPane.instantiate();
		});
		HBox buttonPanel=new HBox();
		buttonPanel.getChildren().addAll(update,save,addVariable,addFunction);
		
		
		
		this.setCenter(myPane);
		this.setBottom(buttonPanel);
		
	}
	

}

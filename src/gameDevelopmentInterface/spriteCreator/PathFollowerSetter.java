package gameDevelopmentInterface.spriteCreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gameDevelopmentInterface.Path;
import gameDevelopmentInterface.developerdata.ComponentSetterView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import newengine.sprite.components.PathFollower;

public class PathFollowerSetter extends ComponentSetterView<PathFollower> {
	private Path selectedPath;
	private Map<String,Path> paths;

	public PathFollowerSetter(List<Path> paths){
		this.paths=new HashMap<>();
		paths.forEach((path)->{
			this.paths.put(path.getName(),path);
		});
		this.getChildren().add(new Label("PathFollower"));
		List<String> pathNames=new ArrayList<>();
		this.paths.forEach((name,path)->{
			pathNames.add(name);
		});
		
		ObservableList<String> observablePaths=FXCollections.observableList(pathNames);
		ChoiceBox<String> box= new ChoiceBox<>(observablePaths);
		
		box.getSelectionModel().selectedItemProperty().addListener((invalidation)->{
			selectedPath=this.paths.get(box.getValue());
			System.out.println("ah");
		});
		this.getChildren().add(box);
	}

	@Override
	public PathFollower produceComponent() throws Exception {
		// TODO Auto-generated method stub
		return new PathFollower(selectedPath);
	}

}

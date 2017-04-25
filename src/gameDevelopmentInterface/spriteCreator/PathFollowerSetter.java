package gameDevelopmentInterface.spriteCreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gameDevelopmentInterface.Path;
import gameDevelopmentInterface.developerdata.ComponentSetterView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import newengine.sprite.components.PathFollower;

public class PathFollowerSetter extends ComponentSetterView<PathFollower> {
	private Path selectedPath;
	private Map<String,Path> pathMap;

	public PathFollowerSetter(List<Path> paths){
		super(PathFollower.class);
		pathMap=new HashMap<>();
		paths.forEach((path)->{
			pathMap.put(path.getName(),path);
		});
		this.getChildren().add(new Label("PathFollower"));
		List<String> pathNames=new ArrayList<>();
		pathMap.forEach((name,path)->{
			pathNames.add(name);
		});
		
		ObservableList<String> observablePaths=FXCollections.observableList(pathNames);
		ChoiceBox<String> box= new ChoiceBox<>(observablePaths);
		
		box.getSelectionModel().selectedItemProperty().addListener((invalidation)->{
			selectedPath=this.pathMap.get(box.getValue());
		});
		this.getChildren().add(box);
	}

	@Override
	public PathFollower produceComponent() throws Exception {
		// TODO Auto-generated method stub
		return new PathFollower(selectedPath);
	}

}

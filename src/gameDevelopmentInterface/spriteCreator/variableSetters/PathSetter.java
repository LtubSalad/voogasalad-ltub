package gameDevelopmentInterface.spriteCreator.variableSetters;

import gameDevelopmentInterface.Path;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class PathSetter extends VariableSetter<Path> {
	ComboBox<Path> pathChoices;
	ObservableList<Path> myPaths;
	
	public PathSetter(ObservableList<Path> paths, String variableName){
		super(Path.class,variableName);
		this.myPaths=paths;		
		
		pathChoices= new ComboBox<>(myPaths);
		
		pathChoices.setCellFactory(new Callback<ListView<Path>, ListCell<Path>>(){
			@Override
			public ListCell<Path> call(ListView<Path> list){
				return new PathCell();
			}
		});
		pathChoices.setButtonCell(new PathButtonCell());
		
		this.getChildren().add(pathChoices);
		
	}
	
	private class PathButtonCell extends ListCell<Path>{
		@Override
		protected void updateItem(Path item, boolean empty){
			super.updateItem(item, empty);
			if(empty){
				setText("");
			}else{
				setText(item.getName());
			}
		}
	}
	
	private class PathCell extends ListCell<Path>{
		@Override
		public void updateItem(Path item, boolean empty){
			super.updateItem(item, empty);
			if(item==null){
				return;
			}
			this.setText(item.getName());
		}
	}
	
	@Override
	public Path getValue() throws Exception {
		return pathChoices.getValue();
	}

	@Override
	public void setField(Path initialValue) {
		if(!myPaths.contains(initialValue)){
			myPaths.add(initialValue);
		}
		pathChoices.setValue(initialValue);
	}

}

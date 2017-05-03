package gameDevelopmentInterface.spriteCreator.variableSetters;

import data.DeveloperData;
import data.SpriteMakerModel;
import exception.UnsupportedTypeException;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class SpriteVariableSelector extends VariableSetter<SpriteMakerModel> {
	private ComboBox<SpriteMakerModel> availableSprites;
	private ObservableList<SpriteMakerModel> sprites;

	public SpriteVariableSelector(String variableName, DeveloperData data) {
		super(variableName);
		sprites=data.getSprites();
		availableSprites = new ComboBox<>(sprites);
		availableSprites.setCellFactory(new Callback<ListView<SpriteMakerModel>, ListCell<SpriteMakerModel>>() {
			@Override
			public ListCell<SpriteMakerModel> call(ListView<SpriteMakerModel> param) {
				return new SpriteCell();
			}
		});

		availableSprites.setButtonCell(new SpriteCell());
		this.getChildren().add(availableSprites);
	}

	private class SpriteCell extends ListCell<SpriteMakerModel> {
		@Override
		public void updateItem(SpriteMakerModel item, boolean empty) {
			super.updateItem(item, empty);
			if(empty||item==null){
				setText("");
			}else{
				setText(item.getName());
			}
		}
	}

	@Override
	public void setField(SpriteMakerModel initialValue) {
		if(!sprites.contains(initialValue)){
			sprites.add(initialValue);
		}
		availableSprites.setValue(initialValue);
	}

	@Override
	public SpriteMakerModel getValue() throws UnsupportedTypeException, Exception {
		return availableSprites.getValue();
	}

}
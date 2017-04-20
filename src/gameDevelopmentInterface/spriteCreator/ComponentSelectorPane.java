package gameDevelopmentInterface.spriteCreator;

import java.util.List;

import data.SpriteMakerModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import newengine.sprite.Sprite;
import newengine.sprite.component.Component;

public class ComponentSelectorPane extends VBox{
	private SpriteMakerModel targetSprite;
	private double prefWidth=300;
	
	public ComponentSelectorPane(String listTitle, List<Class<? extends Component>> displayedData, SpriteMakerModel targetSprite) {
		this.targetSprite=targetSprite;
		ObservableList<Class<? extends Component>> listedComponents = FXCollections.observableList(displayedData);
		ListView<Class<? extends Component>> componentDisplay = new ListView<>();
		componentDisplay.setItems(listedComponents);

		componentDisplay.setCellFactory(new Callback<ListView<Class<? extends Component>>, ListCell<Class<? extends Component>>>() {
			@Override
			public ListCell<Class<? extends Component>> call(ListView<Class<? extends Component>> list) {
				return new ComponentCustomizerOption();
			}
		});

		componentDisplay.setPrefWidth(prefWidth);
		Label title = new Label(listTitle);
		this.getChildren().addAll(title, componentDisplay);
	}

	class ComponentCustomizerOption extends ListCell<Class<? extends Component>> {
		@Override
		public void updateItem(Class<? extends Component> item, boolean empty) {
			super.updateItem(item, empty);
			if (item == null) {
				return;
			}
			Button componentCustomizer = new Button(item.toString());
			componentCustomizer.setOnAction((c) -> {
				new ComponentCustomizerPopup(item,targetSprite);
			});
			componentCustomizer.setPrefWidth(prefWidth);
			setGraphic(componentCustomizer);
		}
	}
}

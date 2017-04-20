package gameDevelopmentInterface.spriteCreator;

import java.util.List;
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
	private Sprite targetSprite;
	private double prefWidth=300;
	
	public ComponentSelectorPane(String listTitle, List<Component> displayedData, Sprite targetSprite) {
		this.targetSprite=targetSprite;
		ObservableList<Component> listedComponents = FXCollections.observableList(displayedData);
		ListView<Component> componentDisplay = new ListView<>();
		componentDisplay.setItems(listedComponents);

		componentDisplay.setCellFactory(new Callback<ListView<Component>, ListCell<Component>>() {
			@Override
			public ListCell<Component> call(ListView<Component> list) {
				return new AttributeCustomizerOption();
			}
		});

		componentDisplay.setPrefWidth(prefWidth);
		Label title = new Label(listTitle);
		this.getChildren().addAll(title, componentDisplay);
	}

	class AttributeCustomizerOption extends ListCell<Component> {
		@Override
		public void updateItem(Component item, boolean empty) {
			super.updateItem(item, empty);
			if (item == null) {
				return;
			}
			Button componentCustomizer = new Button(item.getType().toString());
			componentCustomizer.setOnAction((c) -> {
				new ComponentCustomizerPopup(item,targetSprite);
			});
			componentCustomizer.setPrefWidth(prefWidth);
			setGraphic(componentCustomizer);
		}
	}
}

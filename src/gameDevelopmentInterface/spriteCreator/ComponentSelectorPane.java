package gameDevelopmentInterface.spriteCreator;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import newengine.sprite.component.Component;

public class ComponentSelectorPane extends VBox{
	private SpriteDataPane infoPane;
	private double PREF_WIDTH=300;
	
	public ComponentSelectorPane(String listTitle, ObservableList<Class<? extends Component>> displayedData, SpriteDataPane infoPane) {
		this.infoPane=infoPane;
		this.setPrefWidth(PREF_WIDTH);
		ListView<Class<? extends Component>> componentDisplay = new ListView<>();
		componentDisplay.setItems(displayedData);

		

		componentDisplay.setCellFactory(new Callback<ListView<Class<? extends Component>>, ListCell<Class<? extends Component>>>() {
			@Override
			public ListCell<Class<? extends Component>> call(ListView<Class<? extends Component>> list) {
				return new ComponentCustomizerOption();
			}
		});

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
			Button componentCustomizer = new Button(item.getSimpleName());
			componentCustomizer.setOnAction((c) -> {
				infoPane.addComponent(item);
			});

			componentCustomizer.setPrefWidth(ComponentSelectorPane.this.getPrefWidth());
			this.prefWidthProperty().bind(ComponentSelectorPane.this.prefWidthProperty());
			componentCustomizer.prefWidthProperty().bind(this.prefWidthProperty());
			setGraphic(componentCustomizer);
		}
	}
}

package gameDevelopmentInterface.spriteCreator;

import exception.UnsupportedTypeException;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import newengine.sprite.component.Component;

public abstract class ComponentSetter<T extends Component> extends VBox{
	public abstract T makeComponent() throws UnsupportedTypeException;
}

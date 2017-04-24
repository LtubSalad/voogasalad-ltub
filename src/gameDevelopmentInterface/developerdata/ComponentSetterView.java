package gameDevelopmentInterface.developerdata;

import java.lang.reflect.InvocationTargetException;

import javafx.scene.layout.VBox;
import newengine.sprite.component.Component;

public abstract class  ComponentSetterView<T extends Component> extends VBox {
	public abstract T produceComponent() throws Exception;
}

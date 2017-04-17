package newSprite;

import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class SpriteSpawner<T extends ComponentSprite> extends Component {

	public SpriteSpawner(ComponentSprite sprite) {
//		super(sprite);
		// TODO Auto-generated constructor stub
	}

	public static void spawnSprite(double xDisplacement, double yDisplacement) {

	}

	@Override
	public ComponentType<? extends Component> getType() {
		// TODO Auto-generated method stub
		return null;
	}
}

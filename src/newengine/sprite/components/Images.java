package newengine.sprite.components;

import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.image.ImageSet;
import newengine.utils.image.LtubImage;
import newengine.utils.variable.Var;

public class Images extends Component {

	public static final ComponentType<Images> TYPE = new ComponentType<>(Images.class.getName());
	private final Var<ImageSet> imageSetVar = new Var<>();
	
	public Images(ImageSet imageSet) {
		imageSetVar.set(imageSet);
	}
	
	public LtubImage image() {
		double heading = 0;
		double dist = 100;
		if (sprite.getComponent(Speed.TYPE).isPresent()) {
			heading = sprite.getComponent(Position.TYPE).get().heading();
		}
		return imageSetVar.get().getImage(heading, dist);
	}
	
	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}

}

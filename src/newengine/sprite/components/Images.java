package newengine.sprite.components;

import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.image.ImageSet;
import newengine.utils.image.LtubImage;

public class Images extends Component {

	public static final ComponentType<Images> TYPE = new ComponentType<>(Images.class.getName());
	private ImageSet imageSet;
	
	public Images(ImageSet imageSet) {
		this.imageSet = imageSet;
	}
	
	public LtubImage image() {
		double heading = 0;
		double dist = 100;
		if (sprite.getComponent(Speed.TYPE).isPresent()) {
			heading = sprite.getComponent(Position.TYPE).get().heading();
		}
		return imageSet.getImage(heading, dist);
	}
	
	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}

	@Override
	public Images clone() {
		return new Images(imageSet);
	}
}

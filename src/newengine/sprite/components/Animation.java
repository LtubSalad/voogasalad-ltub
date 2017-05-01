package newengine.sprite.components;

import newengine.animation.SpriteAnimation;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class Animation extends Component {
	
	public static final ComponentType<Animation> TYPE = new ComponentType<>(Animation.class.getName());
	
	private SpriteAnimation animation;
	@Override
	public ComponentType<? extends Component> getType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	public Component clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getGUIParameters() {
		// TODO Auto-generated method stub
		return null;
	}

}

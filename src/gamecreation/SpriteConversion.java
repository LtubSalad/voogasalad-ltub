package gamecreation;

import data.SpriteMakerModel;
import newengine.sprite.Sprite;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class SpriteConversion {
	
	public SpriteConversion() {
		
	}
	
	public Sprite spriteModelToSprite(SpriteMakerModel spriteModel) {
		Sprite sprite = new Sprite();
		for (ComponentType<?> ct : spriteModel.getComponents().keySet()) {
			Component component = spriteModel.getComponents().get(ct);
			sprite.addComponent(component);
		}
		return sprite;
	}
	

}

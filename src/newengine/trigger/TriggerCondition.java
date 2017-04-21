package newengine.trigger;

import newengine.model.Models;
import newengine.sprite.Sprite;

public interface TriggerCondition {
	
	public boolean isTrue(Models models, Sprite triggeringSprite);

}

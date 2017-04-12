package engine.spritecreation.factory;

import data.AttributeData;
import engine.sprite.Attribute;

public interface AttributeFactory {

	public Attribute createAttribute(AttributeData data);
}

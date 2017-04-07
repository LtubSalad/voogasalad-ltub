package engine.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import commons.RunningMode;
import engine.sprite.Sprite;




/*public void addSprite(Sprite sprite) {
	if (sprite == null && RunningMode.DEV_MODE) {
		System.out.println("Model received null sprite: " + sprite);
	}
	if (sprite != null) {
		sprites.add(sprite);
	}
}

public void removeSprite(Sprite sprite) {
	sprites.remove(sprite);
}
*/
public class SpriteModel{
	
	
	public class SpriteHandler implements ISpriteModel<Sprite>{

		@Override
		public void addSprite(Sprite sprite) {
			add(sprite);
			
		}

		@Override
		public void removeSprite(Sprite sprite) {
			remove(sprite);
			
		}

		@Override
		public Collection<Sprite> getSprites() {
			return getCollection(); 
		}
		
	}
	
	private ArrayList<Sprite> sprites; 
	private SpriteHandler spriteHandler; 
	
	public SpriteModel(){
		sprites = new ArrayList<Sprite>();
		spriteHandler = new SpriteHandler(); 
	}
	
	
	public Object getHandler(){
		return spriteHandler; 
	}
	
	
	private void add(Sprite sprite) {
		sprites.add(sprite);
	}


	private void remove(Sprite sprite) {
		sprites.remove(sprite);
	}


	private Collection<Sprite> getCollection() {
		return Collections.unmodifiableCollection(sprites);
	}
	
}

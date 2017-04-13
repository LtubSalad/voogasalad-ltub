package engine.model;

import java.util.ArrayList;
import java.util.List;

import engine.sprite.Sprite;

public class SpriteModel{
	
	public class SpriteHandler implements ISpriteHandler{
		public void addSprite(Sprite s){
			add(s);
		}
		public void removeSprite(Sprite s){
			remove(s);
		}
		@Override
		public List<Sprite> getSprites() {
			// TODO Auto-generated method stub
			return sprites; 
		}
	}
	
	
	private List<Sprite> sprites; 
	
	public SpriteModel(){
		sprites = new ArrayList<Sprite>();
	}
	
	private void add(Sprite s){
		sprites.add(s); 
	}
	
	private void remove(Sprite s){
		sprites.remove(s);
	}
	
	
	public SpriteHandler getHandler(){
		return new SpriteHandler();
	}
}

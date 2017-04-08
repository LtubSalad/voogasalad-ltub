package engine.model;

import java.util.ArrayList;

import engine.sprite.Sprite;

public class SpriteModel{
	
	public class SpriteHandler{
		public void addSprite(Sprite s){
			add(s);
		}
		public void removeSprite(Sprite s){
			remove(s);
		}
	}
	
	
	private ArrayList<Sprite> sprites; 
	
	public SpriteModel(){
		sprites = new ArrayList<Sprite>();
	}
	
	private void add(Sprite s){
		sprites.add(s); 
	}
	
	private void remove(Sprite s){
		sprites.remove(s);
	}
	
}

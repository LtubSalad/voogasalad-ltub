package engine.model;

import java.util.ArrayList;
import java.util.List;

import engine.sprite.Sprite;

public class TileModel {
	
	List<Sprite> tiles; 
	
	public TileModel(){
		tiles = new ArrayList<Sprite>(); 
	}
	
	public void add(Sprite tile){
		tiles.add(tile);
	}
	
	public void remove(Sprite tile){
		tiles.remove(tile);
	}
	
	public List<Sprite> getTiles(){
		return tiles; 
	}

}

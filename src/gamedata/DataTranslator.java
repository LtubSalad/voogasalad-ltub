package gamedata;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bus.BasicEventBus;
import data.SpriteMakerModel;
import newengine.events.SpriteModelEvent;
import newengine.model.SpriteModel;
import newengine.sprite.Sprite;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.sprite.components.Position;

public class DataTranslator implements DataHandling {
	SpriteModel gameModel; 
	List<Sprite> spritesList; 
	
	SpriteMakerModel spriteModel; 
	
	BasicEventBus bus; 
	
	public DataTranslator(){
		bus = new BasicEventBus(); 
	}
	
	@Override
	public void toSprites(List<SpriteMakerModel> data) {
		// TODO: modify sprite add event to take a single sprite 
		gameModel = new SpriteModel(bus); 
		spritesList = new ArrayList<Sprite>(); 		
		for (SpriteMakerModel spriteData : data){
				//Sprite newSprite = assembleSprite(spriteData.getComponents());
				//spritesList.add(newSprite);
		}
		
		bus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, spritesList));		
	}
	
	public void printSpritesList() {
		for (Sprite s : spritesList) {
			System.out.println(s.getComponent(Position.TYPE).get());
		}
	}
	
	
	public BasicEventBus getModelBus(){
		return bus; 
	}
	
	private Sprite assembleSprite(Map<String, List<String>> map) {
		Sprite newSprite = new Sprite(); 
		for (String cName : map.keySet()) {
			//System.out.println(c.getType().getType());
			String className = cName.getClass().getName();
			Class<?> clazz;
			try {
				clazz = Class.forName(className);
				System.out.println(clazz);
				Component newComp = (Component) clazz.newInstance(); //this line is the one that breaks it
				System.out.println(newComp);
				//System.out.println(newComp.getType().getType());
				newSprite.addComponent(newComp);
			} catch (Exception e) {
				System.out.println("Didn't work for " + cName);
			}
		}
		return newSprite; 
	}

	@Override
	public void toModel(Sprite sprite) {
		// TODO implement this, clean this + controller up 
		
	}

	public SpriteModel getGameSprites() {
		return gameModel;
	}

}

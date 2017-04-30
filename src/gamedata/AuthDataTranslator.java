package gamedata;

import java.util.ArrayList;
import java.util.List;

import bus.BasicEventBus;
import data.SpriteMakerModel;
import javafx.collections.ObservableList;
import newengine.events.skill.AddSkillEvent;
import newengine.skill.Skill;
import newengine.sprite.Sprite;
import newengine.sprite.component.Component;
import newengine.sprite.components.EventQueue;
import newengine.sprite.components.GameBus;
import newengine.sprite.components.Images;
import newengine.sprite.components.Position;

/**
 * @author tahiaemran
 * 
 * Class used to translate data from the authoring environment to the game environment
 * Takes in a list of sprite maker models and translates them into sprites 
 *
 */
public class AuthDataTranslator implements Translator<Sprite>{

	//TODO: make a single translator for single sprite translatino 
	//TODO: instantiate skills 

	private List<SpriteMakerModel> spritesToMake; 
	private BasicEventBus gameBus = new BasicEventBus(); 
	private int numRows= 8;
	private int numCols= 8;

	private List<Sprite> constructedSprites = new ArrayList<Sprite>(); 

	private Sprite constructed; 
	@Deprecated
	public AuthDataTranslator(ObservableList<SpriteMakerModel> allObjectsOnScreen) {
		spritesToMake = new ArrayList<SpriteMakerModel>(allObjectsOnScreen); 
	}
	@Deprecated
	public AuthDataTranslator(List<SpriteMakerModel> allObjectsOnScreen) {
		spritesToMake = new ArrayList<SpriteMakerModel>(allObjectsOnScreen); 
	}
	@Deprecated
	public AuthDataTranslator(List<SpriteMakerModel> spriteData, int numCols, int numRows){
		this.numRows = numRows;
		this.numCols = numCols;
		spritesToMake = spriteData;
	}


	public AuthDataTranslator(SpriteMakerModel spriteToMake, int numCols, int numRows){
		this.numRows = numRows;
		this.numCols = numCols;
		spritesToMake = new ArrayList<SpriteMakerModel>();
		spritesToMake.add(spriteToMake);
		makeSingleSprite(spriteToMake);
	}
	
	public AuthDataTranslator(SpriteMakerModel spriteToMake) {
		spritesToMake = new ArrayList<SpriteMakerModel>();
		spritesToMake.add(spriteToMake);
		makeSingleSprite(spriteToMake);
	}



	private void makeSingleSprite(SpriteMakerModel spriteToMake) {
		constructed = handleComponents(spriteToMake.getActualComponents());
		constructed.addComponent(new GameBus());
		//constructed.addComponent(new EventQueue());

	}

	public Sprite getSprite(){
		System.out.println("constructed: " + constructed);
		return constructed; 
	}

	@Override
	public void translate() {
		spritesToMake.stream().forEach(model -> {
			Sprite newSprite = handleComponents(model.getActualComponents());
			// skills
			Sprite skilledSprite = handleSkills(newSprite, model.getSkills());
			constructedSprites.add(skilledSprite);
			/// triggers 
			//constructedSprites.add(handleEventHandlers(skilledSprite, model.getScriptMap()));	
		});
	}


	private Sprite handleSkills(Sprite sprite, List<Skill> skills){
		skills.stream().forEach(skill-> 
		sprite.emit(new AddSkillEvent(AddSkillEvent.TYPE, skill)));
		return sprite; 
	}


//	private Sprite handleEventHandlers(Sprite newSprite, Map<BusEvent, String> scriptMap ) {
//		// TODO: debate design on this 
//		for (BusEvent event : scriptMap.keySet()){
//			newSprite.getSpriteBus().on(event.getEventType(), e ->{
//				try{
//					newSprite.getScriptHandler().eval(scriptMap.get(event));
//				}
//				catch (Exception exception){
//					System.out.println("hi yes scripting does not work properly here" );
//				}
//			});
//		}
//		return newSprite; 
//
//	}
	
	public List<Sprite> getSprites(){
		return constructedSprites;
	}


	private Sprite handleComponents(List<Component>transferComponents) {
		Sprite sprite = new Sprite(); 
		System.out.println("constructed in translate: " + sprite);
		sprite.addComponent(new Position(100,200,0));
		transferComponents.stream().forEach( c->{
			if(c.getType().equals(Position.TYPE)){
				Position position = (Position)c;
				double xPerc = position.pos().x();
				double yPerc = position.pos().y();
				double xPixel = xPerc * 100 * numCols;
				double yPixel = yPerc * 100 * numRows;
				Position newPosition = new Position(xPixel, yPixel, position.heading());
				sprite.addComponent(newPosition);
			}
		});
		for (Component comp: transferComponents){
			//System.out.println(comp.getType().getType());
			if (comp.getType().equals(Images.TYPE)){
				sprite.addComponent(comp);
				}
		}
		transferComponents.stream().forEach(component -> {
			//if (component.getType() != Position.TYPE || component.getType() != Images.TYPE) {
				sprite.addComponent(component);
			//}
		});
		
		return sprite;
	}

	@Override
	public List<Sprite> getTranslated() {
		return constructedSprites; 
	}


}
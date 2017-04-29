package gamedata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bus.BasicEventBus;
import bus.BusEvent;
import commons.point.GamePoint;
import data.SpriteMakerModel;
import javafx.collections.ObservableList;
import newengine.events.skill.AddSkillEvent;
import newengine.skill.Skill;
import newengine.sprite.Sprite;
import newengine.sprite.component.Component;
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

	private List<Sprite> constructedSprites = new ArrayList<Sprite>(); 

	private Sprite constructed; 
	
	public AuthDataTranslator(ObservableList<SpriteMakerModel> allObjectsOnScreen) {
		spritesToMake = new ArrayList<SpriteMakerModel>(allObjectsOnScreen); 
	}

	public AuthDataTranslator(List<SpriteMakerModel> spriteData){
		spritesToMake = spriteData;
	}


	public AuthDataTranslator(SpriteMakerModel spriteToMake){
		spritesToMake = new ArrayList<SpriteMakerModel>();
		spritesToMake.add(spriteToMake);
		makeSingleSprite(spriteToMake);
	}



	private void makeSingleSprite(SpriteMakerModel spriteToMake) {
		constructed = handleComponents(spriteToMake.getActualComponents());
		
	}

	public Sprite getSprite(){
		return constructed; 
	}
	
	@Override
	public void translate() {
		spritesToMake.stream().forEach(model -> {
			//System.out.println(model.getActualComponents().size());
			Sprite newSprite = handleComponents(model.getActualComponents());
			// skills
			Sprite skilledSprite = handleSkills(newSprite, model.getSkills());
			/// triggers 
			constructedSprites.add(handleEventHandlers(skilledSprite, model.getScriptMap()));	
		});
	}
	
	
	private Sprite handleSkills(Sprite sprite, List<Skill> skills){
		skills.stream().forEach(skill-> 
			sprite.emit(new AddSkillEvent(AddSkillEvent.TYPE, skill)));
		return sprite; 
	}
	
	
	private Sprite handleEventHandlers(Sprite newSprite, Map<BusEvent, String> scriptMap ) {
		// TODO: debate design on this 
		for (BusEvent event : scriptMap.keySet()){
			newSprite.getSpriteBus().on(event.getEventType(), e ->{
				try{
					newSprite.getScriptHandler().eval(scriptMap.get(event));
				}
				catch (Exception exception){
					System.out.println("hi yes scripting does not work properly here" );
				}
			});
		}
		return newSprite; 

	}

	
	
//
//	public SpriteModel getSprites(){
//		return constructedModel;

	public List<Sprite> getSprites(){
		return constructedSprites;
	}


	private Sprite handleComponents(List<Component>transferComponents) {
		Sprite sprite = new Sprite(); 
		for (Component comp: transferComponents){
			//System.out.println(comp.getType().getType());
			sprite.addComponent(comp);
			if (comp.getType().equals(Position.TYPE)) {
				Position position = (Position)comp;
				GamePoint oriPos = position.pos();
				double xPerc = oriPos.x();
				double yPerc = oriPos.y();
				
			}
		}
		return sprite;
	}

	@Override
	public List<Sprite> getTranslated() {
		return constructedSprites; 
	}


}

package gamedata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bus.BasicEventBus;
import data.EventHandleData;
import data.SpriteMakerModel;
import gamecreation.DataWrapper;
import javafx.collections.ObservableList;
import newengine.events.SpriteModelEvent;
import newengine.events.skill.AddSkillEvent;
import newengine.model.SpriteModel;
import newengine.skill.Skill;
import newengine.sprite.Sprite;
import newengine.sprite.component.Component;
import newengine.sprite.components.SkillSet;

/**
 * @author tahiaemran
 * 
 * Class used to translate data from the authoring environment to the game environment
 * Takes in a list of sprite maker models and translates them into sprites 
 *
 */
public class AuthDataTranslator implements Translator {
	
	//TODO: make a single translator for single sprite translatino 
	//TODO: instantiate skills 

	private List<SpriteMakerModel> spritesToMake; 
	private BasicEventBus gameBus = new BasicEventBus(); 

	private List<Sprite> constructedSprites = new ArrayList<Sprite>(); 
	private SpriteModel constructedModel = new SpriteModel(gameBus); 

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
			Sprite newSprite = handleComponents(model.getActualComponents());
			// skills
			Sprite skilledSprite = handleSkills(newSprite, model.getSkills());
			/// triggers 
			constructedSprites.add(handleEventHandlers(skilledSprite, model.getEventHandlers()));				
		});
		gameBus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, constructedSprites));
	}
	
//
//	private Sprite handleSkills(Sprite sprite, Map<String, List<DataWrapper>> skills) {
//		for (String skillName: skills.keySet()){
//			List<DataWrapper> parameters = skills.get(skillName);
//			// create skill factory 
//			SkillFactory factory = new SkillFactory(skillName, parameters);
//			Skill constructedSkill= factory.getConstructedSkill(); 
//			// add the skill to the sprite			
//		}
//		return sprite; 
//	}
//	
	private Sprite handleSkills(Sprite sprite, List<Skill> skills){
		skills.stream().forEach(skill-> 
			sprite.emit(new AddSkillEvent(AddSkillEvent.TYPE, skill)));
		return sprite; 
	}
	
	
	

	private Sprite handleEventHandlers(Sprite newSprite, List<EventHandleData> eventHandlers) {
		// loop through 
		return newSprite; 

	}

	
	public SpriteModel getSprites(){
		return constructedModel;
	}


	private Sprite handleComponents(List<Component>transferComponents) {
		Sprite sprite = new Sprite(); 
		for (Component comp: transferComponents){
			sprite.addComponent(comp);
		}
		return sprite; 
	}

}

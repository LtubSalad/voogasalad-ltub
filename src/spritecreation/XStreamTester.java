package spritecreation;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import commons.point.GamePoint;
import data.SpriteMakerModel;
import javafx.application.Application;
import javafx.stage.Stage;
import newengine.player.Player;
import newengine.skill.Skill;
import newengine.skill.SkillType;
import newengine.skill.skills.BuildSkill;
import newengine.skill.skills.FireProjectileSkill;
import newengine.skill.skills.MoveSkill;
import newengine.sprite.Sprite;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.sprite.components.Attacker;
import newengine.sprite.components.Collidable;
import newengine.sprite.components.Collidable.CollisionBoundType;
import newengine.sprite.components.EventQueue;
import newengine.sprite.components.GameBus;
import newengine.sprite.components.Health;
import newengine.sprite.components.Images;
import newengine.sprite.components.Owner;
import newengine.sprite.components.Position;
import newengine.sprite.components.Range;
import newengine.sprite.components.Selectable;
import newengine.sprite.components.Selectable.SelectionBoundType;
import newengine.sprite.components.SoundEffect;
import newengine.sprite.components.Speed;
import newengine.utils.image.ImageSet;
import newengine.utils.image.LtubImage;
import utilities.XStreamHandler;

public class XStreamTester extends Application{
 //TODO: test the serialized lambdas 
	// Xstream the sprite 
	// read out the Xstream + test componetns 
	
	public static void main(String[] args){
		
		launch(args);
		
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
//		Sprite sprite1 = new Sprite();
//		Player player1 = new Player("Player 1");
//		LtubImage image1 = new LtubImage("images/characters/bahamut_left.png");
//		ImageSet imageSet1 = new ImageSet(image1);
//		sprite1.addComponent(new GameBus());
//		//sprite1.addComponent(new SkillSet(skillMap));
//		sprite1.addComponent(new Owner(player1));
//		sprite1.addComponent(new Position(new GamePoint(200, 100), 0));
//		sprite1.addComponent(new SoundEffect("data/sounds/Psyessr4.wav"));
//		sprite1.addComponent(new Images(imageSet1));
//		sprite1.addComponent(new Speed(200));
//		sprite1.addComponent(new Collidable(CollisionBoundType.IMAGE));
//		sprite1.addComponent(new Selectable(SelectionBoundType.IMAGE));
//		sprite1.addComponent(new Range(128));
//		sprite1.addComponent(new Attacker());
//		sprite1.addComponent(new Health(200));
//		sprite1.addComponent(new EventQueue());
		
//		
//		Position p = new Position(new GamePoint(200,100), 0); 
//		XStreamHandler XSH = new XStreamHandler(); 
//		XSH.saveToFile(p);
		//XSH.saveToFile(p, location);
		
		//XSH.saveToFile(sprite1);
		
//		File file = new File("data/XMLfiles/another_component.xml");
//		XStream xstream = new XStream(new DomDriver()); 
//		Position test = (Position) xstream.fromXML(file);
//		Sprite new1 = new Sprite(); 
//		new1.addComponent(test);
//		new1.emit(new MoveEvent(MoveEvent.START_SPRITE, new1,new Target( new GamePoint(0,0))));
////		
		//Sprite newSprite = (Sprite) xstream.fromXML(file);
		//System.out.println(newSprite.hasComponent(Position.TYPE));
		//Position p =  newSprite.getComponent(Position.TYPE).get();
		//newSprite.emit(new MoveEvent(MoveEvent.START_SPRITE, newSprite,new Target( new GamePoint(0,0))));
		
		// building
		
		Sprite building = new Sprite();
		LtubImage buildingImage = new LtubImage("images/skills/build.png");
		ImageSet imageSetBuildSkill = new ImageSet(buildingImage);
		building.addComponent(new Images("images/skills/build.png"));
		building.addComponent(new Selectable(SelectionBoundType.IMAGE));
		
		Player player1 = new Player("Player 1");
		 
		SpriteMakerModel sprite1 = new SpriteMakerModel();
		LtubImage image1 = new LtubImage("images/characters/bahamut_left.png");
		ImageSet imageSet1 = new ImageSet(image1);
		Map<SkillType<? extends Skill>, Skill> skillMap = new HashMap<>();
		skillMap.put(MoveSkill.TYPE, new MoveSkill());
		//skillMap.put(BuildSkill.TYPE, new BuildSkill(building));
		skillMap.put(FireProjectileSkill.TYPE, new FireProjectileSkill());
		sprite1.addComponent(new GameBus());
		//sprite1.addComponent(new SkillSet(skillMap));
		sprite1.addComponent(new Owner(player1));
		sprite1.addComponent(new Position(new GamePoint(200, 100), 0));
		sprite1.addComponent(new SoundEffect("data/sounds/Psyessr4.wav"));
		sprite1.addComponent(new Images("images/characters/bahamut_left.png"));
		sprite1.addComponent(new Speed(200));
		sprite1.addComponent(new Collidable(CollisionBoundType.IMAGE));
		sprite1.addComponent(new Selectable(SelectionBoundType.IMAGE));
		sprite1.addComponent(new Range(128));
		sprite1.addComponent(new Attacker(25, image1));
		sprite1.addComponent(new Health(200));
		sprite1.addComponent(new EventQueue(new LinkedList<>()));
//		
		XStreamHandler XSH = new XStreamHandler();
		XSH.saveToFile(sprite1);
		
		XStream xstream = new XStream(new DomDriver());
		File file = new File("data/XMLFiles/pleasework.xml");
		SpriteMakerModel fromXML = (SpriteMakerModel) xstream.fromXML(new FileReader(file));

		Sprite sprite2 = new Sprite();
		for (ComponentType<?> c : fromXML.getDeprecatedComponents().keySet()) {
			System.out.println("Component type: " + c);
			Component componentSaved = fromXML.getDeprecatedComponents().get(c);
			sprite2.addComponent(componentSaved);
		}
		
		
	}

	
	
	
}

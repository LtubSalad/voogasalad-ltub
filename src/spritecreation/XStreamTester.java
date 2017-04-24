package spritecreation;

import java.io.File;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import commons.point.GamePoint;
import javafx.application.Application;
import javafx.stage.Stage;
import newengine.events.sprite.MoveEvent;
import newengine.player.Player;
import newengine.sprite.Sprite;
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
import newengine.utils.Target;
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
		
		
		Position p = new Position(new GamePoint(200,100), 0); 
		XStreamHandler XSH = new XStreamHandler(); 
		XSH.saveToFile(p);
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
		
	}

	
	
}

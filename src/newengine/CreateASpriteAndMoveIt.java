package newengine;

import java.util.ArrayList;
import java.util.List;

import bus.EventBus;
import engine.app.GameFactory;
import engine.camera.GamePoint;
import engine.skill.Target;
import newengine.component.PositionComponent;
import newengine.component.SpeedComponent;
import newengine.event.MoveEvent;
import newengine.event.UpdateEvent;
import newengine.sprite.Sprite;

public class CreateASpriteAndMoveIt {

	public static void main(String[] args) {
		
		GameFactory factory = new GameFactory();
		EventBus bus = factory.getBus();
		
		// sprite 1
		Sprite sprite1 = new Sprite();
		PositionComponent posComp1 = new PositionComponent(new GamePoint(200,100));
		SpeedComponent speedComp1 = new SpeedComponent(10, 60);
		sprite1.addComponent(posComp1);
		sprite1.addComponent(speedComp1);
		
		// sprite 2
		Sprite sprite2 = new Sprite();
		PositionComponent posComp2 = new PositionComponent(new GamePoint(400,250));
		SpeedComponent speedComp2 = new SpeedComponent(5, 0);
		sprite2.addComponent(posComp2);
		sprite2.addComponent(speedComp2);
		
		List<Sprite> sprites = new ArrayList<>();
		sprites.add(sprite1);
		sprites.add(sprite2);
		
		// emit sprite-specific event to modify only 1 sprite
		System.out.println("test emiting sprite-specific event to modify only 1 sprite");
		sprite1.emit(new MoveEvent(MoveEvent.SPECIFIC, sprite1, new Target(new GamePoint(0,0))));
		sprite2.emit(new MoveEvent(MoveEvent.SPECIFIC, sprite2, new Target(new GamePoint(500,200))));
		
		// test the sprite.update() method and get data from another component
		System.out.println("test the sprite.update() method and get data from another component");
		bus.on(UpdateEvent.TYPE, e -> {
			e.getSprite().update(e.getDT());
		});
		
		// use this to represent calling sprite.update() in the game loop
		for (int i = 0; i < 5; i++) {
			bus.emit(new UpdateEvent(UpdateEvent.TYPE, sprite1, 1));
		}
		
		// emit global event to modify all sprites
		System.out.println("test emiting global event to modify all sprites");
		bus.on(MoveEvent.ALL, e -> {
			for(Sprite s: e.getSprites()) {
				s.emit(new MoveEvent(MoveEvent.SPECIFIC, s, e.getTarget()));
			}
		});
		bus.emit(new MoveEvent(MoveEvent.ALL, sprites, new Target(new GamePoint(300,200))));
		
		
		// manually call sprite to attack another
//		sprite.getControl(AttackControl.TYPE).attack(Target target);
		
		// add AI for range checking and automatically attack
//		AIControl aiControl = new AIControl();
//		aiControl.setAI(AIComponent.RANGE, new RangeAttackAI()); // ???
//		sprite.addControl(aiControl);
		
		
//		sprite.on(InRangeEvent.SPRITE, () -> syso);
		
		// spawn sprite every 3 second
		
		
		// each sprite has a list of skills
		// each skill call various controls to take action
		
		
		// there should be some events programmed in the game level setting.
//		Whole game event:
//		Condition: on initialization:
//			1. sprite("ID1")  call spawn event every three second with a list of sprites presets.
//			2. when each sprite in team "Monster" is generated, set its path.
//			
//		Each condition has to be hardcoded in some place to listen for.
		
		
		
		
	}
	
}

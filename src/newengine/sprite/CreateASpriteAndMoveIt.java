package newengine.sprite;

import java.util.ArrayList;
import java.util.List;

import bus.BasicEventBus;
import bus.EventBus;
import engine.camera.GamePoint;
import engine.skill.Target;

public class CreateASpriteAndMoveIt {

	public static void main(String[] args) {
		
		EventBus bus = new BasicEventBus();
		
		// sprite 1
		Sprite sprite1 = new Sprite();
		
		PositionAttribute positionAttribute1 = new PositionAttribute();
		positionAttribute1.setPos(new GamePoint(200, 100));
		sprite1.addAttribute(positionAttribute1);
		SpeedAttribute speedAttribute1 = new SpeedAttribute();
		speedAttribute1.setSpeed(10);
		speedAttribute1.setHeading(45);
		sprite1.addAttribute(speedAttribute1);

		MoveControl moveControl1 = new MoveControl();
		sprite1.addControl(moveControl1);
		
		// sprite 2
		Sprite sprite2 = new Sprite();

		PositionAttribute positionAttribute2 = new PositionAttribute();
		positionAttribute2.setPos(new GamePoint(300, 200));
		sprite2.addAttribute(positionAttribute2);
		SpeedAttribute speedAttribute2 = new SpeedAttribute();
		speedAttribute2.setSpeed(10);
		speedAttribute2.setHeading(45);
		sprite2.addAttribute(speedAttribute2);

		MoveControl moveControl2 = new MoveControl();
		sprite2.addControl(moveControl2);
		
		List<Sprite> spriteList = new ArrayList<>();
		spriteList.add(sprite1);
		spriteList.add(sprite2);
		
		// sprite bus, move 1 sprite
		System.out.println("Test emiting event to 1 sprite.");
		bus.on(MoveEvent.SPECIFIC, (e) -> {
			Sprite theSprite = e.getSprite();

			theSprite.getSpriteBus().on(MoveEvent.SPECIFIC, e1 -> {
				MoveControl theMoveControl = e1.getSprite().getControl(MoveControl.TYPE);
				theMoveControl.moveTo(e1.getTarget());
			});
			
			theSprite.getSpriteBus().emit(new MoveEvent(MoveEvent.SPECIFIC, theSprite, e.getTarget()));
		});
		bus.emit(new MoveEvent(MoveEvent.SPECIFIC, sprite1, new Target(new GamePoint(100,200))));
		
		// big bus, move all sprites to the same destination
		System.out.println("Test emiting event to all sprites from the big bus.");
		bus.on(MoveEvent.GENERAL, (e) -> {
			List<Sprite> list = e.getSprites();
			for (Sprite s: list) {
				MoveControl theMoveControl = s.getControl(MoveControl.TYPE);
				theMoveControl.moveTo(e.getTarget());
			}
		});
		bus.emit(new MoveEvent(MoveEvent.GENERAL, spriteList, new Target(new GamePoint(500,200))));
		
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

package newengine.sprite;

import engine.camera.GamePoint;

public class CreateASpriteAndMoveIt {

	public static void main(String[] args) {
		
		Sprite sprite = new Sprite();
		PositionAttribute positionAttribute = new PositionAttribute();
		positionAttribute.setPos(new GamePoint(200, 100));
		sprite.addAttribute(positionAttribute);
		
		MoveControl moveControl = new MoveControl();
		sprite.addControl(moveControl);
		
		// manually call sprite to move 
		MoveControl spriteMoveControl = sprite.getControl(MoveControl.TYPE);
		spriteMoveControl.moveTo(Target target);
		
		// manually call sprite to attack another
		sprite.getControl(AttackControl.TYPE).attack(Target target);
		
		// add AI for range checking and automatically attack
		AIControl aiControl = new AIControl();
		aiControl.setAI(AIComponent.RANGE, new RangeAttackAI()); // ???
		sprite.addControl(aiControl);
		
		
		sprite.on(InRangeEvent.SPRITE, () -> syso);
		
		// spawn sprite every 3 second
		
		
		// each sprite has a list of skills
		// each skill call various controls to take action
		
		
		// there should be some events programmed in the game level setting.
		Whole game event:
		Condition: on initialization:
			1. sprite("ID1")  call spawn event every three second with a list of sprites presets.
			2. when each sprite in team "Monster" is generated, set its path.
			
		Each condition has to be hardcoded in some place to listen for.
		
		
		
		
	}
	
}

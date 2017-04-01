package examples;

import api.Sprite.Sprite;

/**
 * This class talks about our example code for this planning stage.
 * There are five total examples
 */
public class ExampleCode {

	/**
	 * Example 1: This example shows how a sprite's attribute will change. This is how
	 * the interface is used change the attributes dynamically. Note: the 
	 * actual setAttribute method is not implemented currently (will use 
	 * reflection and be based on the Strategy design pattern which is discussed
	 * throughout our planning documents)
	 */
	public void exampleCase1(){
		Sprite sprite = new Sprite();
		// this created sprite has some default components:
		// StationaryMover, 
		// This would switch the sprite to be movable
		sprite.setAttribute("MovableMover");

		//Sets the Attacker attribute component of the sprite
		sprite.setAttribute("LongRangeAttacker");
	}

	/**
	 * Example 2: This example shows the adding of an event when a sprite is attacked.
	 */
	
	public void exampleCase2(){
		Sprite someSprite = new Sprite();
		
		//This event bus would be passed in during the real code 
		EventBus eb = new EventBus(); 
		
		if (someSprite.isAttacked()){
			eb.emit(new SpriteAttackedEvent(SpriteAttackedEvent.ANY, someSprite));
		}		
		//You can see the SpriteAttackedEvent class for this sample class
}

	/**
	 * Example 3: Display the circle around sprite which shows selection
	 */
	public void exampleCase3(){
		//Look at the code in example 3 package
	}

	/**
	 * Example 4: Right click to move selected sprite to specific location
	 */
	public void exampleCase4(){
		// right click the mouse to move a sprite
		// 1) JavaFX scene receives mouse click event
		// 2) user input handler processes the event
		//This is reflected in the code from SelectionManager and UserInputManager
	}

	/**
	 * Example 5: Have a Message Box pop-up with event
	 */
	public void exampleCase5(){
		//look at the code in the example 5 package
	}
}

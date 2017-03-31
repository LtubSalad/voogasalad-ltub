package examples;

//import api.EventBus.EventBus;
import api.Sprite.Sprite;

public class ExampleCode {

	/**
	 * This example shows how a sprite's attribute will change. This is how
	 * the interface is used change the attributes dynamically. Note: the 
	 * actual setAttribute method is not implemented currently (will use 
	 * reflection and be based on the Strategy design pattern which is discussed
	 * throughout our planning documents)
	 */
	public void exampleCase1(){
		Sprite sprite = new Sprite();
		// this created sprite has some default components:
		// StationaryMover, 
		//This would switch the sprite to be movable
		sprite.setAttribute("MovableMover");

		//Sets the Attacker attribute component of the sprite
		sprite.setAttribute("LongRangeAttacker");
	}

	/**
	 * This example shows the adding of an event when a sprite is attacked.
	 */
	
	public void exampleCase2(){
		Sprite someSprite = new Sprite();
		/*
		someSprite.onAttacked(e -> {
			getBus().emit(new SpriteAttackedEvent(thisSprite));
		});
		EventBus eb = new EventBus(); 
		if (someSprite.isAttacked()){
			eb.emit(new SpriteAttackedEvent(someSprite));
		}
		*/
}

	public void exampleCase3(){
		//Look at the code in example 3 package
	}

	public void exampleCase4(){
		// right click the mouse to move a sprite
		// 1) JavaFX scene receives mouse click event
		// 2) user input handler processes the event
	}

	public void exampleCase5(){
		//look at the code in the example 5 package
	}
}

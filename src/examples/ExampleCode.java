package examples;

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
	
	public void exampleCase2(){
		
	}

	public void exampleCase3(){
		
	}
	
	public void exampleCase4(){
		
	}
	
	public void exampleCase5(){
		
	}
}

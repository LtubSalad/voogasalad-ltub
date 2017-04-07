package sprite;

import static org.junit.Assert.*;

import org.junit.Test;

public class SpriteTest {
	@Test 
	public void checkReflection(){
	Sprite sprite = new Sprite();
	try {
		sprite.setMoverAttribute("RunnerMoverAttribute");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	assertTrue("Reflection for sprite attributes is incorrect",sprite.getMoverAttribute() instanceof RunnerMoverAttribute);
	
	}
}

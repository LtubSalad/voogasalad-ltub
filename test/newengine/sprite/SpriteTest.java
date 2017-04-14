package newengine.sprite;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SpriteTest {

	Sprite sprite;
	
	@Before
	public void setUp() throws Exception {
		sprite = new Sprite();
	}

	@Test
	public void testAddAttribute() {
		PositionTestAttribute attribute1 = new PositionTestAttribute();
		sprite.addAttribute(attribute1);
		assertEquals(true, sprite.hasAttribute(PositionTestAttribute.TYPE));
//		sprite.getAttribute(PositionTestAttribute.TYPE).getPositionX();
	}
	
	@Test
	public void testRemoveAttribute() {
		PositionTestAttribute attribute1 = new PositionTestAttribute();
		sprite.addAttribute(attribute1);
		sprite.removeAttribute(PositionTestAttribute.TYPE);
		assertEquals(false, sprite.hasAttribute(PositionTestAttribute.TYPE));
	}

	@Test
	public void testGetAttribute() {
		PositionTestAttribute attribute1 = new PositionTestAttribute();
		sprite.addAttribute(attribute1);
		assertEquals(true, attribute1 == sprite.getAttribute(PositionTestAttribute.TYPE));
	}
	
}

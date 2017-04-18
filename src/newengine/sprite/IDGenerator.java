package newengine.sprite;

public class IDGenerator {

	private static int counter = 0;
	
	public static SpriteID generateID() {
		SpriteID spriteID = new SpriteID(Integer.toString(counter));
		counter += 1;
		return spriteID;
	}
	
}

package sprite;

public class TestSprite {

	public static void main(String[] args) {
		Sprite sprite = new Sprite();
		try {
			sprite.setMoverAttribute("RunnerMoverAttribute");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		sprite.move();
		System.out.println("Testing done");
	}

}

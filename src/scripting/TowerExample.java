package scripting;

public class TowerExample {
	private String myImageFile;
	private int myFiringRate;
	private int myRange;
	private int myDamage;

	public TowerExample(String imageFilePath, int firingRate, int range, int damage) {
		myImageFile = imageFilePath;
		myFiringRate = firingRate;
		myRange = range;
		myDamage = damage;
	}

	public String use() {
		return "Tower " + myImageFile + " was used";
	}

	public String getImageFileName() {
		return myImageFile;
	}

	public int getFiringRate() {
		return myFiringRate;
	}

	public int getRange() {
		return myRange;
	}

	public int getDamage() {
		return myDamage;
	}

}

package newengine.player;

public class Player {

	public static final Player DEFAULT = new Player("DEFAULT_PLAYER");
	/**
	 * Trees, tiles, and certain projectiles belong to player NATURE. 
	 * Those things cannot be controlled by either the user or the AI.
	 */
	public static final Player NATURE = new Player("NATURE"); 
	
	public static final String MAIN_PLAYER = "Player 1";
	
	private String name;
	
	public Player(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * Test if this sprite can control the other player
	 * @param other
	 * @return
	 */
	public boolean canControl(Player other) {
		return other == this;
	}
	
	@Override
	public String toString() {
		return name;
	}
}

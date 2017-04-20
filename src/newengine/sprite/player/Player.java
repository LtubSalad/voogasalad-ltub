package newengine.sprite.player;

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
	
	public boolean canControl(Player other) {
		return true;
	}
}

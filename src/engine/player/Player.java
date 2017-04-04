package engine.player;

public class Player {

	public static final Player DEFAULT = new Player("DEFAULT_PLAYER");
	
	private String name;
	
	public Player(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean canControlPlayer(Player other) {
		return true;
	}
}

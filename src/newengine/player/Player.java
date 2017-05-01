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
		return other.equals(this);
	}
	
	public boolean isEnemyWith(Player other) {
		// currently all other players are enemy. TODO
		return !this.equals(other);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}
}

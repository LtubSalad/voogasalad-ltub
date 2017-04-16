package newengine.gamevariable;

public class GameVariableKey {

	public static final GameVariableKey SELECTED_SPRITE = new GameVariableKey("selected_sprite");
	
	private String keyName;
	
	public GameVariableKey(String keyName) {
		this.keyName = keyName;
	}
	
}

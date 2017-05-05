/**
 * 
 */
package player.winnerorloser;

/**
 * @author Zhiyong
 *get the result of the game in the end of a game
 */
public class ResultAccessor {
	private String name;
	public ResultAccessor(){
		
	}
	public ResultAccessor(String name){
		this.name  = name;
	}	
	
	/**
	 * @return health
	 * get the health of sprites
	 */
	public double getHealth() {
		return 0;
	}
	
	/**
	 * @return point
	 * get the points of sprites
	 */
	public double getPoint() {
		return 0;
	}
	
	/**
	 * @return gamename
	 * get the name of the game
	 */
	public String getGameName() {
		return name;
	}
}

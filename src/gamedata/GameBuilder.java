package gamedata;

import newengine.app.Game;

public class GameBuilder {
	private Game game; 
	
	public GameBuilder(){
		game = new Game(); // replace this 
	}
	
	public Game getGame(){
		return game; 
	}

}

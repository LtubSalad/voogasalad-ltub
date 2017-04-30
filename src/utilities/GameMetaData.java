package utilities;

import javafx.scene.image.Image;

public class GameMetaData {
	
	private String gameName;
	private String imageFilePath;
	private String gameFilePath;
	
	public GameMetaData(String gameName, String imageFilePath, String gameFilePath) {
		this.gameName = gameName;
		this.imageFilePath = imageFilePath;
		this.gameFilePath = gameFilePath;
	}
	
	public String getGameName() {
		return gameName;
	}

	public Image getImage() {
		return new Image(imageFilePath);
	}
	
	public String getGameFilePath() {
		return gameFilePath;
	}
}

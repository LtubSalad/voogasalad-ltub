package gameDevelopmentInterface;

import java.io.File;

import data.DeveloperData;
import data.ScreenModelData;
import data.SpriteMakerModel;
import data.SpritesForScreenUse;
import utilities.XStreamHandler;

public class BackgroundSetter extends ScreenModelCreator {
	private XStreamHandler xstream = new XStreamHandler();

	public BackgroundSetter(DeveloperData data) {
		super(data);
		this.setTop(null);
		this.setRight(new BackgroundButtonsPanel(this, data));
		addTiles();
	}
	
	private void addTiles() {
		File defaultTileDirectory = new File("data/defaultTiles");
		File[] tileFiles = defaultTileDirectory.listFiles();
		for (File f : tileFiles) {
			SpriteMakerModel myTile = (SpriteMakerModel) xstream.getAttributeFromFile(f);
			addPossibleSprite(myTile);
		}

		
		//		SpriteMakerModel sprite1 = new SpriteMakerModel();
//		sprite1.addComponent(new Images("images/characters/Lava.jpg"));
//		xstream.saveToFile(sprite1);
//		List<SpriteMakerModel> myTiles = xstream.getScreenModel(defaultTileFile);
//		myTiles.forEach(spriteModel -> addPossibleSprite(spriteModel));
	}

}
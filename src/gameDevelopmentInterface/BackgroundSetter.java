package gameDevelopmentInterface;

import java.io.File;

import data.DeveloperData;
import data.ScreenModelData;
import data.SpriteMakerModel;
import data.SpritesForScreenUse;
import utilities.XStreamHandler;

public class BackgroundSetter extends ScreenModelCreator {
	private XStreamHandler xstream = new XStreamHandler();

	public BackgroundSetter(SpritesForScreenUse spriteModelsToDrag, GeneralDataCreator gdc, ScreenModelData screenData) {
		super(new DeveloperData());
		addTiles();
}
	
	private void addTiles() {
		File defaultTileDirectory = new File("data/defaultTiles");
		File[] tileFiles = defaultTileDirectory.listFiles();
		for (File f : tileFiles) {
			SpriteMakerModel myTile = (SpriteMakerModel) xstream.getAttributeFromFile(f);
			addPossibleSprite(myTile);
		}
	}

}
package gameDevelopmentInterface;

import java.io.File;
import java.util.List;

import data.ScreenModelData;
import data.SpriteMakerModel;
import data.SpritesForScreenUse;
import utilities.XStreamHandler;

public class BackgroundSetter extends ScreenModelCreator {
	private XStreamHandler xstream = new XStreamHandler();

	public BackgroundSetter(SpritesForScreenUse spriteModelsToDrag, GeneralDataCreator gdc, ScreenModelData screenData) {
		super(spriteModelsToDrag, gdc, screenData);
		this.setTop(null);
		this.setRight(new BackgroundButtonsPanel(this));
		addTiles();
	}
	
	private void addTiles() {
		File defaultTileFile = new File("data/attributeSkeletons/userCreatedAttributes/AllThreeFromApp.xml");//"defaultTiles/defaultTiles.xml");
		List<SpriteMakerModel> myTiles = xstream.getScreenModel(defaultTileFile);
		myTiles.forEach(spriteModel -> addPossibleSprite(spriteModel));
	}

}

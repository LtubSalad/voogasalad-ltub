package gameDevelopmentInterface;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import data.AttributeData;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import utilities.XStreamHandler;

public class ButtonsPanel extends VBox {
	private Button drawPathButton = new Button("Start a path");
	private Button finishPathButton = new Button("Check/Save path");
	private Button saveSetupButton = new Button("Save this setup");
	private Button loadButton;
	private Button loadSpriteButton;
	private XStreamHandler xstreamHandler=new XStreamHandler();
	private final String userCreatedAttributesFile = "data/attributeSkeletons/userCreatedAttributes";
	private PathCreator myPathCreator;
	private ScreenModelCreator mySMC;
	
	public ButtonsPanel(ScreenModelCreator smc) {
		mySMC = smc;
		myPathCreator = new PathCreator(smc);
		makeButtons();
		this.getChildren().addAll(drawPathButton, finishPathButton, saveSetupButton,loadButton, loadSpriteButton);		
	}

	private void makeButtons() {
		loadSpriteButton = new Button("Load Sprite from file");
		loadSpriteButton.setOnAction(e -> {
			AttributeData sprite=xstreamHandler.getAttributeFromFile();
			mySMC.addPossibleSprite(sprite);
		});
		drawPathButton.setOnAction(e -> {
			myPathCreator.getReplacementPath().clear();
			myPathCreator.makePath();
		});
		finishPathButton.setOnAction(e -> myPathCreator.replacePath());
		saveSetupButton.setOnAction(e -> {
//			FileOutputStream fs = null;
//			XStream xstream = new XStream(new DomDriver());
			xstreamHandler.saveToFile(mySMC.getScreenData().getDataToSave());
//			try {
//				fs = new FileOutputStream(userCreatedAttributesFile+"/"+ "TEST_JAKE" +".xml");
//				xstream.toXML(mySMC.getScreenData().getDataToSave(), fs);
//			} catch (FileNotFoundException fnf) {
//				//We are making the file ourselves so this will never trigger
//			}
		});
		
		loadButton=new Button("Load screenModel from file");
		loadButton.setOnAction((click)->{
			mySMC.getScreenData().setObjectsOnScreen(xstreamHandler.getScreenModelFile());
			mySMC.getScreenData().printCoordsOfScreenObjects();
		});
	}
}
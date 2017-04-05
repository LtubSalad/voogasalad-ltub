package gameDevelopmentInterface;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.awt.MouseInfo;
import java.awt.Point;

import data.ScreenModelData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

/**
 * 
 * The job of the ScreenModelCreator is to provide an interface for the developer to set all the data for a 
 * given screen. It has no public methods aside from it's instantiator. Things it does: binding between the 
 * DummyScreenView and the ScreenModel, allowing the user to place objects on the screen. Adding an object 
 * to the screen should modify the ScreenModel and display something on the DummyScreen if the object implements
 * Sprite.
 * 
 * @author Jake
 *
 */

public class ScreenModelCreator extends BorderPane {
	private ScreenModelData myData = new ScreenModelData();
	private ScreenObjectHolder myObjectsToPlace = new ScreenObjectHolder(this);
	private ScreenMap myScreen = new ScreenMap();
	private double myMouseX = 0;
	private double myMouseY = 0;
	
	public ScreenModelCreator() {
		this.setBottom(myObjectsToPlace);
		this.setCenter(myScreen);
	}
	
	public ScreenMap getScreen() {
		return myScreen;
	}
	
//	private void getCurrMouseCoords() {
//		EventHandler<MouseEvent> ev = new EventHandler<MouseEvent> () {
//			@Override
//			public void handle(MouseEvent event) {
//				myMouseX = event.getScreenX();
//				myMouseY = event.getScreenY();
//			}
//		};
//	}

}
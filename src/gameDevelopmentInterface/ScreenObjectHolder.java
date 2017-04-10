package gameDevelopmentInterface;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.AttributeData;
import data.ScreenModelData;
import engine.model.BasicModel;
import engine.sprite.Sprite;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Pair;

public class ScreenObjectHolder extends HBox {
	private static final String IMAGE_HOLDER = "ImageHolder";
	private static final String IMAGE = "image";
	private static final String PATH_TO_IMAGE_FILES = "images/characters/";
	private static final String PATH_TO_XML_FILES = "data/attributeSkeletons/";
	private static final String TOWER_XML = PATH_TO_XML_FILES + "presetAttributes/Tower.xml";
	private static final String MONSTER_XML = PATH_TO_XML_FILES + "presetAttributes/Monster.xml";
	private static final String GRASS_XML = PATH_TO_XML_FILES + "presetAttributes/Grass.xml";
	private static final String STONE_XML = PATH_TO_XML_FILES + "presetAttributes/Stone.xml";
	private static final String WATER_XML = PATH_TO_XML_FILES + "presetAttributes/Water.xml";
	private ScreenModelCreator myScreenModel;
	private ScreenModelData myScreenData;
	private Map<ImageView, AttributeData> myScreenObjects = new HashMap<ImageView, AttributeData>();
	
	public ScreenObjectHolder(ScreenModelCreator smc, ScreenModelData smd) {
		myScreenModel = smc;
		myScreenData = smd;
		addObject(new File(TOWER_XML));
		addObject(new File(MONSTER_XML));
		addObject(new File(GRASS_XML));
		addObject(new File(STONE_XML));
		addObject(new File(WATER_XML));
	}
	
	/**
	 * Add a created sprite to the screen object selector
	 * @param screenObject the sprite to add to the HBox
	 */
	public void addObject(AttributeData screenObject) {
		screenObject.getAttributes().forEach(attr -> {
			if (attr.getName().equals(IMAGE_HOLDER)) {
				Map<String,String> varMap = attr.getVariables();
				Image si = new Image(getClass().getClassLoader().getResourceAsStream(PATH_TO_IMAGE_FILES+varMap.get(IMAGE)), 100, 100, false, false);
				ImageView spriteImage = new ImageView(si);
				spriteImage.setOnMousePressed(e -> dragAndDrop(spriteImage));
				myScreenObjects.put(spriteImage, screenObject);
				this.getChildren().add(spriteImage);
				spriteImage.setOnMousePressed(e -> dragAndDrop(spriteImage));
			}
		});
	}
	
	public void addObject(File file) {
		addObject(new AttributeDataFactory().produceAttribute(file));
	}
	
	private void dragAndDrop(ImageView source) {
		ScreenMap target = myScreenModel.getScreen();
		source.setOnDragDetected(e -> sourceSetOnDragDetected(source, e));
		target.setOnDragOver(e -> targetSetOnDragOver(e));
		target.setOnDragEntered(e -> targetSetOnDragEntered(target, e));
		target.setOnDragExited(e -> targetSetOnDragExited(target, e));
		target.setOnDragDropped(e -> targetSetOnDragDropped(target, e));
		source.setOnDragDone(e -> e.consume());
	}

	private void targetSetOnDragDropped(ScreenMap target, DragEvent e) {
		Dragboard db = e.getDragboard();
		boolean success = false;
		if (e.getDragboard().hasImage()) {
			GridPane grid = target.getGrid();
			Image im = db.getImage();
			ImageView toAdd = new ImageView(im);
			toAdd.setFitHeight(grid.getHeight()/target.getNumRows());
			toAdd.setFitWidth(grid.getWidth()/target.getNumCols());
			double spriteX = e.getScreenX();
			double spriteY = e.getScreenY();
			Pair<Integer, Integer> coords = target.getCoordOfMouseHover(spriteX, spriteY);
			grid.add(toAdd, coords.getKey(), coords.getValue());
			//add this sprite to the model
			myScreenData.addObjectData(new Sprite()); //with the source's attribute data loaded up into the sprite
			success = true;
		}
		e.setDropCompleted(success);
		e.consume();
	}

	private void targetSetOnDragExited(ScreenMap target, DragEvent e) {
		if (e.getGestureSource() != target && e.getDragboard().hasImage()) {
			target.getGrid().setGridLinesVisible(false);
		}
		e.consume();
	}

	private void targetSetOnDragEntered(ScreenMap target, DragEvent e) {
		if (e.getDragboard().hasImage()) {
			target.getGrid().setGridLinesVisible(true);
		}
		e.consume();
	}

	private void targetSetOnDragOver(DragEvent e) {
		if (e.getDragboard().hasImage()) {
			e.acceptTransferModes(TransferMode.COPY);
		}
		e.consume();
	}

	private void sourceSetOnDragDetected(ImageView source, MouseEvent e) {
		Dragboard db = source.startDragAndDrop(TransferMode.COPY);
		ClipboardContent content = new ClipboardContent();
		content.putImage(source.getImage());
		db.setContent(content);
		e.consume();
	}

}
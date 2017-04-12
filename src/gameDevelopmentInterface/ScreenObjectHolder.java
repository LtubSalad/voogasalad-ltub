package gameDevelopmentInterface;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import data.AttributeData;
import data.AttributesForScreenUse;
import data.ScreenModelData;
import engine.sprite.Sprite;
import gameDevelopmentInterface.attributeCreator.AttributeDataFactory;
import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Pair;

public class ScreenObjectHolder extends HBox {
	private static final String IMAGE_HOLDER = "ImageHolder";
	private static final String IMAGE = "image";
	private static final String PATH_TO_IMAGE_FILES = "images/characters/";
	private static final String PATH_TO_XML_FILES = "data/attributeSkeletons/";
	private ScreenModelCreator myScreenModel;
	private ScreenModelData myScreenData;
	private Map<Pair<String, Image>, AttributeData> myScreenObjects = new HashMap<Pair<String, Image>, AttributeData>();
	private AttributesForScreenUse myAttributesModel;

	public ScreenObjectHolder(ScreenModelCreator smc, ScreenModelData smd, AttributesForScreenUse attributesModel) {
		myScreenModel = smc;
		myScreenData = smd;
		myAttributesModel = attributesModel;
		myAttributesModel.getScreenAttributes().addListener(new ListChangeListener<AttributeData>() {
			@Override
			public void onChanged(@SuppressWarnings("rawtypes") ListChangeListener.Change change) {
				myAttributesModel.getScreenAttributes().forEach(attr -> {
					attr.getAttributes().forEach(att -> {
						if (att.getName().equals(IMAGE_HOLDER)) {
							String imageName = attr.getVariable(IMAGE);
							if (myScreenObjects.size() == 0) {
								addObject(attr);
							} else {
								boolean wasFound = false;
								for (Pair<String, Image> p : myScreenObjects.keySet()) {
									if (p.getKey().equals(imageName)) {
										wasFound = true;
									}
								}
								if (!wasFound) {
									addObject(attr);
								}
							}
						}
					});
				});
			}
		});
	}

	/**
	 * Add a created sprite to the screen object selector
	 * 
	 * @param screenObject
	 *            the sprite to add to the HBox
	 */
	public void addObject(AttributeData screenObject) {
		screenObject.getAttributes().forEach(attr -> {
			if (attr.getName().equals(IMAGE_HOLDER)) {
				Map<String, String> varMap = attr.getVariables();
				String imageName = varMap.get(IMAGE);
				Image si = new Image(getClass().getClassLoader().getResourceAsStream(PATH_TO_IMAGE_FILES + imageName),
						100, 100, false, false);
				ImageView spriteImage = new ImageView(si);
				spriteImage.setOnMousePressed(e -> dragAndDrop(spriteImage));
				myScreenObjects.put(new Pair<String, Image>(imageName, si), screenObject);
				this.getChildren().add(spriteImage);
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
			String imageName = db.getString();
			ImageView toAdd = new ImageView(im);
			toAdd.setFitHeight(grid.getHeight() / target.getNumRows());
			toAdd.setFitWidth(grid.getWidth() / target.getNumCols());
			double spriteX = e.getScreenX();
			double spriteY = e.getScreenY();
			Pair<Integer, Integer> coords = target.getCoordOfMouseHover(spriteX, spriteY);
			for (Pair<String, Image> p : myScreenObjects.keySet()) {
				String iName = p.getKey();
				if (imageName.equals(iName)) {
					AttributeData anActualPlacedScreenObject = (AttributeData) new UnoptimizedDeepCopy().copy(myScreenObjects.get(p));//new AttributeData(myScreenObjects.get(p));
					anActualPlacedScreenObject.setVariable("xPosition", coords.getKey() + "");
					anActualPlacedScreenObject.setVariable("yPosition", coords.getValue() + "");
					myScreenData.addObjectData(anActualPlacedScreenObject);
					break;
				}
			}
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
		Image sourceImage = source.getImage();
		String imageName = getImageName(sourceImage);
		content.putString(imageName);
		content.putImage(source.getImage());
		db.setContent(content);
		e.consume();
	}
	
	private String getImageName(Image image) {
		String toReturn = "";
		for (Pair<String, Image> p : myScreenObjects.keySet()) {
			String imageName = p.getKey();
			Image imageValue = p.getValue();
			if (imageValue.equals(image)) {
				toReturn = imageName;
			}
		}
		return toReturn;
	}
}
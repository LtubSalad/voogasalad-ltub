package gameDevelopmentInterface;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import data.AttributeData;
import data.AttributesForScreenUse;
import data.ScreenModelData;
import engine.camera.GamePoint;
import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Pair;

public class ScreenObjectHolder extends HBox {
	private static final String IMAGE_HOLDER = "filepath";
	private static final String Y_POSITION = "yPosition";
	private static final String X_POSITION = "xPosition";
	private static final String IMAGE = "image";
	private static final String PATH_TO_IMAGE_FILES = "images/characters/";
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
				System.out.println("Heard a change in the model");
				myAttributesModel.getScreenAttributes().forEach(attr -> {
					if (attr.hasVariable(IMAGE_HOLDER)){
						String imageName = attr.getVariable(IMAGE_HOLDER);
						boolean wasFound = false;
						if (myScreenObjects.size() == 0) {
							addObject(attr);
						}
						for (Pair<String, Image> p : myScreenObjects.keySet()) {
							if (p.getKey().equals(imageName)) {
								wasFound = true;
							}
							if (!wasFound) {
								addObject(attr);
							}
						}
					}
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
		String imageName = screenObject.getVariable(IMAGE_HOLDER);
		Image si = new Image(getClass().getClassLoader().getResourceAsStream(PATH_TO_IMAGE_FILES + imageName),
				100, 100, false, false);
		ImageView spriteImage = new ImageView(si);
		spriteImage.setOnMousePressed(e -> dragAndDrop(spriteImage));
		myScreenObjects.put(new Pair<String, Image>(imageName, si), screenObject);
		this.getChildren().add(spriteImage);
	}
	/**
	 * Make an attribute data object from a file
	 * @param file
	 */
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
			String imageName = db.getString();
			ImageView toAdd = new ImageView(db.getImage());
			toAdd.setFitHeight(grid.getHeight() / target.getNumRows());
			toAdd.setFitWidth(grid.getWidth() / target.getNumCols());
			GamePoint coords = target.getCoordOfMouseHover(e.getScreenX(), e.getScreenY());
			for (Pair<String, Image> p : myScreenObjects.keySet()) {
				String iName = p.getKey();
				if (imageName.equals(iName)) {
					AttributeData anActualPlacedScreenObject = (AttributeData) new UnoptimizedDeepCopy().copy(myScreenObjects.get(p));
					anActualPlacedScreenObject.setVariable(X_POSITION, coords.x() + "");
					anActualPlacedScreenObject.setVariable(Y_POSITION, coords.y() + "");
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
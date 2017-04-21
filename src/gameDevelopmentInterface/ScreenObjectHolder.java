package gameDevelopmentInterface;
import java.util.ArrayList;
//import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.ScreenModelData;
//import java.util.ResourceBundle;
import data.SpriteMakerModel;
import data.SpritesForScreenUse;
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
/**
 * This class holds all possible sprites that a user can place on the screen.
 * @author Jake
 *
 */
public class ScreenObjectHolder extends HBox {
//	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
//	private static final String RESOURCE_FILE_NAME = "gameAuthoringEnvironment";
//	private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);
//	private static final String IMAGE_HOLDER = "IMAGE_HOLDER";
//	private static final String IMAGE = "IMAGE";
//	private static final String Y_POSITION = "Y_POSITION";
//	private static final String X_POSITION = "X_POSITION";
//	private static final String PATH_TO_IMAGE_FILES = "PATH_TO_IMAGE_FILES";
	private ScreenModelCreator myScreenModel;
	private ScreenModelData myScreenData;
	private Map<Pair<String, Image>, SpriteMakerModel> myScreenObjects = new HashMap<Pair<String, Image>, SpriteMakerModel>();
	
	public ScreenObjectHolder(ScreenModelCreator smc, ScreenModelData smd, SpritesForScreenUse attributesModel) {
		myScreenModel = smc;
		myScreenData = smd;
//		myScreenModel.getPossibleSprites().addListener(new ListChangeListener<SpriteMakerModel>() {
//			@Override
//			public void onChanged(@SuppressWarnings("rawtypes") ListChangeListener.Change change) {
//				myScreenModel.getPossibleSprites().forEach(spriteMakerModel -> {
//					List<Component> screenObjectComponents = spriteMakerModel.getComponents();
//					for (Component c : screenObjectComponents) {
//						ComponentType<?> type = c.getType();
//						if (type.equals(Images.TYPE)) {
//							Images imageComponent = (Images) c;
//							String imageName = imageComponent.image().getFileName();
//							boolean wasFound = false;
//							if (myScreenObjects.size() == 0) {
//								addObject(spriteMakerModel);
//							} else {
//								for (Pair<String, Image> pair : myScreenObjects.keySet()) {
//									if (pair.getKey().equals(imageName)) {
//										wasFound = true;
//									}
//								}
//								if (!wasFound) {
//									addObject(spriteMakerModel);
//								}	
//							}
//						}
//					}
//				});
//			}
//		});
//		SpriteMakerModel dummySprite = new SpriteMakerModel();
//		ImageSet iSet = new ImageSet();
//		LtubImage lImage = new LtubImage("images/characters/Grass.jpg");
//		iSet.setImage(lImage);
//		dummySprite.addComponent(new Images(iSet));
//		addObject(dummySprite);
	}
	/**
	 * Add a created sprite to the screen object selector
	 * 
	 * @param screenObject
	 *            the sprite to add to the HBox
	 */
	public void addObject(SpriteMakerModel screenObject) {
		Map<String, List<String>> screenObjectComponents = screenObject.getComponents();
		String imageFileName = screenObjectComponents.get("Images").get(0);
		Image image = new Image(getClass().getResourceAsStream(imageFileName));
		ImageView iView = new ImageView(image);
		iView.setOnDragDetected(e -> dragAndDrop(iView));
		this.getChildren().add(iView);
	}
	/**
	 * Make an attribute data object from a file
	 * @param file
	 */
//	public void addObject(File file) {
//		addObject(new SpriteMakerModelFactory().produceAttribute(file));
//	}
	
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
			Pair<Integer, Integer> gridCoords = target.getCoordOfMouseHover(e.getScreenX(), e.getScreenY());
			String xPos = e.getScreenX() + "";
			String yPos = e.getScreenY() + "";
			String heading = 0 + "";
			List<String> positionParams = new ArrayList<String>();
			positionParams.add(xPos);
			positionParams.add(yPos);
			positionParams.add(heading);
			for (Pair<String, Image> p : myScreenObjects.keySet()) {
				if (p.getKey().equals(imageName)) {
					SpriteMakerModel sprite = (SpriteMakerModel) new UnoptimizedDeepCopy().copy(myScreenObjects.get(p));
					sprite.addComponent("Position", positionParams);
					myScreenData.addObjectData(sprite);
				}
			}
			
//			
//			
//			
//			for (Pair<String, Image> p : myScreenObjects.keySet()) {
//				String iName = p.getKey();
//				if (imageName.equals(iName)) {
//					SpriteMakerModel anActualPlacedScreenObject = (SpriteMakerModel) new UnoptimizedDeepCopy().copy(myScreenObjects.get(p));
//		//			anActualPlacedScreenObject.addComponent(); //heading 0 because all sprites default to this
//					myScreenData.addObjectData(anActualPlacedScreenObject);
//					break;
//				}
//			}
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
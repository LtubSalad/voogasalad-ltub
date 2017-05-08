package gameDevelopmentInterface;
import java.io.File;
//import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import commons.point.GamePoint;
import data.DeveloperData;
import data.SpriteMakerModel;
import javafx.collections.ListChangeListener;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.util.Pair;
import newengine.player.Player;
import newengine.skill.Skill;
import newengine.skill.SkillType;
import newengine.skill.skills.BuildSkill;
import newengine.skill.skills.FireProjectileSkill;
import newengine.skill.skills.MoveSkill;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.sprite.components.Attacker;
import newengine.sprite.components.Collidable;
import newengine.sprite.components.Collidable.CollisionBoundType;
import newengine.sprite.components.Cooldown;
import newengine.sprite.components.EventQueue;
import newengine.sprite.components.GameBus;
import newengine.sprite.components.Health;
import newengine.sprite.components.Images;
import newengine.sprite.components.Owner;
import newengine.sprite.components.Position;
import newengine.sprite.components.Range;
import newengine.sprite.components.RangeShootingAI;
import newengine.sprite.components.Selectable;
import newengine.sprite.components.Selectable.SelectionBoundType;
import newengine.sprite.components.SkillSet;
import newengine.sprite.components.SoundEffect;
import newengine.sprite.components.Speed;
import newengine.utils.image.ImageSet;
import newengine.utils.image.LtubImage;
import utilities.XStreamHandler;
/**
 * This class holds all possible sprites that a user can place on the screen.
 * @author Jake
 *
 */
public class ScreenObjectHolder extends HBox {
	private ScreenModelCreator myScreenModel;
	private List<SpriteMakerModel> myScreenData;
	private Map<Pair<String, Image>, SpriteMakerModel> myScreenObjects = new HashMap<Pair<String, Image>, SpriteMakerModel>();
	private DeveloperData myModel;

	public ScreenObjectHolder(DeveloperData model, ScreenModelCreator smc) {
		myScreenModel = smc;
		myModel = model;

		//		myScreenModel = ;
		//myScreenData = myModel.getBackgroundTiles();
		myModel.getTilesToDrag().addListener(new ListChangeListener<SpriteMakerModel>() {
			@Override
			public void onChanged(@SuppressWarnings("rawtypes") ListChangeListener.Change change) {
				myModel.getTilesToDrag().forEach(spriteMakerModel -> {
					Map<ComponentType<?>, Component> screenObjectComponents = spriteMakerModel.getComponents();

					for (Component c : screenObjectComponents.values()) {
						ComponentType<?> type = c.getType();
						if (type.equals(Images.TYPE)) {
							Images imageComponent = (Images) c;
							String imageName = imageComponent.image().getFileName();
							boolean wasFound = false;
							if (myScreenObjects.size() == 0) {
								addObject(spriteMakerModel);
							} else {
								for (Pair<String, Image> pair : myScreenObjects.keySet()) {
									if (pair.getKey().equals(imageName)) {
										wasFound = true;
									}
								}
								if (!wasFound) {
									addObject(spriteMakerModel);
								}	
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
	public void addObject(SpriteMakerModel screenObject) {
		for (Component c : screenObject.getDeprecatedComponents().values()) {
			ComponentType<?> type = c.getType();
			if (type.equals(Images.TYPE)) {
				Images imageComponent = (Images) c;
				Image spriteImage = imageComponent.image().getFXImage();
				ImageView spriteImageView = new ImageView(spriteImage);
				spriteImageView.setFitHeight(100);
				spriteImageView.setFitWidth(100);
				spriteImageView.setOnMousePressed(e -> dragAndDrop(spriteImageView));
				myScreenObjects.put(new Pair<String, Image>(imageComponent.image().getFileName(), spriteImage), screenObject);
				this.getChildren().add(spriteImageView);
			}
		}
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
			String imageName = db.getString();
			Point2D point = target.sceneToLocal(e.getSceneX(), e.getSceneY());
			GamePoint gameCoords = new GamePoint(point.getX()/target.getScreenWidth(), point.getY()/target.getScreenHeight());
			for (Pair<String, Image> p : myScreenObjects.keySet()) {
				String iName = p.getKey();
				if (imageName.equals(iName)) {
					XStreamHandler xstream = new XStreamHandler();
					File tempSpriteFile = new File("data/tempSprites/tempSprite.xml");
					xstream.saveToFile(myScreenObjects.get(p), tempSpriteFile);
					SpriteMakerModel xmlSprite = (SpriteMakerModel) xstream.getAttributeFromFile(tempSpriteFile);
					xmlSprite.addComponent(new Position(gameCoords, 0)); //heading 0 because all sprites default to this
					myModel.addBackgroundTile(xmlSprite);
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

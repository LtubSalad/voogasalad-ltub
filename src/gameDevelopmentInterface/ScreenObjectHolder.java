package gameDevelopmentInterface;
import java.io.File;
//import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import commons.point.GamePoint;
import data.ScreenModelData;
import data.SpriteMakerModel;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableMap;
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
	
	public ScreenObjectHolder(ScreenModelCreator smc, ScreenModelData smd) {
		myScreenModel = smc;
		myScreenData = smd;
		myScreenModel.getPossibleSprites().addListener(new ListChangeListener<SpriteMakerModel>() {
			@Override
			public void onChanged(@SuppressWarnings("rawtypes") ListChangeListener.Change change) {
				myScreenModel.getPossibleSprites().forEach(spriteMakerModel -> {
					ObservableMap<ComponentType<?>, Component> screenObjectComponents = spriteMakerModel.getComponents();
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
		
//		Player player1 = new Player("Player 1");
//		Player player2 = new Player("Player 2");
//		
//		// building
//		SpriteMakerModel building = new SpriteMakerModel();
//		LtubImage buildingImage = new LtubImage("images/skills/build.png");
//		ImageSet imageSetBuildSkill = new ImageSet(buildingImage);
//		building.addComponent(new Images("images/skills/build.png"));
//		//building.addComponent(new Selectable(SelectionBoundType.IMAGE));
//		
//		
//		
//		// sprite 1: the tower
//		SpriteMakerModel sprite1 = new SpriteMakerModel();
//		LtubImage image1 = new LtubImage("images/characters/tower2_resized.gif");
//		ImageSet imageSet1 = new ImageSet(image1);
//		Map<SkillType<? extends Skill>, Skill> skillMap1 = new HashMap<>();
//		skillMap1.put(MoveSkill.TYPE, new MoveSkill());
//		skillMap1.put(BuildSkill.TYPE, new BuildSkill(building));
//		FireProjectileSkill fireSkill1 = new FireProjectileSkill();
//		fireSkill1.setCooldown(3); // add cooldown to the fireProjectilSkill
//		sprite1.addComponent(new Cooldown());
//		skillMap1.put(FireProjectileSkill.TYPE, fireSkill1);
//		sprite1.addComponent(new GameBus());
//		sprite1.addComponent(new SkillSet(skillMap1));
//		sprite1.addComponent(new Owner(player1));
//		sprite1.addComponent(new Position(new GamePoint(200, 100), 0));
//		sprite1.addComponent(new SoundEffect("data/sounds/Psyessr4.wav"));
//		sprite1.addComponent(new Images("images/characters/tower2_resized.gif"));
//		sprite1.addComponent(new Speed(200));
//		sprite1.addComponent(new Collidable(CollisionBoundType.IMAGE));
//		sprite1.addComponent(new Selectable(SelectionBoundType.IMAGE));
//		sprite1.addComponent(new Range(200));
//		sprite1.addComponent(new Attacker());
//		sprite1.addComponent(new Health(200));
//		sprite1.addComponent(new EventQueue(new LinkedList<>()));
//		sprite1.addComponent(new RangeShootingAI());
//			
//		// sprite 2
//		SpriteMakerModel sprite2 = new SpriteMakerModel();
//		LtubImage image2 = new LtubImage("images/characters/bahamut_right.png");
//		ImageSet imageSet2 = new ImageSet(image2);
//		Map<SkillType<? extends Skill>, Skill> skillMap2 = new HashMap<>();
//		skillMap2.put(MoveSkill.TYPE, new MoveSkill());
//		skillMap2.put(FireProjectileSkill.TYPE, new FireProjectileSkill());
//		sprite2.addComponent(new GameBus());
//		sprite2.addComponent(new SkillSet(skillMap2));
//		sprite2.addComponent(new Owner(player2));
//		//sprite2.addComponent(new Position(new GamePoint(300, 250), 0));
//		sprite2.addComponent(new SoundEffect("data/sounds/Psyessr4.wav"));
//		sprite2.addComponent(new Images("images/characters/bahamut_right.png"));
//		sprite2.addComponent(new Speed(100));
//		//sprite2.addComponent(new Collidable(CollisionBoundType.IMAGE));
//		//sprite2.addComponent(new Selectable(SelectionBoundType.IMAGE));
//		sprite2.addComponent(new EventQueue(new LinkedList<>()));
//		sprite2.addComponent(new Attacker());
//		sprite2.addComponent(new Health(60));
//		
//		
//		
//		// spawned sprite
//		SpriteMakerModel child = new SpriteMakerModel();
//		LtubImage childImage = new LtubImage("images/characters/bahamut_left.png");
//		ImageSet childImageSet = new ImageSet(childImage);
//		Map<SkillType<? extends Skill>, Skill> childSkillMap = new HashMap<>();
//		child.addComponent(new GameBus());
//		child.addComponent(new SkillSet(childSkillMap));
//		child.addComponent(new Owner(player2));
//		//child.addComponent(new Position(new GamePoint(300, 50), 0));
//		child.addComponent(new SoundEffect("data/sounds/Psyessr4.wav"));
//		child.addComponent(new Images("images/characters/bahamut_left.png"));
//		child.addComponent(new Speed(200));
//		//child.addComponent(new Collidable(CollisionBoundType.IMAGE));
//		//child.addComponent(new Selectable(SelectionBoundType.IMAGE));
//		child.addComponent(new Range(128));
//		child.addComponent(new Attacker());
//		child.addComponent(new Health(200));
//		child.addComponent(new EventQueue(new LinkedList<>()));
////		child.emit(new QueueEvent(QueueEvent.ADD, new MoveEvent(MoveEvent.START_POSITION, child, 
////			new Target(new GamePoint(300,300)))));
//		
//		// The spawner
//		SpriteMakerModel spawner = new SpriteMakerModel();
//		Map<SkillType<? extends Skill>, Skill> spawnerSkillMap = new HashMap<>();
//		//spawnerSkillMap.put(BuildSkill.TYPE, new BuildSkill(child));
//		spawner.addComponent(new GameBus());
//		spawner.addComponent(new SkillSet(spawnerSkillMap));
//		spawner.addComponent(new Owner(player1));
//		//spawner.addComponent(new Position(new GamePoint(-100, -100), 0));
//		spawner.addComponent(new SoundEffect("data/sounds/Psyessr4.wav"));
//		spawner.addComponent(new Images("images/characters/tower2_resized.gif"));
//		spawner.addComponent(new Speed(200));
//		//spawner.addComponent(new Collidable(CollisionBoundType.IMAGE));
//		//spawner.addComponent(new Selectable(SelectionBoundType.IMAGE));
//		spawner.addComponent(new Range(128));
//		spawner.addComponent(new Attacker());
//		spawner.addComponent(new Health(200));
//		spawner.addComponent(new EventQueue(new LinkedList<>()));
//		addObject(sprite1);
		
		
		
		
		
		
		Player player1 = new Player("Player 1");
		Player player2 = new Player("Player 2");
		
		// building
		SpriteMakerModel building = new SpriteMakerModel();
		LtubImage buildingImage = new LtubImage("images/skills/build.png");
		ImageSet imageSetBuildSkill = new ImageSet(buildingImage);
		building.addComponent(new Images("images/skills/build.png"));
		//building.addComponent(new Selectable(SelectionBoundType.IMAGE));
		
		
		
		// sprite 1: the tower
		SpriteMakerModel sprite1 = new SpriteMakerModel();
		LtubImage image1 = new LtubImage("images/characters/tower2_resized.gif");
		ImageSet imageSet1 = new ImageSet(image1);
		Map<SkillType<? extends Skill>, Skill> skillMap1 = new HashMap<>();
		skillMap1.put(MoveSkill.TYPE, new MoveSkill());
		skillMap1.put(BuildSkill.TYPE, new BuildSkill(building));
		FireProjectileSkill fireSkill1 = new FireProjectileSkill();
		fireSkill1.setCooldown(3); // add cooldown to the fireProjectilSkill
		sprite1.addComponent(new Cooldown());
		skillMap1.put(FireProjectileSkill.TYPE, fireSkill1);
		sprite1.addSkill(new MoveSkill());
		sprite1.addSkill(new BuildSkill(building));
		sprite1.addSkill(fireSkill1);
		sprite1.addComponent(new GameBus());
		sprite1.addComponent(new SkillSet(skillMap1));
		sprite1.addComponent(new Owner(player1));
		sprite1.addComponent(new Position(new GamePoint(200, 100), 0));
		sprite1.addComponent(new SoundEffect("data/sounds/Psyessr4.wav"));
		sprite1.addComponent(new Images("images/characters/tower2_resized.gif"));
		sprite1.addComponent(new Speed(200));
		sprite1.addComponent(new Collidable(CollisionBoundType.IMAGE));
		sprite1.addComponent(new Selectable(SelectionBoundType.IMAGE));
		sprite1.addComponent(new Range(200));
		sprite1.addComponent(new Attacker());
		sprite1.addComponent(new Health(200));
		sprite1.addComponent(new EventQueue(new LinkedList<>()));
		sprite1.addComponent(new RangeShootingAI());
			
		// sprite 2
		SpriteMakerModel sprite2 = new SpriteMakerModel();
		LtubImage image2 = new LtubImage("images/characters/bahamut_right.png");
		ImageSet imageSet2 = new ImageSet(image2);
		Map<SkillType<? extends Skill>, Skill> skillMap2 = new HashMap<>();
		skillMap2.put(MoveSkill.TYPE, new MoveSkill());
		skillMap2.put(FireProjectileSkill.TYPE, new FireProjectileSkill());
		sprite2.addComponent(new GameBus());
		sprite2.addComponent(new SkillSet(skillMap2));
		sprite2.addComponent(new Owner(player2));
		//sprite2.addComponent(new Position(new GamePoint(300, 250), 0));
		sprite2.addComponent(new SoundEffect("data/sounds/Psyessr4.wav"));
		sprite2.addComponent(new Images("images/characters/bahamut_right.png"));
		sprite2.addComponent(new Speed(100));
		//sprite2.addComponent(new Collidable(CollisionBoundType.IMAGE));
		//sprite2.addComponent(new Selectable(SelectionBoundType.IMAGE));
		sprite2.addComponent(new EventQueue(new LinkedList<>()));
		sprite2.addComponent(new Attacker());
		sprite2.addComponent(new Health(60));
		
		
		
		// spawned sprite
		SpriteMakerModel child = new SpriteMakerModel();
		LtubImage childImage = new LtubImage("images/characters/bahamut_left.png");
		ImageSet childImageSet = new ImageSet(childImage);
		Map<SkillType<? extends Skill>, Skill> childSkillMap = new HashMap<>();
		child.addComponent(new GameBus());
		child.addComponent(new SkillSet(childSkillMap));
		child.addComponent(new Owner(player2));
		//child.addComponent(new Position(new GamePoint(300, 50), 0));
		child.addComponent(new SoundEffect("data/sounds/Psyessr4.wav"));
		child.addComponent(new Images("images/characters/bahamut_left.png"));
		child.addComponent(new Speed(200));
		//child.addComponent(new Collidable(CollisionBoundType.IMAGE));
		//child.addComponent(new Selectable(SelectionBoundType.IMAGE));
		child.addComponent(new Range(128));
		child.addComponent(new Attacker());
		child.addComponent(new Health(200));
		child.addComponent(new EventQueue(new LinkedList<>()));
//		child.emit(new QueueEvent(QueueEvent.ADD, new MoveEvent(MoveEvent.START_POSITION, child, 
//			new Target(new GamePoint(300,300)))));
		
		// The spawner
		SpriteMakerModel spawner = new SpriteMakerModel();
		Map<SkillType<? extends Skill>, Skill> spawnerSkillMap = new HashMap<>();
		//spawnerSkillMap.put(BuildSkill.TYPE, new BuildSkill(child));
		spawner.addComponent(new GameBus());
		spawner.addComponent(new SkillSet(spawnerSkillMap));
		spawner.addComponent(new Owner(player1));
		//spawner.addComponent(new Position(new GamePoint(-100, -100), 0));
		spawner.addComponent(new SoundEffect("data/sounds/Psyessr4.wav"));
		spawner.addComponent(new Images("images/characters/tower2_resized.gif"));
		spawner.addComponent(new Speed(200));
		//spawner.addComponent(new Collidable(CollisionBoundType.IMAGE));
		//spawner.addComponent(new Selectable(SelectionBoundType.IMAGE));
		spawner.addComponent(new Range(128));
		spawner.addComponent(new Attacker());
		spawner.addComponent(new Health(200));
		spawner.addComponent(new EventQueue(new LinkedList<>()));
		addObject(sprite1);
		//addObject(sprite2);
		//addObject(spawner);
		
//		List<Sprite> spritesToAdd = new ArrayList<>();
//		spritesToAdd.add(sprite1);
//		spritesToAdd.add(sprite2);
////		spritesToAdd.add(child);
//		spritesToAdd.add(spawner);
		
//		addObject(sprite2);
//		addObject(spawner);
		
	}
	/**
	 * Add a created sprite to the screen object selector
	 * 
	 * @param screenObject
	 *            the sprite to add to the HBox
	 */
	public void addObject(SpriteMakerModel screenObject) {
		for (Component c : screenObject.getComponents().values()) {
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
			String imageName = db.getString();
			GamePoint gameCoords = new GamePoint(e.getScreenX(), e.getScreenY());
			for (Pair<String, Image> p : myScreenObjects.keySet()) {
				String iName = p.getKey();
				if (imageName.equals(iName)) {
					XStreamHandler xstream = new XStreamHandler();
					File tempSpriteFile = new File("data/tempSprites/tempSprite.xml");
					xstream.saveToFile(myScreenObjects.get(p), tempSpriteFile);
					SpriteMakerModel xmlSprite = (SpriteMakerModel) xstream.getAttributeFromFile(tempSpriteFile);
					xmlSprite.addComponent(new Position(gameCoords, 0)); //heading 0 because all sprites default to this
					myScreenData.addObjectData(xmlSprite);
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
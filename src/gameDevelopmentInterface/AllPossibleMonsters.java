package gameDevelopmentInterface;

import java.util.ArrayList;
import java.util.List;

import data.DeveloperData;
import data.SpriteMakerModel;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import newengine.sprite.components.EventQueue;
import newengine.sprite.components.GameBus;
import newengine.sprite.components.Health;
import newengine.sprite.components.Images;
import newengine.sprite.components.PathFollower;
import newengine.sprite.components.SkillSet;
import newengine.sprite.components.Speed;

public class AllPossibleMonsters extends ScrollPane {
	private DeveloperData myData;
	private List<String> monstersAlreadyInBox;
	private SpawnerCreation mySpawnerInfo;
	private SpawnerInfoPane mySpawnerInfoPane;
	private VBox monsterImages;
	
	public AllPossibleMonsters(SpawnerCreation spawnerInfo, DeveloperData data, SpawnerInfoPane spawnerInfoPane) {
		mySpawnerInfoPane = spawnerInfoPane;
		mySpawnerInfo = spawnerInfo;
		myData = data;
		monstersAlreadyInBox = new ArrayList<String>();
		monsterImages = new VBox();	
		this.setContent(monsterImages);
	}
	
	public void getMonstersOnScreen() {
		this.getChildren().clear();
		List<SpriteMakerModel> allSprites = 
				new ArrayList<SpriteMakerModel>(myData.getScreenSprites().getSpriteMakerModels());
		List<SpriteMakerModel> onlyMonsters = new ArrayList<>();
		for (SpriteMakerModel possibleMonster : allSprites) {
			if (isMonster(possibleMonster)) {
				onlyMonsters.add(possibleMonster);			
			}
		}
		for (SpriteMakerModel monster : onlyMonsters) {
			if (!monstersAlreadyInBox.contains(monster.getSpriteName())) {
				monstersAlreadyInBox.add(monster.getSpriteName());
				Images imageComp = (Images) monster.getComponentByType(Images.TYPE);
				ImageView iv = new ImageView(imageComp.image().getFXImage());
				iv.setFitWidth(100);
				iv.setFitHeight(100);
				iv.setOnMouseClicked(click -> setCurrentMonster(monster, new ImageView(imageComp.image().getFXImage())));
				monsterImages.getChildren().add(iv);
			}
		}
	}

	private void setCurrentMonster(SpriteMakerModel monster, ImageView iv) {
		mySpawnerInfo.setCurrentMonsterToSpawn(monster);
		mySpawnerInfoPane.setImage((Images) monster.getComponentByType(Images.TYPE));
	}
	
	private boolean isMonster(SpriteMakerModel possibleMonster) {
		return (possibleMonster.getComponentByType(Images.TYPE) != null &&
				possibleMonster.getComponentByType(Speed.TYPE) != null &&
				possibleMonster.getComponentByType(Health.TYPE) != null &&
				possibleMonster.getComponentByType(PathFollower.TYPE) != null &&
				possibleMonster.getComponentByType(EventQueue.TYPE) != null &&
				possibleMonster.getComponentByType(GameBus.TYPE) != null &&
				possibleMonster.getComponentByType(SkillSet.TYPE) != null);
	}
}

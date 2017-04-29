package gameDevelopmentInterface;

import java.util.HashMap;
import java.util.Map;

import data.DeveloperData;
import data.SpriteMakerModel;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Pair;
import newengine.sprite.components.Images;
import utilities.XStreamHandler;

public class MonsterAdder extends HBox {
	private DeveloperData myDeveloperData;
	private Map<Pair<String, Image>, SpriteMakerModel> myScreenObjects = new HashMap<Pair<String, Image>, SpriteMakerModel>();
	private Button addMonster = new Button("Add a monster");
	private XStreamHandler xtream = new XStreamHandler();

	public MonsterAdder(DeveloperData developerData) {
		myDeveloperData = developerData;
		addMonster.setOnAction(e -> {
			
		});
//		myDeveloperData.getScreenSprites().getSpriteMakerModels().addListener(new ListChangeListener<SpriteMakerModel>() {
//					@Override
//					public void onChanged(@SuppressWarnings("rawtypes") ListChangeListener.Change change) {
//						addMonsters();
//					}
//				});
		this.getChildren().add(addMonster);
		addMonsters();
	}

	private void addMonsters() {
		for (SpriteMakerModel possibleMonster : myDeveloperData.getScreenSprites().getSpriteMakerModels()) {
			if (possibleMonster.getComponentByType(Images.TYPE) != null) {
				Images imageComponent = (Images) possibleMonster.getComponentByType(Images.TYPE);
				Image spriteImage = imageComponent.image().getFXImage();
				ImageView spriteImageView = new ImageView(spriteImage);
				spriteImageView.setFitHeight(100);
				spriteImageView.setFitWidth(100);
				spriteImageView.setOnMousePressed(e -> addMonsterToSpawner(spriteImageView));
				myScreenObjects.put(new Pair<String, Image>(imageComponent.image().getFileName(), spriteImage), possibleMonster);
				this.getChildren().add(spriteImageView);
			}
		}
	}
	
	private void addMonsterToSpawner(ImageView source) {
		source.setOnMouseClicked(e -> {
			
		});
	}

}

//package gameDevelopmentInterface;
//
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import utilities.XStreamHandler;
//
//public class TowerDefenseControlPanel extends VBox {
//	private Button newSprite = new Button("Make new sprite!");
//	private HBox myPathRow = new HBox();
//	private Button pathStart = new Button("Start Path");
//	private Button pathEnd = new Button("End/Save Path");
//	private Button load = new Button("Load");
//	private Button save = new Button("Save");
//	private ScreenModelCreator myScreenModel;
//	private PathCreator myPathCreator; 
//	private XStreamHandler xstreamHandler = new XStreamHandler();
//
//	public TowerDefenseControlPanel(ScreenModelCreator smc) {
//		myScreenModel = smc;
//		myPathCreator = new PathCreator(myScreenModel);
//		myPathRow.getChildren().addAll(pathStart, pathEnd);
//		makeButtons();
//		this.getChildren().addAll(newSprite, myPathRow, load, save);
//	}
//
//	private void makeButtons() {
//		newSprite.setOnAction(e -> {
//			Stage popup = new Stage();
//			NovelSpriteCreator spriteCreator = new NovelSpriteCreator();
//			Scene popupScene = new Scene(spriteCreator);
//			popup.setScene(popupScene);
//			popup.show();
//		});
//		pathStart.setOnAction(e -> {
//			myPathCreator.getReplacementPath().clear();
//			myPathCreator.makePath();
//		});
//		pathEnd.setOnAction(e -> {
//			myPathCreator.replacePath();
//			//TODO pop up a new screen to associate newly made path with TowerMonsterSpawner
//		});
//		load.setOnAction(e -> {
//			myScreenModel.getScreenData().setObjectsOnScreen(xstreamHandler.getScreenModelFile());
//		});
//		save.setOnAction(e -> {
//			xstreamHandler.saveToFile(myScreenModel.getScreenData().getDataToSave());
//		});
//	}
//
//}
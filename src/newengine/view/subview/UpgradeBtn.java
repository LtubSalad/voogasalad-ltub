package newengine.view.subview;

import bus.EventBus;
import gamedata.AuthDataTranslator;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import newengine.events.SpriteModelEvent;
import newengine.events.sprite.ChangeHealthEvent;
import newengine.events.sprite.UpgradeEvent;
import newengine.events.stats.ChangeWealthEvent;
import newengine.events.stats.InsufficientGoldEvent;
import newengine.model.PlayerStatsModel.WealthType;
import newengine.sprite.Sprite;
import newengine.sprite.components.GameBus;
import newengine.sprite.components.Health;
import newengine.sprite.components.Owner;
import newengine.sprite.components.Upgrade;

public class UpgradeBtn extends Button{

	private VBox box;
	private Sprite sprite;
	private Button button;
	private boolean canUpdate = true;

	public UpgradeBtn(){
		box = new VBox();
		button = new Button("UPGRADE THIS SPRITE");
		//initHandlers();
	}

	private void initHandlers() {
		sprite.getComponent(GameBus.TYPE).get().getGameBus().on(InsufficientGoldEvent.ANY, e -> {
			canUpdate = false;
		});
	}
	
	public void render(Sprite sprite) {
		if (this.sprite == sprite) {return;}
		sprite.getComponent(GameBus.TYPE).get().getGameBus().on(InsufficientGoldEvent.ANY, e -> {
			canUpdate = false;
		});
		box.getChildren().clear();
		this.sprite = sprite;
		button.setMinHeight(25);
		button.setMinWidth(175);
		button.setOnMouseClicked(e -> {
			Stage msgStage = new Stage();
			VBox root = new VBox();
			Scene scene = new Scene(root);
			Text  text = new Text("Are you sure you want to upgrade this sprite? It will cost you 55 gold.");
			HBox options = new HBox();
			Button yes = new Button ("yes");
			yes.setOnAction(f -> {
				System.out.println(sprite.getComponent(Owner.TYPE).isPresent());
				EventBus bus = sprite.getComponent(GameBus.TYPE).get().getGameBus();
				System.out.println(sprite.getComponent(Owner.TYPE).get().player().getName());
//				bus.emit(new ChangeWealthEvent
//						(ChangeWealthEvent.CHANGE, sprite.getComponent(Owner.TYPE).get().player(), WealthType.GOLD, sprite.getComponent(Upgrade.TYPE).get().getCost(), canUpdate));
				if (canUpdate) {
					sprite.getComponent(Health.TYPE).ifPresent((health) -> {
						sprite.emit(new UpgradeEvent(UpgradeEvent.RESET, sprite, sprite.getComponent(Health.TYPE).get().getInitHealth()));
					});
					sprite.emit(new UpgradeEvent(UpgradeEvent.DOUBLE, sprite));
//					AuthDataTranslator adt = new AuthDataTranslator(sprite.getComponent(Upgrade.TYPE).get().getSMM());
//					bus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, adt.getSprite()));
//					bus.emit(new SpriteModelEvent(SpriteModelEvent.REMOVE, sprite));
				}
				msgStage.close();
			});
			Button no = new Button("no");
			no.setOnAction(g -> {
				msgStage.close();
			});
			options.getChildren().addAll(yes, no);
			root.getChildren().addAll(text, options);
			msgStage.setScene(scene);
			msgStage.show();
		});

		box.getChildren().add(button);
	}

	public VBox getBox(){
		return box;
	}

	public Button getButton(){
		return button;
	}


}

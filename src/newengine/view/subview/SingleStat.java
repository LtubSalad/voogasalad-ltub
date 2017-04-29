package newengine.view.subview;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import newengine.events.stats.ChangeWealthEvent;
import newengine.model.PlayerStatsModel.WealthType;
import newengine.sprite.Sprite;
import newengine.sprite.components.GameBus;
import newengine.sprite.components.Owner;

public class SingleStat extends HBox {
	
	private Sprite sprite;
	private int cost = 10;
	private String name;
	private Text label;
	private Text value;
	private Button upgradeBtn;
	
	public SingleStat(String name, Object value, Sprite sprite){
		this.sprite = sprite;
		this.name = name;
		this.label = new Text(name + ": ");
		this.value = new Text("" + value);
		this.setMaxWidth(200);
		this.setMaxHeight(25);
		this.getChildren().addAll(this.label, this.value);
	}
	
	public void addUpgradeBtn() {
		upgradeBtn = new Button();
		upgradeBtn.setText("UPGRADE");
		upgradeBtn.setOnAction(e -> {
			Stage msgStage = new Stage();
			VBox root = new VBox();
			Scene scene = new Scene(root);
			Text  text = new Text("Are you sure you want to upgrade " + name + "? It will cost you 10 gold.");
			HBox options = new HBox();
			Button yes = new Button ("yes");
			yes.setOnAction(f -> {
				sprite.getComponent(GameBus.TYPE).get().getGameBus().emit(new ChangeWealthEvent
						(ChangeWealthEvent.CHANGE, sprite.getComponent(Owner.TYPE).get().player(), WealthType.GOLD, -10));
				msgStage.close();
			});
			Button no = new Button("no");
			no.setOnAction(g -> {
				msgStage.close();
			});
			options.getChildren().add(yes);
			root.getChildren().addAll(text, options);
			msgStage.setScene(scene);
			msgStage.show();
		});
		this.getChildren().add(upgradeBtn);
	}
	
	public void removeUpgradeBtn() {
		this.getChildren().remove(upgradeBtn);
	}

}

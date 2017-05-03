package newengine.app;

import java.util.ArrayList;
import java.util.List;

import bus.EventBus;
import commons.point.GamePoint;
import javafx.application.Application;
import javafx.stage.Stage;
import newengine.events.GameInitializationEvent;
import newengine.events.SpriteModelEvent;
import newengine.events.camera.CameraEvent;
import newengine.events.collision.CollisionEvent;
import newengine.events.conditions.SetEndConditionEvent;
import newengine.events.player.MainPlayerEvent;
import newengine.events.sound.SoundEvent;
import newengine.events.stats.ChangeLivesEvent;
import newengine.events.stats.ChangeWealthEvent;
import newengine.managers.conditions.GoldMinimumCondition;
import newengine.managers.conditions.NoLivesCondition;
import newengine.model.PlayerStatsModel.WealthType;
import newengine.player.Player;
import newengine.sprite.Sprite;
import newengine.sprite.components.Collidable;
import newengine.sprite.components.Collidable.CollisionBoundType;
import newengine.sprite.components.GameBus;
import newengine.sprite.components.Images;
import newengine.sprite.components.Position;
import newengine.sprite.components.Selectable;
import newengine.sprite.components.Selectable.SelectionBoundType;
import newengine.sprite.components.Speed;

public class App extends Application {

	@Override
	public void start(Stage stage) throws Exception {

		Game game = new Game();
		Player player1 = new Player("Player 1");
		EventBus bus = game.getBus();

		List<Sprite> spritesToAdd = new ArrayList<>();
		
		double[][] points = new double[][] {
			{0, 0}, {100, 0}, {200,0}, {200, -100}, {200, -200}, {200, -300},
			{200, -400}, {200, -500}, {400, 0}, {400, -100}, {400, -200}, {400, -300}, 
			{400, -500}, {500, 0}, {600, 0}, {700, 0}, 
			{0, 200}, {100, 200}, {200,200}, {300, 200}, {400, 200}, {500, 200}, {600, 200}, {700, 200}, 
		};
		
		for (double[] p : points) {
			GamePoint gamePoint = new GamePoint(p[0],p[1]);
			Sprite tree = new Sprite();
			tree.addComponent(new Position(gamePoint));
			tree.addComponent(new Images("images/characters/tree.png"));
			tree.addComponent(new Collidable(CollisionBoundType.IMAGE));
			spritesToAdd.add(tree);
		}
		
		Sprite car = new Sprite();
		car.addComponent(new Position(new GamePoint(0,100)));
		car.addComponent(new Speed(0, 30));
		car.addComponent(new Images("images/characters/car.png"));
		car.addComponent(new Selectable(SelectionBoundType.IMAGE));
		car.addComponent(new Collidable(CollisionBoundType.IMAGE));
		car.addComponent(new GameBus());
		car.on(CollisionEvent.ANY, (e) -> {
			car.getComponent(GameBus.TYPE).get().getGameBus().emit(
					new SoundEvent(SoundEvent.SOUND_EFFECT, "data/sounds/Alpissd1.wav"));
		});
		
		spritesToAdd.add(car);
		
		bus.on(GameInitializationEvent.ANY, (e) -> {
			bus.emit(new SoundEvent(SoundEvent.BACKGROUND_MUSIC, "data/sounds/01-dark-covenant.mp3"));
			bus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, spritesToAdd));
			bus.emit(new MainPlayerEvent(player1));
			bus.emit(new ChangeLivesEvent(ChangeLivesEvent.SET, player1, 3));
			bus.emit(new ChangeWealthEvent(ChangeWealthEvent.CHANGE, player1, WealthType.GOLD, 100));
			bus.emit(new SetEndConditionEvent(SetEndConditionEvent.SETWIN, new GoldMinimumCondition(1000)));
			bus.emit(new SetEndConditionEvent(SetEndConditionEvent.SETLOSE, new NoLivesCondition()));
			bus.emit(new CameraEvent(CameraEvent.LOCK));
		});
		
		stage.setScene(game.getScene());
		game.start();
		stage.show();
		
	}

	public static void main(String[] args) {
		Application.launch(args);
	}



}

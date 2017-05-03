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
		
		Sprite tree1 = new Sprite();
		tree1.addComponent(new Position(new GamePoint(0,0)));
		tree1.addComponent(new Images("images/characters/tree.png"));
		tree1.addComponent(new Collidable(CollisionBoundType.IMAGE));
		Sprite tree2 = new Sprite();
		tree2.addComponent(new Position(new GamePoint(100,0)));
		tree2.addComponent(new Images("images/characters/tree.png"));
		tree2.addComponent(new Collidable(CollisionBoundType.IMAGE));
		Sprite tree3 = new Sprite();
		tree3.addComponent(new Position(new GamePoint(200,0)));
		tree3.addComponent(new Images("images/characters/tree.png"));
		tree3.addComponent(new Collidable(CollisionBoundType.IMAGE));
		Sprite tree4 = new Sprite();
		tree4.addComponent(new Position(new GamePoint(200,-100)));
		tree4.addComponent(new Images("images/characters/tree.png"));
		tree4.addComponent(new Collidable(CollisionBoundType.IMAGE));
		Sprite tree5 = new Sprite();
		tree5.addComponent(new Position(new GamePoint(200,-200)));
		tree5.addComponent(new Images("images/characters/tree.png"));
		tree5.addComponent(new Collidable(CollisionBoundType.IMAGE));
		Sprite tree6 = new Sprite();
		tree6.addComponent(new Position(new GamePoint(200,-300)));
		tree6.addComponent(new Images("images/characters/tree.png"));
		tree6.addComponent(new Collidable(CollisionBoundType.IMAGE));
		Sprite tree7 = new Sprite();
		tree7.addComponent(new Position(new GamePoint(200,-400)));
		tree7.addComponent(new Images("images/characters/tree.png"));
		tree7.addComponent(new Collidable(CollisionBoundType.IMAGE));
		Sprite tree8 = new Sprite();
		tree8.addComponent(new Position(new GamePoint(200,-500)));
		tree8.addComponent(new Images("images/characters/tree.png"));
		tree8.addComponent(new Collidable(CollisionBoundType.IMAGE));
		Sprite tree9 = new Sprite();
		tree9.addComponent(new Position(new GamePoint(400,0)));
		tree9.addComponent(new Images("images/characters/tree.png"));
		tree9.addComponent(new Collidable(CollisionBoundType.IMAGE));
		Sprite tree10 = new Sprite();
		tree10.addComponent(new Position(new GamePoint(400,-100)));
		tree10.addComponent(new Images("images/characters/tree.png"));
		tree10.addComponent(new Collidable(CollisionBoundType.IMAGE));
		Sprite tree11 = new Sprite();
		tree11.addComponent(new Position(new GamePoint(400,-200)));
		tree11.addComponent(new Images("images/characters/tree.png"));
		tree11.addComponent(new Collidable(CollisionBoundType.IMAGE));
		Sprite tree12 = new Sprite();
		tree12.addComponent(new Position(new GamePoint(400,-300)));
		tree12.addComponent(new Images("images/characters/tree.png"));
		tree12.addComponent(new Collidable(CollisionBoundType.IMAGE));
		Sprite tree13 = new Sprite();
		tree13.addComponent(new Position(new GamePoint(400,-400)));
		tree13.addComponent(new Images("images/characters/tree.png"));
		tree13.addComponent(new Collidable(CollisionBoundType.IMAGE));
		Sprite tree14 = new Sprite();
		tree14.addComponent(new Position(new GamePoint(400,-500)));
		tree14.addComponent(new Images("images/characters/tree.png"));
		tree14.addComponent(new Collidable(CollisionBoundType.IMAGE));
		Sprite tree15 = new Sprite();
		tree15.addComponent(new Position(new GamePoint(500,0)));
		tree15.addComponent(new Images("images/characters/tree.png"));
		tree15.addComponent(new Collidable(CollisionBoundType.IMAGE));
		Sprite tree16 = new Sprite();
		tree16.addComponent(new Position(new GamePoint(600,0)));
		tree16.addComponent(new Images("images/characters/tree.png"));
		tree16.addComponent(new Collidable(CollisionBoundType.IMAGE));
		Sprite tree17 = new Sprite();
		tree17.addComponent(new Position(new GamePoint(700,0)));
		tree17.addComponent(new Images("images/characters/tree.png"));
		tree17.addComponent(new Collidable(CollisionBoundType.IMAGE));
		
		Sprite tree18 = new Sprite();
		tree18.addComponent(new Position(new GamePoint(0,200)));
		tree18.addComponent(new Images("images/characters/tree.png"));
		tree18.addComponent(new Collidable(CollisionBoundType.IMAGE));
		Sprite tree19 = new Sprite();
		tree19.addComponent(new Position(new GamePoint(100,200)));
		tree19.addComponent(new Images("images/characters/tree.png"));
		tree19.addComponent(new Collidable(CollisionBoundType.IMAGE));
		Sprite tree20 = new Sprite();
		tree20.addComponent(new Position(new GamePoint(200,200)));
		tree20.addComponent(new Images("images/characters/tree.png"));
		tree20.addComponent(new Collidable(CollisionBoundType.IMAGE));
		Sprite tree21 = new Sprite();
		tree21.addComponent(new Position(new GamePoint(300,200)));
		tree21.addComponent(new Images("images/characters/tree.png"));
		tree21.addComponent(new Collidable(CollisionBoundType.IMAGE));
		Sprite tree22 = new Sprite();
		tree22.addComponent(new Position(new GamePoint(400,200)));
		tree22.addComponent(new Images("images/characters/tree.png"));
		tree22.addComponent(new Collidable(CollisionBoundType.IMAGE));
		Sprite tree23 = new Sprite();
		tree23.addComponent(new Position(new GamePoint(500,200)));
		tree23.addComponent(new Images("images/characters/tree.png"));
		tree23.addComponent(new Collidable(CollisionBoundType.IMAGE));
		Sprite tree24 = new Sprite();
		tree24.addComponent(new Position(new GamePoint(600,200)));
		tree24.addComponent(new Images("images/characters/tree.png"));
		tree24.addComponent(new Collidable(CollisionBoundType.IMAGE));
		Sprite tree25 = new Sprite();
		tree25.addComponent(new Position(new GamePoint(700,200)));
		tree25.addComponent(new Images("images/characters/tree.png"));
		tree25.addComponent(new Collidable(CollisionBoundType.IMAGE));
		
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
		
		List<Sprite> spritesToAdd = new ArrayList<>();
		spritesToAdd.add(tree1);
		spritesToAdd.add(tree2);
		spritesToAdd.add(tree3);
		spritesToAdd.add(tree4);
		spritesToAdd.add(tree5);
		spritesToAdd.add(tree6);
		spritesToAdd.add(tree7);
		spritesToAdd.add(tree8);
		spritesToAdd.add(tree9);
		spritesToAdd.add(tree10);
		spritesToAdd.add(tree11);
		spritesToAdd.add(tree12);
		spritesToAdd.add(tree13);
		spritesToAdd.add(tree14);
		spritesToAdd.add(tree15);
		spritesToAdd.add(tree16);
		spritesToAdd.add(tree17);
		spritesToAdd.add(tree18);
		spritesToAdd.add(tree19);
		spritesToAdd.add(tree20);
		spritesToAdd.add(tree21);
		spritesToAdd.add(tree22);
		spritesToAdd.add(tree23);
		spritesToAdd.add(tree24);
		spritesToAdd.add(tree25);
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

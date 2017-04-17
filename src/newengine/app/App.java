package newengine.app;

import java.util.ArrayList;
import java.util.List;

import bus.EventBus;
import commons.point.GamePoint;
import javafx.application.Application;
import javafx.stage.Stage;
import newengine.events.MapInitializationEvent;
import newengine.events.SpriteModelEvent;
import newengine.sprite.Sprite;
import newengine.sprite.components.Collidable;
import newengine.sprite.components.Collidable.CollisionBoundType;
import newengine.sprite.components.Images;
import newengine.sprite.components.Owner;
import newengine.sprite.components.Position;
import newengine.sprite.components.RangeDetector;
import newengine.sprite.components.Speed;
import newengine.sprite.player.Player;
import newengine.utils.image.ImageSet;
import newengine.utils.image.LtubImage;

public class App extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		
		Game game = new Game();

		Player player1 = new Player("Player 1");
		
		Sprite sprite1 = new Sprite();
		LtubImage image1 = new LtubImage("images/characters/bahamut_left.png");
		ImageSet imageSet1 = new ImageSet(image1);
		sprite1.addComponent(new Owner(player1));
		sprite1.addComponent(new Position(new GamePoint(200, 100)));
		sprite1.addComponent(new Images(imageSet1));
		sprite1.addComponent(new Speed(200));
		sprite1.addComponent(new Collidable(CollisionBoundType.IMAGE));
		sprite1.addComponent(new RangeDetector(128));
		
		List<Sprite> spritesToAdd = new ArrayList<>();
		spritesToAdd.add(sprite1);
		
		EventBus bus = game.getBus();
		bus.on(MapInitializationEvent.ANY, (e) -> {
			bus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, spritesToAdd));
			// TODO add other map elements to the game (like stats, buttons)
		});
		
		stage.setScene(game.getScene());
		game.start();
		stage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}

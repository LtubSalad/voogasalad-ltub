package newengine.timer;

import bus.EventBus;
import engine.app.GameFactory;
import engine.gameloop.GameLoop;
import javafx.application.Application;
import javafx.stage.Stage;
import newengine.debug.DebugManager;
import newengine.event.debug.SysPrintEvent;
import newengine.event.timer.PeriodicEvent;

public class GameTimerTest extends Application {


	@Override
	public void start(Stage primaryStage) throws Exception {
		
		GameFactory gameFactory = new GameFactory();
		EventBus bus = gameFactory.getBus();
		
		GameLoop gameLoop = gameFactory.createGameLoop();
		
		TimerManager timerManager = new TimerManager(bus);
		new DebugManager(bus);
		
		gameLoop.addLoopComponent((dt) -> timerManager.update(dt));
		
		
//		bus.emit(new DelayedEvent(10, () -> {
//			bus.emit(new SysPrintEvent("Hello Sandy!"));
//		}));
		
		bus.emit(new PeriodicEvent(10,1,() -> {
			bus.emit(new SysPrintEvent("Hello Sandy!"));
		}));
		
		System.out.println("Loop started");
		gameLoop.start();
		
	}
	

	public static void main(String[] args) {
		Application.launch(args);
	}
	
}

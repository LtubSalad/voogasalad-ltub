package newengine.managers.timer;

import bus.BasicEventBus;
import bus.EventBus;
import javafx.application.Application;
import javafx.stage.Stage;
import newengine.events.debug.SysPrintEvent;
import newengine.events.timer.PeriodicEvent;
import newengine.gameloop.FXGameLoop;
import newengine.gameloop.GameLoop;
import newengine.managers.debug.DebugManager;

public class GameTimerTest extends Application {


	@Override
	public void start(Stage primaryStage) throws Exception {
		
		EventBus bus = new BasicEventBus();
		
		GameLoop gameLoop = new FXGameLoop(bus);
		
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

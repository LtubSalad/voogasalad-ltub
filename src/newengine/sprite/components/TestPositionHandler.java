package newengine.sprite.components;

import bus.BusEventHandler;
import newengine.events.sound.SoundEvent;
import newengine.events.sprite.MoveEvent;
import newengine.sprite.Sprite;

public class TestPositionHandler implements BusEventHandler<MoveEvent> {

	private Sprite sprite;
	private TestPosition testPosition;
	
	TestPositionHandler(Sprite sprite, TestPosition testPosition) {
		this.sprite = sprite;
		this.testPosition = testPosition;
	}

	@Override
	public void handle(MoveEvent event) {
		System.out.println("Did it! 2");
		testPosition.moveTo(((MoveEvent) event).getTarget());
		sprite.getComponent(SoundEffect.TYPE).ifPresent((sound) -> {
			sprite.getComponent(GameBus.TYPE).ifPresent((bus) -> {
				bus.getGameBus().emit(new SoundEvent(SoundEvent.SOUND_EFFECT, sound.getMoveSoundFile()));
			});
		});
	}
	
	
	
}

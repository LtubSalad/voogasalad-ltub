package engine.sound;

import java.io.File;

import bus.EventBus;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class FXSoundManager implements SoundManager {

	private EventBus bus;
	
	public FXSoundManager(EventBus bus) {
		this.bus = bus;
		initHandlers();
	}

	private void initHandlers() {
		bus.on(SoundEvent.ANY, (e) -> {
			play(e.getSoundFileName());
		});
	}
	
	@Override
	public void play(String soundFileName) {
		Media sound = new Media(new File(soundFileName).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}
	

	
}

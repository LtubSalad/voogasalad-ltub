package newengine.managers.sound;

import java.io.File;
import static commons.RunningMode.DEV_MODE;
import bus.EventBus;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import newengine.events.sound.SoundEvent;

public class SoundManager {

	private EventBus bus;
	
	public SoundManager(EventBus bus) {
		this.bus = bus;
		initHandlers();
	}

	private void initHandlers() {
		bus.on(SoundEvent.ANY, (e) -> {
			play(e.getSoundFileName());
		});
	}
	
	public void play(Sound sound) {
		
	}
	
	public void play(String soundFileName) {
		Media sound = new Media(new File(soundFileName).toURI().toString());
		try {
			MediaPlayer mediaPlayer = new MediaPlayer(sound);
			mediaPlayer.play();
		}catch (MediaException e) {
			if (DEV_MODE) {
				e.printStackTrace();
			}
		}		
	}
	

	
}

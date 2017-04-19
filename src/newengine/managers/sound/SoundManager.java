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
	private MediaPlayer bgm;
	private double bgmVolume = 0.5;
	private MediaPlayer soundEffect;
	private double soundEffectVolumn = 1.0;
	
	
	public SoundManager(EventBus bus) {
		this.bus = bus;
		initHandlers();
	}

	private void initHandlers() {
		bus.on(SoundEvent.SOUND_EFFECT, (e) -> {
			playSoundEffect(e.getSoundFileName());
		});
		bus.on(SoundEvent.BACKGROUND_MUSIC, (e) -> {
			playBGM(e.getSoundFileName());
		});
	}
	
	private void playBGM(String soundFileName) {
		Media sound = new Media(new File(soundFileName).toURI().toString());
		try {
			bgm = new MediaPlayer(sound);
			bgm.setVolume(bgmVolume);
			bgm.play();
		}catch (MediaException e) {
			if (DEV_MODE) {
				e.printStackTrace();
			}
		}
	}
	
	private void playSoundEffect(String soundFileName) {
		Media sound = new Media(new File(soundFileName).toURI().toString());
		try {
			soundEffect = new MediaPlayer(sound);
			soundEffect.setVolume(soundEffectVolumn);
			soundEffect.play();
		}catch (MediaException e) {
			if (DEV_MODE) {
				e.printStackTrace();
			}
		}		
	}
	

	
}

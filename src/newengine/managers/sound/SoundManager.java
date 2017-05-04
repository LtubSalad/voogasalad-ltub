package newengine.managers.sound;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import bus.EventBus;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import newengine.events.sound.EngineSoundEvent;
import newengine.events.sound.SoundEvent;
import newengine.sprite.components.Speed;
import utilities.CustomAlert;

public class SoundManager {

	private double MIN_ENGINE_NOISE_RATE = 0.5;
	private double MIN_ENGINE_NOISE_VOLUME = 0.1;

	private EventBus bus;
	private MediaPlayer bgm;
	private double bgmVolume = 0.5;
	private MediaPlayer soundEffect;
	private double soundEffectVolumn = 1.0;
	private List<MediaPlayer> soundPlayers = new ArrayList<>();
	private URL engineNoiseURL = getClass().getClassLoader().getResource("sounds/engine_noise.wav");
	private AudioClip engineNoise = new AudioClip(engineNoiseURL.toString());

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
		bus.on(EngineSoundEvent.START_ENGINE, (e) -> {
			startEngineNoise();
		});
		bus.on(EngineSoundEvent.UPDATE_ENGINE, (e) -> {
			updateEngineNoise(e.getSpeed(), e.getAcceleration());
		});
	}

	private void playBGM(String soundFileName) {
		Media sound = new Media(new File(soundFileName).toURI().toString());
		try {
			bgm = new MediaPlayer(sound);
			bgm.setVolume(bgmVolume);
			bgm.play();
		} catch (MediaException e) {
			new CustomAlert(AlertType.ERROR, "Sound Manager Error").show();
		}
	}

	private void playSoundEffect(String soundFileName) {
		Media sound = new Media(new File(soundFileName).toURI().toString());
		try {
			MediaPlayer soundEffect = new MediaPlayer(sound);
			soundEffect.setVolume(soundEffectVolumn);
			soundEffect.play();
			soundPlayers.add(soundEffect);
		} catch (MediaException e) {
			new CustomAlert(AlertType.ERROR, "Sound Manager Error").show();
		}
	}

	private void startEngineNoise() {
		engineNoise.setCycleCount(AudioClip.INDEFINITE);
		engineNoise.setVolume(MIN_ENGINE_NOISE_VOLUME);
		engineNoise.setRate(MIN_ENGINE_NOISE_RATE);
		engineNoise.play();
	}

	private void updateEngineNoise(double speed, double acceleration) {
		engineNoise.setRate(6 * Math.abs(speed) / Speed.MAX_SPEED + MIN_ENGINE_NOISE_RATE);
		engineNoise.setVolume(0.9 * Math.abs(speed) / Speed.MAX_SPEED + MIN_ENGINE_NOISE_VOLUME);
	}

}

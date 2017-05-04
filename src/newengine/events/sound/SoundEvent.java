package newengine.events.sound;

import bus.BusEvent;
import bus.BusEventType;

public class SoundEvent extends BusEvent {

	public static final BusEventType<SoundEvent> BACKGROUND_MUSIC = new BusEventType<>(
			SoundEvent.class.getName() + "BACKGROUND_MUSIC");
	public static final BusEventType<SoundEvent> SOUND_EFFECT = new BusEventType<>(SoundEvent.class.getName() + "SOUND");
	private String soundFileName;

	public SoundEvent(BusEventType<? extends BusEvent> busEventType, String soundFileName) {
		super(busEventType);
		this.soundFileName = soundFileName;
	}

	public String getSoundFileName() {
		return soundFileName;
	}

}

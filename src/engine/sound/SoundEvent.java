package engine.sound;

import bus.BusEvent;
import bus.BusEventType;

public class SoundEvent extends BusEvent {

	public static final BusEventType<SoundEvent> ANY = new BusEventType<>("SOUND_EVENT");
	
	private String soundFileName;
	
	public SoundEvent(String soundFileName) {
		this(ANY, soundFileName);
	}
	
	public SoundEvent(BusEventType<? extends BusEvent> busEventType, String soundFileName) {
		super(busEventType);
		this.soundFileName = soundFileName;
	}

	public String getSoundFileName() {
		return soundFileName;
	}
	
	
}

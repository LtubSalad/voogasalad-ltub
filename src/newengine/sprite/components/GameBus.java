package newengine.sprite.components;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import bus.BusEventHandler;
import bus.EventBus;
import newengine.events.sprite.FireProjectileEvent;
import newengine.events.sprite.SetGameBusEvent;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class GameBus extends Component {

	public final static ComponentType<GameBus> TYPE = new ComponentType<>(GameBus.class.getName());

	@XStreamOmitField
	private EventBus gameBus;

	@Override
	public void afterAdded() {
		sprite.on(SetGameBusEvent.ANY, (e) -> {
			setGameBus(e.getGameBus());
		});
	}

	private void setGameBus(EventBus gameBus) {
		this.gameBus = gameBus;
	}
	public EventBus getGameBus() {
		return gameBus;
	}

	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}

	@Override
	public GameBus clone() {
		return new GameBus();
	}

	@Override
	public Object[] getParameters() {
		// TODO Auto-generated method stub
		return null;
	}
}

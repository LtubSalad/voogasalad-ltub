package examples.example5;

import eventbus.Bus;

public class MessageFilter {
	
	public MessageFilter() {
		initHandlers();
	}

	private boolean isCheatCode(String message) {
		// TODO
		return message.equals("whosyourdaddy");
	}
	
	// TODO: has to query some other parts
	private boolean isSinglePlayerMode() {
		return true;
	}
	
	private void triggerCheatCode(String message) {
		if (message.equals("whosyourdaddy")) {
			if (isSinglePlayerMode()) {
				Bus.bus().emit(new InvulnerableEvent());
			} 
		}
	}

	private void initHandlers() {
		Bus.bus().on(UserSendMessageEvent.RAW, (e) -> {
			String msg = e.getMessage();
			if (isCheatCode(msg)) {
				triggerCheatCode(msg);
			} else {
				Bus.bus().emit(new UserSendMessageEvent(UserSendMessageEvent.FILTERED, e.getMessage()));
			}
		});
	}

}

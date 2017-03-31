package eventbus.examples;

import eventbus.Bus;

public class EventBusTest {

	public static void main(String[] args) {
		Tony tony = new Tony();
		System.out.println(tony.getMessage());
		String messageToSend = "WoW";
		Bus.bus().emit(new HelloEvent(messageToSend));
		System.out.println(tony.getMessage());
	}
	
}
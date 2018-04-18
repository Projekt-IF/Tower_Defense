package graphical_Interface;

import network.Protocol;

public class TD_Client extends Client {

	private TextGui myGui;

	public TD_Client(String pServerIP, int pServerPort, TextGui tg) {
		super(pServerIP, pServerPort);
		this.myGui = tg;

	}

	public void processMessage(String pMessage) {
		String[] tags = pMessage.split(Protocol.SEPARATOR);
		String prefix = tags[0];
		if(tags[1] != null) {
		this.myGui.ausgeben(tags[1]);
		}
	}
}

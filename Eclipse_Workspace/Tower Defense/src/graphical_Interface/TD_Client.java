package graphical_Interface;

public class TD_Client extends Client {

	private TextGui myGui;

	public TD_Client(String pServerIP, int pServerPort, TextGui tg) {
		super(pServerIP, pServerPort);
		this.myGui = tg;

	}

	public void processMessage(String pMessage) {
		this.myGui.ausgeben(pMessage);
	}
}

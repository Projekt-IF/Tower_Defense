package network;

import network.Protocol;
///Server
public class Server_TD extends Server{

	public Server_TD(int pPort) {
		super(pPort);

		System.out.println("Server auf Port: " + pPort + " geöffnet!");
		
	}

	@Override
	public void processNewConnection(String pClientIP, int pClientPort) {
		System.out.println("Connected");
		String backMessage = "Connected!";
		this.send(pClientIP, pClientPort, backMessage);
		
	}

	@Override
	public void processMessage(String pClientIP, int pClientPort, String pMessage) {
		
		System.out.println("Server: Empfangen <" + pMessage + "> von " + pClientIP + "/" + pClientPort);
		String[] token = pMessage.split(Protocol.SEPARATOR);
		String prefix = token[0];

		String backMessage = "";
		int zahl;

		switch (prefix) {
		
		case Protocol.CS_HELLO_WORLD:
			backMessage = Protocol.SC_HELLO_WORLD + Protocol.SEPARATOR + "Hello!";
			break;
			
		case Protocol.CS_LOGIN:
			
			backMessage = Protocol.SC_LOGIN_CONFIRMED +  Protocol.SEPARATOR + "Login Successfull!";
			break;
		default:
			backMessage = Protocol.SC_SENDERRORMESSAGE + Protocol.SEPARATOR + "Error!";
			break;
		}
		System.out.println("Server: Gesendet :<" + backMessage + ">");

		this.send(pClientIP, pClientPort, backMessage);
//		if (prefix.equals(Protocol.CS_LOGOUT)) {
//			this.closeConnection(pClientIP, pClientPort);
//		}
		
	}

	@Override
	public void processClosingConnection(String pClientIP, int pClientPort) {
		System.out.println("Der Client mit der IP: " + pClientIP + " wird abgemeldet.");
		this.closeConnection(pClientIP, pClientPort);
		
	}
	
	public static void main(String[] args) {
		new Server_TD(51678);
	}

}

package game_Controller;

public class Clock {

	private boolean läuft = true;
	private boolean pausiert = false;

	public static void main(String args[]) {
		Clock c = new Clock();
		c.laufenderSpielRing();
	}

	public Clock() {

	}

	//Create a new Thread running the Game Loop
	public void laufenderSpielRing() {
		Thread ring = new Thread() {

			public void run() {
				spielRing();
			}

		};

		ring.start();
	}

	//main construct
	public void spielRing() {

		final double wiederholrate = 30.0;
		final int maxAktualisierungen = 1;

		
		
		while (läuft) {

			try {
				Thread.sleep((long) (maxAktualisierungen / wiederholrate * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (!pausiert) {
				
					aktualisierung();

				}
		}

	}

	//Integrate mechanics
	public void aktualisierung() {
		
		// System.out.println("Pathing Complete");
	}

}

package game_Controller;

//Begriffe und Abk�rzungen / Glossar:
//(bps = Bilder pro Sekunde, Part = Thread, X)

public class Clock {
	private int bps = 60;
	private int bZ�hler = 0;
	private boolean l�uft = true; // M�ssen geregelt werden.
	private boolean pausiert = false; // M�ssen geregelt werden

	public Clock() {

	}

	// Startet einen neuen Part. F�hrt die Methode spielRing() aus.
	public void laufenderSpielRing() {
		Thread ring = new Thread() {

			public void run() {
				spielRing();
			}

		};

		ring.start();
	}

	// Nur in einem anderen Thread ausf�hren!
	public void spielRing() {

		final double wiederholrate = 30.0;

		// Sch�tzt ein wie viel Zeit ben�tigt wird um die angepeilten bps zu erreichen!
		final double zeitZwischenAktualisierungen = 1000000000 / wiederholrate;
		final int maxAktualisierungen = 1;

		double lastUpdateTime = System.nanoTime();

		// Einfacher Weg um die bps festzustellen.
		int lastSecondTime = (int) (lastUpdateTime / 1000000000);

		// Hauptkonstrukt
		while (l�uft) {
			double now = System.nanoTime();
			int updateCount = 0;

			if (!pausiert) {

				// Soviele Aktualisierungen wie n�tig machen. (Wie viele das sein werden bereits
				// in Zeile 41 festgelegt(1 um visuelle Fehler zu vermeiden))
				while (now - lastUpdateTime > zeitZwischenAktualisierungen && updateCount < maxAktualisierungen) {
					aktualisierung();
					lastUpdateTime += zeitZwischenAktualisierungen;
					updateCount++;
				}

				// Aktualisierung der bereits erstellten Bilder.
				int thisSecond = (int) (lastUpdateTime / 1000000000);
				if (thisSecond > lastSecondTime) {
					System.out.println("NEW SECOND " + thisSecond + " " + bZ�hler);
					bps = bZ�hler;
					bZ�hler = 0;
					lastSecondTime = thisSecond;
				}

			}
		}

	}

	// Integration der Aktualisierungen der Spielmechanik!
	public void aktualisierung() {

	}

	public static void main(String args[]) {
		Clock c = new Clock();
		c.laufenderSpielRing();
	}

}

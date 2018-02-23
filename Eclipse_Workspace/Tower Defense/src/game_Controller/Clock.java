package game_Controller;

public class Clock {

	private int counter = 0;
	private boolean running = true;
	private boolean pause = false;

	public static void main(String args[]) {
		Clock c = new Clock();
		c.runningLoop();
	}

	public Clock() {

	}

	// This will run the spielRing method in an other Thread.
	public void runningLoop() {
		Thread loop = new Thread() {

			public void run() {
				gameLoop();
			}

		};

		loop.start();
	}

	// ONLY run this in an other thread!
	public void gameLoop() {

		final double refreshRate = 60.0;
		final int maxUpdates = 1;

		// Main part
		while (running) {

			try {
				Thread.sleep((long) (maxUpdates / refreshRate * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (!pause) {

				update();

			}
		}

	}

	// Update game mechanics
	public void update() {
		
		System.out.println("Update" + " " +counter);
		counter = counter +1;
		if(counter > 59){
			
			System.out.println("Update complete" + " " +counter);
			counter = counter - 60;
		}
	}

}
package game_Controller;

import java.util.Timer;
import java.util.TimerTask;

public class Ingame_Clock {

	@SuppressWarnings("unused")
	private long start;
	@SuppressWarnings("unused")
	private long end;
	@SuppressWarnings("unused")
	private long elapsedTime;

	private String string;

	public Ingame_Clock() {
		string = "Every 5 Seconds";
	}

	public void timer_FiveSec() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				System.out.println(string);
			}
		}, 0, 5000);
	}

	private void changeString() {
		Timer timwe = new Timer();
		timwe.schedule(new TimerTask() {

			@Override
			public void run() {
				string = "Changed!";
			}
		}, 15001);

	}

	public static void main(String args[]) {
		Ingame_Clock iC = new Ingame_Clock();
		iC.timer_FiveSec();
		iC.changeString();
	}
}

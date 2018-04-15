package utility;

import java.util.Timer;
import java.util.TimerTask;

import objects.Tower;

public class TowerCooldownTimer extends TimerTask {

	private Tower tower;

	private Timer timer;

	private static int count = 0;

	public TowerCooldownTimer(Tower pTower, Timer pTimer) {
		super();
		this.timer = pTimer;
		this.tower = pTower;
	}

	@Override
	public void run() {
		count++;
		if (count == 2) {
			timer.cancel();
			count = 0;
		}
		this.tower.setOnCooldown(false);
	}
}

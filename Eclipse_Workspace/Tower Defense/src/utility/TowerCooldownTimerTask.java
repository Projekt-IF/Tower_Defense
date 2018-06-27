package utility;

import java.util.Timer;
import java.util.TimerTask;

import objects.Tower;

/**
 * The TowerCooldownTimerTask extends the TimerTask Class and therefore is able
 * to create a certain Task for a Timer to execute. It is used to to enable the
 * Tower to shoot again.
 * 
 * @author Jonas Schröder
 * @version 1.0
 */
public class TowerCooldownTimerTask extends TimerTask {

	private Tower tower;

	private Timer timer;

	private static int count = 0;

	/**
	 * Constructs an TowerCooldownTimerTask that can be a applied to a Timer.
	 * 
	 * @param pTower
	 *            The Tower the task has to be applied on.
	 * @param pTimer
	 *            The Time that applies the Task to the Tower.
	 */
	public TowerCooldownTimerTask(Tower pTower, Timer pTimer) {
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

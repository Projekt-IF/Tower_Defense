package utility;

import java.util.Timer;
import java.util.TimerTask;

import objects.Enemy;

/**
 * The EnemyCooldownTimerTask extends the TimerTask class and is used to start a
 * Task that is used to enable the Enemy to move again.
 * 
 * @author Jonas Schröder
 * @version 1.0
 */
public class EnemyCooldownTimerTask extends TimerTask {

	private Enemy enemy;

	private Timer timer;

	private static int count = 0;

	/**
	 * Constructs an EnemyCooldownTimerTask that is able to enable the Enemy to move
	 * again.
	 * 
	 * @param pEnemy
	 *            The Enemy the Task should be applied to.
	 * @param pTimer
	 *            The Timer that applies the Task to the Enemy.
	 */
	public EnemyCooldownTimerTask(Enemy pEnemy, Timer pTimer) {
		super();
		this.timer = pTimer;
		this.enemy = pEnemy;
	}

	@Override
	public void run() {
		count++;
		if (count == 2) {
			timer.cancel();
			count = 0;
		}
		this.enemy.setOnCooldown(false);
	}

}

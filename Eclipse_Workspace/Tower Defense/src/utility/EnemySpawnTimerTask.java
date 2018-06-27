package utility;

import java.util.Timer;
import java.util.TimerTask;

import game_Controller.Enemy_Controller;

/**
 * The EnemySpawnTimerTask extends the TimerTask class and is applied to a Timer
 * to start a Task. It is used to enable the EnemyController to spawn another
 * Enemy.
 * 
 * @author Jonas Schröder
 * @version 1.0
 */
public class EnemySpawnTimerTask extends TimerTask {

	private Enemy_Controller enemyController;

	private Timer timer;

	private static int count = 0;

	/**
	 * Constructs an EnemySpawnTimerTask that can be run to enable the given
	 * EnemyController to spawn another Enemy.
	 * 
	 * @param pEnemyController
	 *            The EnemyController the Task should be applied to.
	 * @param pTimer
	 *            The Timer the Task is executed by.
	 */
	public EnemySpawnTimerTask(Enemy_Controller pEnemyController, Timer pTimer) {
		super();
		this.timer = pTimer;
		this.enemyController = pEnemyController;
	}

	@Override
	public void run() {
		count++;
		if (count == 2) {
			timer.cancel();
			count = 0;
		}
		this.enemyController.setOnSpawnCooldown(false);
	}

}

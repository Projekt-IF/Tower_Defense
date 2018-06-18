package utility;

import java.util.Timer;
import java.util.TimerTask;

import game_Controller.Enemy_Controller;

public class EnemySpawnTimer extends TimerTask {

	private Enemy_Controller enemyController;

	private Timer timer;

	private static int count = 0;

	public EnemySpawnTimer(Enemy_Controller pEnemyController, Timer pTimer) {
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

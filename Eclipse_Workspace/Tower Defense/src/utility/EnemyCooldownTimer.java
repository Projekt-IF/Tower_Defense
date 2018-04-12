package utility;

import java.util.Timer;
import java.util.TimerTask;

import objects.Enemy;

public class EnemyCooldownTimer extends TimerTask{
	
	private Enemy enemy;
	
	private Timer timer;
	
	private static int count = 0;
	
	public EnemyCooldownTimer(Enemy pEnemy, Timer pTimer) {
		super();
		this.timer = pTimer;
		this.enemy = pEnemy;
	}
	@Override 
	public void run() {
		System.out.println(count);
		count++;
//		if(count == 2) {
//			timer.cancel();
//		}
		this.enemy.setOnCooldown(false);
		timer.cancel();
	}

}

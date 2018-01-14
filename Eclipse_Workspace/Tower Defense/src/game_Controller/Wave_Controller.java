package game_Controller;

import utility.WaveReader;

public class Wave_Controller {
	
	private Enemy_Controller eC;
	
	private WaveReader wR;
	
	public Wave_Controller(Enemy_Controller pEC) {
		this.eC = pEC;
		wR = new WaveReader();
	}
	
	public void spawnWave() {
		
	}

}

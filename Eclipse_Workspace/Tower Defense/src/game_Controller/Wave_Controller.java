package game_Controller;

import java.util.ArrayList;

import utility.Wave;
import utility.WaveReader;

public class Wave_Controller {

	private Game_Controller game_Controller;

	private WaveReader wR;

	private Wave currentWave;

	private ArrayList<Wave> waveList;

	public Wave_Controller(ArrayList<Wave> pWaveList, Game_Controller pGame_Controller) {
		this.game_Controller = pGame_Controller;
		wR = new WaveReader();
		this.waveList = pWaveList;
	}

	public void loadWaves(String pFileName) {
		System.out.println("Loading Waves");
		ArrayList<Wave> tmpWaveList = wR.loadWaveFile(pFileName);
		for (int i = 0; i < tmpWaveList.size(); i++) {
			this.waveList.add(tmpWaveList.get(i));
		}
		setCurrentWave(waveList.get(0));
	}

	public void setNextWave() {
		waveList.remove(currentWave);
		if (!waveList.isEmpty()) {
			currentWave = waveList.get(0);
		}
	}

	public void clearWaveList() {
		waveList.clear();
	}

	public Wave getCurrentWave() {
		return currentWave;
	}

	public void setCurrentWave(Wave currentWave) {
		this.currentWave = currentWave;
	}

	public ArrayList<Wave> getWaveList() {
		return waveList;
	}

	public void setWaveList(ArrayList<Wave> waveList) {
		this.waveList = waveList;
	}

	public void readWaves() {
		System.out.println("Printing Waves");
		for (int i = 0; i < waveList.size(); i++) {
			Wave current = waveList.get(i);
			System.out.println("Number of Enemies: " + current.getEnemyNumber() + " Level: " + current.getEnemyLevel()
					+ " Type: " + current.getEnemyType());
		}
	}
}

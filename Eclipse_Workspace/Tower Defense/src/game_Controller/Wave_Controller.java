package game_Controller;

import java.util.ArrayList;

import utility.Wave;
import utility.WaveReader;

public class Wave_Controller {

	private WaveReader wR;

	private Wave currentWave;

	private ArrayList<Wave> waveList = new ArrayList<Wave>();

	public Wave_Controller() {
		wR = new WaveReader();
	}

	public void loadWaves(String pFileName) {
		System.out.println("Loading Waves");
		waveList = wR.loadWaveFile(pFileName);
		setCurrentWave(waveList.get(0));
	}
	
	public void setNextWave() {
		System.out.println(waveList.size());
		waveList.remove(currentWave);
		if(!waveList.isEmpty()) {
			currentWave = waveList.get(0);
		}
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
		for(int i = 0; i < waveList.size(); i++) {
			Wave current = waveList.get(i);
			System.out.println("Number of Enemies: " + current.getEnemyNumber() + " Level: " + current.getEnemyLevel() + " Type: " + current.getEnemyType());
		}
	}

	public static void main(String args[]) {
		Wave_Controller wC = new Wave_Controller();
		wC.loadWaves("src/Waves/Wave_1.txt");
		wC.readWaves();
	}

}

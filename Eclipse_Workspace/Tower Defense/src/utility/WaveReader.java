package utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 */
public class WaveReader {

	/**
	 * File structure is the following:
	 * 
	 * [Number of Enemies],[Enemies Type],[Enemies Level];
	 */

	private ArrayList<Wave> waveList = new ArrayList<Wave>();

	public static final String COMMENT_START = "/";

	/**
	 * 
	 */
	public WaveReader() {

	}

	/**
	 * 
	 */
	public ArrayList<Wave> loadWaveFile(String fileName) {
		waveList.clear();
		System.out.println("Searching in File");
		try {
			FileReader file = new FileReader(fileName);
			BufferedReader br = new BufferedReader(file);

			String input = "";

			while ((input = br.readLine()) != null) {
				if (!input.startsWith(WaveReader.COMMENT_START)) {
					processFileContent(input);
				}
			}

			br.close();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		return waveList;
	}

	/**
	 * uses Regular Expression to process the Lines of the File into the requested
	 * format
	 */
	public void processFileContent(String pInput) {
		String regex = "\\D*(\\d+)\\D*,\\D*(\\d+)\\D*,\\D*(\\d+)\\D*;\\D*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(pInput);
		if (matcher.matches()) {
			int numberOfEnemies = Integer.valueOf(matcher.group(1));
			int typeOfEnemies = Integer.valueOf(matcher.group(2));
			int levelOfEnemies = Integer.valueOf(matcher.group(3));
			Wave nW = new Wave(numberOfEnemies, typeOfEnemies, levelOfEnemies);
			this.waveList.add(nW);
		}
	}
}

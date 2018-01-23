package utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 */
public class WaveReader {
	
	/**
	 * File structure is the following:
	 * 		
	 * 			[Number of Enemies],[Enemies Type],[Enemies Level];
	 */
	private String dateiInhalt;

	/**
	 * 
	 */
	public WaveReader() {
		
	}
	
	/**
	 * 
	 */
	public void loadWaveFile(String fileName) {
		
        try {
            FileReader file = new FileReader(fileName);
            BufferedReader  br = new BufferedReader(file);
            
            String input = "";
            
            while((input = br.readLine()) != null) {
                dateiInhalt = dateiInhalt + input;
            }
            
            br.close();
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
	
	public String getDateiInhalt() {
		return dateiInhalt;
	}

	public void setDateiInhalt(String dateiInhalt) {
		this.dateiInhalt = dateiInhalt;
	}
}

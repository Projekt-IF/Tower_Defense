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
	private String fileContent;

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
                fileContent = fileContent + input;
            }
            
            br.close();
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
	
	/**
	 * uses Regular Expression to process the Lines of the File into the requested format
	 */
	public void processFileContent() {
		
	}
	
	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}
}

package sentiment.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

public class ReadWrite
{
	// log
	static private Logger log = Logger.getLogger(ReadWrite.class.getName());
	
	/**
     * 
     * @param filename: 
     * @return
     */
	public String readFile(String filename) {
		StringBuffer inputbuf = new StringBuffer(); 
		try {
			File inputfile = new File(filename);
			BufferedReader br = new BufferedReader(new FileReader(inputfile));
			String line = "";
			while ((line = br.readLine()) != null) {
				inputbuf.append(line);
			}
			br.close();
		}
		catch (FileNotFoundException e) { 
			log.error(e.getMessage(), e);
			e.printStackTrace(); 
		}
		catch (IOException e) { 
			log.error(e.getMessage(), e);
			e.printStackTrace(); 
		}
		
		return inputbuf.toString();
	}
	
	public boolean writeFile(String filename, String output) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true)); 
			bw.write(output);
			bw.newLine();
			bw.close();
			return true;
		}
		catch (FileNotFoundException e) { 
			log.error(e.getMessage(), e);
			e.printStackTrace(); 
		}
		catch (IOException e) { 
			log.error(e.getMessage(), e);
			e.printStackTrace(); 
		}
		return false;
	}
}

package com.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ajo.koshy
 * @category executor
 * @FileName RunTimeExecutor.java
 */
public class RunTimeExecutor {

	private final static Logger LOG =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	public static void videoPlayer(String command) {
		Runtime rt = Runtime.getRuntime();
		try {
			Process proc = rt.exec(command);
			proc.waitFor();
			readStream(proc.getErrorStream(), "ERROR-STREAM");
			readStream(proc.getInputStream(), "OUTPUT-STREAM");
		} catch (IOException e) {
			LOG.log(Level.SEVERE, "IOException ocurred");
			e.printStackTrace();
		} catch (InterruptedException e) {
			LOG.log(Level.SEVERE, "The data uplink was interrupted");
			e.printStackTrace();
		}
	}
	
	public static void readStream(InputStream is, String type) {
		StringBuffer buffer = new StringBuffer();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))){
			br.lines().filter(line -> line != null).forEach(line -> buffer.append(line+" \n"));
		}catch (IOException ioe) {
			ioe.printStackTrace();
		}
		LOG.log(Level.INFO, "Type : " + type + " - " + buffer.toString());
}
}
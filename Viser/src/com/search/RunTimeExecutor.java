package com.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
 /**
  * @author ajo.koshy
  * @category executor
  * @FileName RunTimeExecutor.java*/
public class RunTimeExecutor {
	
	public StreamWrapper getStreamWrapper(InputStream is, String type){
		return new StreamWrapper(is, type);
		}
	
	public class StreamWrapper extends Thread {
		InputStream is = null;
		String type = null;
		String message = null;
 
		public String getMessage() {
            return message;
		}
 
		StreamWrapper(InputStream is, String type) {
			this.is = is;
			this.type = type;
		}
 
		public void run() {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				StringBuffer buffer = new StringBuffer();
				String line = null;
				while ( (line = br.readLine()) != null) {
					buffer.append(line);//.append("\n");
				}
				message = buffer.toString();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
	
public static void videoPlayer(String command){
	  Runtime rt = Runtime.getRuntime();
	  RunTimeExecutor rte = new RunTimeExecutor();
	  StreamWrapper error, output;

    try {
    	Process proc = rt.exec(command);
        error = rte.getStreamWrapper(proc.getErrorStream(), "ERROR");
        output = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT");
        @SuppressWarnings("unused")
		int exitVal = 0;
        error.start();
        output.start();
        error.join(3000);
        output.join(3000);
        exitVal = proc.waitFor();
        //System.out.println("Output: "+output.message+"\nError: "+error.message+"\n\nTime waited:  "+exitVal);
        } catch (IOException e){
        	System.out.println("IOException ocurred");
        	//e.printStackTrace();
            }
          catch (InterruptedException e) {
        	  System.out.println("The data uplink was interrupted");
        	  //e.printStackTrace();
              }
    }
}
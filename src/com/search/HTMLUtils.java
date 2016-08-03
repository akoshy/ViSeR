package com.search;

import java.io.*;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

public class HTMLUtils {
  private HTMLUtils() {}

  public static List<String>extractLinks(String url) throws IOException {
    final ArrayList<String> result = new ArrayList<String>();
    
    Document doc = Jsoup.connect(url).get();
    Elements links = doc.select("a[href]");

    for (Element link : links) {
      result.add(link.attr("abs:href"));
    }
    return result;
  }


  public final static void evaluator(String url_key) throws Exception{
	  
	  List<YoutubeVideo> videoList = new ArrayList<YoutubeVideo>();
      //BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
      //System.out.print("Video Search for (keywords): ");
      //String url_key = bf.readLine();
      if(url_key.contains(" ")){
    	 url_key = url_key.replace(' ', '+');
      }
      String url_video = StringConstants.SEARCH_URL+url_key;
      List<String> links = HTMLUtils.extractLinks(url_video);
      for (String link : links){
    	  YoutubeVideo myVideo;
    	  if(link.length()> 31){
    		  try{
    		  if(link.substring(0,31).equalsIgnoreCase(StringConstants.VIDEO_URL)){
    			myVideo  = new RetrieveSecond(link.substring(31, 42)).returnSearchVideo();
    			if(myVideo!=null)
    			videoList.add(myVideo);
    		  }
    		  }
    		  catch (Exception e) {
			}
        File directory = new File(StringConstants.XML_PATH);
        File[] files = directory.listFiles();
        for (File file : files){
        	file.delete();
        	}
        }
    	  
     }
      ReRanker.reRankedPlaylistExecutor(videoList);
      
  }
  
}

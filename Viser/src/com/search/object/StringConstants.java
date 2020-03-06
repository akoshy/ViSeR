package com.search.object;

/**
 * @author Ajo.Koshy
 * @category Research
 * @version 1.0
 * @FileName StringConstants.java
 * */
public class StringConstants {
	
	public static String GDATA_URL = "https://www.googleapis.com/youtube/v3/videos?id=";
	public static String ATTRIBUTES_PARAM ="&part=snippet,contentDetails,statistics"; 
	public static String SEARCH_URL = "http://www.youtube.com/results?search_query=";
	public static String VIDEO_URL = "http://www.youtube.com/watch?v=";
	public static String VLC_PROG = "vlc ";
	public static String VLC_CLOSE = " vlc://quit";
	public static String VLC_PATH = "vlc.install.path";//Configure this to the path of installation of your VLC Player
	public static String API_KEY = "api.key";
	public static String RERANK_FLAG = "rerank.required";
	public static String PLAYLIST_SIZE= "limit.video.playlist";

}

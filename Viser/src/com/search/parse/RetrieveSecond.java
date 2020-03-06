package com.search.parse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;

import com.search.object.PropertyUtil;
import com.search.object.StringConstants;
import com.search.object.YoutubeVideo;

public class RetrieveSecond {
	private static String  videoName = "Private Video";
	private static String videoUrl = StringConstants.VIDEO_URL;
	private static int duration = 0;
	private static int rating = 0;
	private static int views = 0;
	private static YoutubeVideo video;
	private static String isoDuration;
	private static Map<String,Integer> typeDuration = new TreeMap<String,Integer>(){{
		put("H", 3600);
		put("M", 60);
		put("S", 1);
		
	}};
	private final static Logger LOG =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private RetrieveSecond() {
	}
	public static YoutubeVideo from (String videoId) {
		try {
			URL url = new URL(StringConstants.GDATA_URL + videoId + PropertyUtil.getPropertyMap().getProperty(StringConstants.API_KEY) + StringConstants.ATTRIBUTES_PARAM);
			HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
			urlConn.setRequestMethod("GET");
			urlConn.setRequestProperty("Accept", "application/json");
			StringBuilder responseStrBuilder = new StringBuilder();
			try (BufferedReader streamReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"))) {
				streamReader.lines().filter(line -> line != null).forEach(line -> responseStrBuilder.append(line));
				JSONObject videoObject = new JSONObject(responseStrBuilder.toString());
				if (videoObject.has("items")) {
					videoObject = videoObject.getJSONArray("items").getJSONObject(0);

					JSONObject videoStats = videoObject.getJSONObject("statistics");
					JSONObject videoContentDetails = videoObject.getJSONObject("contentDetails");
					JSONObject videoSnippet = videoObject.getJSONObject("snippet");

					isoDuration = videoContentDetails.getString("duration").substring(2);
					typeDuration.forEach((type, timeMultiplier) -> {
						if (isoDuration.indexOf(type) > -1)
							duration = duration + timeMultiplier * Integer.parseInt(isoDuration.substring(0, isoDuration.indexOf(type)));
							isoDuration = isoDuration.substring(isoDuration.indexOf(type) + 1, isoDuration.length());
						});
					if(Boolean.getBoolean(PropertyUtil.getPropertyMap().getProperty(StringConstants.RERANK_FLAG))) {
						views = Integer.parseInt(videoStats.getString("viewCount"));
						rating = (Integer.parseInt(videoStats.getString("likeCount"))
								- Integer.parseInt(videoStats.getString("dislikeCount")));
					}
					videoUrl += videoId;
					videoName = videoSnippet.getString("title");
					video = new YoutubeVideo(videoId, duration, views, rating, videoName, videoUrl);
				}
			} catch (IOException ioe) {
				LOG.log(Level.SEVERE, ioe.getMessage() + " while reading JSON for video : " + videoId);
				videoUrl += videoId;
				video = new YoutubeVideo(videoId, duration, views, rating, videoName, videoUrl);

			}
		} catch (Exception e) {
			LOG.log(Level.SEVERE, e.getMessage() + " for video : " + videoId);
		}
		return video;
	}
	
	public YoutubeVideo returnSearchVideo() {
		return video;
	}
}
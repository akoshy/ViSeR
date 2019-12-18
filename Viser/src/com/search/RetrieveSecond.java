package com.search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class RetrieveSecond {
	String views = "0", videoName = "Private Video", videoUrl;
	int duration = 0, rating = 0;
	String xml;
	Boolean flag = false;
	YoutubeVideo video;

	public RetrieveSecond(String videoId) {
		try {
			URL url = new URL(StringConstants.GDATA_URL + videoId + StringConstants.API_KEY + StringConstants.ATTRIBUTES_PARAM);
			HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
			urlConn.setRequestMethod("GET");
			urlConn.setRequestProperty("Accept", "application/json");
			StringBuilder responseStrBuilder = new StringBuilder();
			BufferedReader streamReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));
			String inputStr;
			while ((inputStr = streamReader.readLine()) != null)
				responseStrBuilder.append(inputStr);

			JSONObject videoObject = new JSONObject(responseStrBuilder.toString());
			if (videoObject.has("items")) {
				videoObject = videoObject.getJSONArray("items").getJSONObject(0);

				JSONObject videoStats = videoObject.getJSONObject("statistics");
				JSONObject videoContentDetails = videoObject.getJSONObject("contentDetails");
				//JSONObject videoSnippet = videoObject.getJSONObject("snippet");

				String isoDuration = videoContentDetails.getString("duration").substring(2);
				if (isoDuration.indexOf("H") > -1) {
					duration = duration + 3600 * Integer.parseInt(isoDuration.substring(0, isoDuration.indexOf("H")));
					isoDuration = isoDuration.substring(isoDuration.indexOf("H") + 1, isoDuration.length());
				}
				if (isoDuration.indexOf("M") > -1) {
					duration = duration + 60 * Integer.parseInt(isoDuration.substring(0, isoDuration.indexOf("M")));
					isoDuration = isoDuration.substring(isoDuration.indexOf("M") + 1, isoDuration.length());
				}
				if (isoDuration.indexOf("S") > -1) {
					duration = duration + Integer.parseInt(isoDuration.substring(0, isoDuration.indexOf("S")));
					isoDuration = isoDuration.substring(isoDuration.indexOf("S") + 1, isoDuration.length());
				}

				views = videoStats.getString("viewCount");
				rating = (Integer.parseInt(videoStats.getString("likeCount")) - Integer.parseInt(videoStats.getString("dislikeCount")));
				videoUrl = StringConstants.VIDEO_URL + videoId;
				video = new YoutubeVideo(videoId, duration, views, rating, videoName, videoUrl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public YoutubeVideo returnSearchVideo() {
		return video;
	}
}
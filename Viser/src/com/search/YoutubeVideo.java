package com.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ajo.koshy
 * @category pojo
 * @FileName YouTubeVideo.java
 */
public final class YoutubeVideo {
	String videoId;
	Integer duration;
	Integer views;
	Integer rating;
	String videoName;
	String videoUrl;

	public YoutubeVideo(String videoId, Integer duration, Integer views, Integer rating, String videoName, String videoUrl) {
		super();
		this.videoId = videoId;
		this.duration = duration;
		this.views = views;
		this.rating = rating;
		this.videoName = videoName;
		this.videoUrl = videoUrl;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public static Map<String,Integer> rankedMapForSortedList(List<YoutubeVideo> videoList){
		Map<String,Integer> rankedMap = new HashMap<String,Integer>();
		AtomicInteger currentRank = new AtomicInteger(1);
		videoList.forEach(video -> {rankedMap.put(video.getVideoId(),currentRank.getAndIncrement());
		});
		return rankedMap;
	}
}

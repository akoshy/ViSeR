package com.search;

import java.util.Collections;
import java.util.Comparator;
/**
 * @author ajo.koshy
 * @category pojo
 * @FileName YouTubeVideo.java*/
public final class YoutubeVideo {
	String videoId;
	Integer duration;
	String views;
	Integer rating;
	String videoName;
	String videoUrl;
	
	public YoutubeVideo(String videoId, Integer duration, String views,Integer rating, String videoName, String videoUrl) {
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
	public String getViews() {
		return views;
	}
	public void setViews(String views) {
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
	/*
	 * Used to compare to objects in multiple properties as per the requirement*/

	public static enum OrderVideo implements Comparator<YoutubeVideo> {
		ByDuration() {
			public int compare(YoutubeVideo lhs, YoutubeVideo rhs) {
	        	return lhs.duration.compareTo(rhs.duration);
	        	}
			},
		ByRating() {
	        public int compare(YoutubeVideo lhs, YoutubeVideo rhs) {
	           // TODO: Should really use a collator.
				/*
				 * Double rat1 = Double.parseDouble(lhs.rating); Double rat2 =
				 * Double.parseDouble(rhs.rating);
				 */
	        	return lhs.rating.compareTo(rhs.rating);
	        	}
	        }, 
	    ByViews() {
	        	public int compare(YoutubeVideo lhs, YoutubeVideo rhs) {
		        	Integer view1 = Integer.parseInt(lhs.views);
		        	Integer view2 = Integer.parseInt(rhs.views);
		        	return view1.compareTo(view2);
			        }
			     };
		public abstract int compare(YoutubeVideo lhs, YoutubeVideo rhs);
		
	    public Comparator<YoutubeVideo> inAscendingOrder() {
	    	return this;
	    	}

	    public Comparator<YoutubeVideo> inDescendingOrder() {
	    	return Collections.reverseOrder(this);
	    	}
	  }
}

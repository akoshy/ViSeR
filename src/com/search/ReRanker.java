package com.search;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ReRanker {
	public static void reRankedPlaylistExecutor(List<YoutubeVideo> videoList){
		  /**
		   * Reranks the given video list and passes a command to add a playlist of reranked video to VLC Player
		   * @author ajo.koshy
		   *
		   * */
		  int listLimit=0;
		  Map<String,Integer> durationList = new HashMap<String,Integer>();
	      Collections.sort(videoList, YoutubeVideo.OrderVideo.ByDuration.inAscendingOrder());
	      for(YoutubeVideo vid : videoList){
	    	  int durationCountRank=1;
	    	  durationList.put(vid.getVideoId(),durationCountRank);
	    	  durationCountRank++;
	      }
	      Map<String,Integer> ratingList = new HashMap<String,Integer>();
	      Collections.sort(videoList, YoutubeVideo.OrderVideo.ByRating.inDescendingOrder());
	      for(YoutubeVideo vid : videoList){
	    	  int ratingCountRank=1;
	    	  ratingList.put(vid.getVideoId(),ratingCountRank);
	    	  ratingCountRank++;
	      }
	      Map<String,Integer> viewList = new HashMap<String,Integer>();
	      Collections.sort(videoList, YoutubeVideo.OrderVideo.ByViews.inDescendingOrder());
	      int viewsCountRank=1;
	      for(YoutubeVideo vid : videoList){
	    	  
	    	  viewList.put(vid.getVideoId(),viewsCountRank);
	    	  viewsCountRank++;
	      }
	      Map<Integer,YoutubeVideo> rankList = new TreeMap<Integer, YoutubeVideo>();
	      for(YoutubeVideo vid : videoList){
	    	  String videoId = vid.getVideoId();
	    	 // System.out.println(videoId);
	    	  int rank1 = durationList.get(videoId);
	    	  int rank2 = ratingList.get(videoId);
	    	  int rank3 = viewList.get(videoId);
	    	  int rank = (int)Math.ceil((rank1 + rank2 + rank3) / 3);
	    	  rankList.put(rank, vid);
	    	  listLimit=rank;
	      }
	      String playlist="";
	      for(int listRank = 1; listRank<=listLimit;listRank++)
	      {
	    	  YoutubeVideo video = rankList.get(listRank);
	    	  if(video!=null){
	    	  //System.out.println("Video Rank : "+listRank+", Video Name : "+video.getVideoName()+", Video Length : "+video.getDuration()+", Video Views : "+video.getViews()+", Video Rating : "+video.getRating());
	    	  playlist = playlist+video.getVideoUrl()+", ";
	    	  }
	      }
	      playlist = playlist.substring(0, playlist.length()-2);
	      
	      //RunTimeExecutor.videoPlayer(StringConstants.VLC_PATH+StringConstants.VLC_PROG+rankList.get(1).getVideoUrl()+StringConstants.VLC_CLOSE);
	      RunTimeExecutor.videoPlayer(StringConstants.VLC_PATH+StringConstants.VLC_PROG+playlist+StringConstants.VLC_CLOSE);
	  }
}

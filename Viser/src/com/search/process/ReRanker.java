package com.search.process;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.search.object.StringConstants;
import com.search.object.YoutubeVideo;

public class ReRanker {
	public static void reRankedPlaylistExecutor(List<YoutubeVideo> videoList){
		  /**
		   * Reranks the given video list and passes a command to add a playlist of reranked video to VLC Player
		   * @author ajo.koshy
		   *
		   * */
		  final Map<String,Integer> durationList = new HashMap<String,Integer>();
		  durationList.putAll(YoutubeVideo.rankedMapForSortedList(videoList.stream().sorted(Comparator.comparing(YoutubeVideo::getDuration)).collect(Collectors.toList())));
		  
	      final Map<String,Integer> ratingList = new HashMap<String,Integer>();
	      ratingList.putAll(YoutubeVideo.rankedMapForSortedList(videoList.stream().sorted(Comparator.comparing(YoutubeVideo::getRating).reversed()).collect(Collectors.toList())));
	      
	      final Map<String,Integer> viewList = new HashMap<String,Integer>();
	      viewList.putAll(YoutubeVideo.rankedMapForSortedList(videoList.stream().sorted(Comparator.comparing(YoutubeVideo::getViews).reversed()).collect(Collectors.toList())));
	      
	      Map<Integer,YoutubeVideo> rankList = new TreeMap<Integer, YoutubeVideo>();
	      videoList.forEach(currentVideo -> 
	      	{rankList.put((int) Math.ceil((durationList.get(currentVideo.getVideoId()) + ratingList.get(currentVideo.getVideoId()) + viewList.get(currentVideo.getVideoId())) / 3), currentVideo);});
	      
	      StringBuffer playlist = new StringBuffer(StringConstants.VLC_PATH  + StringConstants.VLC_PROG );
	      rankList.forEach((listRank, video) -> playlist.append(rankList.get(listRank).getVideoUrl() + " "));
	      RunTimeExecutor.videoPlayer(playlist + StringConstants.VLC_CLOSE);
	  }
}

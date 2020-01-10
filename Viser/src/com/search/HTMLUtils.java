package com.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;

public class HTMLUtils {
	private HTMLUtils() {
	}

	public static List<YoutubeVideo> extractLinks(String url) throws IOException {
		List<YoutubeVideo> videoList = new ArrayList<YoutubeVideo>();
		videoList = Jsoup.connect(url).get().select("a[href]").stream()
				.filter(videoElement -> videoElement.attr("href").indexOf("watch") > -1)
				.map(videoElement -> new RetrieveSecond(videoElement.attr("href").substring(9, 20)).returnSearchVideo())
				.filter(youtubeVideo -> youtubeVideo!=null)
				.collect(Collectors.toList());
		return videoList;
	}

	public final static void evaluator(String url_key) throws Exception {

		List<YoutubeVideo> videoList = new ArrayList<YoutubeVideo>();
		if (url_key.contains(" ")) {
			url_key = url_key.replace(' ', '+');
		}
		String url_video = StringConstants.SEARCH_URL + url_key;
		videoList = HTMLUtils.extractLinks(url_video);
		ReRanker.reRankedPlaylistExecutor(videoList);

	}

}

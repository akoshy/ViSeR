package com.search;

import java.io.*;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

public class HTMLUtils {
	private HTMLUtils() {
	}

	public static List<String> extractLinks(String url) throws IOException {
		final ArrayList<String> result = new ArrayList<String>();

		Document doc = Jsoup.connect(url).get();
		Elements links = doc.select("a[href]");

		for (Element link : links) {
			if (link.attr("href").indexOf("watch") > -1)
				result.add(link.attr("href"));
		}
		return result;
	}

	public final static void evaluator(String url_key) throws Exception {

		List<YoutubeVideo> videoList = new ArrayList<YoutubeVideo>();
		if (url_key.contains(" ")) {
			url_key = url_key.replace(' ', '+');
		}
		String url_video = StringConstants.SEARCH_URL + url_key;
		List<String> links = HTMLUtils.extractLinks(url_video);
		for (String link : links) {
			YoutubeVideo myVideo;
			try {
				myVideo = new RetrieveSecond(link.substring(9, 20)).returnSearchVideo();
				if (myVideo != null)
					videoList.add(myVideo);
			} catch (Exception e) {
			}
		}
		ReRanker.reRankedPlaylistExecutor(videoList);

	}

}

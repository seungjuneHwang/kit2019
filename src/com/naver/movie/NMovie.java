package com.naver.movie;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.naver.dto.DaumDTO;
import com.naver.dto.NMovieDTO;

public class NMovie {
	public static ArrayList<DaumDTO> getDaum() {
		String connUrl = "https://www.daum.net";
		ArrayList<DaumDTO> list = new ArrayList<>();
		try {
			Document doc = Jsoup.connect(connUrl).get();
			Elements elem = doc.select(".hotissue_mini .link_issue");
			int i = 1;
			for (Element e : elem) {
				DaumDTO dto = new DaumDTO();
				dto.setRank(i++);
				dto.setIssue(e.text());
				list.add(dto);
//				System.out.println(i++ + "위 " + e.text());
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static ArrayList<NMovieDTO> getMovie() {
		ArrayList<NMovieDTO> list = new ArrayList<>();
		String connUrl = "https://movie.naver.com/movie/running/current.nhn";
		try {
			Document doc = Jsoup.connect(connUrl).get();
			Elements elem = doc.select(".current_open .current_list");
			int i = 1;
			Elements link = elem.select("a");
			for (Element e : link) {
//				System.out.println(i++ + "위");
//				System.out.println("영화제목: " + e.attr("title"));
//				System.out.println("영화링크: " + e.attr("href"));
//				System.out.println("영화미지: " + e.select("img").attr("src"));
				NMovieDTO dto = new NMovieDTO();
				dto.setTitle(e.attr("title"));
				dto.setLink("https://movie.naver.com" + e.attr("href"));
				dto.setImg(e.select("img").attr("src").replace("?type=n77_110_2", ""));
				list.add(dto);
			}
//			System.out.println(link.toString());1
//			System.out.println(link.attr("title"));
//			System.out.println(link.attr("href"));
//			System.out.println(link.select("img").attr("src"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}

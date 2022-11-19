package com.ssafy.news.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
 
 
@Controller
@RequestMapping("/news")
@CrossOrigin(origins = { "*" })
public class NewsController {
 
    @RequestMapping(value="/crawl")
    public ResponseEntity<?> crawling() throws IOException {
    	String url = "https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid1=101&sid2=260";
        Document doc = Jsoup.connect(url).get();
        Elements el = doc.select("ul.type06_headline");
        
        List<String> photos = el.select("li>dl>dt.photo>a>img").eachAttr("src");
        List<String> links = el.select("li>dl>dt.photo>a").eachAttr("href");
        List<String> subjects = el.select("li>dl>dt>a").eachText();
        List<String> details = el.select("li>dl>dd>span.lede").eachText();
        List<String> writers = el.select("li>dl>dd>span.writing").eachText();
        List<String> dates = el.select("li>dl>dd>span.date").eachText();
        
        List<Map<String, String>> articles = new ArrayList<>();
        
        for (int i = 0; i < photos.size(); i++) {
        	Map<String, String> article = new HashMap<>();
        	
        	article.put("photo", photos.get(i));
        	article.put("link", links.get(i));
        	article.put("subject", subjects.get(i));
        	article.put("detail", details.get(i));
        	article.put("writer", writers.get(i));
        	article.put("date", dates.get(i));
        	
        	articles.add(article);
		}
        
        return new ResponseEntity<List<Map<String, String>>>(articles, HttpStatus.OK);
    }
}
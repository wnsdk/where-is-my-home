package com.ssafy.news.Controller;

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    	
    	// 반환할 기사 리스트
    	List<Map<String, String>> articles = new ArrayList<>();
    	
    	// 몇 페이지까지 뉴스기사가 있는지 구하기
    	StringBuilder url = new StringBuilder("https://news.naver.com/main/list.naver?mode=LS2D&sid2=260&sid1=101&mid=shm");
    	Document doc = Jsoup.connect(url.toString()).get();
    	int lastpageno = 1;
    	if (doc.select("div.paging>a:last-child").text() != null && !doc.select("div.paging>a:last-child").text().equals(""))
    		lastpageno = Integer.parseInt(doc.select("div.paging>a:last-child").text());
    	
    	// 오늘 날짜 구하기
    	Date today = new Date();
    	SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
    	
    	for (int pageno = 1; pageno < lastpageno + 1; pageno++) {
    		url = new StringBuilder("https://news.naver.com/main/list.naver?mode=LS2D&sid2=260&sid1=101&mid=shm");
        	url.append("&date=" + date.format(today));
    		url.append("&page=" + pageno);
        
			doc = Jsoup.connect(url.toString()).get();
			
			Elements el = doc.select("ul.type06_headline");
            el.addAll(doc.select("ul.type06"));
            if (pageno == 5) System.out.println(el);
            
            List<String> photos = el.select("li>dl>dt.photo>a>img").eachAttr("src");
            List<String> links = el.select("li>dl>dt.photo>a").eachAttr("href");
            List<String> subjects = el.select("li>dl>dt>a").eachText();
            List<String> details = el.select("li>dl>dd>span.lede").eachText();
            List<String> writers = el.select("li>dl>dd>span.writing").eachText();
            List<String> dates = el.select("li>dl>dd>span.date").eachText();
            
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
		}
        
        return new ResponseEntity<List<Map<String, String>>>(articles, HttpStatus.OK);
    }
}
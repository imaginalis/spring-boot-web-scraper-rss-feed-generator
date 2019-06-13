package net.mestwin.webscraperrss.service;

import com.rometools.rome.feed.synd.*;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedOutput;
import lombok.extern.java.Log;
import org.apache.tomcat.util.buf.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Log
@Service
public class WebScraperService {

    @Value("${app.url.example.list}")
    private List<String> urlList;

    public String scrap() throws IOException, FeedException {

        SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType("atom_1.0");
        List<SyndEntry> entries = new ArrayList<>();

        for (String url : urlList) {
            Document doc = Jsoup.connect(url).get();
            String title = doc.title();
            log.info("Website:" + title);
            Elements elements = doc.select(".news-tiles").select(".article-abstract");
            elements.forEach(element->{
                SyndEntry entry = new SyndEntryImpl();
                entry.setTitle(element.select(".article-title").text());
                SyndContent content = new SyndContentImpl();
                content.setType("text/html");
                content.setValue(element.select(".asset-summary-text").text() +
                        "<a href=\"" + "\">" +
                        "<img src=\"" + element.select("img").first().absUrl("src") + "\"/>" +
                        "</a>");

                entry.setLink(element.select(".article-title").select("a").attr("abs:href"));
                entry.setContents(Collections.singletonList(content));
                entries.add(entry);

                log.info("__" + element.select(".article-title").text());
                log.info(element.select(".article-title").select("a").attr("abs:href")); // link
                log.info(element.select(".asset-summary-text").text()); // description
                log.info(element.select("img").first().absUrl("src"));
            });
            log.info("Scrapped website: " + title + ". Added: " + elements.size() + " entries to feed.");
        }
        feed.setEntries(entries);
        log.info("Total: " + entries.size() + ". From: " + urlList.size() + " websites.");
        return new SyndFeedOutput().outputString(feed);
    }

}

package net.mestwin.webscraperrss.service;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedOutput;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import net.mestwin.webscraperrss.model.rss.FeedEntry;
import net.mestwin.webscraperrss.utils.RssUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log
@Service
public class RssGeneratorService {

    private FeedEntryService feedEntryService;

    public RssGeneratorService(FeedEntryService feedEntryService) {
        this.feedEntryService = feedEntryService;
    }

    @SneakyThrows
    public String generateRssFeed() {
        List<FeedEntry> entries = feedEntryService.findAll();
        return processRssFeed(entries);
    }

    @SneakyThrows
    public String generateRssFeedForWebsite(Long websiteId) {
        List<FeedEntry> entries = feedEntryService.findAllByTargetWebsiteId(websiteId);
        return processRssFeed(entries);
    }

    @SneakyThrows
    private String processRssFeed(List<FeedEntry> entries) {
        List<SyndEntry> rssEntries = new ArrayList<>();
        SyndFeed feed = RssUtils.getFeed();
        entries.forEach(entry -> rssEntries.add(generateEntry(entry)));
        feed.setEntries(rssEntries);
        return new SyndFeedOutput().outputString(feed);
    }

    private SyndEntry generateEntry(FeedEntry entry) {
        SyndEntry rssEntry = new SyndEntryImpl();
        rssEntry.setTitle(entry.getTitle());
        SyndContent content = RssUtils.getFeedEntryContent();
        content.setValue(entry.getContent());
        rssEntry.setLink(entry.getUrl());
        rssEntry.setContents(Collections.singletonList(content));
        rssEntry.setAuthor(entry.getAuthor());
        return rssEntry;
    }

}

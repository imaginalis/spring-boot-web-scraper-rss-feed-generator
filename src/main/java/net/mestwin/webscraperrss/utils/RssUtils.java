package net.mestwin.webscraperrss.utils;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndContentImpl;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;

public class RssUtils {
    public static SyndFeed getFeed() {
        SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType("atom_1.0");
        return feed;
    }

    public static SyndContent getFeedEntryContent() {
        SyndContent content = new SyndContentImpl();
        content.setType("text/html");
        return content;
    }
}

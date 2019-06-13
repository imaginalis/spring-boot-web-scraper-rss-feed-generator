package net.mestwin.webscraperrss.service;

import lombok.SneakyThrows;
import lombok.extern.java.Log;
import net.mestwin.webscraperrss.model.parser.ParseEntryRule;
import net.mestwin.webscraperrss.model.parser.TargetWebsite;
import net.mestwin.webscraperrss.model.rss.FeedEntry;
import net.mestwin.webscraperrss.utils.WebScraperUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.List;

@Log
@Service
public class WebScraperService {

    private FeedEntryService feedEntryService;
    private TargetWebsiteService targetWebsiteService;

    public WebScraperService(FeedEntryService feedEntryService, TargetWebsiteService targetWebsiteService) {
        this.feedEntryService = feedEntryService;
        this.targetWebsiteService = targetWebsiteService;
    }

    @SneakyThrows
    public void scrapAndSave() {
        List<TargetWebsite> websites = targetWebsiteService.findAll();
        entriesCleanup();
        processWebsitesScrapping(websites);
        log.info("Total: " + websites.size() + " websites scrapped.");
    }

    @SneakyThrows
    private void processWebsitesScrapping(List<TargetWebsite> websites) {
        for (TargetWebsite website : websites) {
            Document doc = Jsoup.connect(website.getUrl()).get();
            String websiteTitle = doc.title();
            Elements elements = doc.select(website.getParseEntryRule().getNewsContainer());
            elements.forEach(element -> saveEntry(element, website, websiteTitle));
            log.info("Scrapped website: " + websiteTitle + ". " + elements.size() + " entries added to feed.");
        }
    }

    private void entriesCleanup() {
        feedEntryService.deleteAll();
    }

    private FeedEntry saveEntry(Element element, TargetWebsite website, String websiteTitle) {
        ParseEntryRule rule = website.getParseEntryRule();
        String imageUrl = element.select("img").first().absUrl("src");
        return feedEntryService.save(
                FeedEntry.builder()
                        .author(websiteTitle)
                        .title(element.select(rule.getTitle()).text())
                        .content(
                                WebScraperUtils.generateContent(
                                        element.select(rule.getContent()).text(),
                                        imageUrl
                                )
                        )
                        .url(element.select(rule.getLink()).attr("abs:href"))
                        .imageUrl(imageUrl)
                        .targetWebsite(website)
                        .build());
    }

}

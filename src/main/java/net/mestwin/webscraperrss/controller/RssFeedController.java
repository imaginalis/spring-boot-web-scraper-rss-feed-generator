package net.mestwin.webscraperrss.controller;

import net.mestwin.webscraperrss.service.RssGeneratorService;
import net.mestwin.webscraperrss.service.WebScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RssFeedController {

    @Autowired
    private RssGeneratorService rssGeneratorService;
    @Autowired
    private WebScraperService webScraperService;

    @GetMapping("/scrap")
    @ResponseBody
    public void scrap() {
        webScraperService.scrapAndSave();
    }

    @GetMapping("/rss")
    @ResponseBody
    public String/*void*/ generateRssFeed() {
        return rssGeneratorService.generateRssFeed();
    }

    @GetMapping("/rss/{id}")
    @ResponseBody
    public String/*void*/ generateRssFeed(@PathVariable("id") Long websiteId) {
        return rssGeneratorService.generateRssFeedForWebsite(websiteId);
    }


}

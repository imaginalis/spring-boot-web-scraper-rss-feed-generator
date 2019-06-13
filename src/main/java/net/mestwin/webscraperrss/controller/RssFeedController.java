package net.mestwin.webscraperrss.controller;

import net.mestwin.webscraperrss.service.RssGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RssFeedController {

    @Autowired
    private RssGeneratorService rssGeneratorService;


    @GetMapping("/rss")
    @ResponseBody
    public String generateRssFeed() {
        return rssGeneratorService.generateRssFeed();
    }


}

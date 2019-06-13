package net.mestwin.webscraperrss.service;

import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Log
@Service
public class RssGeneratorService {

    @Autowired
    private WebScraperService webScraperService;

    @SneakyThrows
    public String generateRssFeed() {
        return webScraperService.scrap();
    }
}

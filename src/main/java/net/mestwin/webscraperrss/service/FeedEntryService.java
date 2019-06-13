package net.mestwin.webscraperrss.service;

import lombok.extern.java.Log;
import net.mestwin.webscraperrss.model.rss.FeedEntry;
import net.mestwin.webscraperrss.repository.FeedEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log
@Service
public class FeedEntryService {

    @Autowired
    private FeedEntryRepository feedEntryRepository;

    public FeedEntry save(FeedEntry feedEntry) {
        return feedEntryRepository.save(feedEntry);
    }

    public List<FeedEntry> findAll() {
        return feedEntryRepository.findAll();
    }

    public List<FeedEntry> findAllByTargetWebsiteId(Long targetWebsiteId) {
        return feedEntryRepository.findAllByTargetWebsiteId(targetWebsiteId);
    }

    public void deleteAll() {
        feedEntryRepository.deleteAll();
    }
}

package net.mestwin.webscraperrss.repository;

import net.mestwin.webscraperrss.model.rss.FeedEntry;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FeedEntryRepository extends CrudRepository<FeedEntry, Long> {
    List<FeedEntry> findAllByTargetWebsiteId(Long targetWebsiteId);

    List<FeedEntry> findAll();
}

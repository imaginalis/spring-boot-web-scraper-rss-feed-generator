package net.mestwin.webscraperrss.repository;

import net.mestwin.webscraperrss.model.parser.TargetWebsite;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TargetWebsiteRepository extends CrudRepository<TargetWebsite, Long> {
    List<TargetWebsite> findAll();
}

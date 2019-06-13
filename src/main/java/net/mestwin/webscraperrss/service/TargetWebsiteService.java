package net.mestwin.webscraperrss.service;

import lombok.extern.java.Log;
import net.mestwin.webscraperrss.model.parser.TargetWebsite;
import net.mestwin.webscraperrss.repository.TargetWebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log
@Service
public class TargetWebsiteService {

    @Autowired
    private TargetWebsiteRepository targetWebsiteRepository;

    public List<TargetWebsite> findAll() {
        return targetWebsiteRepository.findAll();
    }

}

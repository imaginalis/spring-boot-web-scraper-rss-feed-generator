package net.mestwin.webscraperrss;

import net.mestwin.webscraperrss.service.WebScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebScraperRssFeedGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebScraperRssFeedGeneratorApplication.class, args);
	}



}

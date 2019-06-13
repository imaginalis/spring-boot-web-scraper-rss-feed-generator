package net.mestwin.webscraperrss.model.parser;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Data
@Entity
public class TargetWebsite {

    @Id
    @GeneratedValue
    private Long id;
    private String url;
    @OneToOne
    private ParseEntryRule parseEntryRule;
}

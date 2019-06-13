package net.mestwin.webscraperrss.model.rss;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.mestwin.webscraperrss.model.parser.TargetWebsite;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedEntry {


    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String author;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String url;
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "target_website_id")
    private TargetWebsite targetWebsite;

}

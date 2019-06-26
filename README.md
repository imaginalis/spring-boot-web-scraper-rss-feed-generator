# Spring Boot + JSoup + ROME
## Web scraper and RSS feed generator
Spring Boot based example application for website scraping and RSS feed generation.


## Technologies

+ Spring Boot
+ Gradle
+ JSoup
+ ROME
+ Lombok
+ PostgreSQL

## Configuration

+ Create PostgreSQL database and configure connection in **application.properties**
+ Build with Gradle

    `./gradlew build`


## Endpoints

+ **GET /scrap** – Scrap all websites on demand

+ **GET /rss** – Get general RSS feed

+ **GET /rss/<TARGET_WEBSITE_ID>** – Get RSS feed for specific website


## More information

+ Blog post: [Web scraper and RSS feed generator with Spring Boot](https://blog.mestwin.net/web-scraper-and-rss-feed-generator-with-spring-boot/)

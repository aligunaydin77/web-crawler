import org.junit.Test;

/**
 * Created by agu08 on 19/01/2017.
 */
public class CrawlerTest {

    @Test
    public void shouldVisitThePage() {
        Crawler crawler = new Crawler("http://www.google.com");
        crawler.crawl("http://www.google.com", 0);
    }
}

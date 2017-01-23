import org.junit.Test;

/**
 * Created by agu08 on 19/01/2017.
 */
public class CrawlerTest {

    @Test
    public void shouldVisitThePage() {
        Crawler crawler = new Crawler("http://wiprodigital.com");
        Footstep footstep = new Footstep("http://wiprodigital.com");
        crawler.crawl("http://wiprodigital.com", 0, footstep);
        crawler.writeSiteMapToFile(footstep);
    }
}

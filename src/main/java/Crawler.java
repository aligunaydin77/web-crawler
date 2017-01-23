import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Crawler {

    private  String seed;

    private  Footstep footstep;

    public Crawler(String seed) {
        this.seed = seed;
        this.footstep  = new Footstep(seed);
    }

    public  void crawl(String url, int depth) {
        if(depth++ < 5) {
            Connection connection = Jsoup.connect(url);
            Document htmlDocument = null;
            String subUrl = null;
            try {
                htmlDocument = connection.get();
                if (connection.response().statusCode() == 200) {
                    Elements linksOnPage = htmlDocument.select("a[href]");
                    Set<Footstep> contentList = new HashSet<Footstep>();
                    for (Element element : linksOnPage) {
                        subUrl = element.attr("href");
                        if (!subUrl.contains("http")) {
                            contentList.add(new Footstep(subUrl));
                        }
                    }

                    for(Footstep footstep: contentList) {
                        crawl(seed + "/" + footstep.getUrl(), depth);
                    }
                    footstep.setContent(contentList);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
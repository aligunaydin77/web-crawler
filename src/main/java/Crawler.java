import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Crawler {

    private  String seed;
    
    private Set<String> sitesVisited = new HashSet<>();	

    public Crawler(String seed) {
        this.seed = seed;
    }

    public void crawl(String url, int depth, Footstep footstep) {
        if(depth++ < 5) {
            Connection connection = Jsoup.connect(url);
            Document htmlDocument = null;
            String subUrl = null;
            try {
                htmlDocument = connection.get();
                if (connection.response().statusCode() == 200) {
                    Elements linksOnPage = htmlDocument.select("a[href]");
                    Set<Footstep> uniqueListOfLinks = new HashSet<Footstep>();
                    System.out.println("urls under: " + url + " in depth: " + depth);
                    for (Element element : linksOnPage) {
                        subUrl = element.attr("href");
                        if (subUrl.startsWith("/") || subUrl.contains(seed)) {
                            uniqueListOfLinks.add(new Footstep(subUrl));
                        }
                    }
                    for(Footstep eachFootstep: uniqueListOfLinks) {
                    	subUrl = footstep.getUrl();
                    	if(subUrl.contains(seed) && !sitesVisited.contains(subUrl)) {
                    		crawl(subUrl, depth, eachFootstep);
                    		sitesVisited.add(subUrl);
                    	}
                    }
                    footstep.setContent(uniqueListOfLinks);
                }
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
    }
    
    public void writeSiteMapToFile(Footstep footstep) {
    	
    	JsonFactory factory = new JsonFactory();
    	// configure, if necessary:
    	factory.enable(JsonParser.Feature.ALLOW_COMMENTS);
    	try {
			JsonGenerator generator = factory.createJsonGenerator(new File("E:/eclipseWorkspace/web-crawler/footstep.json"), JsonEncoding.UTF8);
			generator.setCodec(new ObjectMapper());
			generator.writeObject(footstep);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
import java.util.Set;

/**
 * Created by agu08 on 23/01/2017.
 */
public class Footstep {

    private Set<Footstep> content;
    private String url;

    public Footstep(String url) {
        this.url = url;
    }

    public Set<Footstep> getContent() {
        return content;
    }

    public void setContent(Set<Footstep> content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public boolean equals(Object theOther) {
        if(theOther instanceof Footstep) {
            return ((Footstep)theOther).getUrl().equals(url);
        }
        return false;
    }

    public int hashCode() {
        return url.hashCode();
    }

}

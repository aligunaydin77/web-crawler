import java.util.regex.Pattern;

/**
 * Created by agu08 on 23/01/2017.
 */
public class PageStack {

    private int stackSize;

    private static PageStack instance = new PageStack();

    private PageStack() {

    }

    public static void incrementStackSize() {
        instance.stackSize++;
        if(instance.stackSize == 5) {
            throw new RuntimeException("stack overflow");
        }
    }
}

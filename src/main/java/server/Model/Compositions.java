package server.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IgorPc on 11/20/2018.
 */
public class Compositions {
    private static List<String> compositions;


    static {
        compositions=new ArrayList<>();
        compositions.add("Belle - Notre Dame de Paris");
        compositions.add("Mozart");
        compositions.add("Steel alive");
        compositions.add("BlackHeart");
    }

    public static List<String> compositions(){
        return compositions;
    }
}

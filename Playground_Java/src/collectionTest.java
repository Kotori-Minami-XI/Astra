import org.junit.Test;

import java.util.*;

class myComp implements Comparator<String> {
    @Override
    public int compare(String a, String b) {
        if (a.equals(b))
            return 0;
        if (a.length()>=b.length())
            return 1;
        else
            return -1;
    }

}

public class collectionTest {
    public static void main(String[] args) {
        Map<String,Integer> map = new TreeMap(new myComp());
        map.put("zsasd",20);
        map.put("zsasd",20);
        map.put("lsa",22);
        map.put("wcas",25);

        Set<Map.Entry<String,Integer>> set = map.entrySet();
        for (Map.Entry<String,Integer> entry : set){
            System.out.println(entry.getKey()+"-"+entry.getValue());
        }


    }
}

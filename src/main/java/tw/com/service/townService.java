package tw.com.service;

import java.util.ArrayList;

import static tw.com.Utils.MapBuilder.getTownByName;

public class townService {
    public static double calculateDistance(ArrayList<String> names) {
        double sumDistance = 0.0;
        for (int i = 0; i < names.size() - 1; i++) {
            Double distance = getTownByName(names.get(i)).getDisByDes(getTownByName(names.get(i + 1)));
            if (distance == Double.POSITIVE_INFINITY) {
                System.out.print("NO SUCH ROUTE");
            }
            sumDistance = sumDistance + distance;
        }
        return sumDistance;

    }
    

}

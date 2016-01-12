package tw.com;

import tw.com.Utils.MapBuilder;

import java.io.IOException;
import java.util.ArrayList;

import static java.util.Arrays.asList;
import static tw.com.service.townService.calculateDistance;

public class Train {
    public static void main(String[] args) throws IOException {
        MapBuilder.initMap();
        System.out.println("1.the Distance of the route A-B-C:" + calculateDistance(new ArrayList(asList("A", "B", "C"))));
        System.out.println("2.the Distance of the route A-D:" + calculateDistance(new ArrayList(asList("A", "D"))));
        System.out.println("3.the Distance of the route A-D-C:" + calculateDistance(new ArrayList(asList("A", "D", "C"))));
        System.out.println("4.the Distance of the route A-E-B-C-D:" + calculateDistance(new ArrayList(asList("A", "E", "B", "C", "D"))));
        System.out.println("5.the Distance of the route A-E-D:" + calculateDistance(new ArrayList(asList("A", "E", "D"))));


    }
}

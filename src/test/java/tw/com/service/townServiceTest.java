package tw.com.service;

import org.junit.Before;
import org.junit.Test;
import tw.com.Utils.MapBuilder;
import tw.com.model.Edge;
import tw.com.model.Town;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static tw.com.Utils.MapBuilder.getTownByName;
import static tw.com.service.townService.calculateDistance;

public class townServiceTest {

    private List<Town> towns;

    @Before
    public void setUp() throws Exception {
        towns = MapBuilder.initMap();
    }

    @Test
    public void testDistanceFromAtoBtoC() throws Exception {
        ArrayList<String> names = new ArrayList(Arrays.asList("A", "B", "C"));
        System.out.println("1.the Distance of the route A-B-C:" + calculateDistance(names));
        assertThat(calculateDistance(names), is(9.0));
    }

    @Test
    public void testDistanceFromAtoD() throws Exception {
        ArrayList<String> names = new ArrayList(Arrays.asList("A", "D"));
        System.out.println("2.the Distance of the route A-D:" + calculateDistance(names));
        assertThat(calculateDistance(names), is(5.0));
    }

    @Test
    public void testDistanceFromAtoDtoC() throws Exception {
        ArrayList<String> names = new ArrayList(Arrays.asList("A", "D", "C"));
        System.out.println("3.the Distance of the route A-D-C:" + calculateDistance(names));
        assertThat(calculateDistance(names), is(13.0));
    }

    @Test
    public void testDistanceFromAtoEtoBtoCtoD() throws Exception {
        ArrayList<String> names = new ArrayList(Arrays.asList("A", "E", "B", "C", "D"));
        System.out.println("4.the Distance of the route A-E-B-C-D:" + calculateDistance(names));
        assertThat(calculateDistance(names), is(22.0));
    }

    @Test
    public void testDistanceFromAtoEtoD() throws Exception {
        ArrayList<String> names = new ArrayList(Arrays.asList("A", "E", "D"));
        System.out.println("5.the Distance of the route A-E-D:" + calculateDistance(names));

    }

    @Test
    public void testShouldGet2PathWhenGiven3Stops() throws Exception {
        assertThat(calculatePathWithStops("C", "C", 2), is(new ArrayList(Arrays.asList("C", "D", "C"))));

    }

    private List<Town> calculatePathWithStops(String startTown, String destinationTown, int numberOfStop) {
        Town staTown = getTownByName(startTown);
        Town targetTown = getTownByName(destinationTown);
        List<Town> path = new ArrayList<Town>();

        double stops = 0;
        for (Town town = staTown; town != targetTown && stops <= numberOfStop; ) {
            for(Edge edge :town.getAdj()) {
                Town desTown = edge.getDestination();
                //if 
                town = edge.getDestination();
                path.add(targetTown);
            }
            stops++;
        }
        return path;
    }

}
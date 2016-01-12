package tw.com.model;

import org.junit.Before;
import org.junit.Test;
import tw.com.Utils.MapBuilder;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static tw.com.model.Town.calculateDistance;

public class TownTest {

    private ArrayList<Town> towns;

    @Before
    public void setUp() throws Exception {
         MapBuilder.initMap();
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
        ArrayList<String> names = new ArrayList(Arrays.asList("A", "D","C"));
        System.out.println("3.the Distance of the route A-D-C:" + calculateDistance(names));
        assertThat(calculateDistance(names), is(13.0));
    }

    @Test
    public void testDistanceFromAtoEtoBtoCtoD() throws Exception {
        ArrayList<String> names = new ArrayList(Arrays.asList("A", "E","B","C","D"));
        System.out.println("4.the Distance of the route A-E-B-C-D:" + calculateDistance(names));
        assertThat(calculateDistance(names), is(22.0));
    }

    @Test
    public void testDistanceFromAtoEtoD() throws Exception {
        ArrayList<String> names = new ArrayList(Arrays.asList("A","E","D"));
        System.out.println("5.the Distance of the route A-E-D:" + calculateDistance(names));

    }




}
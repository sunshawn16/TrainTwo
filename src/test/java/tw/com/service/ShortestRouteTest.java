package tw.com.service;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ShortestRouteTest {

    private ShortestRouteService shortestRouteService;
    private IoService ioService;
    private CalculateDistance calculateDistance;

    @Before
    public void setUp() throws Exception {
        ioService = new IoService();
        ioService.read();
        calculateDistance = new CalculateDistance(ioService);
        shortestRouteService = new ShortestRouteService(ioService, calculateDistance);

    }

    @Test
    public void testGetShortestRouteFromAToC() throws Exception {
        assertThat(shortestRouteService.findShortestRoute("A", "C"), is(9));
    }

    @Test
    public void testGetShortestRouteFromBToB() throws Exception {
        assertThat(shortestRouteService.findShortestRoute("B","B"),is(9));

    }

}
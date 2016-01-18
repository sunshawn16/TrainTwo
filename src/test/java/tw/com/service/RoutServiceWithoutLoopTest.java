package tw.com.service;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RoutServiceWithoutLoopTest {

    private RouteServiceWithoutLoop routeServiceWithoutLoop;
    private IoService ioService;

    @Before
    public void setUp() throws Exception {
        ioService = new IoService();
        ioService.read();
        CovertService covertService = new CovertService();
        routeServiceWithoutLoop = new RouteServiceWithoutLoop(ioService,covertService);

    }

    @Test
    public void testGetShortestRouteFromAToC() throws Exception {
        assertThat(routeServiceWithoutLoop.findShortestDistance("A", "C"), is(9));
    }

    @Test
    public void testGetShortestRouteFromBToB() throws Exception {
        assertThat(routeServiceWithoutLoop.findShortestDistance("B", "B"),is(9));

    }

    @Test
    public void testGetNumberOfPathGivenDistance30FromCtoC() throws Exception {
        assertThat(routeServiceWithoutLoop.getNumOfPathsWithStopRequ("C","C","distance",30).size(),is(7));

    }


}
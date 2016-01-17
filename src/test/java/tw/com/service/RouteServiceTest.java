package tw.com.service;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RouteServiceTest {
    private RouteService routeService;
    private IoService ioService;
    private CalculateDistance calculateDistance;

    @Before
    public void setUp() throws Exception {
        ioService = new IoService();     // 依赖注入问题    Mokito?
        ioService.read();
        routeService = new RouteService(ioService,calculateDistance);
    }

    @Test
    public void testGetNumberOfPathsGivenMax3FromCToC() throws Exception {
        assertThat(routeService.getNumOfPathsWithStopRequ("C", "C", 3, "max").size(), is(2));

    }

    @Test
    public void testGetNumberOfPathGiven4StopsFromAToC() throws Exception {
        assertThat(routeService.getNumOfPathsWithStopRequ("A", "C", 4, "num").size(), is(3));
    }

    @Test
    public void testGetShortestRouteFromAToC() throws Exception {
        assertThat(routeService.findShortestRoute("A","C"),is(9));
    }

    @Ignore
    public void testGetShortestRouteFromBToB() throws Exception {
        routeService.getNumOfPathsWithStopRequ("B", "B", 999999, "shortest");
//        assertThat(routeService.findShortestRoute("B","B"),is(9));

    }

}
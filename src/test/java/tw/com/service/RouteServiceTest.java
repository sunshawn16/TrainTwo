package tw.com.service;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RouteServiceTest {
    private RouteService routeService;
    private IoService ioService;

    @Before
    public void setUp() throws Exception {
        ioService = new IoService();     // 依赖注入问题    Mokito?
        ioService.read();
        routeService = new RouteService(ioService);

    }

    @Test
    public void testGetNumberOfPathsGivenMax3FromCToC() throws Exception {
        assertThat(routeService.getNumOfPaths("C", "C", 3), is(2));
    }

    @Ignore
    public void testGetNumberOfPathGiven4StopsFromAToC() throws Exception {
        assertThat(routeService.getNumOfPaths("A","C",4),is(3));
    }

    @Ignore
    public void testGetShortestRouteFromAToC() throws Exception {

    }
    @Ignore
    public void testGetShortestRouteFromBToB() throws Exception {

    }
}
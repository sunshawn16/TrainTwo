package tw.com.service;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RouteServiceTest {
    private RouteService routeService;
    private IoService ioService;

    @Before
    public void setUp() throws Exception {
        ioService = new IoService();     // 依赖注入问题    Mokito
        ioService.read();
        routeService = new RouteService(ioService);
    }

    @Test
    public void testGetNumberOfPathsGivenMax3FromCToC() throws Exception {
        assertThat(routeService.getNumOfPathsWithStopRequ("C", "C", 3, "max").size(), is(2));
    }

    @Test
    public void testGetNumberOfPathGiven4StopsFromAToC() throws Exception {
        assertThat(routeService.getNumOfPathsWithStopRequ("A", "C", 4, "num").size(), is(3));
    }


}
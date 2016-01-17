package tw.com.service;

import org.junit.Before;
import org.junit.Test;
import tw.com.model.Path;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculateDistanceServiceTest {
    private static final int INFINITY = 999999;
    private IoService ioService;
    private CalculateDistanceService calculateDistanceService;

    @Before
    public void setUp() throws Exception {
        this.ioService = new IoService();
        calculateDistanceService = new CalculateDistanceService(ioService);//mockito
        ioService.read();
    }

    @Test
    public void testDistanceFromAtoBtoC() throws Exception {
        List<String> names = asList("A", "B", "C");
        Path path = new Path(names);
        assertThat(calculateDistanceService.getDistance(path), is(9));
    }

    @Test
    public void testtestDistanceFromAToD() throws Exception {
        List<String> names = asList("A", "D");
        Path path = new Path(names);
        assertThat(calculateDistanceService.getDistance(path), is(5));
    }

    @Test
    public void testtestDistanceFromAToDToC() throws Exception {
        List<String> names = asList("A", "D", "C");
        Path path = new Path(names);
        assertThat(calculateDistanceService.getDistance(path), is(13));
    }

    @Test
    public void testtestDistanceFromAToEToBToCToD() throws Exception {
        List<String> names = asList("A", "E", "B", "C", "D");
        Path path = new Path(names);
        assertThat(calculateDistanceService.getDistance(path), is(22));
    }

    @Test
    public void testtestDistanceFromAToEToD() throws Exception {
        List<String> names = asList("A", "E", "D");
        Path path = new Path(names);
        assertThat(calculateDistanceService.getDistance(path), is(INFINITY));
    }

}
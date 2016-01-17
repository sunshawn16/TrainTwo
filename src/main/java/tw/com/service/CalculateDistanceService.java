package tw.com.service;

import tw.com.model.Edge;
import tw.com.model.Path;

import java.io.IOException;
import java.util.List;

import static com.sun.xml.internal.bind.v2.schemagen.Util.equalsIgnoreCase;

public class CalculateDistanceService {
    private static final int INFINITY = 999999;
    private List<Edge> edges;

    public CalculateDistanceService(IoService ioService) throws IOException {
        edges = ioService.read();
    }

    public int getDistance(Path path) {
        int totalDistance = 0;

        for (int town = 1; town < path.getTowns().size(); town++) {
            String startTown = path.getTowns().get(town - 1);
            String endTown = path.getTowns().get(town);

            totalDistance += edges.stream()
                    .filter(edge -> equalsIgnoreCase(edge.getStartTown(), startTown) && equalsIgnoreCase(edge.getEndTown(), endTown))
                    .findFirst()
                    .orElse(new Edge(startTown, endTown, INFINITY)).getDistance();
        }

        return totalDistance > INFINITY ? INFINITY : totalDistance;
    }

}

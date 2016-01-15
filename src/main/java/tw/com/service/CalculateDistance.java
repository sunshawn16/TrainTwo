package tw.com.service;

import tw.com.model.Edge;
import tw.com.model.Path;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

import static com.sun.xml.internal.bind.v2.schemagen.Util.equalsIgnoreCase;

public class CalculateDistance {
    public static final int INFINITY = 999999;
    private List<Edge> edges;

    public CalculateDistance(IoService ioService) throws IOException {
        edges = ioService.read();
    }

    public int getDistance(Path path) {
        int totalDistance = 0;

        for (int town = 1; town < path.getTowns().size() ; town++) {
            String startTown = path.getTowns().get(town - 1);
            String endTown = path.getTowns().get(town);

            totalDistance += edges.stream().filter(new Predicate<Edge>() {
                @Override
                public boolean test(Edge edge) {
                    return equalsIgnoreCase(edge.getStartTown(), startTown) && equalsIgnoreCase(edge.getEndTown(), endTown);
                }
            }).findFirst().orElse(new Edge(startTown,endTown,INFINITY)).getDistance();
        }

        if(totalDistance >INFINITY){
            return INFINITY;
        }
        return totalDistance;
    }

}

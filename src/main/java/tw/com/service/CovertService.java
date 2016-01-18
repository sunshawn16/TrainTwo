package tw.com.service;

import tw.com.model.Edge;
import tw.com.model.Path;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class CovertService {
    public List<Path> convertToPath(List<Edge> routes, Path currentPath) {
        List<String> towns = currentPath.getTowns();
        return routes.stream().map(edge -> {
            List<String> newTowns = towns.stream().collect(toList());
            newTowns.add(edge.getEndTown());
            return new Path(newTowns);
        }).collect(toList());
    }

    public List<Path> convertToPath(List<Edge> edges) {
        return edges.stream().map(edge -> {
            List<String> towns = new ArrayList<>();
            towns.add(edge.getStartTown());
            towns.add(edge.getEndTown());
            return new Path(towns);
        }).collect(toList());
    }

}

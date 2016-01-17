package tw.com.service;

import tw.com.model.Edge;
import tw.com.model.Path;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.sun.xml.internal.bind.v2.schemagen.Util.equalsIgnoreCase;
import static java.util.stream.Collectors.toList;

public class RouteService {

    public IoService ioService;

    public RouteService(IoService ioService) throws IOException {
        this.ioService = ioService;
    }

    public List<Path> getNumOfPathsWithStopRequ(String startTown, String endTown, int refNum, String require) {
        List<Path> finalPaths = new ArrayList<Path>();
        List<Path> processedPaths = new ArrayList<Path>();

        List<Path> pathsFromStartTown = convertToPath(ioService.getEdgesStartFrom(startTown));
        processedPaths.addAll(pathsFromStartTown);

        for (Path path : pathsFromStartTown) {
            findNextPath(processedPaths, finalPaths, path, endTown, 0, refNum, require);
        }

        return finalPaths;
    }

    private void findNextPath(List<Path> processedPaths, List<Path> finalPaths, Path currentPath, String endTown,
                              int currentStop, int refNum, String require) {
        String endTownOfCurrentPath = currentPath.getTowns().get(currentPath.getTowns().size() - 1);

        if (decideFinalPath(finalPaths, currentPath, endTown, currentStop, refNum, require, endTownOfCurrentPath))
            return;

        List<Edge> edges = ioService.getEdgesStartFrom(endTownOfCurrentPath);
        List<Path> newPaths = convertToPath(edges, currentPath);

        processedPaths.addAll(newPaths);

        for (Path newPath : newPaths) {
            findNextPath(processedPaths, finalPaths, newPath, endTown, currentStop + 1, refNum, require);
        }
    }

    private boolean decideFinalPath(List<Path> finalPaths, Path currentPath, String endTown, int currentStop, int refNum, String require, String endTownOfCurrentPath) {
        if (require.equals("max")) {
            if (currentStop < refNum && equalsIgnoreCase(endTown, endTownOfCurrentPath)) {
                finalPaths.add(currentPath);
                return true;
            }
        }
        if (require.equals("num")) {
            if (currentStop == refNum - 1 && equalsIgnoreCase(endTown, endTownOfCurrentPath)) {
                finalPaths.add(currentPath);
                return true;
            }
        }
        if (currentStop >= refNum) {
            return true;
        }
        return false;
    }

    private List<Path> convertToPath(List<Edge> routes, Path currentPath) {
        List<String> towns = currentPath.getTowns();
        return routes.stream().map(edge -> {
            List<String> newTowns = towns.stream().collect(toList());
            newTowns.add(edge.getEndTown());
            return new Path(newTowns);
        }).collect(toList());
    }

    private List<Path> convertToPath(List<Edge> edges) {
        return edges.stream().map(edge -> {
            List<String> towns = new ArrayList<>();
            towns.add(edge.getStartTown());
            towns.add(edge.getEndTown());
            return new Path(towns);
        }).collect(toList());
    }


}

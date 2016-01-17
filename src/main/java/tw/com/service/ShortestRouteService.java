package tw.com.service;

import tw.com.model.Edge;
import tw.com.model.Path;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.sun.xml.internal.bind.v2.schemagen.Util.equalsIgnoreCase;
import static java.util.stream.Collectors.toList;

public class ShortestRouteService {
    private static final int INFINITY = 999999;
    private final List<Edge> edges;
    private final CalculateDistance calculateDistance;

    public ShortestRouteService(IoService ioService,CalculateDistance calculateDistance) throws IOException {
        this.edges = ioService.read();
        this.calculateDistance = new CalculateDistance(ioService);

    }

    public List<Path> getNumOfPathsWithStopRequ(String startTown, String endTown) {
        List<Path> finalPaths = new ArrayList<Path>();
        List<Path> processedPaths = new ArrayList<Path>();

        List<Path> pathsFromStartTown = convertToPath(getEdgesStartFrom(startTown));
        processedPaths.addAll(pathsFromStartTown);

        for (Path path : pathsFromStartTown) {
            findNextPath(processedPaths, finalPaths, path, endTown, 0);
        }

        for (Path path : finalPaths) {
            path.getTowns().stream().forEach(System.out::print);
            System.out.println("");
        }
        return finalPaths;
    }

    private void findNextPath(List<Path> processedPaths, List<Path> finalPaths, Path currentPath, String endTown,
                              int currentStop) {
        String endTownOfCurrentPath = currentPath.getTowns().get(currentPath.getTowns().size() - 1);
        if (isNotRepeated(currentPath)) {
            if (equalsIgnoreCase(endTown, endTownOfCurrentPath)) {
                finalPaths.add(currentPath);
                return;
            }

            List<Edge> edges = getEdgesStartFrom(endTownOfCurrentPath);
            List<Path> newPaths = convertToPath(edges, currentPath);
            processedPaths.addAll(newPaths);

            for (Path newPath : newPaths) {

                findNextPath(processedPaths, finalPaths, newPath, endTown, currentStop + 1);

            }
        }
    }

    private List<Path> convertToPath(List<Edge> routes, Path currentPath) {
        List<String> towns = currentPath.getTowns();
        return routes.stream().map(new Function<Edge, Path>() {
            @Override
            public Path apply(Edge edge) {
                List<String> newTowns = towns.stream().collect(toList());
                newTowns.add(edge.getEndTown());
                return new Path(newTowns);
            }
        }).collect(toList());
    }

    private List<Path> convertToPath(List<Edge> edges) {
        return edges.stream().map(new Function<Edge, Path>() {
            @Override
            public Path apply(Edge edge) {
                List<String> towns = new ArrayList<>();
                towns.add(edge.getStartTown());
                towns.add(edge.getEndTown());
                return new Path(towns);
            }
        }).collect(toList());
    }

    private List<Edge> getEdgesStartFrom(String startTown) {
        return edges.stream().filter(new Predicate<Edge>() {
            @Override
            public boolean test(Edge edge) {
                return edge.getDistance() != INFINITY && equalsIgnoreCase(edge.getStartTown(),
                        startTown);
            }
        }).collect(toList());
    }


    public int findShortestRoute(String startTown, String endTown) {
        List<Path> pathList = getNumOfPathsWithStopRequ(startTown, endTown);
        int finalPathDistance = calculateDistance.getDistance(pathList.get(0));
        for (Path path : pathList) {
            int pathDistance = calculateDistance.getDistance(path);
            if (finalPathDistance > pathDistance) {
                finalPathDistance = pathDistance;
            }
        }
        return finalPathDistance;
    }

    private boolean isNotRepeated(Path currentPath) {
        List<String> towns = currentPath.getTowns().stream().collect(toList());
        String endOfCurrentPath = towns.get(towns.size() - 1);

        boolean flag = true;
        for (int i = 1; i < towns.size() - 2; i++) {
            if (endOfCurrentPath == towns.get(i)) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}

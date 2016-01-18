package tw.com.service;

import tw.com.model.Edge;
import tw.com.model.Path;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.sun.xml.internal.bind.v2.schemagen.Util.equalsIgnoreCase;
import static java.util.Collections.min;
import static java.util.stream.Collectors.toList;

public class RouteServiceWithoutLoop {
    private IoService ioService;
    private CalculateDistanceService calculateDistanceService;
    private CovertService covertService;


    public RouteServiceWithoutLoop(IoService ioService,CovertService covertService) throws IOException {
        this.ioService = ioService;
        this.covertService = covertService;
        this.calculateDistanceService = new CalculateDistanceService(ioService);

    }

    public List<Path> getNumOfPathsWithStopRequ(String startTown, String endTown, String require, int refNum) {
        List<Path> finalPaths = new ArrayList<Path>();
        List<Path> processedPaths = new ArrayList<Path>();

        List<Path> pathsFromStartTown = covertService.convertToPath(ioService.getEdgesStartFrom(startTown));
        processedPaths.addAll(pathsFromStartTown);

        for (Path path : pathsFromStartTown) {
            findNextPath(processedPaths, finalPaths, path, endTown, 0, require, refNum);
        }

        return finalPaths;
    }

    private void findNextPath(List<Path> processedPaths, List<Path> finalPaths, Path currentPath, String endTown,
                              int currentStop, String require, int refNum) {
        String endTownOfCurrentPath = currentPath.getTowns().get(currentPath.getTowns().size() - 1);
        if (isNotLooped(currentPath)) {
            if ("shortest".equals(require)) {
                if (equalsIgnoreCase(endTown, endTownOfCurrentPath)) {
                    finalPaths.add(currentPath);
                    return;
                }
            }
            if ("distance".equals(require)) {
                if (equalsIgnoreCase(endTown, endTownOfCurrentPath)
                        && calculateDistanceService.getDistance(currentPath) < refNum) {
                    finalPaths.add(currentPath);
                    return;
                }
            }

            List<Edge> edges = ioService.getEdgesStartFrom(endTownOfCurrentPath);
            List<Path> newPaths = covertService.convertToPath(edges, currentPath);
            processedPaths.addAll(newPaths);

            for (Path newPath : newPaths) {
                findNextPath(processedPaths, finalPaths, newPath, endTown, currentStop + 1, require, refNum);
            }
        }
    }

    public int findShortestDistance(String startTown, String endTown) {
        List<Path> pathList = getNumOfPathsWithStopRequ(startTown, endTown, "shortest", 0);
        List<Integer> distanceList = pathList.stream()
                .map(path -> calculateDistanceService.getDistance(path))
                .collect(toList());
        return min(distanceList);
    }

    private boolean isNotLooped(Path currentPath) {
        List<String> towns = currentPath.getTowns().stream().collect(toList());
        String endOfCurrentPath = towns.get(towns.size() - 1);

        boolean flag = true;
        if (towns.size() > 3 && endOfCurrentPath.equals(towns.get(towns.size() - 3))) flag = false;
        return flag;
    }
}

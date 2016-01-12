package tw.com.Utils;

import tw.com.model.Edge;
import tw.com.model.Town;

import java.io.IOException;
import java.lang.String;import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import static java.lang.Integer.parseInt;
import static java.nio.file.Files.readAllLines;

public class MapBuilder {
    public static List<Town> towns = new ArrayList<>();

    public static void initMap() throws IOException {
        List<String> lines;
        MapBuilder mapBuilder = new MapBuilder();
        lines = readAllLines(Paths.get("src/main/resource/input.txt"));
        for (String line : lines) {
            String[] vals = line.split("\\s+");
            Town townA = new Town(vals[0]);
            Town townB = new Town(vals[1]);
            double weight = parseInt(vals[2]);
            Town aDex = mapBuilder.addTownToMap(townA, towns);
            Town bDex = mapBuilder.addTownToMap(townB, towns);
            aDex.addEdge(bDex, weight);
        }
    }

    public static Town getTownByName(String name) {
        return towns.stream()
                .filter(town->town.getName().equals(name))
                .findAny()
                .get();
    }

    private void townBuilder(Town source) {
        source.setVertDistance(0.0);
        PriorityQueue<Town> townQueue = new PriorityQueue<Town>();
        townQueue.add(source);
        while (!townQueue.isEmpty()) {
            Town startTown = townQueue.poll();
            distanceAndPreBuilder(townQueue, startTown);
        }
    }

    private void distanceAndPreBuilder(PriorityQueue<Town> townQueue, Town startTown) {
        for (Edge edge : startTown.getAdj()) {
            Town destinationTown = edge.getDestination();
            double distToStart = startTown.getVertDistance() + edge.getDistance();
            if (distToStart < destinationTown.getVertDistance()) {
                townQueue.remove(destinationTown);
                destinationTown.setVertDistance(distToStart);
//                destinationTown.setPrevious(startTown);
                townQueue.add(destinationTown);
            }
        }
    }

    private Town addTownToMap(Town toAdd, List<Town> towns) {
        for (Town town : towns) {
            if (town.equals(toAdd))
                return town;
        }
        towns.add(toAdd);
        return towns.get(towns.size() - 1);
    }

}

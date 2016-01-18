package tw.com.service;

import tw.com.model.Edge;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static com.sun.xml.internal.bind.v2.schemagen.Util.equalsIgnoreCase;
import static java.nio.file.Files.readAllLines;
import static java.util.stream.Collectors.toList;

public class IoService {
    private static final int INFINITY = 999999;

    private List<Edge> edges;

    public List<Edge> read() throws IOException {
        List<String> texts = readAllLines(Paths.get("src/main/resource/input.txt"));
        return edges = texts.stream().map(line -> convertStringToEdge(line)).collect(toList());

    }

    public List<Edge> getEdgesStartFrom(String startTown) {
        return edges.stream().filter(edge -> edge.getDistance() != INFINITY && equalsIgnoreCase(edge.getStartTown(),
                startTown)).collect(toList());
    }

    private Edge convertStringToEdge(String line) {
        String startPoint = getString(line, 0);
        String endPoint = getString(line, 1);
        int distance = Integer.parseInt(getString(line, 2));
        return new Edge(startPoint, endPoint, distance);
    }

    private String getString(String line, int index) {
        return new String(new char[]{line.charAt(index)});
    }
}

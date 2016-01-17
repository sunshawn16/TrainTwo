package tw.com.service;

import tw.com.model.Edge;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.sun.xml.internal.bind.v2.schemagen.Util.equalsIgnoreCase;
import static java.nio.file.Files.readAllLines;
import static java.util.stream.Collectors.toList;

public class IoService {
    private static final int INFINITY = 999999;

    private List<Edge> edges;

    public List<Edge> read() throws IOException {
        List<String> texts = readAllLines(Paths.get("src/main/resource/input.txt"));
        return edges = texts.stream().map(new Function<String, Edge>() {
            @Override
            public Edge apply(String line) {
                return convertStringToEdge(line);
            }
        }).collect(Collectors.toList());

    }

    private Edge convertStringToEdge(String line) {
        String startPoint = new String(new char[]{line.charAt(0)});
        String endPoint = new String(new char[]{line.charAt(1)});
        int distance = Integer.parseInt(new String(new char[]{line.charAt(2)}));
        return new Edge(startPoint, endPoint, distance);
    }

    public List<Edge> getEdgesStartFrom(String startTown) {
        return edges.stream().filter(edge -> edge.getDistance() != INFINITY && equalsIgnoreCase(edge.getStartTown(),
                startTown)).collect(toList());
    }
}

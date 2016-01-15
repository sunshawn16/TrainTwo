package tw.com.service;

import tw.com.model.Edge;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.nio.file.Files.readAllLines;

public class IoService {
    public List<Edge> read() throws IOException {
        List<String> texts = readAllLines(Paths.get("src/main/resource/input.txt"));
        return texts.stream().map(new Function<String, Edge>() {
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
}

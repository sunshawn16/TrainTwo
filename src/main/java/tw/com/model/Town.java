package tw.com.model;


import java.util.ArrayList;

import static java.lang.Double.POSITIVE_INFINITY;
import static java.lang.Double.compare;
import static tw.com.Utils.MapBuilder.getTownByName;

public class Town implements Comparable<Town> {
    private String name;
    private ArrayList<Edge> adj = new ArrayList<Edge>();
    private double vertDistance = POSITIVE_INFINITY;

    public String getName() {
        return name;
    }

    public ArrayList<Edge> getAdj() {
        return adj;
    }

    public void setAdj(ArrayList<Edge> adj) {
        this.adj = adj;
    }

    public double getVertDistance() {
        return vertDistance;
    }

    public void setVertDistance(double vertDistance) {
        this.vertDistance = vertDistance;
    }

    public Town(String argName) {
        name = argName;
    }

    public String toString() {
        return name;
    }

    public int compareTo(Town other) {
        return compare(vertDistance, other.vertDistance);
    }

    public boolean equals(Town other) {
        return other.name.equals(name);
    }

    public void addEdge(Town target, double distance) {
        adj.add(new Edge(target, distance));
    }

    private Double getDisByDes(Town target) {
        return this.getAdj().stream()
                .filter(edge -> target.equals(edge.getDestination()))
                .findFirst()
                .map(Edge::getDistance)
                .orElse(null);
    }

    public static double calculateDistance(ArrayList<String> names) {
        double sumDistance = 0.0;
        for (int i = 0; i < names.size() - 1; i++) {
            Double distance = getTownByName(names.get(i)).getDisByDes(getTownByName(names.get(i + 1)));
            if (distance == null) {
//                System.out.println("NO SUCH ROUTE");
                throw new NullPointerException("NO SUCH ROUTE");
            }
            sumDistance = sumDistance + distance;
        }
        return sumDistance;

    }

}
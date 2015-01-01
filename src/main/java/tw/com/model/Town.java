package tw.com.model;


import java.util.ArrayList;

import static java.lang.Double.POSITIVE_INFINITY;
import static java.lang.Double.compare;

public class Town implements Comparable<Town> {
    private String name;
    private ArrayList<Edge> adj = new ArrayList<Edge>();
    private double vertDistance = POSITIVE_INFINITY;
    private Town previous;
    private Town next;

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

    public Double getDisByDes(Town target) {
        return this.getAdj().stream()
                .filter(edge -> target.equals(edge.getDestination()))
                .findFirst()
                .map(Edge::getDistance)
                .orElse(POSITIVE_INFINITY);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Town getPrevious() {
        return previous;
    }

    public void setPrevious(Town previous) {
        this.previous = previous;
    }

    public void setNext(Town next) {
        this.next = next;
    }

    public Town getNext() {
        return next;
    }
}
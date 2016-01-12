package tw.com.model;

public class Edge {
    private Town destination;
    private double distance;

    public Edge(Town target, double distance)
    {
        this.destination = target;
        this.distance = distance;
    }

    public Town getDestination() {
        return destination;
    }

    public void setDestination(Town destination) {
        this.destination = destination;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}

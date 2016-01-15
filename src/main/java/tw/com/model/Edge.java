package tw.com.model;

public class Edge {
    private String startTown;
    private String endTown;
    private int distance;

    public Edge(String startTown, String endTown, int distance) {
        this.startTown = startTown;
        this.endTown = endTown;
        this.distance = distance;
    }

    public String getStartTown() {
        return startTown;
    }

    public void setStartTown(String startTown) {
        this.startTown = startTown;
    }

    public String getEndTown() {
        return endTown;
    }

    public void setEndTown(String endTown) {
        this.endTown = endTown;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}

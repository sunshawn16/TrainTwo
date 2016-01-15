package tw.com.model;

import java.util.List;

public class Path {
    List<String> towns;

    public Path(List<String> towns) {
        this.towns = towns;
    }

    public List<String> getTowns() {
        return towns;
    }

    public void setTowns(List<String> towns) {
        this.towns = towns;
    }
}

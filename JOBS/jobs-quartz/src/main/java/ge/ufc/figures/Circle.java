package ge.ufc.figures;

import java.util.ArrayList;
import java.util.List;

public class Circle {
    private double radius;
    public Circle(){

    }
    public Circle(double radius) {
        this.radius = radius;
    }
    @Override
    public String toString() {
        return "Circle radius - " + radius+ ";";

    }
    private List<Circle> circleList = new ArrayList<>();

    public List<Circle> getCircleList() {
        return circleList;
    }
}

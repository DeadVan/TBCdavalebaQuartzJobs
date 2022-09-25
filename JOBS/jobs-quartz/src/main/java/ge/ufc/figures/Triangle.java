package ge.ufc.figures;

import java.util.ArrayList;
import java.util.List;

public class Triangle
{
    private double a;
    private double b;
    private double c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    private List<Triangle> triangleList = new ArrayList<>();

    public Triangle() {

    }

    public List<Triangle> getTriangleList() {
        return triangleList;
    }

    @Override
    public String toString() {
        return "Triangle  [" +
                "a - " + a +
                "b - " + b +
                "c - " + c + "]";
    }
}
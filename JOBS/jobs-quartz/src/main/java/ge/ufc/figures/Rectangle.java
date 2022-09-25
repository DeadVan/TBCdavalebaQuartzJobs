package ge.ufc.figures;

import java.util.ArrayList;
import java.util.List;

public class Rectangle
{
    private double width;
    private double length;
    public Rectangle(double width, double length) {
        this.width = width;
        this.length = length;
    }
    private List<Rectangle> rectangleList = new ArrayList<>();

    public Rectangle() {

    }

    public List<Rectangle> getRectangleList() {
        return rectangleList;
    }

    @Override
    public String toString() {
        return "Rectangle [" +
                "Width - " + width +
                "Length - " + length;
    }
}

package ru.academits.gerasimenko.shapes;

public class Triangle implements Shape {
    private final double x1;
    private final double y1;

    private final double x2;
    private final double y2;

    private final double x3;
    private final double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;

        this.x2 = x2;
        this.y2 = y2;

        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    public double getWidth() {
        return Math.max(x1, Math.max(x2, x3)) - Math.min(x1, Math.min(x2, x3));
    }

    @Override
    public double getHeight() {
        return Math.max(y1, Math.max(y2, y3)) - Math.min(y1, Math.min(y2, y3));
    }

    @Override
    public double getArea() {
        return Math.abs((x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1)) / 2;
    }

    private double getSideLength(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    @Override
    public double getPerimeter() {
        return getSideLength(x1, y1, x2, y2)
                + getSideLength(x1, y1, x3, y3)
                + getSideLength(x2, y2, x3, y3);
    }

    @Override
    public String toString() {
        return "Shape : Triangle" + System.lineSeparator()
                + "First point : " + String.format("(%f; %f)", x1, y1) + System.lineSeparator()
                + "First point : " + String.format("(%f; %f)", x2, y2) + System.lineSeparator()
                + "First point : " + String.format("(%f; %f)", x3, y3) + System.lineSeparator()
                + "Area : " + getArea() + System.lineSeparator()
                + "Perimeter : " + getPerimeter();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Triangle triangle = (Triangle) o;

        return Double.compare(x1, triangle.x1) == 0 && Double.compare(y1, triangle.y1) == 0 &&
                Double.compare(x2, triangle.x2) == 0 && Double.compare(y2, triangle.y2) == 0 &&
                Double.compare(x3, triangle.x3) == 0 && Double.compare(y3, triangle.y3) == 0;
    }

    @Override
    public int hashCode() {
        final int prime = 19;
        int hash = 1;

        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y3);

        return hash;
    }
}
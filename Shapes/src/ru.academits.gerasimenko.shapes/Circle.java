package ru.academits.gerasimenko.shapes;

public class Circle implements Shape {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getWidth() {
        return radius * 2;
    }

    @Override
    public double getHeight() {
        return radius * 2;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "Shape : Circle" + System.lineSeparator()
                + "Radius : " + radius + System.lineSeparator()
                + "Diameter : " + getHeight() + System.lineSeparator()
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

        Circle circle = (Circle) o;

        return Double.compare(radius, circle.radius) == 0;
    }

    @Override
    public int hashCode() {
        final int prime = 17;

        return prime + Double.hashCode(radius);
    }
}
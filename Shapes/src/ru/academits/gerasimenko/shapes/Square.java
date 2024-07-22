package ru.academits.gerasimenko.shapes;

public class Square implements Shape {
    private final double sideLength;

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public double getWidth() {
        return sideLength;
    }

    @Override
    public double getHeight() {
        return sideLength;
    }

    @Override
    public double getArea() {
        return sideLength * sideLength;
    }

    @Override
    public double getPerimeter() {
        return sideLength * 4;
    }

    @Override
    public String toString() {
        return "Shape: Square" + System.lineSeparator()
                + "Side length: " + sideLength + System.lineSeparator()
                + "Area: " + getArea() + System.lineSeparator()
                + "Perimeter: " + getPerimeter();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Square square = (Square) o;

        return sideLength == square.sideLength;
    }

    @Override
    public int hashCode() {
        final int prime = 17;

        return prime + Double.hashCode(sideLength);
    }
}
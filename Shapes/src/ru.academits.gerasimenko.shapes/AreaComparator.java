package ru.academits.gerasimenko.shapes;

import java.util.Comparator;

public class AreaComparator implements Comparator<Shape> {
    private static final double EPSILON = 1e-10;

    @Override
    public int compare(Shape o1, Shape o2) {
        if (o1.getArea() - o2.getArea() > EPSILON) {
            return 1;
        }

        if (o1.getArea() - o2.getArea() < -EPSILON) {
            return -1;
        }

        return 0;
    }
}
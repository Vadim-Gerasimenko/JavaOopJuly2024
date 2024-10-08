package ru.academits.gerasimenko.shapes;

import ru.academits.gerasimenko.shapes.comparators.AreaComparator;
import ru.academits.gerasimenko.shapes.comparators.PerimeterComparator;

import java.util.Arrays;
import java.util.Comparator;

public class ShapesSort {
    public static void sort(Shape[] shapes, Comparator<Shape> comparator) {
        Arrays.sort(shapes, comparator);
    }

    public static Shape getShapeWithOrderLargestArea(Shape[] shapes, int order) {
        validateOrderExistsInArray(shapes, order);

        sort(shapes, new AreaComparator());
        return shapes[shapes.length - order];
    }

    public static Shape getShapeWithOrderLargestPerimeter(Shape[] shapes, int order) {
        validateOrderExistsInArray(shapes, order);

        sort(shapes, new PerimeterComparator());
        return shapes[shapes.length - order];
    }

    private static void validateOrderExistsInArray(Shape[] shapes, int order) {
        if (shapes.length < order) {
            throw new ArrayIndexOutOfBoundsException(
                    "There is no shape in the array with the specified order. "
                            + "Array length: " + shapes.length + ". "
                            + "Current order: " + order
            );
        }
    }
}
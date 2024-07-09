package ru.academits.gerasimenko.shapes.main;

import ru.academits.gerasimenko.shapes.*;

import java.util.Arrays;

public class Main {
    public static Shape getShapeWithMaxArea(Shape[] shapes) {
        AreaComparator areaComparator = new AreaComparator();

        Arrays.sort(shapes, areaComparator);

        return shapes[shapes.length - 1];
    }

    public static void main(String[] args) {
        Shape[] shapes = {
                new Square(4), new Square(3),
                new Triangle(0, 0, 5, 0, 0, 5),
                new Triangle(-2, 0, 2, 0, 0, 2),
                new Rectangle(2, 4),
                new Rectangle(7, 2.5),
                new Circle(2),
                new Circle(3),
                new Circle(2)
        };

        Shape shapeWithMaxArea = getShapeWithMaxArea(shapes);

        System.out.println("Информация о фигуре с максимальной площадью:");
        System.out.println("Название фигуры: " + shapeWithMaxArea.toString());
        System.out.println("Ширина: " + shapeWithMaxArea.getWidth());
        System.out.println("Высота: " + shapeWithMaxArea.getHeight());
        System.out.println("Площадь: " + shapeWithMaxArea.getArea());
        System.out.println("Периметр: " + shapeWithMaxArea.getPerimeter());
    }
}
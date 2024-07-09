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
        Shape square1 = new Square(3.3);
        Shape square2 = new Square(4);

        Shape triangle1 = new Triangle(0, 0, 5, 0, 0, 5);
        Shape triangle2 = new Triangle(-2, 0, 2, 0, 0, 2);

        Shape rectangle1 = new Rectangle(2, 4);
        Shape rectangle2 = new Rectangle(7, 2.5);

        Shape circle1 = new Circle(2);
        Shape circle2 = new Circle(3);
        Shape circle3 = new Circle(4);

        Shape[] shapes = {square1, square2, triangle1, triangle2, rectangle1, rectangle2, circle1, circle2, circle3};

        Shape shapeWithMaxArea = getShapeWithMaxArea(shapes);

        System.out.println("Информация о фигуре с максимальной площадью:");
        System.out.println("Название фигуры: " + shapeWithMaxArea.getClass().getSimpleName());
        System.out.println("Ширина: " + shapeWithMaxArea.getWidth());
        System.out.println("Высота: " + shapeWithMaxArea.getHeight());
        System.out.println("Площадь: " + shapeWithMaxArea.getArea());
        System.out.println("Периметр: " + shapeWithMaxArea.getPerimeter());
    }
}
package ru.academits.gerasimenko.vector.main;

import ru.academits.gerasimenko.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(5);
        Vector vector2 = new Vector(vector1);
        Vector vector3 = new Vector(new double[]{1, 2.44354, 0, 3, 5, 0, 1});
        Vector vector4 = new Vector(4, new double[]{0, 1, 2});

        System.out.println("1-ый вектор: " + vector1);
        System.out.println("2-ой вектор: " + vector2);
        System.out.println("3-ий вектор: " + vector3);
        System.out.println("4-ый вектор: " + vector4);
        System.out.println();

        System.out.println("Размерность 3-его вектора: " + vector3.getSize());

        vector3.add(vector4);
        System.out.println("3-ий вектор после прибавления к нему 4-ого: " + vector3);

        vector4.subtract(vector3);
        System.out.println("4-ый вектор после вычитания из него 3-его: " + vector4);

        vector3.multiplyByScalar(4);
        System.out.println("3-ий вектор после умножения на скаляр: " + vector3);

        vector4.reverse();
        System.out.println("4-ый вектор после разворота: " + vector4);

        System.out.println("Длина 4-ого вектора: " + vector4.getLength());
        System.out.println();

        System.out.println("Компонента 3-его вектора по 1-ому индексу: " + vector3.getComponent(1));

        vector3.setComponent(1, 4.5);
        System.out.println("Компонента 3-его вектора по 1-ому индексу после изменения: " + vector3.getComponent(1));
        System.out.println();

        Vector vector5 = new Vector(new double[]{1, 2, 3, 4, 5});
        Vector vector6 = new Vector(new double[]{1, 2, 3, 4, 5});
        Vector vector7 = new Vector(new double[]{1, 2, 3, 3, 5});

        System.out.println("Сравнение 5-ого и 6-ого векторов на эквивалентность:");
        System.out.println("Результат: " + vector5.equals(vector6));
        System.out.println();

        System.out.println("Сравнение 6-ого и 7-ого векторов на эквивалентность:");
        System.out.println("Результат: " + vector6.equals(vector7));
        System.out.println();

        System.out.println("Хэш-код 2-ого вектора: " + vector2.hashCode());
        System.out.println();

        System.out.println("Результат сложения 5-ого и 7-ого векторов: " + Vector.getSum(vector5, vector7));
        System.out.println("Результат вычитания 5-ого вектора из 7-ого: " + Vector.getDifference(vector7, vector5));
        System.out.println("Скалярное произведение 5-ого и 7-ого векторов: " + Vector.getDotProduct(vector5, vector7));
    }
}
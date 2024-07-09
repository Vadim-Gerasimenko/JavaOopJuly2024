package ru.academits.gerasimenko.vector.main;

import ru.academits.gerasimenko.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(5);
        Vector vector2 = new Vector(vector1);
        Vector vector3 = new Vector(new double[]{1, 2.44354, 0, 3, 5, 0, 1});
        Vector vector4 = new Vector(4, new double[]{0, 1, 2});

        System.out.println(vector1);
        System.out.println(vector2);
        System.out.println(vector3);
        System.out.println(vector4);
    }
}
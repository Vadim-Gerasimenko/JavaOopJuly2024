package ru.academits.gerasimenko.vector;

import java.util.Arrays;

public class Vector {
    private double[] array;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("The dimension of the vector must be positive.");
        }

        array = new double[n];
    }

    public Vector(Vector vectorToCopy) {
        array = Arrays.copyOf(vectorToCopy.array, vectorToCopy.array.length);
    }

    public Vector(double[] array) {
        this.array = Arrays.copyOf(array, array.length);
    }

    public Vector(int n, double[] array) {
        array = Arrays.copyOf(array, n);
    }
}
package ru.academits.gerasimenko.vector;

import java.util.Arrays;

public class Vector {
    private final double[] array;

    public Vector(int n) {
        dimensionValidation(n);

        array = new double[n];
    }

    public Vector(Vector vectorToCopy) {
        array = Arrays.copyOf(vectorToCopy.array, vectorToCopy.array.length);
    }

    public Vector(double[] array) {
        this.array = Arrays.copyOf(array, array.length);
    }

    public Vector(int n, double[] array) {
        dimensionValidation(n);

        this.array = Arrays.copyOf(array, n);
    }

    private void dimensionValidation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("The dimension of the vector must be positive.");
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (double element : array) {
            stringBuilder.append(element);
            stringBuilder.append(", ");
        }

        stringBuilder.delete(stringBuilder.lastIndexOf(", "), stringBuilder.length());

        return stringBuilder.append("}").toString();
    }

    public int getSize() {
        return array.length;
    }
}
package ru.academits.gerasimenko.vector;

import java.util.Arrays;

public class Vector {
    private double[] array;

    public Vector(int size) {
        validateSize(size);

        array = new double[size];
    }

    public Vector(Vector vectorToCopy) {
        array = Arrays.copyOf(vectorToCopy.array, vectorToCopy.array.length);
    }

    public Vector(double[] array) {
        validateSize(array.length);

        this.array = Arrays.copyOf(array, array.length);
    }

    public Vector(int size, double[] array) {
        validateSize(size);

        this.array = Arrays.copyOf(array, size);
    }

    private void validateSize(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("The size of the vector must be positive. "
                    + System.lineSeparator()
                    + "Current size : " + size);
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("Index out of range. "
                    + System.lineSeparator()
                    + "Valid index : from \"0\" to \"vector size - 1\". "
                    + System.lineSeparator()
                    + "Current index : " + index);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{');

        for (double component : array) {
            stringBuilder.append(component).append(", ");
        }

        final int componentsSeparatorLength = 2;

        stringBuilder.delete(stringBuilder.length() - componentsSeparatorLength, stringBuilder.length());

        return stringBuilder.append('}').toString();
    }

    public int getSize() {
        return array.length;
    }

    public void add(Vector vector) {
        if (array.length < vector.array.length) {
            double[] sumArray = new double[vector.array.length];

            for (int i = 0; i < array.length; i++) {
                sumArray[i] += array[i];
            }

            for (int i = 0; i < vector.array.length; i++) {
                sumArray[i] += vector.array[i];
            }

            array = sumArray;
            return;
        }

        for (int i = 0; i < vector.array.length; i++) {
            array[i] += vector.array[i];
        }
    }

    public void subtract(Vector vector) {
        if (array.length < vector.array.length) {
            double[] differenceArray = new double[vector.array.length];

            for (int i = 0; i < array.length; i++) {
                differenceArray[i] += array[i];
            }

            for (int i = 0; i < vector.array.length; i++) {
                differenceArray[i] -= vector.array[i];
            }

            array = differenceArray;
            return;
        }

        for (int i = 0; i < vector.array.length; i++) {
            array[i] -= vector.array[i];
        }
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < array.length; i++) {
            array[i] *= scalar;
        }
    }

    public void reverse() {
        final int reverseCoefficient = -1;

        multiplyByScalar(-reverseCoefficient);
    }

    public double getLength() {
        double componentsSquaresSum = 0;

        for (double component : array) {
            componentsSquaresSum += component * component;
        }

        return Math.sqrt(componentsSquaresSum);
    }

    public double getComponent(int index) {
        validateIndex(index);

        return array[index];
    }

    public void setComponent(int index, double component) {
        validateIndex(index);

        array[index] = component;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Vector vector = (Vector) o;

        return Arrays.equals(array, vector.array);
    }

    @Override
    public int hashCode() {
        final int prime = 37;

        return prime + Arrays.hashCode(array);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(vector1);
        resultVector.add(vector2);

        return resultVector;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(vector1);
        resultVector.subtract(vector2);

        return resultVector;
    }

    public static double getDotProduct(Vector vector1, Vector vector2) {
        int minSize = Math.min(vector1.array.length, vector2.array.length);

        double dotProduct = 0;

        for (int i = 0; i < minSize; i++) {
            dotProduct += vector1.array[i] * vector2.array[i];
        }

        return dotProduct;
    }
}
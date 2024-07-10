package ru.academits.gerasimenko.vector;

import java.util.Arrays;

public class Vector {
    private double[] array;

    public Vector(int n) {
        validateSize(n);

        array = new double[n];
    }

    public Vector(Vector vectorToCopy) {
        array = Arrays.copyOf(vectorToCopy.array, vectorToCopy.array.length);
    }

    public Vector(double[] array) {
        this.array = Arrays.copyOf(array, array.length);
    }

    public Vector(int n, double[] array) {
        validateSize(n);

        this.array = Arrays.copyOf(array, n);
    }

    private void validateSize(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("The size of the vector must be positive.");
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= this.array.length) {
            throw new IllegalArgumentException("Index out of range.");
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (double component : array) {
            stringBuilder.append(component);
            stringBuilder.append(", ");
        }

        stringBuilder.delete(stringBuilder.lastIndexOf(", "), stringBuilder.length());
        stringBuilder.append("}");

        return stringBuilder.toString();
    }

    public int getSize() {
        return array.length;
    }

    public void add(Vector vector) {
        double[] sumArray = new double[Math.max(array.length, vector.array.length)];

        for (int i = 0; i < array.length; i++) {
            sumArray[i] += array[i];
        }

        for (int i = 0; i < vector.array.length; i++) {
            sumArray[i] += vector.array[i];
        }

        array = sumArray;
    }

    public void subtract(Vector vector) {
        double[] differenceArray = new double[Math.max(array.length, vector.array.length)];

        for (int i = 0; i < array.length; i++) {
            differenceArray[i] += array[i];
        }

        for (int i = 0; i < vector.array.length; i++) {
            differenceArray[i] -= vector.array[i];
        }

        array = differenceArray;
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < array.length; i++) {
            array[i] *= scalar;
        }
    }

    public void reverse() {
        final int reverseCoefficient = -1;

        for (int i = 0; i < array.length; i++) {
            array[i] *= reverseCoefficient;
        }
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

        if (array.length != vector.array.length) {
            return false;
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i] != vector.array[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 37;

        return prime + Arrays.hashCode(array);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(Math.max(vector1.array.length, vector2.array.length));

        for (int i = 0; i < vector1.array.length; i++) {
            resultVector.array[i] += vector1.array[i];
        }

        for (int i = 0; i < vector2.array.length; i++) {
            resultVector.array[i] += vector2.array[i];
        }

        return resultVector;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(Math.max(vector1.array.length, vector2.array.length));

        for (int i = 0; i < vector1.array.length; i++) {
            resultVector.array[i] += vector1.array[i];
        }

        for (int i = 0; i < vector2.array.length; i++) {
            resultVector.array[i] -= vector2.array[i];
        }

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
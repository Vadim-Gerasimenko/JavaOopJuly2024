package ru.academits.gerasimenko.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        validateSize(size);

        components = new double[size];
    }

    public Vector(Vector vectorToCopy) {
        components = Arrays.copyOf(vectorToCopy.components, vectorToCopy.components.length);
    }

    public Vector(double[] components) {
        validateSize(components.length);

        this.components = Arrays.copyOf(components, components.length);
    }

    public Vector(int size, double[] components) {
        validateSize(size);

        this.components = Arrays.copyOf(components, size);
    }

    private void validateSize(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("The size of the vector must be positive. "
                    + System.lineSeparator()
                    + "Current size : " + size);
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= components.length) {
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

        for (double component : components) {
            stringBuilder.append(component).append(", ");
        }

        final int componentsSeparatorLength = 2;

        stringBuilder.delete(stringBuilder.length() - componentsSeparatorLength, stringBuilder.length());

        return stringBuilder.append('}').toString();
    }

    public int getSize() {
        return components.length;
    }

    public void add(Vector vector) {
        if (components.length < vector.components.length) {
            double[] sumComponents = new double[vector.components.length];

            for (int i = 0; i < components.length; i++) {
                sumComponents[i] += components[i];
            }

            for (int i = 0; i < vector.components.length; i++) {
                sumComponents[i] += vector.components[i];
            }

            components = sumComponents;
            return;
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        if (components.length < vector.components.length) {
            double[] differenceComponents = new double[vector.components.length];

            for (int i = 0; i < components.length; i++) {
                differenceComponents[i] += components[i];
            }

            for (int i = 0; i < vector.components.length; i++) {
                differenceComponents[i] -= vector.components[i];
            }

            components = differenceComponents;
            return;
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] -= vector.components[i];
        }
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    public void reverse() {
        final int reverseCoefficient = -1;

        multiplyByScalar(-reverseCoefficient);
    }

    public double getLength() {
        double componentsSquaresSum = 0;

        for (double component : components) {
            componentsSquaresSum += component * component;
        }

        return Math.sqrt(componentsSquaresSum);
    }

    public double getComponent(int index) {
        validateIndex(index);

        return components[index];
    }

    public void setComponent(int index, double component) {
        validateIndex(index);

        components[index] = component;
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

        return Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        final int prime = 37;

        return prime + Arrays.hashCode(components);
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
        int minSize = Math.min(vector1.components.length, vector2.components.length);

        double dotProduct = 0;

        for (int i = 0; i < minSize; i++) {
            dotProduct += vector1.components[i] * vector2.components[i];
        }

        return dotProduct;
    }
}
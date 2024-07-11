package ru.academits.gerasimenko.matrix;

import ru.academits.gerasimenko.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int n, int m) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("The sizes of the matrix must be positive.");
        }

        rows = new Vector[n];

        for (int i = 0; i < n; i++) {
            rows[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrixToCopy) {
        int matrixToCopyRowsCount = matrixToCopy.getRowsCount();
        rows = new Vector[matrixToCopyRowsCount];
        for (int i = 0; i < matrixToCopyRowsCount; i++) {
            rows[i] = new Vector(matrixToCopy.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        rows = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(array[i]);
        }
    }

    public Matrix(Vector[] vectorsArray) {
        rows = new Vector[vectorsArray.length];

        for (int i = 0; i < vectorsArray.length; i++) {
            rows[i] = new Vector(vectorsArray[i]);
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        validateRowIndex(index);

        return rows[index];
    }

    public void setRow(Vector row, int index) {
        validateRowIndex(index);

        if (row.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("The row size must match with the sizes of the matrix rows.");
        }

        int rowSize = row.getSize();

        for (int i = 0; i < rowSize; i++) {
            rows[index].setComponent(i, row.getComponent(i));
        }
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new ArrayIndexOutOfBoundsException("The column index out of range.");
        }

        int rowsCount = getRowsCount();
        Vector column = new Vector(rowsCount);

        for (int i = 0; i < rowsCount; i++) {
            column.setComponent(i, rows[i].getComponent(index));
        }

        return column;
    }

    public void transpose() {
        int columnsCount = getColumnsCount();

        Matrix transposedMatrix = new Matrix(columnsCount, getRowsCount());

        for (int i = 0; i < columnsCount; i++) {
            transposedMatrix.rows[i] = getColumn(i);
        }

        rows = transposedMatrix.rows;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector row : rows) {
            row.multiplyByScalar(scalar);
        }
    }

    public void add(Matrix matrix) {
        validateArithmeticOperationsPossibility(matrix);

        int rowsCount = getRowsCount();

        for (int i = 0; i < rowsCount; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        validateArithmeticOperationsPossibility(matrix);

        int rowsCount = getRowsCount();

        for (int i = 0; i < rowsCount; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public void multiplyByVector(Vector vector) {
        if (getColumnsCount() != 1) {
            throw new IllegalArgumentException("To multiply by vector matrix must have only 1 column.");
        }

        int rowsCount = getRowsCount();

        Matrix resultMatrix = new Matrix(rowsCount, vector.getSize());

        for (int i = 0; i < rowsCount; i++) {
            Vector row = new Vector(vector);

            row.multiplyByScalar(rows[i].getComponent(0));
            resultMatrix.rows[i] = row;
        }

        rows = resultMatrix.rows;
    }

    public double getDeterminant() {
        if (getRowsCount() != getColumnsCount()) {
            throw new IllegalArgumentException("To calculate the determinant, the matrix must be square.");
        }

        return 0;
    } //TODO: get Determinant of the matrix

    private void validateRowIndex(int index) {
        if (index < 0 || index >= rows.length) {
            throw new ArrayIndexOutOfBoundsException("The row index out of range.");
        }
    }

    private void validateArithmeticOperationsPossibility(Matrix matrix) {
        if (getRowsCount() != matrix.getRowsCount() || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("For arithmetic operations, the matrices must be the same size.");
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        matrix1.validateArithmeticOperationsPossibility(matrix2);

        int rowsCount = matrix1.getRowsCount();
        Matrix resultMatrix = new Matrix(rowsCount, matrix1.getColumnsCount());

        for (int i = 0; i < rowsCount; i++) {
            resultMatrix.rows[i] = Vector.getSum(matrix1.rows[i], matrix2.rows[i]);
        }

        return resultMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        matrix1.validateArithmeticOperationsPossibility(matrix2);

        int rowsCount = matrix1.getRowsCount();
        Matrix resultMatrix = new Matrix(rowsCount, matrix1.getColumnsCount());

        for (int i = 0; i < rowsCount; i++) {
            resultMatrix.rows[i] = Vector.getDifference(matrix1.rows[i], matrix2.rows[i]);
        }

        return resultMatrix;
    }


    public static Matrix getMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("For multiplication columns number of the 1-st matrix " +
                    "must be equal to the rows number of the 2-nd matrix.");
        }

        int rowsCount = matrix1.getRowsCount();
        int columnsCount = matrix2.getColumnsCount();

        Matrix resultMatrix = new Matrix(rowsCount, columnsCount);

        for (int i = 0; i < rowsCount; i++) {
            Vector row = matrix1.rows[i];

            for (int j = 0; j < columnsCount; j++) {
                Vector column = matrix2.getColumn(j);

                resultMatrix.rows[i].setComponent(j, Vector.getDotProduct(row, column));
            }
        }

        return resultMatrix;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (Vector row : rows) {
            stringBuilder.append(row.toString());
            stringBuilder.append(", ");
        }

        stringBuilder.delete(stringBuilder.lastIndexOf(", "), stringBuilder.length());
        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}
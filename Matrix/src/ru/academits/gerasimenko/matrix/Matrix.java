package ru.academits.gerasimenko.matrix;

import ru.academits.gerasimenko.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        validateRowsCount(rowsCount);
        validateColumnsCount(columnsCount);

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
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
        validateRowsCount(array.length);

        int maxRowLength = 0;

        for (double[] arrayRow : array) {
            maxRowLength = Math.max(maxRowLength, arrayRow.length);
        }

        validateColumnsCount(maxRowLength);
        rows = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(maxRowLength, array[i]);
        }
    }

    public Matrix(Vector[] vectorsArray) {
        validateRowsCount(vectorsArray.length);

        int maxRowLength = 0;

        for (Vector vector : vectorsArray) {
            maxRowLength = Math.max(maxRowLength, vector.getSize());
        }

        rows = new Vector[vectorsArray.length];

        for (int i = 0; i < vectorsArray.length; i++) {
            rows[i] = new Vector(maxRowLength);
            rows[i].add(vectorsArray[i]);
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

        return new Vector(rows[index]);
    }

    public void setRow(Vector row, int index) {
        validateRowIndex(index);

        if (row.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("The row size must match with the sizes of the matrix rows. "
                    + "Matrix rows size: " + getColumnsCount() + ". "
                    + "Current row size: " + row.getSize() + "."
            );
        }

        int rowSize = row.getSize();

        for (int i = 0; i < rowSize; i++) {
            rows[index].setComponent(i, row.getComponent(i));
        }
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("The column index out of range. "
                    + "Valid indexes: from 0 to " + (getColumnsCount() - 1) + ". "
                    + "Current index: " + index + "."
            );
        }

        int rowsCount = getRowsCount();
        Vector column = new Vector(rowsCount);

        for (int i = 0; i < rowsCount; i++) {
            column.setComponent(i, rows[i].getComponent(index));
        }

        return column;
    }

    public void transpose() {
        int rowsCount = getRowsCount();

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = getColumn(i);
        }
    }

    public void multiplyByScalar(double scalar) {
        for (Vector row : rows) {
            row.multiplyByScalar(scalar);
        }
    }

    public void add(Matrix matrix) {
        validateSizeEquality(matrix);

        int rowsCount = getRowsCount();

        for (int i = 0; i < rowsCount; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        validateSizeEquality(matrix);

        int rowsCount = getRowsCount();

        for (int i = 0; i < rowsCount; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public Vector multiplyByVector(Vector vector) {
        if (getColumnsCount() != vector.getSize()) {
            throw new IllegalArgumentException("The size of the column vector must be equal to the number of matrix columns. "
                    + "Matrix columns count: " + getColumnsCount() + ". "
                    + "Column vector size: " + vector.getSize() + "."
            );
        }

        int rowsCount = getRowsCount();
        int vectorSize = vector.getSize();

        Vector resultVector = new Vector(rowsCount);

        for (int i = 0; i < rowsCount; i++) {
            double component = 0;

            for (int j = 0; j < vectorSize; j++) {
                component += rows[i].getComponent(j) * vector.getComponent(j);
            }

            resultVector.setComponent(i, component);
        }

        return resultVector;
    }

    private void validateRowsCount(int rowsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Matrix rows count must be positive. "
                    + "Current rows count: " + rowsCount + "."
            );
        }
    }

    private void validateColumnsCount(int columnsCount) {
        if (columnsCount <= 0) {
            throw new IllegalArgumentException("Matrix columns count must be positive. "
                    + "Current columns count: " + columnsCount + "."
            );
        }
    }

    private void validateRowIndex(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("The row index out of range. "
                    + "Valid indexes: from 0 to " + (rows.length - 1) + "."
                    + "Current index: " + index + "."
            );
        }
    }

    private void validateSizeEquality(Matrix matrix) {
        if (getRowsCount() != matrix.getRowsCount() || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("For arithmetic operations, the matrices must be the same size. "
                    + "Current matrix rows count: " + getRowsCount() + ", "
                    + "columns count: " + getColumnsCount() + ". "
                    + "Passed matrix rows count: " + matrix.getRowsCount() + ", "
                    + "columns count: " + matrix.getColumnsCount() + "."
            );
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        matrix1.validateSizeEquality(matrix2);

        Matrix resultMatrix = new Matrix(matrix1.getRowsCount(), matrix1.getColumnsCount());

        resultMatrix.add(matrix1);
        resultMatrix.add(matrix2);

        return resultMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        matrix1.validateSizeEquality(matrix2);

        Matrix resultMatrix = new Matrix(matrix1.getRowsCount(), matrix1.getColumnsCount());

        resultMatrix.add(matrix1);
        resultMatrix.subtract(matrix2);

        return resultMatrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("For the product columns the number of the 1-st matrix "
                    + "must be equal to the rows number of the 2-nd matrix. "
                    + "First matrix columns count: " + matrix1.getColumnsCount() + ". "
                    + "Second matrix rows count: " + matrix2.getRowsCount() + "."
            );
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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{');

        for (Vector row : rows) {
            stringBuilder.append(row).append(", ");
        }

        final int separatorLength = 2;

        stringBuilder.delete(stringBuilder.length() - separatorLength, stringBuilder.length());
        stringBuilder.append('}');

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Matrix matrix = (Matrix) o;

        if (rows.length != matrix.rows.length || this.getColumnsCount() != matrix.getColumnsCount()) {
            return false;
        }

        for (int i = 0; i < rows.length; i++) {
            if (!rows[i].equals(matrix.rows[i])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 17;
        int hash = 1;

        for (Vector row : rows) {
            hash = prime * hash + row.hashCode();
        }

        return hash;
    }
}
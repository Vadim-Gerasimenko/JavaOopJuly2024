package ru.academits.gerasimenko.matrix.main;

import ru.academits.gerasimenko.matrix.Matrix;
import ru.academits.gerasimenko.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(3, 3);

        System.out.println("1-st matrix: " + matrix1);
        System.out.println("Rows count: " + matrix1.getRowsCount());
        System.out.println("Columns count: " + matrix1.getColumnsCount());

        System.out.println();

        Matrix matrix2 = new Matrix(new double[][]{{1, 4, 3}, {2, 5, 7}, {6, 8, 9}});
        System.out.println("2-nd matrix: " + matrix2);

        Matrix matrix3 = new Matrix(matrix2);
        System.out.println("3-rd matrix: " + matrix3);

        Vector vector = new Vector(new double[]{17, 23, 42});
        Vector vectorCopy = new Vector(vector);

        Vector[] vectorsArray = {vectorCopy, vector, vectorCopy};

        Matrix matrix4 = new Matrix(vectorsArray);
        System.out.println("4-th matrix: " + matrix4);

        System.out.println();

        System.out.println("3-rd row from 2-nd matrix: " + matrix2.getRow(2));

        matrix2.setRow(vector, 2);
        System.out.println("2-nd matrix after setting the 3-rd row: " + matrix2);

        System.out.println("3-rd column from 2-nd matrix: " + matrix2.getColumn(2));

        System.out.println();

        System.out.println("2-nd matrix: " + matrix2);

        matrix2.multiplyByScalar(3);
        System.out.println("2-nd matrix after multiplication by scalar: " + matrix2);

        System.out.println();

        Matrix matrix5 = new Matrix(new double[][]{{2, 3, 4}, {-5, 6, -7}, {8, -9, 10}});
        System.out.println("5-th matrix: " + matrix5);
        System.out.println("5-th matrix determinant: " + matrix5.getDeterminant());

        System.out.println();

        Matrix matrix6 = new Matrix(new double[][]{{-1, 3, 5}, {5, 6, -3}, {1, -1, -1}});
        System.out.println("6-th matrix: " + matrix6);

        matrix5.add(matrix6);
        System.out.println("5-th matrix after adding 6-th matrix: " + matrix5);

        matrix6.subtract(matrix5);
        System.out.println("6-th matrix after subtracting 5-th matrix: " + matrix6);

        System.out.println();

        System.out.println("5-th matrix now: " + matrix5);
        System.out.println("6-th matrix now: " + matrix6);
        System.out.println("Sum of the 5-th and 6-th matrices: " + Matrix.getSum(matrix5, matrix6));
        System.out.println("Subtracting 6-th matrix from 5-th matrix: " + Matrix.getDifference(matrix5, matrix6));

        System.out.println();

        Matrix matrix7 = new Matrix(new double[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}});
        System.out.println("7-th matrix: " + matrix7);

        Vector vectorToMultiplication = new Vector(new double[]{5, 7});
        System.out.println("Column vector to multiplication: " + vectorToMultiplication);
        System.out.println("The result of multiplying a 7-th matrix by the column vector: "
                + matrix7.multiplyByVector(vectorToMultiplication));

        System.out.println();

        System.out.println("2-nd matrix: " + matrix2);

        Matrix matrix8 = new Matrix(new double[][]{{1, 2, 3, 4, 5}, {2, 3, 4, 5, 6}, {0, 1, 2, 7, 1}});
        System.out.println("8-th matrix: " + matrix8);

        matrix8.transpose();
        System.out.println("8-th matrix after transposition: " + matrix8);

        matrix8.transpose();
        System.out.println("8-th matrix after re-transposition: " + matrix8);

        System.out.println("Result of product of 2-nd and 8-th matrices: " + Matrix.getProduct(matrix2, matrix8));
    }
}
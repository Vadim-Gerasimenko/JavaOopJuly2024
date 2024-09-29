package ru.academits.gerasimenko.graph;

import java.util.Deque;
import java.util.Queue;
import java.util.LinkedList;
import java.util.function.IntConsumer;

public class Graph {
    private final int[][] adjacencyMatrix;

    public Graph(int[][] adjacencyMatrix) {
        for (int[] component : adjacencyMatrix) {
            if (adjacencyMatrix.length != component.length) {
                throw new IllegalArgumentException("the adjacency matrix must be square.");
            }
        }

        this.adjacencyMatrix = adjacencyMatrix;
    }

    public void searchBreadthFirst(IntConsumer consumer) {
        int verticesCount = adjacencyMatrix.length;
        boolean[] visited = new boolean[verticesCount];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < verticesCount; ++i) {
            if (visited[i]) {
                continue;
            }

            queue.add(i);

            while (!queue.isEmpty()) {
                int vertex = queue.remove();

                if (visited[vertex]) {
                    continue;
                }

                consumer.accept(vertex);
                visited[vertex] = true;

                for (int j = 0; j < verticesCount; ++j) {
                    if (vertex != j && adjacencyMatrix[vertex][j] != 0 && !visited[j]) {
                        queue.add(j);
                    }
                }
            }
        }
    }

    public void searchDepthFirst(IntConsumer consumer) {
        int verticesCount = adjacencyMatrix.length;
        boolean[] visited = new boolean[verticesCount];
        Deque<Integer> stack = new LinkedList<>();

        for (int i = 0; i < verticesCount; ++i) {
            if (visited[i]) {
                continue;
            }

            stack.push(i);

            while (!stack.isEmpty()) {
                int vertex = stack.pop();

                if (visited[vertex]) {
                    continue;
                }

                consumer.accept(vertex);
                visited[vertex] = true;

                for (int j = verticesCount - 1; j != 0; --j) {
                    if (vertex != j && adjacencyMatrix[vertex][j] != 0 && !visited[j]) {
                        stack.push(j);
                    }
                }
            }
        }
    }

    public void searchDepthFirstRecursively(IntConsumer consumer) {
        int verticesCount = adjacencyMatrix.length;
        boolean[] visited = new boolean[verticesCount];

        for (int i = 0; i < verticesCount; ++i) {
            if (!visited[i]) {
                searchDepthFirstRecursively(visited, i, consumer);
            }
        }
    }

    private void searchDepthFirstRecursively(boolean[] visited, int vertex, IntConsumer consumer) {
        consumer.accept(vertex);
        visited[vertex] = true;

        for (int i = 0; i < visited.length; ++i) {
            if (vertex != i && adjacencyMatrix[vertex][i] != 0 && !visited[i]) {
                searchDepthFirstRecursively(visited, i, consumer);
            }
        }
    }
}
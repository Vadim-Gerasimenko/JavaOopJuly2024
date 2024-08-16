package ru.academits.gerasimenko.graph;

import java.util.Deque;
import java.util.Queue;
import java.util.LinkedList;

public class Graph {
    private final boolean[][] graph;

    public Graph(boolean[][] graph) {
        for (boolean[] component : graph) {
            if (graph.length != component.length) {
                throw new IllegalArgumentException("the adjacency matrix must be square.");
            }
        }

        this.graph = graph;
    }

    public static void breadthFirstSearch(Graph graph) {
        final int verticesCount = graph.graph.length;
        boolean[] visited = new boolean[verticesCount];
        Queue<Integer> queue = new LinkedList<>();

        System.out.println("BFS and print vertices:");

        for (int i = 0; i < verticesCount; ++i) {
            queue.add(i);

            while (!queue.isEmpty()) {
                int vertex = queue.remove();

                if (!visited[vertex]) {
                    System.out.print(vertex + " ");

                    for (int j = 0; j < verticesCount; ++j) {
                        if (vertex != j && graph.graph[vertex][j] && !visited[j]) {
                            queue.add(j);
                        }
                    }

                    visited[vertex] = true;
                }
            }
        }

        System.out.println();
    }


    public static void depthFirstSearch(Graph graph) {
        final int verticesCount = graph.graph.length;
        boolean[] visited = new boolean[verticesCount];
        Deque<Integer> stack = new LinkedList<>();

        System.out.println("DFS and print vertices:");

        for (int i = 0; i < verticesCount; ++i) {
            stack.addFirst(i);

            while (!stack.isEmpty()) {
                int vertex = stack.removeFirst();

                if (!visited[vertex]) {
                    System.out.print(vertex + " ");

                    for (int j = verticesCount - 1; j != 0; --j) {
                        if (vertex != j && graph.graph[vertex][j] && !visited[j]) {
                            stack.addFirst(j);
                        }
                    }

                    visited[vertex] = true;
                }
            }
        }

        System.out.println();
    }

    public static void depthFirstSearchRecursive(Graph graph) {
        final int verticesCount = graph.graph.length;
        boolean[] visited = new boolean[verticesCount];

        System.out.println("Recursive DFS and print vertices:");
        visit(graph, visited, 0);
        System.out.println();
    }

    private static void visit(Graph graph, boolean[] visited, int vertex) {
        visited[vertex] = true;
        System.out.print(vertex + " ");

        for (int i = 0; i < visited.length; ++i) {
            if (vertex != i && graph.graph[vertex][i] && !visited[i]) {
                visit(graph, visited, i);
            }
        }
    }
}
package ru.academits.gerasimenko.graph.main;

import ru.academits.gerasimenko.graph.Graph;

public class Main {
    public static void main(String[] args) {
        int[][] adjacencyMatrix = {
                {0, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 1, 0},
                {0, 1, 0, 1, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 1, 0, 1},
                {0, 0, 1, 0, 0, 1, 0}
        };

        Graph graph = new Graph(adjacencyMatrix);

        System.out.println("BFS and print vertices numbers:");
        graph.searchBreadthFirst(e -> System.out.print(e + " "));
        System.out.println();

        System.out.println("DFS and print vertices numbers:");
        graph.searchDepthFirst(e -> System.out.print(e + " "));
        System.out.println();

        System.out.println("Recursively DFS and print vertices numbers:");
        graph.searchDepthFirstRecursively(e -> System.out.print(e + " "));
    }
}
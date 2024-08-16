package ru.academits.gerasimenko.graph.main;

import ru.academits.gerasimenko.graph.Graph;

public class Main {
    public static void main(String[] args) {
        boolean[][] adjacencyMatrix = new boolean[][]{
                {false, true, false, false, false, false, false},
                {true, false, true, true, true, true, false},
                {false, true, false, false, false, false, true},
                {false, true, false, false, false, false, false},
                {false, true, false, false, false, true, false},
                {false, true, false, false, true, false, true},
                {false, false, true, false, false, true, false},
        };

        Graph graph = new Graph(adjacencyMatrix);

        Graph.breadthFirstSearch(graph);
        Graph.depthFirstSearch(graph);
        Graph.depthFirstSearchRecursive(graph);
    }
}
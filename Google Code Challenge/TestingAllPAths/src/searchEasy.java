import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class searchEasy {
    private static final int START = 0;
    private static final int END = 19;

    public static void main(String[] args) {
        // this graph is directional
        Graph graph = new Graph();
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);

        graph.addEdge(1,0);
        graph.addEdge(1,5);
        graph.addEdge(1,2);
        graph.addEdge(2,1);
        graph.addEdge(2,6);
        graph.addEdge(2,3);

        graph.addEdge(3,2);
        graph.addEdge(3,7);

        graph.addEdge(7,3);
        graph.addEdge(7,6);
        graph.addEdge(7,11);

        graph.addEdge(5,1);
        graph.addEdge(5,9);
        graph.addEdge(5,6);
        graph.addEdge(5,4);

        graph.addEdge(9,8);
        graph.addEdge(9,5);
        graph.addEdge(9,13);
        graph.addEdge(9,10);

        graph.addEdge(13,17);
        graph.addEdge(13,14);
        graph.addEdge(13,9);
        graph.addEdge(13,12);

        graph.addEdge(4,0);
        graph.addEdge(4,5);
        graph.addEdge(4,8);
        graph.addEdge(8,4);
        graph.addEdge(8,12);
        graph.addEdge(8,9);
        graph.addEdge(12,8);
        graph.addEdge(12,16);
        graph.addEdge(12,13);
        graph.addEdge(16,12);
        graph.addEdge(16,17);
        graph.addEdge(17,13);
        graph.addEdge(17,16);
        graph.addEdge(17,18);

        graph.addEdge(18,17);
        graph.addEdge(18,14);
        graph.addEdge(18,19);

        graph.addEdge(19,18);
        graph.addEdge(19,15);
        LinkedList<Integer> visited = new LinkedList();
        List<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
        int currentNode = START;
        visited.add(START);
        new searchEasy().findAllPaths(graph, visited, paths, currentNode);
        for(ArrayList<Integer> path : paths){
            for (Integer node : path) {
                System.out.print(node);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    private void findAllPaths(Graph graph, List<Integer> visited, List<ArrayList<Integer>> paths, int currentNode) {
        if (currentNode == END) {
            paths.add(new ArrayList(Arrays.asList(visited.toArray())));
            return;
        }
        else {
            LinkedList<Integer> nodes = graph.adjacentNodes(currentNode);
            for (int node : nodes) {
                if (visited.contains(node)) {
                    continue;
                }
                List<Integer> temp = new ArrayList<Integer>();
                temp.addAll(visited);
                temp.add(node);
                findAllPaths(graph, temp, paths, node);
            }
        }
    }
}
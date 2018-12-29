import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Graph {
    private Map<Integer, LinkedHashSet<Integer>> map = new HashMap();

    public void addEdge(int node1, int node2) {
        LinkedHashSet<Integer> adjacent = map.get(node1);
        if(adjacent==null) {
            adjacent = new LinkedHashSet();
            map.put(node1, adjacent);
        }
        adjacent.add(node2);
    }

    public void addTwoWayVertex(int node1, int node2) {
        addEdge(node1, node2);
        addEdge(node2, node1);
    }

    public boolean isConnected(int node1, int node2) {
        Set adjacent = map.get(node1);
        if(adjacent==null) {
            return false;
        }
        return adjacent.contains(node2);
    }

    public LinkedList<Integer> adjacentNodes(int last) {
        LinkedHashSet<Integer> adjacent = map.get(last);
        if(adjacent==null) {
            return new LinkedList();
        }
        return new LinkedList<Integer>(adjacent);
    }
}
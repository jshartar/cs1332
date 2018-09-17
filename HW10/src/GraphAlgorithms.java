import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.LinkedList;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.HashMap;
/**
 * Your implementation of various different graph algorithms.
 *
 * @author jordan shartar
 * @userid jshartar6
 * @GTID 903131050
 * @version 1.0
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     *
     * When exploring a vertex, make sure to explore in the order that the
     * adjacency list returns the neighbors to you. Failure to do so may cause
     * you to lose points.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.List},
     * {@code java.util.Queue}, and any classes that implement the
     * aforementioned interfaces, as long as it is efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for BFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the bfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     */
    // needs to check taht visited set is same size of # of vertices to terminate loop
    public static <T> List<Vertex<T>> breadthFirstSearch(Vertex<T> start,
                                            Graph<T> graph) {
        if (start == null || graph == null
                || graph.getAdjList().get(start) == null) {
            throw new IllegalArgumentException("start, graph is null/ invalid "
                    + "or start not in the graph");
        }
        List<Vertex<T>> out = new ArrayList<>();
        HashSet<Vertex<T>> visited = new HashSet<>();
        Queue<Vertex<T>> toSee = new LinkedList<>();
        toSee.add(start);
        while (!toSee.isEmpty()) {
            Vertex<T> current = toSee.remove();
            if (!visited.contains(current)) {
                out.add(current);
                for (Edge<T> edge : graph.getAdjList().get(current)) {
                    toSee.add(edge.getV());
                }
                visited.add(current);
            }
        }
        return out;
    }


    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     *
     * When deciding which neighbors to visit next from a vertex, visit the
     * vertices in the order presented in that entry of the adjacency list.
     *
     * *NOTE* You MUST implement this method recursively, or else you will lose
     * most if not all points for this method.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.List}, and
     * any classes that implement the aforementioned interfaces, as long as it
     * is efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     */
    public static <T> List<Vertex<T>> depthFirstSearch(Vertex<T> start,
                                            Graph<T> graph) {
        if (start == null || graph == null
                || graph.getAdjList().get(start) == null) {
            throw new IllegalArgumentException("start, graph is null/ invalid "
                    + "or start not in the graph");
        }
        List<Vertex<T>> out = new ArrayList<>();
        Set<Vertex<T>>  visited = new HashSet<>();
        dfsHelp(start, graph, visited, out);
        return out;
    }
    /**
     * Recursive Helper method for dfs traversal, builds the call stack
     *
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @param visited visited nodes
     * @param out list of vertex to return
     * @param <T> the generic typing of the data
     * @return out variable
     */
    private static <T> List<Vertex<T>> dfsHelp(Vertex<T> start, Graph<T> graph,
                                               Set<Vertex<T>> visited,
                                               List<Vertex<T>> out) {
        out.add(start);
        visited.add(start);
        for (Edge<T> edge : graph.getAdjList().get(start)) {
            if (!visited.contains(edge.getV())) {
                dfsHelp(edge.getV(), graph, visited, out);
            }
        }
        return out;
    }


    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing infinity)
     * if no path exists.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Map}, and {@code java.util.Set} and any class that
     * implements the aforementioned interfaces, as long as it's efficient.
     *
     * You should implement the version of Dijkstra's where you terminate the
     * algorithm once either all vertices have been visited or the PQ becomes
     * empty.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input is null, or if start
     *  doesn't exist in the graph.
     * @param <T> the generic typing of the data
     * @param start index representing which vertex to start at (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every other node
     *         in the graph
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                      Graph<T> graph) {
        if (start == null || graph == null
                || graph.getAdjList().get(start) == null) {
            throw new IllegalArgumentException("start, graph is null/ invalid "
                    + "or start not in the graph");
        }
        Map<Vertex<T>, Integer> lengthAway = new HashMap<>();
        Queue<Edge<T>> toView = new PriorityQueue<>();
        Set<Vertex<T>> visited = new HashSet<>();
        toView.add(new Edge<T>(start, start, 0));
        int i = 0;
        for (Vertex<T> v : graph.getAdjList().keySet()) {
            lengthAway.put(v, Integer.MAX_VALUE);
            i++;
        }
        while (!toView.isEmpty() && visited.size() != i) {
            Edge<T> current = toView.remove();
            if (lengthAway.get(current.getV()) > current.getWeight()) {
                lengthAway.put(current.getV(), current.getWeight());
                for (Edge<T> e : graph.getAdjList().get(current.getV())) {
                    int weight = lengthAway.get(e.getU()) + e.getWeight();
                    toView.add(new Edge<>(e.getU(), e.getV(), weight));
                }
                visited.add(current.getV());
            }
        }
        return lengthAway;
    }


    /**
     * Runs Prim's algorithm on the given graph and return the Minimal
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * opposite edge to the set as well. This is for testing purposes.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * You should NOT allow self-loops into the MST.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Set}, and any class that implements the aforementioned
     * interface, as long as it's efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for this method (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin Prims on
     * @param graph the graph we are applying Prims to
     * @return the MST of the graph or null if there is no valid MST
     */
    // Prims fails for single vertex and large graph (should not check weight);
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null
                || graph.getAdjList().get(start) == null) {
            throw new IllegalArgumentException("start, graph is null/ invalid "
                    + "or start not in the graph");
        }
        Map<Vertex<T>, Integer> away = new HashMap<>();
        Set<Edge<T>> mst = new HashSet<>();
        Set<Vertex<T>> visited = new HashSet<>();
        Queue<Edge<T>> toView = new PriorityQueue<>();
        toView.add(new Edge<T>(start, start, 0));
        int i = 0;
        for (Vertex<T> v : graph.getAdjList().keySet()) {
            away.put(v, Integer.MAX_VALUE);
            i++;
        }
        while (!toView.isEmpty() && visited.size() != i) {
            Edge<T> current = toView.remove();
            if (away.get(current.getV()) > current.getWeight()) {
                away.put(current.getV(), current.getWeight());
                for (Edge<T> e : graph.getAdjList().get(current.getV())) {
                    toView.add(new Edge<>(e.getU(), e.getV(), e.getWeight()));
                    toView.add(new Edge<>(e.getV(), e.getU(), e.getWeight()));
                }
                if (!visited.contains(current.getV())) {
                    mst.add(toView.peek());
                    mst.add(new Edge<>(toView.peek().getV(),
                            toView.peek().getU(), toView.peek().getWeight()));
                    visited.add(current.getV());
                }
            }
        }
        if (visited.size() < graph.getVertices().size()) {
            return null;
        }
        return mst;
    }
}
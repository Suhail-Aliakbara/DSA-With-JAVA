import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class graph {
  /*
   * Print adjacency list
   * Given an undirected graph with V nodes and E edges, create and return an
   * adjacency list of the graph. 0-based indexing is followed everywhere.
   * Input:
   * V = 5, E = 7
   * edges = [[0,1], [0,4], [4,1], [4,3], [1,3], [1,2], [3,2]]
   * 
   * Output:
   * [[1,4], [0,2,3,4], [1,3], [1,2,4], [0,1,3]]
   * 
   */
  public List<List<Integer>> printGraph(int V, int edges[][]) {

    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < V; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < edges.length; i++) {
      int connection1 = edges[i][0];
      int connection2 = edges[i][1];
      graph.get(connection1).add(connection2);
      graph.get(connection2).add(connection1);
    }

    return graph;
  }

  /*
   * BFS of graph
   * Given an undirected graph represented by an adjacency list adj, which is a
   * vector of vectors where each adj[i] represents the list of vertices connected
   * to vertex i. Perform a Breadth First Traversal (BFS) starting from vertex 0,
   * visiting vertices from left to right according to the adjacency list, and
   * return a list containing the BFS traversal of the graph.
   * Input: adj = [[2,3,1], [0], [0,4], [0], [2]]
   * Output: [0, 2, 3, 1, 4]
   */
  public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
    ArrayList<Integer> bfsTraversal = new ArrayList<>();
    Queue<Integer> queue = new LinkedList<>();
    boolean[] visited = new boolean[V];

    // Start BFS from vertex 0
    queue.add(0);
    visited[0] = true;

    while (!queue.isEmpty()) {
      int currentVertex = queue.remove();
      bfsTraversal.add(currentVertex);

      // Get all adjacent vertices of the dequeued vertex
      ArrayList<Integer> adjacentVertices = adj.get(currentVertex); // List of adjNodes
      for (int i = 0; i < adjacentVertices.size(); i++) {
        int adjacentVertex = adjacentVertices.get(i);
        if (!visited[adjacentVertex]) {
          queue.add(adjacentVertex);
          visited[adjacentVertex] = true;
        }
      }
    }
    return bfsTraversal;
  }

  /*
   * Graphs_ Source to Destination Path
   * You have been given edges of a graph and a source node(sn) and destination
   * node(dn). Return true if there is a path from source node to destination node
   * or false otherwise.
   */
  public boolean srcToDes(int V, ArrayList<ArrayList<Integer>> adj, int src, int destination) {
    Queue<Integer> queue = new LinkedList<>();
    boolean[] visited = new boolean[V];

    // Start BFS from vertex 0
    queue.add(src);
    visited[src] = true;

    while (!queue.isEmpty()) {
      int currentVertex = queue.remove();

      // Get all adjacent vertices of the dequeued vertex
      ArrayList<Integer> adjacentVertices = adj.get(currentVertex); // List of adjNodes
      for (int i = 0; i < adjacentVertices.size(); i++) {
        int adjacentVertex = adjacentVertices.get(i);
        if (!visited[adjacentVertex]) {
          queue.add(adjacentVertex);
          visited[adjacentVertex] = true;
        }
      }
    }
    return visited[destination];
  }

  // Example usage
  public static void main(String[] args) {
    graph g = new graph();
    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    adj.add(new ArrayList<>(List.of(2, 3, 1)));
    adj.add(new ArrayList<>(List.of(0)));
    adj.add(new ArrayList<>(List.of(0, 4)));
    adj.add(new ArrayList<>(List.of(0)));
    adj.add(new ArrayList<>(List.of(2)));

    ArrayList<Integer> bfsResult = g.bfsOfGraph(5, adj);
    System.out.println(bfsResult); // Output: [0, 2, 3, 1, 4]
  }
}
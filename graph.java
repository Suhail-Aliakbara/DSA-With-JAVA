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
   * Given a undirected graph represented by an adjacency list adj, which is a
   * vector of vectors where each adj[i] represents the list of vertices connected
   * to vertex i. Perform a Breadth First Traversal (BFS) starting from vertex 0,
   * visiting vertices from left to right according to the adjacency list, and
   * return a list containing the BFS traversal of the graph.
   * Input: adj = [[2,3,1], [0], [0,4], [0], [2]]
   * Output: [0, 2, 3, 1, 4]
   */
  public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
    // code here
    ArrayList<Integer> ans = new ArrayList<>();
    Queue<Integer> q = new LinkedList<>();
    boolean[] vis = new boolean[V];

    q.add(0);
    vis[0] = true;

    while (q.size() > 0) {
      int rem = q.remove();
      ArrayList<Integer> ls = adj.get(rem);
      ans.add(rem);
      for (int i = 0; i < ls.size(); i++) {
        if (!vis[ls.get(i)]) {
          q.add(ls.get(i));
          vis[ls.get(i)] = true;
        }
      }
    }
    return ans;
  }

}

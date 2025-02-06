import java.util.*;

import org.w3c.dom.Node;

public class Tree2 {
  public static class Node {
    int data;
    Node left, right;

    Node(int data) {
      this.data = data;
      left = right = null;
    }
  }

  /*
   * Top View of Binary Tree
   * Input: root[] = [10, 20, 30, 40, 60, 90, 100]
   * Output: [40, 20, 10, 30, 100]
   */

  static class Pair {
    Node root;
    int hd;

    Pair(Node root, int hd) {
      this.root = root;
      this.hd = hd;
    }
  }

  // Function to return a list of nodes visible from the top view
  static ArrayList<Integer> topView(Node root) {
    ArrayList<Integer> ans = new ArrayList<>();
    if (root == null)
      return ans;

    // Queue for BFS traversal
    Queue<Pair> q = new LinkedList<>();
    // Map to store first node at each horizontal distance
    TreeMap<Integer, Integer> map = new TreeMap<>();

    // Initialize BFS with root at hd = 0
    q.add(new Pair(root, 0));

    while (!q.isEmpty()) {
      Pair curr = q.remove();
      int hd = curr.hd;
      Node node = curr.root;

      // Store the first node encountered at this horizontal distance
      if (!map.containsKey(hd)) {
        map.put(hd, node.data);
      }

      // Traverse left and right subtrees
      if (node.left != null) {
        q.add(new Pair(node.left, hd - 1));
      }
      if (node.right != null) {
        q.add(new Pair(node.right, hd + 1));
      }
    }

    // Extracting values in sorted order of keys
    for (int value : map.values()) {
      ans.add(value);
    }

    return ans;
  }

  /*
   * Bottom View of Binary Tree
   * Given a binary tree, return an array where elements represent
   * the bottom view of the binary tree from left to right.
   * Input:
   * 10
   * / \
   * 20 30
   * / \
   * 40 60
   * Output: 40 20 60 30
   */
  static class Pair2 {
    Node root;
    int hd;

    Pair2(Node root, int hd) {
      this.root = root;
      this.hd = hd;
    }
  }

  public ArrayList<Integer> bottomView(Node root) {
    // Code here
    ArrayList<Integer> ans = new ArrayList<>();
    if (root == null)
      return ans;

    // Queue for BFS traversal
    Queue<Pair2> q = new LinkedList<>();
    // Map to store first node at each horizontal distance
    HashMap<Integer, Integer> map = new HashMap<>();
    int max = 0;
    int min = 0;

    // Initialize BFS with root at hd = 0
    q.add(new Pair2(root, 0));

    while (!q.isEmpty()) {
      Pair2 curr = q.remove();
      int hd = curr.hd;
      Node node = curr.root;

      map.put(hd, node.data);

      // Traverse left and right subtrees
      if (node.left != null) {
        q.add(new Pair2(node.left, hd - 1));
        min = Math.min(min, hd - 1);
      }

      if (node.right != null) {
        q.add(new Pair2(node.right, hd + 1));
        max = Math.max(max, hd + 1);
      }
    }

    // Extracting values in sorted order of keys
    for (int i = min; i <= max; i++) {
      ans.add(map.get(i));
    }
    return ans;
  }

}

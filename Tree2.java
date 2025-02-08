import java.util.*;

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

  /*
   * construct BT using preorder and inorder
   * Given two integer arrays preorder and inorder where preorder is
   * the preorder traversal of a binary tree and inorder is
   * the inorder traversal of the same tree, construct and return
   * the `binary tree.
   * 
   * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
   * Output: [3,9,20,null,null,15,7]
   */
  public Node helper(int[] preorder, int preLow, int preHigh, int[] inorder, int inLow, int inHigh) {
    if (preLow > preHigh)
      return null;
    Node root = new Node(preorder[preLow]);
    int i = 0;
    while (inorder[i] != root.data) {
      i++;
    }
    int leftSize = i - inLow;
    root.left = helper(preorder, preLow + 1, preLow + leftSize, inorder, inLow, i - 1);
    root.right = helper(preorder, preLow + leftSize + 1, preHigh, inorder, i + 1, inHigh);
    return root;
  }

  public Node buildTree(int[] preorder, int[] inorder) {
    int ph = preorder.length;
    int ih = inorder.length;
    return helper(preorder, 0, ph - 1, inorder, 0, ih - 1);
  }

  /*
   * 106. Construct Binary Tree from Inorder and Postorder Traversal
   * Given two integer arrays inorder and postorder where inorder
   * is the inorder traversal of a binary tree and postorder is
   * the postorder traversal of the same tree, construct and
   * return the binary tree.
   * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
   * Output: [3,9,20,null,null,15,7]
   */
  public Node postAndIn(int[] postorder, int postLow, int postHigh, int[] inorder, int inLow, int inHigh) {
    if (postLow > postHigh)
      return null;
    Node root = new Node(postorder[postHigh]);
    int i = inLow;
    while (inorder[i] != root.data)
      i++;
    int leftSize = i - inLow - 1;
    root.left = postAndIn(postorder, postLow, postLow + leftSize, inorder, inLow, i - 1);
    root.right = postAndIn(postorder, postLow + leftSize + 1, postHigh - 1, inorder, i + 1, inHigh);
    return root;
  }

  public Node buildTree1(int[] inorder, int[] postorder) {
    int postHigh = postorder.length;
    int inHigh = inorder.length;
    return postAndIn(postorder, 0, postHigh - 1, inorder, 0, inHigh - 1);
  }

  /*
   * 
   * 112. Path Sum
   * Given the root of a binary tree and an integer targetSum, return
   * true if the tree has a root-to-leaf path such that adding up all
   * the values along the path equals targetSum.
   * Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
   * Output: true
   */
  public boolean hasPathSum(Node root, int targetSum) {
    if (root != null && root.left == null && root.right == null) {
      if (targetSum == root.data) {
        return true;
      }
    }
    if (root == null)
      return false;
    return (hasPathSum(root.left, targetSum - root.data) || hasPathSum(root.right, targetSum - root.data));
  }

  /*
   * 113. Path Sum II
   * Given the root of a binary tree and an integer targetSum, return
   * all root-to-leaf paths where the sum of the node values in the
   * path equals targetSum. Each path should be returned as a list
   * of the node values, not node references.
   * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
   * Output: [[5,4,11,2],[5,8,4,5]]
   */
  public void helper(Node root, int target, List<Integer> ls, List<List<Integer>> ans) {
    if (root == null)
      return;
    if (root.left == null && root.right == null) {
      if (target == root.data) {
        ls.add(root.data);
        List<Integer> list = new ArrayList<>(ls);
        ans.add(list);
        ls.remove(ls.size() - 1);
      }
      return;
    }
    ls.add(root.data);
    helper(root.left, target - root.data, ls, ans);
    helper(root.right, target - root.data, ls, ans);
    ls.remove(ls.size() - 1);
  }

  public List<List<Integer>> pathSum2(Node root, int targetSum) {
    List<List<Integer>> ans = new ArrayList<>();
    if (root == null)
      return ans;
    List<Integer> ls = new ArrayList<>();
    helper(root, targetSum, ls, ans);
    return ans;
  }

  /*
   * 437. Path Sum III
   * Given the root of a binary tree and an integer targetSum, return
   * the number of paths where the sum of the values along
   * the path equals targetSum.
   * 
   * The path does not need to start or end at the root or a leaf,
   * but it must go downwards (i.e., traveling only from parent nodes
   * to child nodes).
   * Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
   * Output: 3
   * 
   */
  public int countPathSum(Node root, long target) {
    if (root == null)
      return 0;
    int count = 0;
    if ((long) root.data == target)
      count++;
    return count + countPathSum(root.left, target - (long) root.data)
        + countPathSum(root.right, target - (long) root.data);
  }

  public int pathSum(Node root, int targetSum) {
    if (root == null)
      return 0;
    int count = countPathSum(root, targetSum);
    count += pathSum(root.left, targetSum) + pathSum(root.right, targetSum);
    return count;
  }

  // Optimal verstion TC=Same(O(N))-- SC(O(N))
  public int pathSum1(Node root, int targetSum) {
    HashMap<Long, Integer> prefixSumMap = new HashMap<>();
    prefixSumMap.put(0L, 1); // Base case for sum = targetSum at the root itself
    return helper(root, 0L, targetSum, prefixSumMap);
  }

  private int helper(Node node, long currentSum, int target, HashMap<Long, Integer> prefixSumMap) {
    if (node == null)
      return 0;

    currentSum += node.data;

    int count = prefixSumMap.getOrDefault(currentSum - target, 0);
    prefixSumMap.put(currentSum, prefixSumMap.getOrDefault(currentSum, 0) + 1);

    count += helper(node.left, currentSum, target, prefixSumMap);
    count += helper(node.right, currentSum, target, prefixSumMap);

    prefixSumMap.put(currentSum, prefixSumMap.get(currentSum) - 1);

    return count;
  }

}

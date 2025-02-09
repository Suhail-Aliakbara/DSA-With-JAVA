import java.util.*;

import javax.swing.tree.TreeNode;

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

  /*
   * Flatten binary tree to linkedList
   * Given the root of a binary tree, flatten the tree into a "linked list":
   * 
   * The "linked list" should use the same TreeNode class where the
   * right child pointer points to the next node in the list and
   * the left child pointer is always null.
   * The "linked list" should be in the same order as a pre-order
   * traversal of the binary tree.
   * Input: root = [1,2,5,3,4,null,6]
   * Output: [1,null,2,null,3,null,4,null,5,null,6]
   */
  public void flatten(Node root) {
    if (root == null)
      return;
    Node leftN = root.left;
    Node rightN = root.right;
    flatten(leftN);
    flatten(rightN);
    root.left = null;
    root.right = leftN;
    Node temp = leftN;
    while (temp != null && temp.right != null) {
      temp = temp.right;
    }
    if (temp != null)
      temp.right = rightN;
    else
      root.right = rightN;
  }

  // Optimal Solution for flatten using morries traversal
  public void flatten1(Node root) {
    if (root == null)
      return;
    Node curr = root;
    while (curr != null) {
      if (curr.left != null) {
        Node pred = curr.left;
        while (pred.right != null) {
          pred = pred.right;
        }
        pred.right = curr.right;
        curr.right = curr.left;
        curr.left = null;
      }
      curr = curr.right;
    }
  }

  /*
   * Tree Boundary Traversal
   * Given a Binary Tree, find its Boundary Traversal. The traversal
   * should be in the following order:
   * Left Boundary
   * Leaf Nodes
   * Reverse Right Boundary
   * Input: root[] = [1, 2, 3, 4, 5, 6, 7, N, N, 8, 9, N, N, N, N]
   * Output: [1, 2, 4, 8, 9, 6, 7, 3]
   */
  void addLeftBoundary(Node root, ArrayList<Integer> ans) {
    Node curr = root.left;
    while (curr != null) {
      if (!isLeaf(curr))
        ans.add(curr.data);
      if (curr.left != null)
        curr = curr.left;
      else
        curr = curr.right;
    }
  }

  void addLeafNodes(Node root, ArrayList<Integer> ans) {
    if (isLeaf(root)) {
      ans.add(root.data);
      return;
    }
    if (root.left != null)
      addLeafNodes(root.left, ans);
    if (root.right != null)
      addLeafNodes(root.right, ans);
  }

  void addRightBoundary(Node root, ArrayList<Integer> ans) {
    Node curr = root.right;
    Stack<Integer> st = new Stack<>();
    while (curr != null) {
      if (!isLeaf(curr))
        st.push(curr.data);
      if (curr.right != null)
        curr = curr.right;
      else
        curr = curr.left;
    }
    while (!st.isEmpty()) {
      ans.add(st.pop());
    }
  }

  ArrayList<Integer> boundaryTraversal(Node node) {
    // code here
    ArrayList<Integer> ans = new ArrayList<>();
    if (!isLeaf(node))
      ans.add(node.data);
    addLeftBoundary(node, ans);
    addLeafNodes(node, ans);
    addRightBoundary(node, ans);

    return ans;
  }

  boolean isLeaf(Node root) {
    if (root.left == null && root.right == null)
      return true;
    else
      return false;
  }

  /*
   * 2385. Amount of Time for Binary Tree to Be Infected
   * You are given the root of a binary tree with unique values, and
   * an integer start. At minute 0, an infection starts from the
   * node with value start.
   * 
   * Each minute, a node becomes infected if:
   * 
   * The node is currently uninfected.
   * The node is adjacent to an infected node.
   * Return the number of minutes needed for the entire tree to be infected.
   * Input: root = [1,5,3,null,4,10,6,9,2], start = 3
   * Output: 4
   */
  public static Node getInfectedNode(Node root, int target) {
    if (root == null)
      return null;
    if (root.data == target)
      return root;
    Node left = getInfectedNode(root.left, target);
    Node right = getInfectedNode(root.right, target);
    if (left == null)
      return right;
    else
      return left;
  }

  public static void preorder(Node root, Map<Node, Node> childParent) {
    if (root == null)
      return;
    if (root.left != null)
      childParent.put(root.left, root);
    if (root.right != null)
      childParent.put(root.right, root);
    preorder(root.left, childParent);
    preorder(root.right, childParent);
  }

  public static int minTime(Node root, int target) {
    // code here
    Node node = getInfectedNode(root, target);

    Map<Node, Node> childParent = new HashMap<>();
    preorder(root, childParent);

    Queue<Node> q = new LinkedList<>();
    q.add(node);
    Map<Node, Integer> isVisited = new HashMap<>();
    isVisited.put(node, 0);

    while (!q.isEmpty()) {
      Node temp = q.remove();
      int level = isVisited.get(temp);

      if (temp.left != null && !isVisited.containsKey(temp.left)) {
        q.add(temp.left);
        isVisited.put(temp.left, level + 1);
      }
      if (temp.right != null && !isVisited.containsKey(temp.right)) {
        q.add(temp.right);
        isVisited.put(temp.right, level + 1);
      }
      if (childParent.containsKey(temp) && !isVisited.containsKey(childParent.get(temp))) {
        q.add(childParent.get(temp));
        isVisited.put(childParent.get(temp), level + 1);
      }
    }
    int maxTime = -1;
    for (int i : isVisited.values()) {
      maxTime = Math.max(maxTime, i);
    }
    return maxTime;
  }

  /*
   * 662 Maximum width of the binary tree
   * Given the root of a binary tree, return the maximum width of the given tree.
   * The maximum width of a tree is the maximum width among all levels.
   * 
   * Input: root = [1,3,2,5,null,null,9,6,null,7]
   * Output: 7
   * Input: root = [1,3,2,5]
   * Output: 2
   * Explanation: The maximum width exists in the second level with length 2
   * (3,2).
   */
  static class Pair3 {
    Node node;
    int index;

    Pair3(Node node, int index) {
      this.node = node;
      this.index = index;
    }
  }

  public int widthOfBinaryTree(Node root) {
    if (root == null)
      return 0;

    int maxWidth = 0;
    Queue<Pair3> queue = new LinkedList<>();
    queue.add(new Pair3(root, 0));

    while (!queue.isEmpty()) {
      int size = queue.size();

      // Minimum index at the current level
      int minIndex = queue.peek().index;
      int firstIndex = 0, lastIndex = 0;

      for (int i = 0; i < size; i++) {
        Pair3 currentPair = queue.remove();

        // Normalize index to avoid overflow
        int currentIndex = currentPair.index - minIndex;
        Node currentNode = currentPair.node;

        if (i == 0)
          firstIndex = currentIndex; // First index at the current level
        if (i == size - 1)
          lastIndex = currentIndex; // Last index at the current level
        if (currentNode.left != null) {
          queue.add(new Pair3(currentNode.left, currentIndex * 2 + 1));
        }
        if (currentNode.right != null) {
          queue.add(new Pair3(currentNode.right, currentIndex * 2 + 2));
        }
      }
      maxWidth = Math.max(maxWidth, lastIndex - firstIndex + 1);
    }

    return maxWidth;
  }

}

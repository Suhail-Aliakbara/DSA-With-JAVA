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

  /*
   * 124. BT maximum path sum
   * Input: root = [1,2,3]
   * Output: 6
   * Input: root = [-10,9,20,null,null,15,7]
   * Output: 42
   */
  public int maxPath(Node root, int[] maximum) {
    if (root == null)
      return 0;
    int leftSum = Math.max(0, maxPath(root.left, maximum));
    int rightSum = Math.max(0, maxPath(root.right, maximum));
    maximum[0] = Math.max(maximum[0], leftSum + rightSum + root.data);
    return Math.max(leftSum, rightSum) + root.data;
  }

  int findMaxSum(Node root) {
    // your code goes here
    int maximum[] = new int[1];
    maximum[0] = Integer.MIN_VALUE;
    maxPath(root, maximum);
    return maximum[0];
  }

  /*
   * BT Right Side view
   * Given the root of a binary tree, imagine yourself standing on
   * the right side of it, return the values of the nodes you can
   * see ordered from top to bottom.
   * Input: root = [1,2,3,null,5,null,4]
   * Output: [1,3,4]
   */
  class Pair4 {
    Node node;
    int hd;

    Pair4(Node node, int hd) {
      this.node = node;
      this.hd = hd;
    }
  }

  public List<Integer> rightSideView(Node root) {
    List<Integer> ans = new ArrayList<>();
    if (root == null)
      return ans;

    Map<Integer, Integer> map = new HashMap<>();
    Queue<Pair4> q = new LinkedList<>();
    q.add(new Pair4(root, 0));

    while (!q.isEmpty()) {
      Pair4 currPair = q.remove();
      Node node = currPair.node;
      int hd = currPair.hd;

      if (!map.containsKey(hd)) {
        map.put(hd, node.data);
      }

      if (node.right != null)
        q.add(new Pair4(node.right, hd + 1));
      if (node.left != null)
        q.add(new Pair4(node.left, hd + 1));
    }

    for (int value : map.values()) {
      ans.add(value);
    }

    return ans;
  }

  /*
   * 987 Vertical order traversal of BT
   * The vertical order traversal of a binary tree is a list of
   * top-to-bottom orderings for each column index starting from
   * the leftmost column and ending on the rightmost column.
   * There may be multiple nodes in the same row and same column.
   * In such a case, sort these nodes by their values
   * Input: root = [3,9,20,null,null,15,7]
   * Output: [[9],[3,15],[20],[7]]
   * Explanation:
   * Column -1: Only node 9 is in this column.
   * Column 0: Nodes 3 and 15 are in this column in that order from top to bottom.
   * Column 1: Only node 20 is in this column.
   * Column 2: Only node 7 is in this column.
   */
  class turple {
    Node node;
    int row;
    int col;

    turple(Node node, int row, int col) {
      this.node = node;
      this.row = row;
      this.col = col;
    }
  }

  public List<List<Integer>> verticalTraversal(Node root) {
    List<List<Integer>> ans = new ArrayList<>();
    if (root == null)
      return ans;

    TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
    Queue<turple> q = new LinkedList<>();
    q.add(new turple(root, 0, 0));

    while (!q.isEmpty()) {
      turple curr = q.remove();
      Node node = curr.node;
      int vertical = curr.row;
      int level = curr.col;

      if (!map.containsKey(vertical)) {
        map.put(vertical, new TreeMap<>());
      }

      if (!map.get(vertical).containsKey(level)) {
        map.get(vertical).put(level, new PriorityQueue<>());
      }

      map.get(vertical).get(level).offer(node.data);

      if (node.left != null)
        q.add(new turple(node.left, vertical - 1, level + 1));
      if (node.right != null)
        q.add(new turple(node.right, vertical + 1, level + 1));
    }

    for (TreeMap<Integer, PriorityQueue<Integer>> ys : map.values()) {
      List<Integer> ls = new ArrayList<>();
      for (PriorityQueue<Integer> pq : ys.values()) {
        while (!pq.isEmpty()) {
          ls.add(pq.poll());
        }
      }
      ans.add(ls);
    }
    return ans;
  }

  /*
   * All node Distance K in binary tree
   * 
   * You can return the answer in any order.
   * 
   * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
   * Output: [7,4,1]
   * Explanation: The nodes that are a distance 2 from the target
   * node (with value 5) have values 7, 4, and 1.
   */
  public void getParentNode(Node root, HashMap<Node, Node> childParent) {
    if (root == null)
      return;
    if (root.left != null)
      childParent.put(root.left, root);
    if (root.right != null)
      childParent.put(root.right, root);
    getParentNode(root.left, childParent);
    getParentNode(root.right, childParent);

  }

  public List<Integer> distanceK(Node root, Node target, int k) {
    List<Integer> ans = new ArrayList<>();

    HashMap<Node, Node> childParent = new HashMap<>();
    getParentNode(root, childParent);

    Queue<Node> q = new LinkedList<>();
    q.add(target);

    HashMap<Node, Boolean> isVisited = new HashMap<>();
    isVisited.put(target, true);
    int curr_level = 0;

    while (!q.isEmpty()) {
      int size = q.size();
      if (curr_level == k)
        break;
      curr_level++;
      for (int i = 0; i < size; i++) {
        Node currNode = q.remove();

        if (currNode.left != null && isVisited.get(currNode.left) == null) {
          q.offer(currNode.left);
          isVisited.put(currNode.left, true);
        }
        if (currNode.right != null && isVisited.get(currNode.right) == null) {
          q.offer(currNode.right);
          isVisited.put(currNode.right, true);
        }
        if (childParent.containsKey(currNode) && isVisited.get(childParent.get(currNode)) == null) {
          q.offer(childParent.get(currNode));
          isVisited.put(childParent.get(currNode), true);
        }
      }
    }
    while (!q.isEmpty()) {
      ans.add(q.poll().data);
    }
    return ans;
  }

  /*
   * Children Sum in a Binary Tree
   * Binary tree
   * 35
   * / \
   * 20 15
   * / \ / \
   * 15 5 10 5
   * 
   * Output: 1
   */
  public static int isSumProperty(Node root) {
    // add your code here
    if (root == null || root.left == null && root.right == null)
      return 1;
    int sum = 0;
    if (root.left != null)
      sum = root.left.data;
    if (root.right != null)
      sum += root.right.data;
    if (root.data == sum && isSumProperty(root.left) == 1 && isSumProperty(root.right) == 1) {
      return 1;
    }
    return 0;
  }

  /*
   * (in copy 52 question) BT inorder Mories Traversal
   * Input: root = [1,null,2,3]
   * Output: [1,3,2]
   */
  public List<Integer> inorderTraversal(Node root) {
    List<Integer> ls = new ArrayList<>();
    Node curr = root;
    while (curr != null) {
      if (curr.left != null) {
        Node pred = curr.left;
        while (pred.right != null && pred.right != curr) {
          pred = pred.right;
        }
        if (pred.right == null) {
          pred.right = curr;
          curr = curr.left;
        }
        if (pred.right == curr) {
          pred.right = null;
          ls.add(curr.data);
          curr = curr.right;
        }
      } else {
        ls.add(curr.data);
        curr = curr.right;
      }
    }
    return ls;
  }

  /*
   * 297. Serialize and Deserialize Binary Tree
   * Serialization is the process of converting a data structure or object
   * into a sequence of bits so that it can be stored in a file or memory buffer,
   * or transmitted across a network connection link to be reconstructed later in
   * the same or another computer environment.
   * 
   * Input: root = [1,2,3,null,null,4,5]
   * Output: [1,2,3,null,null,4,5]
   * 
   */
  // Encodes a tree to a single string.
  public String serialize(Node root) {
    if (root == null)
      return "";
    Queue<Node> q = new LinkedList<>();
    StringBuilder res = new StringBuilder();
    q.add(root);
    while (!q.isEmpty()) {
      Node curr = q.remove();
      if (curr == null) {
        res.append("N ");
        continue;
      }
      res.append(curr.data + " ");
      q.add(curr.left);
      q.add(curr.right);
    }
    return res.toString();
  }

  // Decodes your encoded data to tree.
  public Node deserialize(String data) {
    if (data == "")
      return null;
    Queue<Node> q = new LinkedList<>();
    String[] values = data.split(" ");
    Node root = new Node(Integer.parseInt(values[0]));
    q.add(root);
    for (int i = 1; i < values.length; i++) {
      Node parent = q.poll();
      if (!values[i].equals("N")) {
        Node left = new Node(Integer.parseInt(values[i]));
        parent.left = left;
        q.add(left);
      }
      if (!values[++i].equals("N")) {
        Node right = new Node(Integer.parseInt(values[i]));
        parent.right = right;
        q.add(right);
      }
    }
    return root;
  }

}

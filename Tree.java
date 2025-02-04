import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import javax.swing.tree.TreeNode;

import org.w3c.dom.Node;

public class Tree {
  public static class Node {
    int data;
    Node left, right;

    Node(int data) {
      this.data = data;
      left = right = null;
    }
  }

  public static class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
      this.key = key;
      this.value = value;
    }

    public K getKey() {
      return key;
    }

    public void setKey(K key) {
      this.key = key;
    }

    public V getValue() {
      return value;
    }

    public void setValue(V value) {
      this.value = value;
    }
  }

  // --------Number of Nodes--------
  /*
   * Given an integer i. Print the maximum number of nodes on
   * level i of a binary tree.
   * Input: 5
   * Output: 16
   */
  static int countNodes(int i) {
    // code here
    return (int) Math.pow(2, i - 1);
  }

  // ---------------preorder Traversal------------------------
  public List<Integer> preOrderTraverse(Node root, List<Integer> ans) {
    if (root == null) {
      return ans;
    }
    ans.add(root.data);
    preOrderTraverse(root.left, ans);
    preOrderTraverse(root.right, ans);
    return ans;
  }

  public List<Integer> preorderTraversal(Node root) {
    List<Integer> ans = new ArrayList<>();
    return preOrderTraverse(root, ans);
  }

  // ------------------postorder Traversal-------------------
  public List<Integer> postOrderTraverse(Node root, List<Integer> ans) {
    if (root == null) {
      return ans;
    }
    postOrderTraverse(root.left, ans);
    postOrderTraverse(root.right, ans);
    ans.add(root.data);
    return ans;
  }

  public List<Integer> postorderTraversal(Node root) {
    List<Integer> ans = new ArrayList<>();
    return postOrderTraverse(root, ans);
  }

  // -------------inorder Traversal-----------------
  public List<Integer> inorderTraverse(Node root, List<Integer> ls) {
    if (root == null)
      return ls;
    inorderTraverse(root.left, ls);
    ls.add(root.data);
    inorderTraverse(root.right, ls);
    return ls;
  }

  public List<Integer> inorderTraversal(Node root) {
    List<Integer> ls = new ArrayList<>();
    return inorderTraverse(root, ls);
  }

  /*
   * 102. Binary Tree Level Order Traversal
   * Given the root of a binary tree, return the level order traversal of its
   * nodes' values. (i.e., from left to right, level by level).
   * 
   * Input: root = [3,9,20,null,null,15,7]
   * Output: [[3],[9,20],[15,7]]
   */
  public List<List<Integer>> levelOrder(Node root) {
    List<List<Integer>> ans = new ArrayList<>();

    Queue<Node> q = new LinkedList<>();
    if (root != null)
      q.add(root);

    while (!q.isEmpty()) {
      int size = q.size();
      List<Integer> ls = new ArrayList<>();

      for (int i = 0; i < size; i++) {
        Node newNode = q.remove();
        if (newNode.left != null)
          q.add(newNode.left);
        if (newNode.right != null)
          q.add(newNode.right);
        ls.add(newNode.data);
      }
      ans.add(ls);
    }
    return ans;
  }

  // -----------preorder Traversal Iterative Approach-----------
  public List<Integer> preorderTraversal1(Node root) {
    List<Integer> ans = new ArrayList<>();
    Stack<Node> st = new Stack<>();

    if (root == null)
      return ans;
    st.push(root);

    while (!st.isEmpty()) {
      Node newNode = st.pop();
      ans.add(newNode.data);
      if (newNode.right != null)
        st.push(newNode.right);
      if (newNode.left != null)
        st.push(newNode.left);
    }
    return ans;
  }

  // -----------inorder Traversal Iterative Approach-----------
  public List<Integer> inorderTraversal1(Node root) {
    List<Integer> ls = new ArrayList<>();
    Stack<Node> st = new Stack<Node>();

    if (root == null)
      return ls;
    pushAllLeft(root, st);

    while (!st.isEmpty()) {
      Node newNode = st.pop();
      ls.add(newNode.data);
      pushAllLeft(newNode.right, st);
    }

    return ls;
  }

  public void pushAllLeft(Node root, Stack<Node> st) {
    while (root != null) {
      st.push(root);
      root = root.left;
    }
  }

  // -----------post-order Traversal Iterative Approach-----------

  // Using Linked List and Stack
  public List<Integer> postorderTraversal1(Node root) {
    LinkedList<Integer> ans = new LinkedList<>();
    Stack<Node> st = new Stack<>();

    if (root == null)
      return ans;
    st.push(root);

    while (!st.isEmpty()) {
      Node currNode = st.pop();
      ans.addFirst(currNode.data);
      if (currNode.left != null)
        st.push(currNode.left);
      if (currNode.right != null)
        st.push(currNode.right);
    }
    return ans;
  }

  // Using List and Deque
  public List<Integer> postorderTraversa2(Node root) {
    List<Integer> result = new ArrayList<>();
    Deque<Node> stack = new ArrayDeque<>();
    Node currentNode = root;

    // Traverse the tree
    while (currentNode != null || !stack.isEmpty()) {
      if (currentNode != null) {
        result.add(currentNode.data); // Add before going to children
        stack.push(currentNode);
        currentNode = currentNode.right; // Move to the right child
      } else {
        currentNode = stack.pop();
        currentNode = currentNode.left; // Move to the left child
      }
    }
    // Reverse the result list to get the postorder traversal
    Collections.reverse(result);
    return result;
  }

  // Post-order Traversal of Binary Tree using 2 stack
  public List<Integer> postorderTraversalUsingTwoStacks(Node root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    Stack<Node> stack1 = new Stack<>();
    Stack<Node> stack2 = new Stack<>();

    stack1.push(root);

    while (!stack1.isEmpty()) {
      Node currentNode = stack1.pop();
      stack2.push(currentNode);

      if (currentNode.left != null) {
        stack1.push(currentNode.left);
      }
      if (currentNode.right != null) {
        stack1.push(currentNode.right);
      }
    }

    while (!stack2.isEmpty()) {
      result.add(stack2.pop().data);
    }
    return result;
  }

  public static List<List<Integer>> preInPostTraversal(Node root) {
    List<Integer> pre = new ArrayList<>();
    List<Integer> in = new ArrayList<>();
    List<Integer> post = new ArrayList<>();

    if (root == null) {
      return new ArrayList<>();
    }
    Stack<Pair<Node, Integer>> st = new Stack<>();
    st.push(new Pair<>(root, 1));

    while (!st.empty()) {
      Pair<Node, Integer> it = st.pop();

      // this is part of pre
      if (it.getValue() == 1) {
        pre.add(it.getKey().data);
        it.setValue(2);
        st.push(it);
        if (it.getKey().left != null) {
          st.push(new Pair<>(it.getKey().left, 1));
        }
      }

      // this is a part of in
      else if (it.getValue() == 2) {
        in.add(it.getKey().data);
        it.setValue(3);
        st.push(it);
        if (it.getKey().right != null) {
          st.push(new Pair<>(it.getKey().right, 1));
        }
      }

      // this is a part of post
      else {
        post.add(it.getKey().data);
      }
    }
    // Returning the traversals
    List<List<Integer>> result = new ArrayList<>();
    result.add(pre);
    result.add(in);
    result.add(post);
    return result;
  }

  /*
   * 104 Maximum depth of the binary tree
   * Input: root = [3,9,20,null,null,15,7]
   * Output: 3
   * 
   */
  public int maxDepth(Node root) {
    if (root == null)
      return 0;
    return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
  }

  // Iterative solution using BFS
  public int maxDepth1(Node root) {
    if (root == null)
      return 0;

    int depth = 0;
    Queue<Node> q = new LinkedList<>();
    q.offer(root);

    while (!q.isEmpty()) {
      int size = q.size();
      depth++;
      while (size-- > 0) {
        Node node = q.poll();
        if (node.left != null)
          q.offer(node.left);
        if (node.right != null)
          q.offer(node.right);
      }
    }
    return depth;
  }

  /*
   * 543. Diameter of Binary Tree
   * The diameter of a binary tree is the length of the longest path
   * between any two nodes in a tree. This path may or may not pass
   * through the root.
   * 
   * Input: root = [1,2,3,4,5]
   * Output: 3
   * Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
   */

  // Method to calculate the height of the tree
  public int height(Node root) {
    // Base case: if the tree is empty or a leaf node
    if (root == null || (root.left == null && root.right == null))
      return 0;

    // Recursively calculate the height of the left and right subtrees
    return 1 + Math.max(height(root.left), height(root.right));
  }

  // Method to calculate the diameter of the binary tree
  public int diameterOfBinaryTree(Node root) {
    // Base case: if the tree is empty or a leaf node
    if (root == null || (root.left == null && root.right == null))
      return 0;

    // Recursively calculate the diameter of the left and right subtrees
    int leftDiameter = diameterOfBinaryTree(root.left);
    int rightDiameter = diameterOfBinaryTree(root.right);

    // Calculate the height of the left and right subtrees
    int leftHeight = height(root.left);
    int rightHeight = height(root.right);

    // Calculate the diameter passing through the root
    int rootDiameter = leftHeight + rightHeight;
    if (root.left != null)
      rootDiameter++;
    if (root.right != null)
      rootDiameter++;

    // The diameter of the tree is the maximum of the three diameters
    int maxDiameter = Math.max(rootDiameter, Math.max(leftDiameter, rightDiameter));

    return maxDiameter;
  }

  /*
   * 110. Balanced Binary Tree
   * A height-balanced binary tree is a binary tree in which
   * the depth of the two subtrees of every node never
   * differs by more than one
   */
  public int height1(Node root) {
    if (root == null)
      return 0;
    return 1 + Math.max(height(root.left), height(root.right));
  }

  public boolean isBalanced(Node root) {
    if (root == null)
      return true;
    int lh = height(root.left);
    int rh = height(root.right);
    int diff = lh - rh;
    if (diff < 0)
      diff = -diff;
    if (diff > 1)
      return false;
    return (isBalanced(root.left) && isBalanced(root.right));
  }

  /*
   * ---------Same Tree-----------
   * Given the roots of two binary trees p and q, write a function to
   * check if they are the same or not.
   * 
   * Two binary trees are considered the same if they are
   * structurally identical, and the nodes have the same value.
   */

  public boolean isSameTree(Node p, Node q) {
    if (p == null && q == null)
      return true;
    else if (p == null || q == null)
      return false;
    if (p.data != q.data)
      return false;

    return (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
  }

  /*
   * 257 binary tree paths
   * Given the root of a binary tree, return all root-to-leaf paths in any order.
   * Input: root = [1,2,3,null,5]
   * Output: ["1->2->5","1->3"]
   */
  public void helper(Node root, List<String> ans, String s) {
    if (root == null)
      return;
    if (root.left == null && root.right == null) {
      s += root.data;
      ans.add(s);
      return;
    }
    helper(root.left, ans, s + root.data + "->");
    helper(root.right, ans, s + root.data + "->");
  }

  public List<String> binaryTreePaths(Node root) {
    List<String> ans = new ArrayList<>();
    helper(root, ans, "");
    return ans;
  }
}

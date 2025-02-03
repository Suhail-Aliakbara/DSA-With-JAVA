import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

class Tree {
  class Node {
    int data;
    Node left;
    Node right;

    Node() {
      this.data = 0;
      this.left = null;
      this.right = null;
    }

    Node(int data) {
      this.data = data;
      this.left = null;
      this.right = null;
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

}
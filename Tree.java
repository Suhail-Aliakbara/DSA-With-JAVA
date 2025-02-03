import java.util.ArrayList;
import java.util.List;

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

}
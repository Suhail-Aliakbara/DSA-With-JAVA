import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.TreeNode;

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

  // ------preorder Traversal---------
  /*
   * Given the root of a binary tree, return the preorder traversal
   * of its nodes values.
   * 
   * Input: root = [1,2,3,4,5,null,8,null,null,6,7,9]
   * Output: [1,2,4,5,6,7,3,8,9]
   */
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

  // --------postorder Traversal-------------
  /*
   * 
   */
}
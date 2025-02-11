public class BST {
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  /*
   * 700 Search in BST
   * ou are given the root of a binary search tree (BST) and an integer val.
   * 
   * Find the node in the BST that the node's value equals val and return
   * the subtree rooted with that node. If such a node does not exist,
   * return null.
   * Input: root = [4,2,7,1,3], val = 2
   * Output: [2,1,3]
   */
  public TreeNode searchBST(TreeNode root, int val) {
    if (root == null)
      return null;
    if (root.val == val)
      return root;
    else if (root.val > val)
      return searchBST(root.left, val);
    else
      return searchBST(root.right, val);
  }

  /*
   * 701 Insert in BST
   * Input: root = [4,2,7,1,3], val = 5
   * Output: [4,2,7,1,3,5]
   */
  public TreeNode insertIntoBST(TreeNode root, int val) {
    if (root == null)
      return new TreeNode(val);

    if (root.val > val) {
      if (root.left == null)
        root.left = new TreeNode(val);
      else
        insertIntoBST(root.left, val);
    } else {
      if (root.right == null)
        root.right = new TreeNode(val);
      else
        insertIntoBST(root.right, val);
    }
    return root;
  }
}

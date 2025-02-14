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

  public class Node {
    int data;
    Node left;
    Node right;

    Node() {
    }

    Node(int data) {
      this.data = data;
    }

    Node(int data, Node left, Node right) {
      this.data = data;
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

  /*
   * 98. validate BST
   * Given the root of a binary tree, determine if it is a valid
   * binary search tree (BST).
   * Input: root = [2,1,3]
   * Output: true
   * 
   */
  public boolean isValidBST(TreeNode root) {
    return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
  }

  boolean isValidBST(TreeNode root, long minValue, long maxValue) {
    if (root == null)
      return true;
    if (root.val >= maxValue || root.val <= minValue)
      return false;
    return isValidBST(root.left, minValue, (long) root.val) && isValidBST(root.right, (long) root.val, maxValue);
  }

  // Delete: The node has 0 child
  public void deleteLeafNode(TreeNode root, int target) {
    if (root == null) {
      return;
    }
    if (root.val > target) {
      if (root.left.val == target)
        root.left = null;
      else
        deleteLeafNode(root.left, target);
    }
    if (root.val < target) {
      if (root.right.val == target)
        root.right = null;
      else
        deleteLeafNode(root.right, target);
    }
  }

  // Delete: The Node who has 1 child
  public void deleteOneChildNode(TreeNode root, int target) {
    if (root == null) {
      return;
    }
    if (root.val > target) {
      if (root.left.val == target) {
        TreeNode l = root.left;
        if (l.left == null && l.right == null)
          root.left = null;
        else if (l.left != null || l.right != null) {
          if (l.left != null)
            root.left = l.left;
          else
            root.left = l.right;
        }
      } else
        deleteOneChildNode(root.left, target);
    }
    if (root.val < target) {
      if (root.right.val == target) {
        TreeNode r = root.right;
        if (r.left == null && r.right == null)
          root.right = null;
        else if (r.left == null || r.right == null) {
          if (r.left != null)
            root.right = r.left;
          else
            root.right = r.right;
        }
      } else
        deleteOneChildNode(root.right, target);
    }
  }

  /*
   * 450. Delete Node in a BST
   * Given a root node reference of a BST and a key, delete the node
   * with the given key in the BST. Return the root node reference
   * (possibly updated) of the BST.
   * 
   * Input: root = [5,3,6,2,4,null,7], key = 3
   * Output: [5,4,6,2,null,null,7]
   */
  public void delete(TreeNode root, int target) {
    if (root == null)
      return;
    if (root.val > target) {
      if (root.left == null)
        return;
      if (root.left.val == target) {
        TreeNode l = root.left;
        if (l.left == null && l.right == null)
          root.left = null;
        else if (l.left == null || l.right == null) {
          if (l.left != null)
            root.left = l.left;
          else
            root.left = l.right;
        } else {
          TreeNode curr = l;
          TreeNode pred = curr.left;
          while (pred.right != null)
            pred = pred.right;
          delete(root, pred.val);
          pred.left = curr.left;
          pred.right = curr.right;
          root.left = pred;
        }
      } else
        delete(root.left, target);
    }
    if (root.val < target) {
      if (root.right == null)
        return;
      if (root.right.val == target) {
        TreeNode l = root.right;
        if (l.left == null && l.right == null)
          root.right = null;
        else if (l.left == null || l.right == null) {
          if (l.left != null)
            root.right = l.left;
          else
            root.right = l.right;
        } else {
          TreeNode curr = l;
          TreeNode pred = curr.left;
          while (pred.right != null)
            pred = pred.right;
          delete(root, pred.val);
          pred.left = curr.left;
          pred.right = curr.right;
          root.right = pred;
        }
      } else
        delete(root.right, target);
    }
  }

  public TreeNode deleteNode(TreeNode root, int key) {
    TreeNode temp = new TreeNode(Integer.MAX_VALUE);
    temp.left = root;
    delete(temp, key);
    return temp.left;
  }

  /*
   * Pair Sum in BST
   * Given a Binary Search Tree(BST) and a target. Check whether
   * there's a pair of Nodes in the BST with value summing up to
   * the target.
   * 
   * Input: root = [7, 3, 8, 2, 4, N, 9], target = 12
   * Output: True
   * Explanation: In the binary tree above, there are two nodes
   * (8 and 4) that add up to 12.
   */
  boolean findTarget(Node root, int target) {
    // Write your code here
    return helper(root, root, target);
  }

  boolean helper(Node root, Node current, int target) {
    if (root == null || current == null) {
      return false;
    }
    if (findNode(root, current, target - current.data)) {
      return true;
    }
    return helper(root, current.left, target)
        || helper(root, current.right, target);
  }

  boolean findNode(Node root, Node current, int target) {
    if (root == null || root == current) {
      return false;
    }
    if (root.data == target) {
      return true;
    } else if (root.data > target) {
      return findNode(root.left, current, target);
    } else {
      return findNode(root.right, current, target);
    }
  }
}

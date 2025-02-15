import java.util.ArrayList;
import java.util.List;

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

  /*
   * 235. Lowest Common Ancestor of a Binary Search Tree
   * Given a binary search tree (BST), find the lowest common ancestor
   * (LCA) node of two given nodes in the BST.
   * 
   * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
   * Output: 6
   */

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (p.val == root.val || q.val == root.val)
      return root;
    else if (p.val < root.val && q.val > root.val)
      return root;
    else if (p.val > root.val && q.val < root.val)
      return root;
    else if (p.val < root.val && q.val < root.val)
      return lowestCommonAncestor(root.left, p, q);
    else
      return lowestCommonAncestor(root.right, p, q);
  }

  // short version bcz 3 time i am just return root in above
  public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
    if (p.val < root.val && q.val < root.val)
      return lowestCommonAncestor2(root.left, p, q);
    else if (p.val > root.val && q.val > root.val)
      return lowestCommonAncestor2(root.right, p, q);
    else
      return root;
  }

  /*
   * Predecessor and Successor
   * You are given root node of the BST and an integer key. You need
   * to find the in-order successor and predecessor of the given key.
   * If either predecessor or successor is not found, then set it to NULL.
   * 
   * Note:- In an inorder traversal the number just smaller than the
   * target is the predecessor and the number just greater than the target
   * is the successor.
   * 
   * Input: root[] = [8, 1, 9, N, 4, N, 10, 3, N, N, N]
   * 8
   * / \
   * 1 9
   * \ \
   * 4 10
   * /
   * 3
   * key = 8
   * Output: 4 9
   */

  public static void findPreSuc(Node root, Node[] pre, Node[] suc, int key) {
    // code here.
    // update pre[0] with the predecessor of the key
    // update suc[0] with the successor of the key
    Node curr = root;

    while (curr != null) {
      if (curr.data < key) {
        pre[0] = curr;
        curr = curr.right;
      } else {
        curr = curr.left;
      }
    }

    curr = root;

    while (curr != null) {
      if (curr.data > key) {
        suc[0] = curr;
        curr = curr.left;
      } else {
        curr = curr.right;
      }
    }
  }

  /*
   * 108. Convert Sorted Array to Binary Search Tree
   * Given an integer array nums where the elements are sorted in
   * ascending order, convert it to a height-balanced binary search tree.
   * 
   * Input: nums = [-10,-3,0,5,9]
   * Output: [0,-3,9,-10,null,5]
   */
  public TreeNode constructBST(int[] nums, int low, int high) {
    if (low > high)
      return null;
    int mid = (low + high) / 2;
    TreeNode root = new TreeNode(nums[mid]);
    root.left = constructBST(nums, low, mid - 1);
    root.right = constructBST(nums, mid + 1, high);
    return root;
  }

  public TreeNode sortedArrayToBST(int[] nums) {
    return constructBST(nums, 0, nums.length - 1);
  }

  /*
   * 230. Kth Smallest Element in a BST
   * Given the root of a binary search tree, and an integer k, return
   * the kth smallest value (1-indexed) of all the values of the
   * nodes in the tree.
   * 
   * Input: root = [3,1,4,null,2], k = 1
   * Output: 1
   */
  public void inorderKthSmallest(TreeNode root, int k, List<Integer> ans) {
    if (root == null)
      return;
    inorderKthSmallest(root.left, k, ans);
    ans.add(root.val);
    inorderKthSmallest(root.right, k, ans);
  }

  public int kthSmallest(TreeNode root, int k) {
    List<Integer> ans = new ArrayList<>();
    inorderKthSmallest(root, k, ans);
    int value = -1;
    for (int i = 0; i < ans.size(); i++) {
      if (i == k) {
        break;
      }
      value = ans.get(i);
    }
    return value;
  }

  // Optimal Solution for Kth Smallest and Largest

  private void reverseInorder(TreeNode node, int[] counter, int k, int[] kLargest) {
    if (node == null || counter[0] >= k)
      return;

    reverseInorder(node.right, counter, k, kLargest);
    counter[0]++;

    if (counter[0] == k) {
      kLargest[0] = node.val;
      return;
    }

    reverseInorder(node.left, counter, k, kLargest);
  }

  private void inorder(TreeNode node, int[] counter, int k, int[] kSmallest) {
    if (node == null || counter[0] >= k)
      return;

    inorder(node.left, counter, k, kSmallest);
    counter[0]++;

    if (counter[0] == k) {
      kSmallest[0] = node.val;
      return;
    }

    inorder(node.right, counter, k, kSmallest);
  }

  public int[] findKth(TreeNode root, int k) {
    int[] kSmallest = new int[] { Integer.MIN_VALUE };
    int[] kLargest = new int[] { Integer.MIN_VALUE };
    // Counter to track visited nodes
    int[] counter = new int[] { 0 };

    // Find Kth smallest element (perform inorder traversal)
    inorder(root, counter, k, kSmallest);

    // Reset counter for Kth largest element
    counter[0] = 0;
    // Find Kth largest element (perform reverse inorder traversal)
    reverseInorder(root, counter, k, kLargest);

    return new int[] { kSmallest[0], kLargest[0] };
  }

}

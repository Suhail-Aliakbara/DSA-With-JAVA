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

  public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  /*
   * 700 Search in BST
   * You are given the root of a binary search tree (BST) and an integer val.
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

  // ------- Delete: The node has 0 child---------------
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
   * target is the predecessor and the number just greater than the targets the
   * successor.
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

  /*
   * 109. Convert Sorted List to Binary Search Tree
   * Given the head of a singly linked list where elements are sorted in
   * ascending order, convert it to a height-balanced binary search tree.
   * 
   * Input: head = [-10,-3,0,5,9]
   * Output: [0,-3,9,-10,null,5]
   */
  public TreeNode inorderLinkedList(List<Integer> list, int low, int high) {
    if (low > high)
      return null;
    int mid = (low + high) / 2;
    TreeNode root = new TreeNode(list.get(mid));
    root.left = inorderLinkedList(list, low, mid - 1);
    root.right = inorderLinkedList(list, mid + 1, high);
    return root;
  }

  public TreeNode sortedListToBST(ListNode head) {
    List<Integer> list = new ArrayList<>();

    ListNode temp = head;
    while (temp != null) {
      list.add(temp.val);
      temp = temp.next;
    }

    TreeNode root = inorderLinkedList(list, 0, list.size() - 1);
    return root;
  }

  /*
   * 1008. Construct Binary Search Tree from Preorder Traversal
   * Given an array of integers preorder, which represents the preorder traversal
   * of a BST (i.e., binary search tree), construct the tree and return its root.
   * 
   * Input: preorder = [8,5,1,7,10,12]
   * Output: [8,5,10,1,7,null,12]
   */
  public TreeNode bstFromPreorder(int[] preorder) {
    TreeNode root = new TreeNode(preorder[0]);
    for (int i = 1; i < preorder.length; i++) {
      insertIntoBST(root, preorder[i]); // Used above InserInto method
    }
    return root;
  }

  /*
   * 538. Convert BST to Greater Tree
   * Given the root of a Binary Search Tree (BST), convert it to a Greater Tree
   * such that every key of the original BST is changed to the original key plus
   * the sum of all keys greater than the original key in BST.
   * 
   * Input: root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
   * Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
   */
  public void greaterTree(TreeNode root, int[] sum) {
    if (root == null)
      return;

    greaterTree(root.right, sum);
    sum[0] = root.val + sum[0];
    root.val = sum[0];
    greaterTree(root.left, sum);

  }

  public TreeNode convertBST(TreeNode root) {
    greaterTree(root, new int[] { 0 });
    return root;
  }

  /*
   * 783. Minimum Distance Between BST Nodes
   * Given the root of a Binary Search Tree (BST), return the minimum difference
   * between the values of any two different nodes in the tree.
   * 
   * Input: root = [1,0,48,null,null,12,49]
   * Output: 1
   */

  public void calculateDiff(TreeNode root, TreeNode[] prev, int[] minDiff) {
    if (root == null)
      return;

    calculateDiff(root.left, prev, minDiff);

    if (prev[0] != null) {
      int curr = root.val - prev[0].val;
      minDiff[0] = Math.min(minDiff[0], curr);
    }

    prev[0] = root;
    calculateDiff(root.right, prev, minDiff);

  }

  public int minDiffInBST(TreeNode root) {
    TreeNode[] prev = new TreeNode[1];
    int[] minDiff = new int[] { Integer.MAX_VALUE };
    calculateDiff(root, prev, minDiff);
    return minDiff[0];
  }

  /*
   * 1305. All Elements in Two Binary Search Trees
   * Given two binary search trees root1 and root2, return a list containing
   * all the integers from both trees sorted in ascending order.
   * 
   * Input: root1 = [2,1,4], root2 = [1,0,3]
   * Output: [0,1,1,2,3,4]
   */
  public void inorderMorries(TreeNode root, List<Integer> ls) {
    TreeNode curr = root;
    while (curr != null) {
      if (curr.left != null) {
        TreeNode pred = curr.left;
        while (pred.right != null && pred.right != curr)
          pred = pred.right;
        if (pred.right == null) {
          pred.right = curr;
          curr = curr.left;
        }
        if (pred.right == curr) {
          pred.right = null;
          ls.add(curr.val);
          curr = curr.right;
        }
      } else {
        ls.add(curr.val);
        curr = curr.right;
      }
    }
  }

  public void merge(List<Integer> arr1, List<Integer> arr2, List<Integer> ans) {
    int m = arr1.size(), n = arr2.size();
    int i = 0, j = 0;

    while (i < m && j < n) {
      if (arr1.get(i) <= arr2.get(j)) {
        ans.add(arr1.get(i++));
      } else {
        ans.add(arr2.get(j++));
      }
    }

    while (i < m) {
      ans.add(arr1.get(i++));
    }
    while (j < n) {
      ans.add(arr2.get(j++));
    }
  }

  public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
    List<Integer> arr1 = new ArrayList<>();
    List<Integer> arr2 = new ArrayList<>();
    List<Integer> ans = new ArrayList<>();
    inorderMorries(root1, arr1);
    inorderMorries(root2, arr2);

    merge(arr1, arr2, ans);
    return ans;
  }

  /*
   * Ceil in BST
   * Given a BST and a number X, find Ceil of X.
   * Note: Ceil(X) is a number that is either equal to X or is immediately greater
   * than X.
   * If Ceil could not be found, return -1.
   * Input: root = [5, 1, 7, N, 2, N, N, N, 3], X = 3
   * 5
   * / \
   * 1 7
   * \
   * 2
   * \
   * 3
   * Output: 3
   */
  int findCeil(Node root, int key) {
    if (root == null)
      return -1;
    // Code here
    Node curr = root;
    while (curr != null) {
      if (curr.left != null) {
        Node pred = curr.left;
        while (pred.right != null && pred.right != curr)
          pred = pred.right;
        if (pred.right == null) {
          pred.right = curr;
          curr = curr.left;
        }
        if (pred.right == curr) {
          pred.right = null;
          if (curr.data >= key) {
            return curr.data;
          }
          curr = curr.right;
        }
      } else {
        if (curr.data >= key) {
          return curr.data;
        }
        curr = curr.right;
      }
    }
    return -1;
  }

  /*
   * 653. Two Sum IV - Input is a BST
   * Given the root of a binary search tree and an integer k, return true if
   * there exist two elements in the BST such that their sum is equal to k,
   * or false otherwise.
   * 
   * Input: root = [5,3,6,2,4,null,7], k = 9
   * Output: true
   */
  public boolean findTarget(TreeNode root, int k) {
    ArrayList<Integer> arr = new ArrayList<>();
    inorderMorries(root, arr);

    int i = 0, j = arr.size() - 1;
    while (i < j) {
      int total = arr.get(i) + arr.get(j);
      if (total == k) {
        return true;
      } else if (total < k)
        i++;
      else
        j--;
    }
    return false;
  }

  /*
   * 669. Trim a Binary Search Tree
   * Given the root of a binary search tree and the lowest and highest boundaries
   * as low and high, trim the tree so that all its elements lies in [low, high].
   * 
   * Input: root = [1,0,2], low = 1, high = 2
   * Output: [1,null,2]
   */

  public void trim(TreeNode root, int low, int high) {
    if (root == null)
      return;
    while (root.left != null) {
      if (root.left.val < low)
        root.left = root.left.right;
      else if (root.left.val > high)
        root.left = root.left.left;
      else
        break;
    }
    while (root.right != null) {
      if (root.right.val < low)
        root.right = root.right.right;
      else if (root.right.val > high)
        root.right = root.right.left;
      else
        break;
    }

    trim(root.left, low, high);
    trim(root.right, low, high);
  }

  public TreeNode trimBST(TreeNode root, int low, int high) {
    TreeNode temp = new TreeNode(Integer.MAX_VALUE);
    temp.left = root;
    trim(temp, low, high);
    return temp.left;
  }

  /*
   * 99. Recover Binary Search Tree
   * You are given the root of a binary search tree (BST), where the values of
   * exactly two nodes of the tree were swapped by mistake. Recover the tree
   * without changing its structure.
   * 
   * Input: root = [1,3,null,null,2]
   * Output: [3,1,null,null,2]
   * Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3
   * makes the BST valid.
   */
  public void recoverTree(TreeNode root) {
    List<TreeNode> ls = new ArrayList<>();
    TreeNode curr = root;
    TreeNode prev = null;
    TreeNode prevPrev = null;
    while (curr != null) {

      if (curr.left != null) {
        TreeNode pred = curr.left;
        while (pred.right != null && pred.right != curr)
          pred = pred.right;
        if (pred.right == null) {
          pred.right = curr;
          curr = curr.left;
        }
        if (pred.right == curr) {
          pred.right = null;

          if (prevPrev != null && prev != null) {
            if (prevPrev.val < prev.val && prev.val > curr.val)
              ls.add(prev);
            if (prevPrev.val > prev.val && prev.val < curr.val)
              ls.add(prev);
          } else if (prev != null && prev.val > curr.val) {
            ls.add(prev);
          }

          prevPrev = prev;
          prev = curr;
          curr = curr.right;
        }

      } else {
        if (prevPrev != null && prev != null) {
          if (prevPrev.val < prev.val && prev.val > curr.val)
            ls.add(prev);
          if (prevPrev.val > prev.val && prev.val < curr.val)
            ls.add(prev);
        } else if (prev != null && prev.val > curr.val) {
          ls.add(prev);
        }

        prevPrev = prev;
        prev = curr;
        curr = curr.right;
      }
    }
    if (prevPrev.val > prev.val)
      ls.add(prev);
    TreeNode firstNode = ls.get(0);
    TreeNode lastNode = ls.get(ls.size() - 1);
    int temp = firstNode.val;
    firstNode.val = lastNode.val;
    lastNode.val = temp;
  }

}

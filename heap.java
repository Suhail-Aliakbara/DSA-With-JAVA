import java.util.ArrayList;
import java.util.PriorityQueue;

public class heap {
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
   * 215. Kth Largest Element in an Array
   * Given an integer array nums and an integer k, return the kth
   * largest element in the array.
   * Input: nums = [3,2,1,5,6,4], k = 2
   * Output: 5
   */
  public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    for (int i = 0; i < nums.length; i++) {
      pq.add(nums[i]);
      if (pq.size() > k) {
        pq.poll();
      }
    }
    return pq.poll();
  }

  /*
   * Kth Smallest
   * Given an array arr[] and an integer k where k is smaller than the
   * size of the array, the task is to find the kth smallest element
   * in the given array.
   * Input: arr[] = [7, 10, 4, 3, 20, 15], k = 3
   * Output: 7
   */
  public static int kthSmallest(int[] arr, int k) {
    // Your code here
    PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> Integer.compare(y, x));
    for (int i = 0; i < arr.length; i++) {
      pq.add(arr[i]);
      if (pq.size() > k) {
        pq.poll();
      }
    }
    return pq.poll();
  }

  /*
   * Merge k Sorted Arrays
   * Given k sorted arrays arranged in the form of a matrix of size
   * k * k. The task is to merge them into one sorted array.
   * Return the merged sorted array
   * 
   * Input: k = 4, arr[][]={{1,2,3,4},{2,2,3,4},{5,5,6,6},{7,8,9,9}}
   * Output: 1 2 2 2 3 3 4 4 5 5 6 6 7 8 9 9
   */
  public static ArrayList<Integer> mergeKArrays(int[][] arr, int K) {
    // Write your code here.
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        pq.add(arr[i][j]);
      }
    }
    ArrayList<Integer> ans = new ArrayList<>();
    while (!pq.isEmpty()) {
      ans.add(pq.poll());
    }
    return ans;
  }

  /*
   * Given an array of points where each point is represented as
   * points[i] = [xi, yi] on the X-Y plane and an integer k,
   * return the k closest points to the origin (0, 0).
   * 
   * The distance between two points on the X-Y plane is the
   * Euclidean distance, defined as:
   * sqrt( (x2 - x1)2 + (y2 - y1)2 )
   * 
   * Input: k = 2, points[] = [[1, 3], [-2, 2], [5, 8], [0, 1]]
   * Output: [[-2, 2], [0, 1]]
   */
  public int[][] kClosest(int[][] points, int k) {
    // Your code here
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[2], a[2]));

    for (int i = 0; i < points.length; i++) {

      int x = points[i][0];
      int y = points[i][1];
      int dist = x * x + y * y;
      pq.add(new int[] { x, y, dist });
      if (pq.size() > k) {
        pq.poll();
      }
    }

    int[][] ans = new int[k][2];

    for (int i = 0; i < k; i++) {
      int[] arr = pq.poll();
      ans[i][0] = arr[0];
      ans[i][1] = arr[1];
    }

    return ans;
  }

  /*
   * 23. Merge k Sorted Lists
   * You are given an array of k linked-lists lists, each linked-list is
   * sorted in ascending order.
   * Merge all the linked-lists into one sorted linked-list and return it.
   * Input: lists = [[1,4,5],[1,3,4],[2,6]]
   * Output: [1,1,2,3,4,4,5,6]
   * Explanation: The linked-lists are:
   * [
   * 1->4->5,
   * 1->3->4,
   * 2->6
   * ]
   * merging them into one sorted list:
   * 1->1->2->3->4->4->5->6
   */
  class Pair {
    int num;
    ListNode node;

    Pair(int num, ListNode node) {
      this.num = num;
      this.node = node;
    }
  }

  public ListNode mergeKLists(ListNode[] lists) {
    PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> Integer.compare(x.num, y.num));
    int n = lists.length;

    for (int i = 0; i < n; i++) {
      if (lists[i] != null) {
        pq.add(new Pair(lists[i].val, lists[i]));
      }
    }
    ListNode dummy = new ListNode(-1);
    ListNode temp = dummy;
    while (!pq.isEmpty()) {
      Pair p = pq.remove();
      ListNode node = p.node;
      temp.next = node;
      if (node.next != null) {
        pq.add(new Pair(node.next.val, node.next));
      }
      temp = temp.next;
    }
    return dummy.next;
  }

  /*
   * 295. Find Median from Data Stream
   * The median is the middle value in an ordered integer list. If the size
   * of the list is even, there is no middle value, and the median is the
   * mean of the two middle values.
   * 
   */
  class MedianFinder {

    PriorityQueue<Integer> leftPq;
    PriorityQueue<Integer> rightPq;

    public MedianFinder() {
      leftPq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
      rightPq = new PriorityQueue<>();

    }

    public void addNum(int num) {
      if (leftPq.size() == rightPq.size()) {
        rightPq.add(num);
        leftPq.add(rightPq.remove());
      } else {
        leftPq.add(num);
        rightPq.add(leftPq.remove());
      }
    }

    public double findMedian() {
      if (leftPq.size() == rightPq.size()) {
        return (leftPq.peek() + rightPq.peek()) / 2.0;
      } else {
        return leftPq.peek() * 1.0;
      }
    }
  }

}

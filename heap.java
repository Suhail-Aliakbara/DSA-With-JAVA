import java.util.ArrayList;
import java.util.PriorityQueue;

public class heap {
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

}

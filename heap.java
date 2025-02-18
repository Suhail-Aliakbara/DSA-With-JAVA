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
}

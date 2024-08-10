import java.util.ArrayList;

public class binarySearch {

  /*
   * Median in a row-wise sorted Matrix
   * Given a row wise sorted matrix of size R*C where R and C are always odd, find
   * the median of the matrix.
   * Input: R = 3, C = 3
   * M= [[1, 3, 5],
   * [2, 6, 9],
   * [3, 6, 9]]
   * Output: 5
   * Explanation: Sorting matrix elements gives
   * us {1,2,3,3,5,6,6,9,9}. Hence, 5 is median.
   */

  int upperBound(int[] matrix, int m, int x) {
    int low = 0;
    int high = m - 1;
    int ans = m;

    // Perform binary search to find the first element greater than x
    while (low <= high) {
      int mid = (low + high) / 2;

      if (matrix[mid] > x) {
        high = mid - 1;
        ans = mid;
      } else {
        low = mid + 1;
      }
    }

    return ans;
  }

  int count(int[][] matrix, int n, int m, int x) {
    int cnt = 0;
    // Count the number of elements less than or equal to x in each row
    for (int i = 0; i < n; i++) {
      cnt += upperBound(matrix[i], m, x);
    }
    return cnt;
  }

  int median(int matrix[][], int R, int C) {
    // Initialize low and high to the minimum and maximum elements in the matrix
    int low = Integer.MAX_VALUE;
    int high = Integer.MIN_VALUE;

    int n = matrix.length;
    int m = matrix[0].length;

    // Find the minimum and maximum elements in the matrix
    for (int i = 0; i < n; i++) {
      if (low > matrix[i][0]) {
        low = matrix[i][0];
      }
      if (high < matrix[i][m - 1]) {
        high = matrix[i][m - 1];
      }
    }
    int req = (m * n) / 2;

    // Perform binary search to find the median
    while (low <= high) {
      int mid = (low + high) / 2;
      int count = count(matrix, n, m, mid);
      if (count <= req)
        low = mid + 1;
      else
        high = mid - 1;
    }

    return low;
  }
  /*
   * O(log(10^9)) * O(M(logN)), where M = number of rows in the given matrix, N =
   * number of columns in the given matrix
   * 
   * Reason: Our search space lies between [0, 109] as the min(matrix) can be 0
   * and the max(matrix) can be 109. We are applying binary search in this search
   * space and it takes O(log(109)) time complexity.
   * Then we call countSmallEqual() function for every ‘mid’ and this function
   * takes O(M(logN)) time complexity.
   * 
   * Space Complexity : O(1)
   */

  /*
   * 540. Single Element in a Sorted Array
   * You are given a sorted array consisting of only integers where every element
   * appears exactly twice, except for one element which appears exactly once.
   * Return the single element that appears only once.
   * Your solution must run in O(log n) time and O(1) space.
   * Input: nums = [3,3,7,7,10,11,11]
   * Output: 10
   */

  public int singleNonDuplicate(int[] arr) {
    int n = arr.length;

    int low = 0;
    int high = n - 2;

    while (low <= high) {
      int mid = (low + high) / 2;

      if (arr[mid] == arr[mid ^ 1]) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return arr[low];
  }
  /*
   * 540 ->
   * if i am in left half -> 1st instance == even && 2nd instance == odd of
   * currElement
   * if i am in right half -> 1st instance == odd && 2nd instance == even of
   * currElement
   */

  /*
   * 33. Search in Rotated Sorted Array
   * Input: nums = [4,5,6,7,0,1,2], target = 0
   * Output: 4
   * Input: nums = [1], target = 0
   * Output: -1
   */
  public static int search(ArrayList<Integer> arr, int n, int k) {
    int low = 0, high = n - 1;
    while (low <= high) {
      int mid = (low + high) / 2;

      // if mid points to the target
      if (arr.get(mid) == k)
        return mid;

      // if left part is sorted
      if (arr.get(low) <= arr.get(mid)) {
        if (arr.get(low) <= k && k <= arr.get(mid)) {
          // element exists
          high = mid - 1;
        } else {
          // element does not exist
          low = mid + 1;
        }
      } else { // if right part is sorted
        if (arr.get(mid) <= k && k <= arr.get(high)) {
          // element exists
          low = mid + 1;
        } else {
          // element does not exist
          high = mid - 1;
        }
      }
    }
    return -1;
  }

  /*
   * 4. Median of Two Sorted Arrays
   * Input: nums1 = [1,3], nums2 = [2]
   * Output: 2.00000
   * Explanation: merged array = [1,2,3] and median is 2.
   * 
   * Input: nums1 = [1,2], nums2 = [3,4]
   * Output: 2.50000
   * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
   */

  public static double median(int[] a, int[] b) {
    // Size of two given arrays
    int n1 = a.length;
    int n2 = b.length;

    int n = n1 + n2; // total size
    // required indices:
    int ind2 = n / 2;
    int ind1 = ind2 - 1;
    int cnt = 0;
    int ind1el = -1, ind2el = -1;

    // apply the merge step:
    int i = 0, j = 0;
    while (i < n1 && j < n2) {
      if (a[i] < b[j]) {
        if (cnt == ind1)
          ind1el = a[i];
        if (cnt == ind2)
          ind2el = a[i];
        cnt++;
        i++;
      } else {
        if (cnt == ind1)
          ind1el = b[j];
        if (cnt == ind2)
          ind2el = b[j];
        cnt++;
        j++;
      }
    }

    // copy the left-out elements:
    while (i < n1) {
      if (cnt == ind1)
        ind1el = a[i];
      if (cnt == ind2)
        ind2el = a[i];
      cnt++;
      i++;
    }
    while (j < n2) {
      if (cnt == ind1)
        ind1el = b[j];
      if (cnt == ind2)
        ind2el = b[j];
      cnt++;
      j++;
    }

    // Find the median:
    if (n % 2 == 1) {
      return (double) ind2el;
    }

    return (double) ((double) (ind1el + ind2el)) / 2.0;
  }

  // OPTIMAL APPROACH
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int n1 = nums1.length; // Length of the first array
    int n2 = nums2.length; // Length of the second array

    // Ensure that nums1 is the smaller array to minimize the number of operations
    if (n1 > n2)
      return findMedianSortedArrays(nums2, nums1);

    int leftEl = (n1 + n2 + 1) / 2; // Calculate the number of elements on the left side of the partition
    int n = n1 + n2; // Total number of elements in both arrays
    int low = 0, high = n1; // Initialize the binary search range for nums1

    // Binary search to find the correct partition
    while (low <= high) {
      int mid1 = (low + high) / 2; // Mid index for the partition in nums1
      int mid2 = leftEl - mid1; // Mid index for the partition in nums2, based on the total left elements

      // Elements just after the partition in nums1 and nums2
      int r1 = mid1 < n1 ? nums1[mid1] : Integer.MAX_VALUE; // Right element in nums1 or positive infinity if out of
                                                            // bounds
      int r2 = mid2 < n2 ? nums2[mid2] : Integer.MAX_VALUE; // Right element in nums2 or positive infinity if out of
                                                            // bounds

      // Elements just before the partition in nums1 and nums2
      int l1 = mid1 - 1 >= 0 ? nums1[mid1 - 1] : Integer.MIN_VALUE; // Left element in nums1 or negative infinity if out
                                                                    // of bounds
      int l2 = mid2 - 1 >= 0 ? nums2[mid2 - 1] : Integer.MIN_VALUE; // Left element in nums2 or negative infinity if out
                                                                    // of bounds

      // Check if we have found the correct partition
      if (l1 <= r2 && l2 <= r1) {
        // If total number of elements is odd, return the max of the left partition
        if (n % 2 == 1) {
          return Math.max(l1, l2);
        } else {
          // If total number of elements is even, return the average of the middle two
          // elements
          return ((double) (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0);
        }
      } else if (l1 > r2) {
        // If l1 is greater than r2, move the partition in nums1 to the left
        high = mid1 - 1;
      } else {
        // If l2 is greater than r1, move the partition in nums1 to the right
        low = mid1 + 1;
      }
    }

    return 0; // This line should never be reached, added to satisfy the return type
  }

}

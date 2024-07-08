
import java.util.HashMap;

public class Arrays {
  // ---------------------------Print All divisor-----------------==-------
  /*
   * Given a positive integer N., The task is to find the value of Σi from 1 to N
   * F(i) where function F(i) for the number i is defined as the sum of all
   * divisors of i.
   * Input: N = 4
   * Output: 15
   * Explanation:
   * F(1) = 1
   * F(2) = 1 + 2 = 3
   * F(3) = 1 + 3 = 4
   * F(4) = 1 + 2 + 4 = 7
   * ans = F(1) + F(2) + F(3) + F(4) = 1 + 3 + 4 + 7 = 15
   */
  static long sumOfDivisors(int N) {
    long sum = 0;
    for (int i = 1; i <= N; i++) {
      sum += (N / i) * i;
    }
    return sum;
  }

  // ------------------Check if the Array is rotated and
  // Sorted--------------------
  /*
   * *
   * Rotated and Sorted array
   * Input: nums = [3,4,5,1,2]
   * Output: true
   *
   */
  public boolean check(int[] nums) {
    int k = 0;
    int n = nums.length - 1;

    if (n == 0)
      return true;

    for (int i = 0; i < n; i++) {
      if (nums[i] > nums[i + 1]) {
        k++;
      }
    }
    if (k == 0)
      return true;
    else if (k == 1 && nums[0] >= nums[n])
      return true;
    else
      return false;
  }

  // ---------------------Remove Duplicates from array------------------------

  /*
   * Solved by seeing and understanding the solution
   * 
   * Input: nums = [0,0,1,1,1,2,2,3,3,4]
   * Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
   * Explanation: Your function should return k = 5, with the first five elements
   * of nums being 0, 1, 2, 3, and 4 respectively.
   */
  public int removeDuplicates(int[] nums) {
    int k = 1;
    int n = nums.length - 1;
    for (int i = 0; i < n; i++) {
      if (nums[i] != nums[i + 1]) {
        nums[k] = nums[i + 1];
        k++;
      }
    }
    return k;
  }

  // ----------------------Rotate an Array by K places--------------------------

  /*
   * Given an integer array nums, rotate the array to the right by k steps, where
   * k is non-negative.
   * 
   * Input: nums = [1,2,3,4,5,6,7], k = 3
   * Output: [5,6,7,1,2,3,4]
   */
  public static void Rotatetoright(int[] arr, int n, int k) {
    if (n == 0)
      return;
    k = k % n;
    if (k > n)
      return;
    int[] temp = new int[k];
    for (int i = 0; i < n - k; i++) {
      temp[i] = arr[i];
    }
    for (int i = n - k; i < n; i++) {
      arr[i + k - n] = arr[i];
    }
    for (int i = k; i < n; i++) {
      arr[i] = temp[i - k];
    }
  }

  // Optimal solution for same problem without using any extra space

  public static void Reverse(int[] arr, int start, int end) {
    while (start <= end) {
      int temp = arr[start];
      arr[start] = arr[end];
      arr[end] = temp;
      start++;
      end--;
    }
  }

  // Function to Rotate k elements to right
  public static void Rotateeletoright(int[] arr, int n, int k) {
    // Reverse first n-k elements
    Reverse(arr, 0, n - k - 1);
    // Reverse last k elements
    Reverse(arr, n - k, n - 1);
    // Reverse whole array
    Reverse(arr, 0, n - 1);
  }

  // -------------------Move Zeros to end of the array------------------------
  /*
   * Given an integer array nums, move all 0's to the end of it while maintaining
   * the relative order of the non-zero elements.
   * 
   * Note that you must do this in-place without making a copy of the array.
   * 
   * Input: nums = [0,1,0,3,12]
   * Output: [1,3,12,0,0]
   * 
   */

  public void moveZeroes(int[] arr) {
    int k = 0;
    int n = arr.length;
    for (int i = 0; i < n; i++) {
      if (arr[i] == 0) {
        k++;
      } else if (k > 0) {
        arr[i - k] = arr[i];
        arr[i] = 0;
      }
    }
  }

  /*
   * A permutation of an array of integers is an arrangement of its members into a
   * sequence or linear order.
   * 
   * For example, for arr = [1,2,3], the following are all the permutations of
   * arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
   * 
   * Input: nums = [1,2,3]
   * Output: [1,3,2]
   * 
   * Input: nums = [3,2,1]
   * Output: [1,2,3]
   */

  public void reverse(int arr[], int si, int ei) { // Reverse function
    while (si < ei) {
      int temp = arr[si];
      arr[si] = arr[ei];
      arr[ei] = temp;
      si++;
      ei--;
    }
  }

  public void nextPermutation(int[] arr) {
    int n = arr.length;
    int pivot = -1; // storing pivot index

    for (int i = n - 2; i >= 0; i--) { // Backward looping whenever i < i + 1 and break it
      if (arr[i] < arr[i + 1]) {
        pivot = i; // store i in pivot
        break;
      }
    }
    if (pivot == -1) { // if array is sorted in decreasing order then just reverse it
      reverse(arr, 0, n - 1);
    } else {
      for (int i = n - 1; i >= 0; i--) { // if pivot exist swap pivot value with just bigger then pivot value from right
                                         // side
        if (arr[pivot] < arr[i]) {
          int temp = arr[pivot];
          arr[pivot] = arr[i];
          arr[i] = temp;
          break;
        }
      }
      reverse(arr, pivot + 1, n - 1); // Reverse entire right side from pivot + 1 to n-1
    }
  }

  /*
   * Kadane's algorithm
   * Given an integer array nums, find the subarray
   * with the largest sum, and return its sum.
   * 
   * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
   * Output: 6
   * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
   */

  public int maxSubArray(int[] nums) {
    int currsum = nums[0];
    int maxsum = nums[0];
    for (int i = 1; i < nums.length; i++) {
      currsum = Math.max(currsum + nums[i], nums[i]);
      maxsum = Math.max(currsum, maxsum);
    }
    return maxsum;
  }

  /*
   * You are given two integer arrays nums1 and nums2, sorted in non-decreasing
   * order, and two integers m and n, representing the number of elements in nums1
   * and nums2 respectively.
   * 
   * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
   * 
   * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
   * Output: [1,2,2,3,5,6]
   * Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
   * The result of the merge is [1,2,2,3,5,6] with the underlined elements coming
   * from nums1.
   */

  public void merge(int[] nums1, int m, int[] nums2, int n) {
    // Initialize pointers for nums1 and nums2 respectively.
    int i = m - 1, j = n - 1;
    // Pointer for the last position in nums1 where the next element should be
    // placed.
    int k = m + n - 1;

    // While there are elements to be compared in nums1 and nums2.
    while (i >= 0 && j >= 0) {
      // Place the larger element at the end of nums1.
      if (nums1[i] > nums2[j]) {
        nums1[k--] = nums1[i--];
      } else {
        nums1[k--] = nums2[j--];
      }
    }

    // If there are still elements in nums2, place them in nums1.
    // This is needed if nums2 has the smallest elements that need to be placed at
    // the start of nums1.
    while (j >= 0) {
      nums1[k--] = nums2[j--];
    }
  }

  /*
   * Given an array of integers nums containing n + 1 integers where each integer
   * is in the range [1, n] inclusive.
   * 
   * There is only one repeated number in nums, return this repeated number.
   * You must solve the problem without modifying the array nums and uses only
   * constant extra space.
   * 
   * Input: nums = [1,3,4,2,2]
   * Output: 2
   * Input: nums = [3,3,3,3,3]
   * Output: 3
   */

  public int findDuplicate(int[] nums) { // TC(O(NlogN))
    int n = nums.length;
    int low = 1; // The lower bound of the search space
    int high = n; // The upper bound of the search space, inclusive
    int mid; // The midpoint of the current search space

    // Binary search to find the duplicate
    while (low < high) {
      mid = (low + high) / 2; // Calculate the midpoint
      int count = 0; // Counter for the number of elements less than or equal to mid

      // Count how many numbers are less than or equal to mid
      for (int num : nums) {
        if (num <= mid)
          count++;
      }

      // If count is more than mid, the duplicate is in the lower half
      if (count > mid)
        high = mid; // Narrow the search space to the lower half
      else
        low = mid + 1; // Narrow the search space to the upper half
    }

    // low (or high) is the duplicate number
    return low;
  }

  // Fast-Slow Pointers // Optimal approach TC(O(N))

  public int findDuplicate1(int[] nums) {
    // Initialize two pointers, slow and fast, both starting at the first element
    int slow = nums[0];
    int fast = nums[0];

    // Phase 1: Find the intersection point of the two runners.
    do {
      // Move slow pointer by one step
      slow = nums[slow];
      // Move fast pointer by two steps
      fast = nums[nums[fast]];
    } while (slow != fast); // Continue until they meet

    // Phase 2: Find the entrance to the cycle (duplicate number).
    // Reset fast to the beginning
    fast = nums[0];
    while (slow != fast) { // Move slow and fast at the same pace
      // Move each pointer by one step
      slow = nums[slow];
      fast = nums[fast];
    }
    // When they meet again, slow (or fast) is pointing to the duplicate number
    return slow;
  }

  /*
   * Pow(x, n)
   * Input: x = 2.00000, n = 10
   * Output: 1024.00000
   * 
   * Input: x = 2.00000, n = -2
   * Output: 0.25000
   * Explanation: 2-2 = 1/22 = 1/4 = 0.25
   */
  public static double myPow(double x, int n) {
    double ans = 1.0; // Initialize answer to 1.0 as anything power 0 is 1
    long nn = n; // Convert n to long to handle cases when n is Integer.MIN_VALUE
    if (nn < 0)
      nn = -1 * nn; // If n is negative, make it positive for now and invert the answer later

    while (nn > 0) { // Loop until nn becomes 0
      if (nn % 2 == 1) { // If nn is odd
        ans = ans * x; // Multiply current answer by x
        nn = nn - 1; // Decrement nn by 1 to make it even
      } else { // If nn is even
        x = x * x; // Square x
        nn = nn / 2; // Divide nn by 2
      }
    }
    if (n < 0)
      ans = (double) (1.0) / (double) (ans); // If original n was negative, invert the answer
    return ans; // Return the computed power
  }

  /*
   * 62. Unique Paths
   * Given the two integers m and n, return the number of possible unique paths
   * that the robot can take to reach the bottom-right corner.
   * 
   * Input: m = 3, n = 2
   * Output: 3
   * Explanation: From the top-left corner, there are a total of 3 ways to reach
   * the bottom-right corner:
   * 1. Right -> Down -> Down
   * 2. Down -> Down -> Right
   * 3. Down -> Right -> Down
   */
  public int uniquePaths(int m, int n) {
    int steps = n + m - 2; // Total steps needed to go from top-left to bottom-right
    int r = m - 1; // Number of steps down (or right, depending on perspective)
    double result = 1; // Initialize result to 1 for multiplication

    for (int i = 1; i <= r; i++) {
      // Calculate the number of unique paths using the formula for combinations
      // C(steps, r) = (steps! / (r! * (steps-r)!))
      // This loop incrementally calculates the combination to avoid overflow
      result = result * (steps - r + i) / i;
    }
    return (int) result; // Cast the result to int and return
  }

  /*
   * 493. Reverse Pairs
   * Given an integer array nums, return the number of reverse pairs in the array.
   * A reverse pair is a pair (i, j) where:
   * 0 <= i < j < nums.length and
   * nums[i] > 2 * nums[j].
   * 
   * Input: nums = [1,3,2,3,1]
   * Output: 2
   * Explanation: The reverse pairs are:
   * (1, 4) --> nums[1] = 3, nums[4] = 1, 3 > 2 * 1
   * (3, 4) --> nums[3] = 3, nums[4] = 1, 3 > 2 * 1
   */

  // TC(O(2N*logN)) SC(O(N))
  // Method to perform merge sort and count reverse pairs
  public int mergeSort(int[] arr, int si, int ei) {
    int cnt = 0; // Initialize count of reverse pairs to 0
    if (si >= ei) { // Base case: if start index is greater or equal to end index
      return cnt;
    }
    int mid = (si + ei) / 2; // Find the middle index of the array
    cnt += mergeSort(arr, si, mid); // Recursively sort the first half and count reverse pairs
    cnt += mergeSort(arr, mid + 1, ei); // Recursively sort the second half and count reverse pairs
    cnt += merge(arr, si, mid, ei); // Merge the two halves and count reverse pairs during merging
    return cnt; // Return total count of reverse pairs
  }

  // Method to merge two sorted halves of an array and count reverse pairs
  public int merge(int arr[], int si, int mid, int ei) {
    long temp[] = new long[ei - si + 1]; // Temporary array to store merged result
    int i = si; // Pointer for the first half
    int j = mid + 1; // Pointer for the second half
    int k = 0; // Pointer for the temporary array
    int cnt = 0; // Initialize count of reverse pairs to 0

    // Loop through both halves to find reverse pairs
    while (i <= mid && j <= ei) {
      if ((long) arr[i] > (long) arr[j] * 2) { // If reverse pair found
        cnt += mid - i + 1; // Count all possible reverse pairs from current i
        j++; // Move pointer in the second half
      } else {
        i++; // Move pointer in the first half
      }
    }
    // Reset pointers to start positions for merging
    i = si;
    j = mid + 1;
    // Merge the two halves into the temporary array
    while (i <= mid && j <= ei) {
      if (arr[i] <= arr[j]) {
        temp[k++] = arr[i++];
      } else {
        temp[k++] = arr[j++];
      }
    }
    // Copy any remaining elements from the first half
    while (i <= mid) {
      temp[k++] = arr[i++];
    }
    // Copy any remaining elements from the second half
    while (j <= ei) {
      temp[k++] = arr[j++];
    }

    // Copy the merged elements back into the original array
    for (i = si; i <= ei; i++) {
      arr[i] = (int) temp[i - si];
    }
    return cnt; // Return count of reverse pairs found during merging
  }

  // Method to count reverse pairs in an array
  public int reversePairs(int[] nums) {
    int n = nums.length; // Length of the array
    return mergeSort(nums, 0, n - 1); // Perform merge sort and count reverse pairs
  }

  // BY Striver same TC and SC
  public static int countPairs(int[] arr, int low, int mid, int high) {
    // Initialize the pointer for the right half of the array
    int right = mid + 1;
    // Initialize the counter for reverse pairs
    int cnt = 0;
    // Iterate through the left half of the array
    for (int i = low; i <= mid; i++) {
      // Move the right pointer as long as the condition for a reverse pair is met
      while (right <= high && arr[i] > 2 * arr[right])
        right++;
      // Count the number of reverse pairs for the current element in the left half
      // by calculating the distance between the current position of the right pointer
      // and its initial position
      cnt += (right - (mid + 1));
    }
    // Return the total count of reverse pairs found in this segment of the array
    return cnt;
  }

  public static int mergeSort1(int[] arr, int low, int high) {
    int cnt = 0;
    if (low >= high)
      return cnt;
    int mid = (low + high) / 2;
    cnt += mergeSort1(arr, low, mid); // left half
    cnt += mergeSort1(arr, mid + 1, high); // right half
    cnt += countPairs(arr, low, mid, high); // Modification
    // merge(arr, low, mid, high); // merging sorted halves
    // same as origninal merge function
    return cnt;
  }

  /*
   * 3. Longest Substring Without Repeating Characters
   * Given a string s, find the length of the longest substring
   * without repeating characters.
   * Input: s = "abcabcbb"
   * Output: 3
   * Explanation: The answer is "abc", with the length of 3.
   */
  public int lengthOfLongestSubstring(String s) {
    int n = s.length();
    HashMap<Character, Integer> mpp = new HashMap<Character, Integer>();
    int left = 0;
    int right = 0;
    int len = 0;

    while (right < n) { // Iterate through the string with the right pointer
      if (mpp.containsKey(s.charAt(right))) { // If the current character is already in the hashmap
        // Move the left pointer to the right of the last index of the current character
        // to avoid repeating characters
        left = Math.max(mpp.get(s.charAt(right)) + 1, left);
      }
      // Update the current character's latest index in the hashmap
      mpp.put(s.charAt(right), right);

      // Calculate the length of the current substring without repeating characters
      // and update the maximum length
      len = Math.max(len, right - left + 1);
      right++; // Move the right pointer to the next character
    }
    return len;
  }

  /*
   * 42. Trapping Rain Water
   * Given n non-negative integers representing an elevation map where the width
   * of each bar is 1, compute how much water it can trap after raining.
   * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
   * Output: 6
   * Explanation: The above elevation map (black section) is represented by array
   * [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section)
   * are being trapped.
   */
  public int trap(int[] height) {
    int n = height.length;
    int left = 0, right = n - 1;
    int maxLeft = 0, maxRight = 0;
    int res = 0;

    while (left <= right) {
      if (height[left] <= height[right]) {
        if (height[left] >= maxLeft)
          maxLeft = height[left];
        else
          res += maxLeft - height[left];

        left++;
      } else {
        if (height[right] >= maxRight)
          maxRight = height[right];
        else
          res += maxRight - height[right];

        right--;
      }
    }
    return res;
  }
}
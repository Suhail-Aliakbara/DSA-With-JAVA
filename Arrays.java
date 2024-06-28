
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

  // ----------------------Check if the Array is Sorted-----------------------
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
   * Given an array nums containing n distinct numbers in the range [0, n], return
   * the only number in the range that is missing from the array.
   * 
   * Input: nums = [3,0,1]
   * Output: 2
   * Explanation: n = 3 since there are 3 numbers, so all numbers are in the range
   * [0,3]. 2 is the missing number in the range since it does not appear in nums.
   */

  public static int missingNumber1(int[] a, int N) {
    int xor1 = 0, xor2 = 0;

    for (int i = 0; i < N - 1; i++) {
      xor2 = xor2 ^ a[i]; // XOR of array elements
      xor1 = xor1 ^ (i + 1); // XOR up to [1...N-1]
    }
    xor1 = xor1 ^ N; // XOR up to [1...N]

    return (xor1 ^ xor2); // the missing number
  }

  public int missingNumber(int[] nums) {
    int xor = nums.length;
    for (int i = 0; i < nums.length; i++) {
      xor ^= i;
      xor ^= nums[i];
    }
    return xor;
  }

  // The result will be the missing number because the XOR operation will cancel
  // out all the numbers that are present.
  // the XOR operation helps in finding the missing number in the array
  // efficiently
  // XOR of two same numbers is always 0 i.e. a ^ a = 0. ←Property 1.
  // XOR of a number with 0 will result in the number itself i.e. 0 ^ a = a.
  // ←Property 2

  public static int missingNumber2(int[] a, int N) {
    // Summation of first N numbers:
    int sum = ((N + 1) * N) / 2;

    // Summation of all array elements:
    int s2 = 0;
    for (int i = 0; i < N - 1; i++) {
      s2 += a[i];
    }
    int missingNum = sum - s2;
    return missingNum;
  }
  // We know that the summation of the first N numbers is (N*(N+1))/2.
  // and while adding all the numbers in the s2, we did not add that particular
  // number in s2 that is missing.

  /*
   * Given a non-empty array of integers nums, every element appears twice except
   * for one. Find that single one.
   * 
   * Input: nums = [2,2,1]
   * Output: 1
   * 
   * Input: nums = [4,1,2,1,2]
   * Output: 4
   */
  public int singleNumber(int[] nums) {

    int n = nums.length;
    int xor = nums[0];

    for (int i = 1; i < n; i++) {
      xor = xor ^ nums[i];
    }
    return xor;
  }
  // it will cancell all the element twice hence which element appear once will
  // remain

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
    // Reset slow to the beginning
    slow = nums[0];
    while (slow != fast) { // Move slow and fast at the same pace
      // Move each pointer by one step
      slow = nums[slow];
      fast = nums[fast];
    }
    // When they meet again, slow (or fast) is pointing to the duplicate number
    return slow;
  }

  /*
   * You are given a read only array of n integers from 1 to n.
   * Each integer appears exactly once except A which appears twice and B which is
   * missing.
   * Return A and B.
   * 
   * Input:[3 1 2 5 3]*
   * Output:[3, 4]
   */
  public static int[] findMissingRepeatingNumbers(int[] a) { // TC(O(2N)) SC(O(N))
    int n = a.length; // size of the array
    int[] hash = new int[n + 1]; // hash array

    // update the hash array:
    for (int i = 0; i < n; i++) {
      hash[a[i]]++;
    }

    // Find the repeating and missing number:
    int repeating = -1, missing = -1;
    for (int i = 1; i <= n; i++) {
      if (hash[i] == 2)
        repeating = i;
      else if (hash[i] == 0)
        missing = i;

      if (repeating != -1 && missing != -1)
        break;
    }
    int[] ans = { repeating, missing };
    return ans;
  }

  public static int[] findMissingRepeatingNumbers1(int[] a) { // TC(O(N)) SC(1)
    long n = a.length; // size of the array
    // Find Sn and S2n:
    long SN = (n * (n + 1)) / 2;
    long S2N = (n * (n + 1) * (2 * n + 1)) / 6;

    // Calculate S and S2:
    long S = 0, S2 = 0;
    for (int i = 0; i < n; i++) {
      S += a[i];
      S2 += (long) a[i] * (long) a[i];
    }

    // S-Sn = X-Y:
    long val1 = S - SN;

    // S2-S2n = X^2-Y^2:
    long val2 = S2 - S2N;

    // Find X+Y = (X^2-Y^2)/(X-Y):
    val2 = val2 / val1;

    // Find X and Y: X = ((X+Y)+(X-Y))/2 and Y = X-(X-Y),
    // Here, X-Y = val1 and X+Y = val2:
    long x = (val1 + val2) / 2;
    long y = x - val1;

    int[] ans = { (int) x, (int) y };
    return ans;
  }

  public static int[] findMissingRepeatingNumbers2(int[] a) { // 2nd optimal solution. Same Time Complexity (TC), Space
                                                              // Complexity (SC)
    int n = a.length; // size of the array
    int xr = 0;

    // Step 1: Find XOR of all elements and their indices:
    for (int i = 0; i < n; i++) {
      xr = xr ^ a[i]; // XOR with array element
      xr = xr ^ (i + 1); // XOR with index (1-based)
    }

    // Step 2: Find the rightmost set bit (differentiating bit):
    /*
     * int bitno = 0; // wherever there is number put bitno
     * while (true) {
     * if ((xr & (1 << bitno)) != 0) {
     * break;
     * }
     * bitno++;
     * }
     */
    int number = (xr & ~(xr - 1)); // Isolates the rightmost set bit

    // Step 3: Group the numbers based on the set bit:
    int zero = 0; // XOR of elements where the set bit is not set
    int one = 0; // XOR of elements where the set bit is set
    for (int i = 0; i < n; i++) {
      if ((a[i] & number) != 0) { // If set bit is set in the current element
        one = one ^ a[i];
      } else { // If set bit is not set in the current element
        zero = zero ^ a[i];
      }
    }

    // XOR with all possible numbers (1 to n) to find the missing and repeating:
    for (int i = 1; i <= n; i++) {
      if ((i & number) != 0) { // If set bit is set in the index
        one = one ^ i;
      } else { // If set bit is not set in the index
        zero = zero ^ i;
      }
    }

    // Last step: Identify which is the missing and which is the repeating number:
    int cnt = 0; // Count how many times the 'zero' number appears in the array
    for (int i = 0; i < n; i++) {
      if (a[i] == zero)
        cnt++;
    }

    // If 'zero' appears twice, it is the repeating number; otherwise, 'one' is:
    if (cnt == 2)
      return new int[] { zero, one }; // 'zero' is repeating
    return new int[] { one, zero }; // 'one' is repeating
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
}

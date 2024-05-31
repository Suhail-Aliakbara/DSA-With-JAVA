import java.util.*;

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
   * Solved this question by seeing and understanding the solution
   * 
   * Rotated and Sorted array
   * 
   * Input: nums = [3,4,5,1,2]
   * Output: true
   * Explanation: [1,2,3,4,5] is the original sorted array.
   * You can rotate the array by x = 3 positions to begin on the the element of
   * value 3: [3,4,5,1,2].
   * 
   * Input: nums = [2,1,3,4]
   * Output: false
   * Explanation: There is no sorted array once rotated that can make nums.
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
   * Solved by seeing and understanding the solution (only 1 line was missing in
   * my code)
   * 
   * Input: nums = [0,0,1,1,1,2,2,3,3,4]
   * Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
   * Explanation: Your function should return k = 5, with the first five elements
   * of nums being 0, 1, 2, 3, and 4 respectively.
   * It does not matter what you leave beyond the returned k (hence they are
   * underscores).
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
   * Explanation:
   * rotate 1 steps to the right: [7,1,2,3,4,5,6]
   * rotate 2 steps to the right: [6,7,1,2,3,4,5]
   * rotate 3 steps to the right: [5,6,7,1,2,3,4]
   */
  public static void Rotatetoright(int[] arr, int n, int k) {
    if (n == 0)
      return;
    k = k % n;
    if (k > n)
      return;
    int[] temp = new int[k];
    for (int i = n - k; i < n; i++) {
      temp[i - n + k] = arr[i];
    }
    for (int i = n - k - 1; i >= 0; i--) {
      arr[i + k] = arr[i];
    }
    for (int i = 0; i < k; i++) {
      arr[i] = temp[i];
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

  /*
   * public static void main(String args[]) {
   * int n = 7;
   * int[] arr = {1,2,3,4,5,6,7};
   * int k = 2;
   * Rotatetoright(arr, n, k);
   * System.out.println("After Rotating the elements to right ");
   * for (int i = 0; i < n; i++) {
   * System.out.print(arr[i] + " ");
   * }
   * }
   */

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

  // ------------------------Union of two Arrays--------------------
  /*
   * Given two sorted arrays of size n and m respectively, find their union
   * 
   * Input:
   * n = 5, arr1[] = {1, 2, 3, 4, 5}
   * m = 5, arr2 [] = {1, 2, 3, 6, 7}
   * Output: 1 2 3 4 5 6 7
   * Explanation:
   * Distinct elements including both the arrays are: 1 2 3 4 5 6 7.
   */

  public static ArrayList<Integer> findUnion(int arr1[], int arr2[], int n, int m) {
    // add your code here
    HashSet<Integer> union = new HashSet<>();

    for (int nums : arr1) {
      union.add(nums);
    }

    for (int nums : arr2) {
      union.add(nums);
    }

    ArrayList<Integer> unionArray = new ArrayList<>(union);
    Collections.sort(unionArray);
    return unionArray;
  }

  // Another Solution without using HastSet
  public static ArrayList<Integer> findUnionWithoutHashSet(int arr1[], int arr2[], int n, int m) {
    ArrayList<Integer> ans = new ArrayList<>();

    int i = 0, j = 0;

    while (i < n && j < m) {
      int x = arr1[i];
      int y = arr2[j];

      if (x < y) {
        ans.add(x);
        while (i < n && arr1[i] == x)
          i++; // Until the current element is not in array
      } else if (x == y) {
        ans.add(x);
        while (i < n && arr1[i] == x)
          i++;
        while (j < m && arr2[j] == y)
          j++;
      } else {
        ans.add(y);
        while (j < m && arr2[j] == y)
          j++;
      }
    }

    while (i < n) {
      int x = arr1[i];
      ans.add(x);
      while (i < n && arr1[i] == x)
        i++;
    }

    while (j < m) {
      int y = arr2[j];
      ans.add(y);
      while (j < m && arr2[j] == y)
        j++;
    }

    return ans;
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
    int res = nums.length;
    for (int i = 0; i < nums.length; i++) {
      res ^= i;
      res ^= nums[i];
    }
    return res;
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
  // We know that the summation of the first N numbers is (N*(N+1))/2. We can say
  // this S1. Now, in the given array, every number between 1 to N except one
  // number is present. So, if we add the numbers of the array (say S2), the
  // difference between S1 and S2 will be the missing number. Because, while
  // adding all the numbers of the array, we did not add that particular number
  // that is missing.

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
   * Given an array containing N integers and an integer K., Your task is to find
   * the length of the longest Sub-Array with the sum of the elements equal to the
   * given value K.
   * Input :
   * A[] = {10, 5, 2, 7, 1, 9}
   * K = 15
   * Output : 4
   * Explanation: The sub-array is {5, 2, 7, 1}.
   * 
   * Input : A[] = {-1, 2, 3}
   * K = 6
   * Output : 0
   * Explanation: There is no such sub-array with sum 6.
   */

  // --- This Solution will work for both Positive and negative--- TC(O(N^2))
  // SC(O(N))
  public static int getLongestSubarray(int[] a, long k) {
    int n = a.length; // size of the array.

    Map<Long, Integer> preSumMap = new HashMap<>();
    long sum = 0;
    int maxLen = 0;
    for (int i = 0; i < n; i++) {
      // calculate the prefix sum till index i:
      sum += a[i];

      // if the sum = k, update the maxLen:
      if (sum == k) {
        maxLen = Math.max(maxLen, i + 1);
      }

      // calculate the sum of remaining part i.e. x-k:
      long rem = sum - k;

      // Calculate the length and update maxLen:
      if (preSumMap.containsKey(rem)) {
        int len = i - preSumMap.get(rem);
        maxLen = Math.max(maxLen, len);
      }

      // Finally, update the map checking the conditions:
      if (!preSumMap.containsKey(sum)) {
        preSumMap.put(sum, i);
      }
    }
    return maxLen;
  }

  // Optimal Solution for only positive and zeros TC(O(2N)) SC(1)

  public static int getLongestSubarray1(int arr[], int N, int K) {

    int maxLen = 0, sum = arr[0];

    int left = 0, right = 0;

    while (right < N) {
      while (left <= right && sum > K) {
        sum = sum - arr[left];
        left++;
      }
      if (sum == K) {
        maxLen = Math.max(maxLen, right - left + 1);
      }
      right++;
      if (right < N)
        sum += arr[right];
    }
    return maxLen;
  }

  /*
   * Given an array of integers nums and an integer target, return indices of the
   * two numbers such that they add up to target.
   * 
   * You may assume that each input would have exactly one solution, and you may
   * not use the same element twice.
   * Example 1:
   * 
   * Input: nums = [2,7,11,15], target = 9
   * Output: [0,1]
   * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
   *
   * Input: nums = [3,2,4], target = 6
   * Output: [1,2]
   */

  /*
   * Time Complexity: O(N), where N = size of the array.
   * Reason: The loop runs N times in the worst case and searching in a hashmap
   * takes O(1) generally. So the time complexity is O(N).
   * 
   * Note: In the worst case(which rarely happens), the unordered_map takes O(N)
   * to find an element. In that case, the time complexity will be O(N2). If we
   * use map instead of unordered_map, the time complexity will be O(N* logN) as
   * the map data structure takes logN time to find an element.
   * 
   * Space Complexity: O(N) as we use the map data structure.
   */
  public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> numMap = new HashMap<>();
    int n = nums.length;

    for (int i = 0; i < n; i++) {
      int complement = target - nums[i];
      if (numMap.containsKey(complement)) {
        return new int[] { numMap.get(complement), i };
      }
      numMap.put(nums[i], i);
    }
    return new int[] {};
  }

  // with out using the HashMap TC and SC will be same
  public static int[] twoSum(int n, int[] arr, int target) {
    // Arrays.sort(arr);
    int left = 0, right = n - 1;

    while (left < right) {
      int sum = arr[left] + arr[right];

      if (sum == target) {
        return new int[] { left, right };
      } else if (sum < target)
        left++;
      else
        right--;
    }
    return new int[] {};
  }
  /*
   * Given an array nums of size n, return the majority element.
   * 
   * The majority element is the element that appears more than ⌊n / 2⌋ times. You
   * may assume that the majority element always exists in the array.
   *
   * Input: nums = [3,2,3]
   * Output: 3
   * 
   * Input: nums = [2,2,1,1,1,2,2]
   * Output: 2
   */

  public int majorityElement(int[] nums) { // TC(O(N))
    int n = nums.length;
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < n; i++) {
      map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
    }

    n = n / 2;
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      if (entry.getValue() > n) {
        return entry.getKey();
      }
    }
    return 0;
  }
  // Approach 3: Moore Voting Algorithm
  // The intuition behind the Moore's Voting Algorithm is based on the fact that
  // if there is a majority element in an array, it will always remain in the
  // lead, even after encountering other elements.

  public int majorityElement2(int[] nums) {
    int count = 0;
    int candidate = 0;

    for (int num : nums) {
      if (count == 0) {
        candidate = num;
      }

      if (num == candidate) {
        count++;
      } else {
        count--;
      }
    }
    return candidate;
  }
}

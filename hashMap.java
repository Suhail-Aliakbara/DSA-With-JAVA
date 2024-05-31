import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class hashMap {

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

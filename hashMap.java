import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;

public class hashMap {

  /*------- 3. Longest Substring Without Repeating Characters ---------
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
  /*
   * Time Complexity: O(N) or O(N*logN) depending on which map data structure we
   * are using, where N = size of the array.
   * Reason: For example, if we are using an unordered_map data structure in C++
   * the time complexity will be O(N)(though in the worst case, unordered_map
   * takes O(N) to find an element and the time complexity becomes O(N2)) but if
   * we are using a map data structure, the time complexity will be O(N*logN). The
   * least complexity will be O(N) as we are using a loop to traverse the array.
   * SC(O(N))
   */
  // --- This Solution will work for both Positive and negative---

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
   *
   * Input: nums = [2,7,11,15], target = 9
   * Output: [0,1]
   * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
   */

  /*
   * Time Complexity: O(N), where N = size of the array.
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

  // with out using the HashMap TC(O(N)) SC(O(1))
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

  /*
   * Given an array of integers nums and an integer k, return the total number of
   * subarrays whose sum equals to k.
   * 
   * A subarray is a contiguous non-empty sequence of elements within an array.
   * 
   * Input: nums = [1,1,1], k = 2
   * Output: 2
   * 
   * Input: nums = [1,2,3], k = 3
   * Output: 2
   */
  public static int findAllSubarraysWithGivenSum(int arr[], int k) {
    int n = arr.length; // size of the given array.
    Map<Integer, Integer> map = new HashMap<>();
    int preSum = 0, cnt = 0;

    map.put(0, 1); // Setting 0 in the map.
    for (int i = 0; i < n; i++) {
      // add current element to prefix Sum:
      preSum += arr[i];
      // Calculate x-k:
      int remove = preSum - k;
      // Add the number of subarrays to be removed:
      cnt += map.getOrDefault(remove, 0);
      // Update the count of prefix sum in the map.
      map.put(preSum, map.getOrDefault(preSum, 0) + 1);
    }
    return cnt;
  }

  /*
   * Given an integer array of size n, find all elements that appear more than
   * ⌊ n/3 ⌋ times.
   * 
   * Input: nums = [3,2,3]
   * Output: [3]
   * 
   * Input: nums = [1]
   * Output: [1]
   * 
   * Input: nums = [1,2]
   * Output: [1,2]
   */

  // Better Approach
  /*
   * Time Complexity: O(N*logN), where N = size of the given array.
   * 
   * Reason: We are using a map data structure. Insertion in the map takes logN
   * time. And we are doing it for N elements. So, it results in the first term
   * O(N*logN).
   * If we use unordered_map instead, the first term will be O(N) for the best and
   * average case and for the worst case, it will be O(N2).
   * 
   * Space Complexity: O(N) as we are using a map data structure. We are also
   * using a list that stores a maximum of 2 elements. That space used is so small
   * that it can be considered constant.
   */

  public static List<Integer> majorityElement3(int[] nums) {
    int n = nums.length; // size of the array
    List<Integer> ls = new ArrayList<>(); // list of answers
    // declaring a map:
    HashMap<Integer, Integer> mpp = new HashMap<>();
    // least occurrence of the majority element:
    int mini = (int) (n / 3) + 1;

    // storing the elements with its occurnce:
    for (int i = 0; i < n; i++) {
      int value = mpp.getOrDefault(nums[i], 0);
      mpp.put(nums[i], value + 1);

      // checking if nums[i] is the majority element:
      if (mpp.get(nums[i]) == mini) {
        ls.add(nums[i]);
      }
      if (ls.size() == 2)
        break;
    }
    return ls;
  }

  // Optimal Approach
  /*
   * Time Complexity: O(N) + O(N), where N = size of the given array.
   * 
   * Reason: The first O(N) is to calculate the counts and find the expected
   * majority elements. The second one is to check if the calculated elements are
   * the majority ones or not.
   * 
   * Space Complexity: O(1) as we are only using a list that stores a maximum of 2
   * elements. The space used is so small that it can be considered constant.
   */

  public static List<Integer> majorityElement4(int[] v) {
    int n = v.length; // size of the array

    int cnt1 = 0, cnt2 = 0; // counts
    int el1 = Integer.MIN_VALUE; // element 1
    int el2 = Integer.MIN_VALUE; // element 2

    // applying the Extended Boyer Moore's Voting Algorithm:
    for (int i = 0; i < n; i++) {
      if (cnt1 == 0 && el2 != v[i]) {
        cnt1 = 1;
        el1 = v[i];
      } else if (cnt2 == 0 && el1 != v[i]) {
        cnt2 = 1;
        el2 = v[i];
      } else if (v[i] == el1)
        cnt1++;
      else if (v[i] == el2)
        cnt2++;
      else {
        cnt1--;
        cnt2--;
      }
    }

    List<Integer> ls = new ArrayList<>(); // list of answers

    // Manually check if the stored elements in
    // el1 and el2 are the majority elements:
    cnt1 = 0;
    cnt2 = 0;
    for (int i = 0; i < n; i++) {
      if (v[i] == el1)
        cnt1++;
      if (v[i] == el2)
        cnt2++;
    }

    int mini = (int) (n / 3) + 1;
    if (cnt1 >= mini)
      ls.add(el1);
    if (cnt2 >= mini)
      ls.add(el2);

    // Uncomment the following line
    // if it is told to sort the answer array:
    // Collections.sort(ls); //TC --> O(2*log2) ~ O(1);
    return ls;
  }

  /*
   * 3Sum problem
   * Given an integer array nums, return all the triplets [nums[i], nums[j],
   * nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] +
   * nums[k] == 0.
   * 
   * Notice that the solution set must not contain duplicate triplets.
   * 
   * 
   * Input: nums = [-1,0,1,2,-1,-4]
   * Output: [[-1,-1,2],[-1,0,1]]
   * Explanation:
   * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
   * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
   * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
   * The distinct triplets are [-1,0,1] and [-1,-1,2].
   * Notice that the order of the output and the order of the triplets does not
   * matter.
   */
  // Better approach
  public static List<List<Integer>> triplet(int n, int[] arr) {
    Set<List<Integer>> st = new HashSet<>();

    for (int i = 0; i < n; i++) {
      Set<Integer> hashset = new HashSet<>();
      for (int j = i + 1; j < n; j++) {
        // Calculate the 3rd element:
        int third = -(arr[i] + arr[j]);

        // Find the element in the set:
        if (hashset.contains(third)) {
          List<Integer> temp = Arrays.asList(arr[i], arr[j], third);
          temp.sort(null);
          st.add(temp);
        }
        hashset.add(arr[j]);
      }
    }

    // store the set elements in the answer:
    List<List<Integer>> ans = new ArrayList<>(st);
    return ans;
  }
  /*
   * Time Complexity: O(N^2 * log(no. of unique triplets)), where N = size of the
   * array.
   * Space Complexity: O(2 * no. of the unique triplets) + O(N) as we are using a
   * set data structure and a list to store the triplets and extra O(N) for
   * storing the array elements in another Hashset.
   */

  /*
   * Optimal Approach
   * Time Complexity: O(NlogN)+O(2N), where N = size of the array.
   * Reason: The pointer i, is running for approximately N times. And both the
   * pointers j and k combined can run for approximately N times including the
   * operation of skipping duplicates. So the total time complexity will be O(N2).
   * 
   * Space Complexity: O(no. of quadruplets), This space is only used to store the
   * answer.
   * We are not using any extra space to solve this problem. So, from that
   * perspective, space complexity can be written as O(1).
   */
  public static List<List<Integer>> triplet1(int n, int[] arr) {
    List<List<Integer>> ans = new ArrayList<>();
    Arrays.sort(arr);

    for (int i = 0; i < n; i++) {
      // remove duplicates:
      if (i != 0 && arr[i] == arr[i - 1]) // [0,0,0,1,1,1,2,2,3]
        continue;
      // moving 2 pointers:
      int j = i + 1;
      int k = n - 1;
      while (j < k) {
        int sum = arr[i] + arr[j] + arr[k];
        if (sum < 0) {
          j++;
          while (j < k && arr[j] == arr[j - 1])
            j++;
        } else if (sum > 0) {
          k--;
          while (j < k && arr[k] == arr[k + 1])
            k--;
        } else {
          List<Integer> temp = Arrays.asList(arr[i], arr[j], arr[k]);
          ans.add(temp);
          j++;
          k--;
          // skip the duplicates:
          while (j < k && arr[j] == arr[j - 1])
            j++;
          while (j < k && arr[k] == arr[k + 1])
            k--;
        }
      }
    }
    return ans;
  }

  /*
   * 4sum
   * Given an array nums of n integers, return an array of all the unique
   * quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
   * 
   * 0 <= a, b, c, d < n
   * a, b, c, and d are distinct.
   * nums[a] + nums[b] + nums[c] + nums[d] == target
   * 
   * Input: nums = [1,0,-1,0,-2,2], target = 0
   * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
   */

  // BETTER SOLUTION
  public static List<List<Integer>> fourSum(int[] nums, int target) {
    int n = nums.length; // size of the array
    Set<List<Integer>> st = new HashSet<>();

    // checking all possible quadruplets:
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        Set<Long> hashset = new HashSet<>();
        for (int k = j + 1; k < n; k++) {
          // taking bigger data type
          // to avoid integer overflow:
          long sum = nums[i] + nums[j];
          sum += nums[k];
          long fourth = target - sum;
          if (hashset.contains(fourth)) {
            List<Integer> temp = new ArrayList<>();
            temp.add(nums[i]);
            temp.add(nums[j]);
            temp.add(nums[k]);
            temp.add((int) fourth);
            temp.sort(Integer::compareTo);
            st.add(temp);
          }
          // put the kth element into the hashset:
          hashset.add((long) nums[k]);
        }
      }
    }
    List<List<Integer>> ans = new ArrayList<>(st);
    return ans;
  }
  /*
   * Time Complexity: O(N3*log(M)), where N = size of the array, M = no. of
   * elements in the set.
   * Reason: Here, we are mainly using 3 nested loops, and inside the loops there
   * are some operations on the set data structure which take log(M) time
   * complexity.
   * Space Complexity: O(2 * no. of the quadruplets)+O(N)
   * Reason: we are using a set data structure and a list to store the quads. This
   * results in the first term. And the second space is taken by the set data
   * structure we are using to store the array elements. At most, the set can
   * contain approximately all the array elements and so the space complexity is
   * O(N).
   */

  // OPTIMAL SOLUTION
  public List<List<Integer>> fourSum1(int[] nums, int target) {

    List<List<Integer>> ans = new ArrayList<>();
    Arrays.sort(nums);
    int n = nums.length;

    for (int i = 0; i < n - 3; i++) {
      if (i != 0 && nums[i] == nums[i - 1])
        continue;
      int a = i + 1;

      while (a < n - 2) {
        int j = a + 1;
        int k = n - 1;
        while (j < k) {
          long sum = nums[i] + nums[a];
          sum += nums[j];
          sum += nums[k];

          if (sum < target) {
            j++;
          } else if (sum > target) {
            k--;
          } else {
            List<Integer> temp = Arrays.asList(nums[i], nums[a], nums[j], nums[k]);
            // temp.sort(null);
            ans.add(temp);
            j++;
            k--;
            while (j < k && nums[j] == nums[j - 1])
              j++;
            while (j < k && k < n - 1 && nums[k] == nums[k + 1])
              k--;
          }
        }
        a++;
        while (a < n && nums[a] == nums[a - 1])
          a++;
      }
    }
    return ans;
  }

  /*
   * 128. Longest Consecutive Sequence
   * Given an unsorted array of integers nums, return the length of the longest
   * consecutive elements sequence.
   * 
   * You must write an algorithm that runs in O(n) time.
   * Input: nums = [100,4,200,1,3,2]
   * Output: 4
   * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4].
   * Therefore its length is 4.
   */
  // BETTER APPROACH TC(O(NlogN + N) SC(O(1))
  public static int longestSuccessiveElements(int[] a) {
    int n = a.length;
    if (n == 0)
      return 0;

    // sort the array:
    Arrays.sort(a);
    int lastSmaller = Integer.MIN_VALUE;
    int cnt = 0;
    int longest = 1;

    // find longest sequence:
    for (int i = 0; i < n; i++) {
      if (a[i] - 1 == lastSmaller) {
        // a[i] is the next element of the
        // current sequence.
        cnt += 1;
        lastSmaller = a[i];
      } else if (a[i] != lastSmaller) {
        cnt = 1;
        lastSmaller = a[i];
      }
      longest = Math.max(longest, cnt);
    }
    return longest;
  }

  // OPTIMAL SOLUTION TC(O(N + 2N ~ 3N)) SC(O(N))
  /*
   * Note: The time complexity is computed under the assumption that we are using
   * unordered_set and it is taking O(1) for the set operations.
   * 
   * If we consider the worst case the set operations will take O(N) in that case
   * and the total time complexity will be approximately O(N^2).
   * And if we use the set instead of unordered_set, the time complexity for the
   * set operations will be O(logN) and the total time complexity will be
   * O(NlogN).
   */
  public static int longestSuccessiveElements1(int[] a) {
    int n = a.length;
    if (n == 0)
      return 0;

    int longest = 1;
    Set<Integer> set = new HashSet<>();

    // put all the array elements into set
    for (int i = 0; i < n; i++) {
      set.add(a[i]);
    }
    // Find the longest sequence
    for (int it : set) {
      // if 'it' is a starting number
      if (!set.contains(it - 1)) {
        // find consecutive numbers
        int cnt = 1;
        int x = it;
        while (set.contains(x + 1)) {
          x = x + 1;
          cnt = cnt + 1;
        }
        longest = Math.max(longest, cnt);
      }
    }
    return longest;
  }

}

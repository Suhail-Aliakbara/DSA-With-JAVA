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

}

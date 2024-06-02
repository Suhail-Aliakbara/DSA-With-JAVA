import java.util.ArrayList;

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
   * Solved by seeing and understanding the solution (only 1 line was missing in
   * my code)
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
  // We know that the summation of the first N numbers is (N*(N+1))/2. and while
  // adding all the numbers of the array, we did not add that particular number in
  // s2
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
   * Set matrix zeros
   * 
   * Given an m x n integer matrix matrix, if an element is 0, set its entire row
   * and column to 0's.
   * 
   * input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
   * Output: [[1,0,1],[0,0,0],[1,0,1]]
   * 
   * Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
   * Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
   * 
   */

  public static ArrayList<ArrayList<Integer>> zeroMatrix(ArrayList<ArrayList<Integer>> matrix, int n, int m) {
    int[] row = new int[n]; // row array
    int[] col = new int[m]; // col array

    // Traverse the matrix:
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (matrix.get(i).get(j) == 0) {
          // mark ith index of row wih 1:
          row[i] = 1;
          // mark jth index of col wih 1:
          col[j] = 1;
        }
      }
    }
    // Finally, mark all (i, j) as 0
    // if row[i] or col[j] is marked with 1.
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (row[i] == 1 || col[j] == 1) {
          matrix.get(i).set(j, 0);
        }
      }
    }
    return matrix;
  }

}

import java.util.ArrayList;
import java.util.HashMap;

public class BitManupilation {

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
   * You are given a read only array of n integers from 1 to n.
   * Each integer appears exactly once except A which appears twice and B which is
   * missing.Return A and B.
   * Input:[3 1 2 5 3]*
   * Output:[3, 4]
   */
  public static int[] findMissingRepeatingNumbers(int[] a) { // TC(O(2N)) SC(O(N))
    int n = a.length; // size of the array
    int[] temp = new int[n + 1]; // temp array

    // update the temp array:
    for (int i = 0; i < n; i++) {
      temp[a[i]]++;
    }

    // Find the repeating and missing number:
    int repeating = -1, missing = -1;
    for (int i = 1; i <= n; i++) {
      if (temp[i] == 2)
        repeating = i;
      else if (temp[i] == 0)
        missing = i;

      if (repeating != -1 && missing != -1)
        break;
    }
    int[] ans = { repeating, missing };
    return ans;
  }

  // OPTIMAL SOLUTION
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
   * Subarray with given XOR
   * Given an array of integers A and an integer B.
   * Find the total number of subarrays having bitwise XOR of all elements equals
   * to B.
   * 
   * intput : A = [4, 2, 2, 6, 4]
   * B = 6
   * output: 4
   * Explanation: [4, 2], [4, 2, 2, 6, 4], [2, 2, 6], [6]
   */

  public int solve(ArrayList<Integer> A, int B) {

    int n = A.size(); // Get the size of the arraylist
    HashMap<Integer, Integer> map = new HashMap<>(); // Create a hashmap to store XOR values and their counts
    int xor = 0; // Initialize XOR to 0
    map.put(0, 1); // Put the initial value in the map to handle the case where subarray starts
                   // from index 0
    int cnt = 0; // Initialize count of subarrays with XOR equal to B

    for (int i = 0; i < n; i++) { // Iterate through the arraylist
      xor = xor ^ A.get(i); // Calculate the cumulative XOR from start to the current index
      int x = xor ^ B; // Calculate the value needed to achieve XOR = B

      cnt += map.getOrDefault(x, 0); // If x is found in map, it means there are subarrays ending at i with XOR = B

      map.put(xor, map.getOrDefault(xor, 0) + 1); // Update the map with the current XOR value and its count
    }
    return cnt; // Return the count of subarrays with XOR equal to B
  }

}

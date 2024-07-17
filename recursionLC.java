import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class recursionLC {

  /*
   * Subset Sum (GFG)
   * Given a list arr of n integers, return sums of all subsets in it. Output sums
   * can be printed in any order.
   * 
   * input: n = 3
   * arr = {5, 2, 1}
   * Output:
   * 0 1 2 3 5 6 7 8
   */

  // Recursive function to calculate subset sums
  public void subSum(ArrayList<Integer> arr, int n, int i, ArrayList<Integer> ans, int sum) {
    if (i == n) {
      ans.add(sum);
      return;
    }
    subSum(arr, n, i + 1, ans, sum + arr.get(i)); // Include current element in the sum
    subSum(arr, n, i + 1, ans, sum); // Exclude current element from the sum
  }

  // Function to calculate subset sums
  ArrayList<Integer> subsetSums(ArrayList<Integer> arr, int n) {
    ArrayList<Integer> ans = new ArrayList<>();
    subSum(arr, n, 0, ans, 0);
    return ans;
  }
/*
 * Time Complexity: O(2^n)+O(2^n log(2^n)). Each index has two ways. You can
 * either pick it up or not pick it. So for n index time complexity for O(2^n)
 * and for sorting it will take (2^n log(2^n)).
 * 
 * Space Complexity: O(2^n) for storing subset sums, since 2^n subsets can be
 * generated for an array of size n.
 */

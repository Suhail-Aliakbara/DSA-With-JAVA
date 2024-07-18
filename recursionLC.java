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

  public void subSum(ArrayList<Integer> arr, int n, int i, ArrayList<Integer> ans, int sum) {
    if (i == n) {
      ans.add(sum);
      return;
    }
    subSum(arr, n, i + 1, ans, sum + arr.get(i)); // Include current element in the sum
    subSum(arr, n, i + 1, ans, sum); // Exclude current element from the sum
  }

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

  /*
   * 90. Subset Sum
   * Given an integer array nums that may contain duplicates, return all possible
   * subsets (the power set).
   * The solution set must not contain duplicate subsets. Return the solution in
   * any order.
   * Input: nums = [1,2,2]
   * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
   */
  void findSubset(int idx, int[] nums, List<Integer> ds, List<List<Integer>> ansList) {
    ansList.add(new ArrayList<>(ds)); // Add current subset to the answer list
    for (int i = idx; i < nums.length; i++) {
      if (i != idx && nums[i] == nums[i - 1])
        continue; // Skip duplicates to avoid duplicate subsets
      ds.add(nums[i]); // Include current element in the subset
      findSubset(i + 1, nums, ds, ansList); // Recursively find subsets starting from the next index
      ds.remove(ds.size() - 1); // Backtrack by removing the last element from the subset
    }
  }

  public List<List<Integer>> subsetsWithDup(int[] nums) {
    Arrays.sort(nums); // Sort the array to handle duplicates
    List<List<Integer>> ansList = new ArrayList<>();
    findSubset(0, nums, new ArrayList<>(), ansList);
    return ansList;
  }
  /*
   * Time Complexity: O(2^n) for generating every subset and O(k) to insert every
   * subset in another data structure if the average length of every subset is k.
   * Overall O(k * 2^n).
   * 
   * Space Complexity: O(2^n * k) to store every subset of average length k.
   * Auxiliary space is O(n) if n is the depth of the recursion tree.
   */

  /*
   * 39. Combination Sum
   * Given an array of distinct integers candidates and a target integer target,
   * return a list of all unique combinations of candidates where the chosen
   * numbers sum to target. You may return the combinations in any order.
   *
   * Input: candidates = [2,3,6,7], target = 7
   * Output: [[2,2,3],[7]]
   */
  void funComb(int[] candidates, int target, List<List<Integer>> ans, int idx, List<Integer> ds) {
    if (idx == candidates.length) {
      return;
    }
    if (target == 0) {
      ans.add(new ArrayList<>(ds));
      return;
    }
    if (candidates[idx] <= target) {
      ds.add(candidates[idx]);
      funComb(candidates, target - candidates[idx], ans, idx, ds);
      ds.remove(ds.size() - 1);
    }
    funComb(candidates, target, ans, idx + 1, ds);
  }

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> ans = new ArrayList<>();
    funComb(candidates, target, ans, 0, new ArrayList<>());
    return ans;
  }
  /*
   * Time Complexity: O(2^t * k) where t is the target, k is the average length
   * 
   * Reason: Assume if you were not allowed to pick a single element multiple
   * times, every element will have a couple of options: pick or not pick which is
   * 2^n different recursion calls, also assuming that the average length of every
   * combination generated is k. (to put length k data structure into another data
   * structure)
   * 
   * Why not (2^n) but (2^t) (where n is the size of an array)?
   * Assume that there is 1 and the target you want to reach is 10 so 10 times you
   * can “pick or not pick” an element.
   * 
   * Space Complexity: O(k*x), k is the average length and x is the no. of
   * combinationsś
   */
}

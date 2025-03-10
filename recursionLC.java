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
  /*
   * 40. Combination Sum II
   * Given a collection of candidate numbers (candidates) and a target number
   * (target), find all unique combinations in candidates where the candidate
   * numbers sum to target.
   * Each number in candidates may only be used once in the combination.
   * Note: The solution set must not contain duplicate combinations.
   * 
   * Input: candidates = [10,1,2,7,6,1,5], target = 8
   * Output:[ [1,1,6], [1,2,5], [1,7], [2,6] ]
   */
  public void findComb2(int[] candidates, int target, List<List<Integer>> ans, int idx, List<Integer> ds) {
    int n = candidates.length;
    if (target == 0) {
      ans.add(new ArrayList<>(ds));
      return;
    }
    for (int i = idx; i < n; i++) {
      if (i != idx && candidates[i] == candidates[i - 1])
        continue;
      if (candidates[i] > target)
        break;
      ds.add(candidates[i]);
      findComb2(candidates, target - candidates[i], ans, i + 1, ds);
      ds.remove(ds.size() - 1);
    }
  }

  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    Arrays.sort(candidates);
    List<List<Integer>> ans = new ArrayList<>();
    findComb2(candidates, target, ans, 0, new ArrayList<>());
    return ans;
  }

  /*
   * 131. Palindrome Partitioning
   * Given a String s, partition s such that every subString of
   * the partition is a palindromeReturn
   * all possible palindrome partitioning of s.
   * 
   * Input: s = "aab"
   * Output: [["a","a","b"],["aa","b"]]
   */

  boolean isPalindrome(String str, int start, int end) {
    while (start <= end) {
      if (str.charAt(start++) != str.charAt(end--)) {
        return false;
      }
    }
    return true;
  }

  void paliPartition(int idx, String str, List<List<String>> ans, List<String> ds) {
    if (idx == str.length()) {
      ans.add(new ArrayList<>(ds));
      return;
    }
    for (int i = idx; i < str.length(); i++) {
      if (isPalindrome(str, idx, i)) {
        ds.add(str.substring(idx, i + 1));
        paliPartition(i + 1, str, ans, ds);
        ds.remove(ds.size() - 1);
      }
    }
  }

  public List<List<String>> partition(String s) {
    List<List<String>> ans = new ArrayList<>();
    paliPartition(0, s, ans, new ArrayList<>());
    return ans;
  }
  /*
   * Time Complexity: O( (2^n) *k*(n/2) )
   * Reason: O(2^n) to generate every subString and O(n/2) to check if the
   * subString generated is a palindrome. O(k) is for inserting the palindromes in
   * another data structure, where k is the average length of the palindrome list.
   * 
   * Space Complexity: O(k * x)
   * Reason: The space complexity can vary depending upon the length of the
   * answer. k is the average length of the list of palindromes and if we have x
   * such list of palindromes in our final answer. The depth of the recursion tree
   * is n, so the auxiliary space required is equal to the O(n)
   */
  /*
   * 60. Permutation Sequence
   * The set [1, 2, 3, ..., n] contains a total of n! unique permutations.
   * By listing and labeling all of the permutations in order, we get the
   * following sequence for
   * n = 3: "123" "132" "213" "231" "312" "321"
   * Given n and k, return the kth permutation sequence.
   * 
   * Input: n = 3, k = 3
   * Output: "213"
   */

  public String getPermutation(int n, int k) {
    int fact = 1;
    ArrayList<Integer> ans = new ArrayList<>();
    for (int i = 1; i < n; i++) {
      fact *= i;
      ans.add(i);
    }
    ans.add(n);
    k = k - 1;
    String str = "";
    while (true) {
      str += ans.get(k / fact);
      ans.remove(k / fact);
      if (ans.isEmpty()) {
        break;
      }
      k = k % fact;
      fact = fact / ans.size();
    }
    return str;
  }

}

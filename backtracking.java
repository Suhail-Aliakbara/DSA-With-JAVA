import java.util.ArrayList;
import java.util.List;

public class backtracking {

  /*
   * 46. Permutations
   * Given an array nums of distinct integers, return all the possible
   * permutations. You can return the answer in any order.
   * Input: nums = [1,2,3]
   * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
   * 
   * Time Complexity: N! x N
   * Space Complexity: O(N)
   */
  public void findPermu(int nums[], List<List<Integer>> ans, List<Integer> ds, boolean[] map) {
    if (ds.size() == nums.length) {
      ans.add(new ArrayList<>(ds));
      return;
    }
    for (int i = 0; i < nums.length; i++) {
      if (!map[i]) {
        map[i] = true;
        ds.add(nums[i]);
        findPermu(nums, ans, ds, map);
        ds.remove(ds.size() - 1);
        map[i] = false;
      }
    }
  }

  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    findPermu(nums, ans, new ArrayList<>(), new boolean[nums.length]);
    return ans;
  }

  /*
   * Optimal Solution
   * Time Complexity: O(N! X N)
   * Space Complexity: O(1)
   */
  private void recurPermute(int index, int[] nums, List<List<Integer>> ans) {
    if (index == nums.length) {
      // copy the ds to ans
      List<Integer> ds = new ArrayList<>();
      for (int i = 0; i < nums.length; i++) {
        ds.add(nums[i]);
      }
      ans.add(new ArrayList<>(ds));
      return;
    }
    for (int i = index; i < nums.length; i++) {
      swap(i, index, nums);
      recurPermute(index + 1, nums, ans);
      swap(i, index, nums);
    }
  }

  private void swap(int i, int j, int[] nums) {
    int t = nums[i];
    nums[i] = nums[j];
    nums[j] = t;
  }

  public List<List<Integer>> permute1(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    recurPermute(0, nums, ans);
    return ans;
  }

  /*
   * 51. N-Queens
   * The n-queens puzzle is the problem of placing n queens on an n x n chessboard
   * such that no two queens attack each other.
   * Given an integer n, return all distinct solutions to the n-queens puzzle. You
   * may return the answer in any order.
   * 
   * Input: n = 4
   * Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
   * Explanation: There exist two distinct solutions to the 4-queens puzzle as
   * shown above
   * 
   * Time Complexity: Exponential in nature since we are trying out all ways, to
   * be precise its O(N! * N).
   * Space Complexity: O( N2 )
   */
  public static List<String> convertToList(char[][] array) {
    List<String> list = new ArrayList<>();
    for (char[] row : array) {
      list.add(new String(row));
    }
    return list;
  }

  public boolean isSafe(char[][] board, int row, int col) {
    for (int i = row - 1; i >= 0; i--) {
      if (board[i][col] == 'Q')
        return false;
    }
    for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
      if (board[i][j] == 'Q')
        return false;
    }
    for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
      if (board[i][j] == 'Q')
        return false;
    }
    return true;
  }

  public void nQueens(int n, List<List<String>> ans, char[][] board, int row) {
    if (row == n) {
      ans.add(convertToList(board));
      return;
    }
    for (int col = 0; col < n; col++) {
      if (isSafe(board, row, col)) {
        board[row][col] = 'Q';
        nQueens(n, ans, board, row + 1);
        board[row][col] = '.';
      }
    }
  }

  public List<List<String>> solveNQueens(int n) {
    List<List<String>> ans = new ArrayList<>();
    char[][] board = new char[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        board[i][j] = '.';
      }
    }
    nQueens(n, ans, board, 0);
    return ans;
  }
  /*
   * Optimal Solution
   * Time Complexity: Exponential in nature since we are trying out all ways, to
   * be precise its O(N! * N).
   * Space Complexity: O(N)
   */
}

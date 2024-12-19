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
  public static List<String> convertToList1(char[][] array) {
    List<String> list = new ArrayList<>();
    for (char[] row : array) {
      list.add(new String(row));
    }
    return list;
  }

  public void nQueens(int n, int row, List<List<String>> ans, char[][] board, int[] upperCol, int[] leftUpperDiagonal,
      int[] rightUpperDiagonal) {

    if (row == n) {
      ans.add(convertToList1(board));
      return;
    }

    for (int col = 0; col < n; col++) {
      if (upperCol[col] == 0 && leftUpperDiagonal[n - 1 + row - col] == 0 && rightUpperDiagonal[row + col] == 0) {
        upperCol[col] = 1;
        leftUpperDiagonal[n - 1 + row - col] = 1;
        rightUpperDiagonal[row + col] = 1;

        board[row][col] = 'Q';
        nQueens(n, row + 1, ans, board, upperCol, leftUpperDiagonal, rightUpperDiagonal);
        board[row][col] = '.';

        upperCol[col] = 0;
        leftUpperDiagonal[n - 1 + row - col] = 0;
        rightUpperDiagonal[row + col] = 0;
      }
    }
  }

  public List<List<String>> solveNQueens1(int n) {
    List<List<String>> ans = new ArrayList<>();
    char[][] board = new char[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        board[i][j] = '.';
      }
    }

    int upperCol[] = new int[n];
    int leftUpperDiagonal[] = new int[2 * n - 1];
    int rightUpperDiagonal[] = new int[2 * n - 1];

    nQueens(n, 0, ans, board, upperCol, leftUpperDiagonal, rightUpperDiagonal);
    return ans;
  }

  /*
   * Sudoku solver codition with only 1 loop
   * boolean isSafe(char[][] board, int row,int col, char digit){
   * for(int i=0; i<9; i++){
   * if(board[row][i] == digit) return false;
   * if(board[i][col] == digit) return false;
   * 
   * if(board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == digit) return
   * false;
   * }
   * return true;
   * }
   */

  /*
   * M-Coloring Problem
   * Given an undirected graph and an integer M. The task is to determine if the
   * graph can be colored with at most M colors such that no two adjacent vertices
   * of the graph are colored with the same color. Here coloring of a graph means
   * the assignment of colors to all vertices. Print 1 if it is possible to colour
   * vertices and 0 otherwise.
   * 
   */
  // Check if it is possible to assign color 'clr' to 'vertex' considering its
  // adjacency
  public boolean isPossible(boolean graph[][], int colors[], int n, int clr, int vertex) {
    for (int i = 0; i < n; i++) {
      // If there is an edge between vertex and i, and i is already colored with clr,
      // return false
      if (graph[vertex][i] && colors[i] == clr) {
        return false;
      }
    }
    return true; // If no adjacent vertex is colored with clr, return true
  }

  // Recursive function to solve the graph coloring problem
  public boolean solve(boolean[][] graph, int colors[], int m, int n, int vertex) {
    // If all vertices are assigned a color, return true
    if (vertex == n) {
      return true;
    }
    // Try assigning each color from 1 to m to the vertex
    for (int clr = 1; clr <= m; clr++) {
      if (isPossible(graph, colors, n, clr, vertex)) {
        colors[vertex] = clr;
        if (solve(graph, colors, m, n, vertex + 1)) {
          return true; // If successful, return true
        }
        // If coloring the vertex with clr doesn't lead to a solution, backtrack
        colors[vertex] = 0;
      }
    }
    // If no color can be assigned to this vertex, return false
    return false;
  }

  public boolean graphColoring(boolean graph[][], int m, int n) {
    int colors[] = new int[n]; // Array to store colors assigned to vertices
    return solve(graph, colors, m, n, 0); // Start the problem with vertex 0
  }

  /*
   * Rat in a Maze Problem - I
   * Consider a rat placed at (0, 0) in a square matrix mat of order n* n. It has
   * to reach the destination at (n - 1, n - 1).
   * Find all possible paths that the rat can take to reach from source to
   * destination. The directions in which the rat can move are 'U'(up), 'D'(down),
   * 'L' (left), 'R' (right).
   * Value 0 at a cell in the matrix represents that it is blocked and rat cannot
   * move to it while value 1 at a cell in the matrix represents that rat can be
   * travel through it.
   * 
   * Input: mat[][] = {{1, 0, 0, 0},
   * {1, 1, 0, 1},
   * {1, 1, 0, 0},
   * {0, 1, 1, 1}}
   * Output: DDRDRR DRDDRR
   * 
   */
  /*
   * for many cases and for interview
   */

  private void ratInMaze(int[][] mat, List<String> ans, StringBuilder str, int m, int n, int[] di, int[] dj) {
    int len = mat.length;
    if (m == len - 1 && n == len - 1) {
      ans.add(str.toString());
      return;
    }

    String direction = "DLRU";
    for (int i = 0; i < 4; i++) {
      int nexti = m + di[i];
      int nextj = n + dj[i];
      if (nexti >= 0 && nextj >= 0 && nexti < len && nextj < len && mat[nexti][nextj] == 1) {
        mat[nexti][nextj] = 0;
        str.append(direction.charAt(i));
        ratInMaze(mat, ans, str, nexti, nextj, di, dj);
        str.deleteCharAt(str.length() - 1);
        mat[nexti][nextj] = 1;
      }
    }
  }

  public ArrayList<String> findPath(int[][] mat) {
    ArrayList<String> ans = new ArrayList<>();
    int[] di = { 1, 0, 0, -1 };
    int[] dj = { 0, -1, 1, 0 };
    if (mat[0][0] == 1) {
      mat[0][0] = 0; // mark the starting cell as visited
      ratInMaze(mat, ans, new StringBuilder(), 0, 0, di, dj);
      mat[0][0] = 1; // unmark the starting cell after the search
    }
    return ans;
  }

  /*
   * Word break 2
   * You are given a non-empty String S containing no spaces’ and a dictionary of
   * non-empty Strings (say the list of words).
   * You are supposed to construct and return all possible sentences after adding
   * spaces in the originally given String ‘S’, such that each word in a sentence
   * exists in the given dictionary.
   * Sample Input 1: 6 1
   * god is now no where here
   * godisnowherenowhere
   * Sample Output 1:
   * god is no where no where
   * god is no where now here
   * god is now here no where
   * god is now here now here
   */
  public static void solveWordBreak(String s, ArrayList<String> dictionary, ArrayList<String> ans, String str) {
    if (s.length() == 0) {
      ans.add(str);
      return;
    }
    for (int i = 0; i < s.length(); i++) {
      String sub = s.substring(0, i + 1);
      if (dictionary.contains(sub)) {
        String remaning = s.substring(i + 1);
        solveWordBreak(remaning, dictionary, ans, str + sub + " ");
      }
    }
  }

  public static ArrayList<String> wordBreak(String s, ArrayList<String> dictionary) {
    // Write your code here.
    ArrayList<String> ans = new ArrayList<>();
    solveWordBreak(s, dictionary, ans, "");
    return ans;
  }
  /*
   * Time complextiy : O(n + 2^n) n→ for String length and 2^n for all possible
   * partition
   * space complexity : O(n*2^n) stack space and for extra ArrayList
   */
  /*
   * nnn
   */
}

import java.util.Arrays;
import java.util.*;

public class Arrays2D {

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

  // Optimal Solution
  public void setZeroes(int[][] matrix) {

    // int[] row = new int[n]; --> matrix[..][0]
    // int[] col = new int[m]; --> matrix[0][..]

    int n = matrix.length;
    int m = matrix[0].length;

    int col0 = 1;
    // step 1: Traverse the matrix and
    // mark 1st row & col accordingly:
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (matrix[i][j] == 0) {
          // mark i-th row:
          matrix[i][0] = 0;

          // mark j-th column:
          if (j != 0) {
            matrix[0][j] = 0;
          } else {
            col0 = 0;
          }
        }
      }
    }
    // Step 2: Mark with 0 from (1,1) to (n-1, m-1):
    for (int i = 1; i < n; i++) {
      for (int j = 1; j < m; j++) {
        if (matrix[i][j] != 0) {
          // check for col & row:
          if (matrix[0][j] == 0 || matrix[i][0] == 0) {
            matrix[i][j] = 0;
          }
        }
      }
    }
    // step 3: Finally mark the 1st col & then 1st row:
    if (matrix[0][0] == 0) {
      for (int j = 0; j < m; j++)
        matrix[0][j] = 0;
    }
    if (col0 == 0) {
      for (int i = 0; i < n; i++)
        matrix[i][0] = 0;
    }
  }

  /*
   * You are given an n x n 2D matrix representing an image, rotate the image by
   * 90 degrees (clockwise).
   * 
   * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
   * Output: [[7,4,1],[8,5,2],[9,6,3]]
   * 
   * Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
   * Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
   */

  static int[][] rotate(int[][] matrix) { // Brute force approach SC(O(n*n))
    int n = matrix.length;
    int rotated[][] = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        rotated[j][n - i - 1] = matrix[i][j];
      }
    }
    return rotated;
  }

  // Optimal Approach
  public void rotate1(int[][] matrix) { // SC(0(1)) Constant
    int n = matrix.length;

    // Transpose the matrix
    for (int i = 0; i < n - 1; i++) {
      for (int j = i + 1; j < n; j++) {
        // Swap matrix[i][j] and matrix[j][i]
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
      }
    }
    // Reverse each row
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix.length / 2; j++) {
        // Swap matrix[i][j] and matrix[i][matrix.length - 1 - j]
        int temp = matrix[i][j];
        matrix[i][j] = matrix[i][matrix.length - 1 - j];
        matrix[i][matrix.length - 1 - j] = temp;
      }
    }
  }

  /*
   * Given an m x n matrix, return all elements of the matrix in spiral order.
   * 
   * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
   * Output: [1,2,3,6,9,8,7,4,5]
   * 
   * Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
   * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
   */

  public List<Integer> spiralOrder(int[][] matrix) {
    // Get the number of rows and columns in the matrix
    int n = matrix.length;
    int m = matrix[0].length;

    // Initialize the result list
    List<Integer> list = new ArrayList<>();

    // Define the boundaries of the matrix
    int startRow = 0, endRow = n - 1;
    int startCol = 0, endCol = m - 1;

    // Loop through the matrix in a spiral order
    while (startRow <= endRow && startCol <= endCol) {
      // Traverse the top row from left to right
      for (int j = startCol; j <= endCol; j++) {
        list.add(matrix[startRow][j]);
      }
      // Traverse the right column from top to bottom
      for (int i = startRow + 1; i <= endRow; i++) {
        list.add(matrix[i][endCol]);
      }
      // Traverse the bottom row from right to left
      for (int j = endCol - 1; j >= startCol; j--) {
        // Check if it is a single row then don't repeat column
        if (startRow < endRow) {
          list.add(matrix[endRow][j]);
        }
      }
      // Traverse the left column from bottom to top
      for (int i = endRow - 1; i > startRow; i--) {
        // Check if it is a single column then don't repeat row
        if (startCol < endCol) {
          list.add(matrix[i][startCol]);
        }
      }
      // Move the boundaries inward
      startRow++;
      endRow--;
      startCol++;
      endCol--;
    }
    // Return the result list
    return list;
  }

  /*
   * Merge overlaping
   * Given an array of intervals where intervals[i] = [start, end], merge all
   * overlapping intervals, and return an array of the non-overlapping intervals
   * that cover all the intervals in the input.
   * 
   * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
   * Output: [[1,6],[8,10],[15,18]]
   * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6]
   * 
   */
  public int[][] merge(int[][] intervals) {
    // Initialize a list to hold the merged intervals
    List<int[]> res = new ArrayList<>();
    // If the input array has 0 or 1 interval, no merge is needed
    if (intervals.length <= 1) {
      return intervals;
    }

    // Sort the intervals by their starting points to ensure order
    Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

    // Initialize 'start' and 'end' with the first interval's boundaries
    int start = intervals[0][0];
    int end = intervals[0][1];

    // Iterate through each interval
    for (int[] i : intervals) {
      // If the current interval's start is less than or equal to 'end', there's an
      // overlap
      if (i[0] <= end) {
        // Merge the intervals by updating 'end' to the maximum end of the overlapping
        // intervals
        end = Math.max(end, i[1]);
      } else {
        // If no overlap, add the previous interval to the result list
        res.add(new int[] { start, end });
        // Update 'start' and 'end' to the current interval's boundaries
        start = i[0];
        end = i[1];
      }
    }
    // Add the last interval to the result list
    res.add(new int[] { start, end });
    // Convert the list to an array and return
    return res.toArray(new int[0][]);
  }

}

import java.util.ArrayList;
import java.util.List;

public class Arraylist1 {
  /*
   * Pascal triangle
   * Variation 1: Given row number r and column number c. Print the element at
   * position (r, c) in Pascal’s triangle.
   * 
   * Time Complexity: O(c), where c = given column number.
   * Reason: We are running a loop for r times, where r is c-1.
   * 
   * Space Complexity: O(1) as we are not using any extra space.
   * 
   */

  public static long nCr(int n, int r) {
    long res = 1;

    // calculating nCr:
    for (int i = 0; i < r; i++) {
      res = res * (n - i);
      res = res / (i + 1);
    }
    return res;
  }

  public static int pascalTriangle(int r, int c) {
    int element = (int) nCr(r - 1, c - 1);
    return element; // Output: The element at position (r = 5,c = 3) is: 6
  }

  /*
   * Variation 2: Given the row number n. Print the n-th row of Pascal’s triangle.
   * // Brute Force
   * 
   * Time Complexity: O(n*r), where n is the given row number, and r is the column
   * index which can vary from 0 to n-1.
   * 
   * Reason: We are calculating the element for each column. Now, there are total
   * n columns, and for each column, the calculation of the element takes O(r)
   * time where r is the column index.
   * 
   * Space Complexity: O(1) as we are not using any extra space.
   */

  public static long nCr1(int n, int r) {
    long res = 1;

    // calculating nCr:
    for (int i = 0; i < r; i++) {
      res = res * (n - i);
      res = res / (i + 1);
    }
    return res;
  }

  public static void pascalTriangle1(int n) {
    // printing the entire row n:
    for (int c = 1; c <= n; c++) {
      System.out.print(nCr(n - 1, c - 1) + " ");
    }
    System.out.println();
  }

  public static void main1(String[] args) {
    int n = 5; // Output: 1 4 6 4 1
    pascalTriangle1(n);
  }

  /*
   * Variant 2 Given the row number n. Print the n-th row of Pascal’s triangle.
   * // Optimal Approach
   * 
   * Time Complexity: O(N) where N = given row number. Here we are using only a
   * single loop.
   * 
   * Space Complexity: O(1) as we not using any extra space.
   */
  static void pascalTriangle2(int n) {
    long ans = 1;
    System.out.print(ans + " "); // printing 1st element

    // Printing the rest of the part:
    for (int i = 1; i < n; i++) {
      ans = ans * (n - i);
      ans = ans / i;
      System.out.print(ans + " ");
    }
    System.out.println();
  }

  /*
   * Variation 3: Given the number of rows n. Print the first n rows of Pascal’s
   * triangle.
   * // Optimal Approach
   * 
   * Time Complexity: O(n2), where n = number of rows(given).
   * Reason: We are generating a row for each single row. The number of rows is n.
   * And generating an entire row takes O(n) time complexity.
   * 
   * Space Complexity: In this case, we are only using space to store the answer.
   * That is why space complexity can still be considered as O(1).
   * 
   */

  public List<Integer> generateRow(int row) {
    List<Integer> ans = new ArrayList<>();
    ans.add(1);
    int result = 1;
    for (int col = 1; col < row; col++) {
      result = result * (row - col);
      result = result / col;
      ans.add(result);
    }
    return ans;
  }

  public List<List<Integer>> generate(int numRows) {
    List<List<Integer>> ans = new ArrayList<>();
    for (int row = 1; row <= numRows; row++) {
      ans.add(generateRow(row));
    }
    return ans;
  }

}

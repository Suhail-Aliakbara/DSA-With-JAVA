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

}

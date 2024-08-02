public class binarySearch {

  /*
   * Median in a row-wise sorted Matrix
   * Given a row wise sorted matrix of size R*C where R and C are always odd, find
   * the median of the matrix.
   * Input: R = 3, C = 3
   * M= [[1, 3, 5],
   * [2, 6, 9],
   * [3, 6, 9]]
   * Output: 5
   * Explanation: Sorting matrix elements gives
   * us {1,2,3,3,5,6,6,9,9}. Hence, 5 is median.
   */

  int upperBound(int[] matrix, int m, int x) {
    int low = 0;
    int high = m - 1;
    int ans = m;

    // Perform binary search to find the first element greater than x
    while (low <= high) {
      int mid = (low + high) / 2;

      if (matrix[mid] > x) {
        high = mid - 1;
        ans = mid;
      } else {
        low = mid + 1;
      }
    }

    return ans;
  }

  int count(int[][] matrix, int n, int m, int x) {
    int cnt = 0;
    // Count the number of elements less than or equal to x in each row
    for (int i = 0; i < n; i++) {
      cnt += upperBound(matrix[i], m, x);
    }
    return cnt;
  }

  int median(int matrix[][], int R, int C) {
    // Initialize low and high to the minimum and maximum elements in the matrix
    int low = Integer.MAX_VALUE;
    int high = Integer.MIN_VALUE;

    int n = matrix.length;
    int m = matrix[0].length;

    // Find the minimum and maximum elements in the matrix
    for (int i = 0; i < n; i++) {
      if (low > matrix[i][0]) {
        low = matrix[i][0];
      }
      if (high < matrix[i][m - 1]) {
        high = matrix[i][m - 1];
      }
    }
    int req = (m * n) / 2;

    // Perform binary search to find the median
    while (low <= high) {
      int mid = (low + high) / 2;
      int count = count(matrix, n, m, mid);
      if (count <= req)
        low = mid + 1;
      else
        high = mid - 1;
    }

    return low;
  }
  /*
   * O(log(10^9)) * O(M(logN)), where M = number of rows in the given matrix, N =
   * number of columns in the given matrix
   * 
   * Reason: Our search space lies between [0, 109] as the min(matrix) can be 0
   * and the max(matrix) can be 109. We are applying binary search in this search
   * space and it takes O(log(109)) time complexity.
   * Then we call countSmallEqual() function for every ‘mid’ and this function
   * takes O(M(logN)) time complexity.
   * 
   * Space Complexity : O(1)
   */

}

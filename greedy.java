
import java.util.Arrays;

class Item { // class for Fractional Knapscak
  int value, weight;

  Item(int x, int y) {
    this.value = x;
    this.weight = y;
  }
}

public class greedy {

  /*
   * N meetings in one room
   * 
   * Input: n = 6, start[] = {1,3,0,5,8,5}, end[] = {2,4,6,7,9,9}
   * Output: 4
   * Explanation: Maximum four meetings can be held with given start and end
   * timings. The meetings are - (1, 2),(3, 4), (5,7) and (8,9)
   */

  public static int maxMeetings(int start[], int end[], int n) {
    // add your code here

    int activities[][] = new int[n][3];
    for (int i = 0; i < n; i++) {
      activities[i][0] = i;
      activities[i][1] = start[i];
      activities[i][2] = end[i];
    }

    Arrays.sort(activities, (a, b) -> Integer.compare(a[2], b[2]));

    int lastmeeting = activities[0][2];
    int maxActivity = 1;

    for (int i = 1; i < n; i++) {
      if (activities[i][1] > lastmeeting) {
        maxActivity++;
        lastmeeting = activities[i][2];
      }
    }
    return maxActivity;
  }

  /*
   * Minimum Platforms
   * 
   * Input: n = 6, arr[] = {0900, 0940, 0950, 1100, 1500, 1800},
   * dep[] = {0910, 1200, 1120, 1130, 1900, 2000}
   * Output: 3
   * Explanation: There are three trains during the time 0940 to 1200. So we need
   * minimum 3 platforms.
   */

  static int findPlatform(int arr[], int dep[], int n) {
    Arrays.sort(arr);
    Arrays.sort(dep);

    int plat_needed = 1, result = 1;
    int i = 1, j = 0;

    while (i < n && j < n) {

      if (arr[i] <= dep[j]) {
        plat_needed++;
        i++;
      }

      else if (arr[i] > dep[j]) {
        plat_needed--;
        j++;
      }

      if (plat_needed > result)
        result = plat_needed;
    }

    return result;
  }
  /*
   * Fractional Knapsack
   * Given weights and values of n items, we need to put these items in a knapsack
   * of capacity w to get the maximum total value in the knapsack. Return a double
   * value representing the maximum value in knapsack.
   * 
   * Input: n = 3, w = 50, value[] = [60,100,120], weight[] = [10,20,30]
   * Output: 240.000000
   * Explanation: Take the item with value 60 and weight 10, value 100 and weight
   * 20 and split the third item with value 120 and weight 30, to fit it into
   * weight 20. so it becomes (120/30)*20=80, so the total value becomes
   * 60+100+80.0=240.0 Thus, total maximum value of item we can have is 240.00
   * from the given capacity of sack.
   */

  double fractionalKnapsack(int w, Item arr[], int n) {
    // Your code here
    double ratio[][] = new double[n][2];
    for (int i = 0; i < n; i++) {
      ratio[i][0] = i;
      ratio[i][1] = arr[i].value / (double) arr[i].weight;
    }

    Arrays.sort(ratio, (a, b) -> Double.compare(a[1], b[1]));

    int capacity = w;
    double finalValue = 0;

    for (int i = n - 1; i >= 0; i--) {
      int idx = (int) ratio[i][0];

      if (capacity >= arr[idx].weight) {
        finalValue += arr[idx].value;
        capacity -= arr[idx].weight;
      } else {
        finalValue += ((double) ratio[i][1] * capacity);
        capacity = 0;
        break;
      }
    }
    return finalValue;
  }

}

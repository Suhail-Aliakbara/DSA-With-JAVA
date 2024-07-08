
import java.util.Arrays;

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
}

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class stackQueue {

  /*
   * 20. Valid Parentheses
   * Given a string s containing just the characters '(', ')', '{', '}', '[' and
   * ']', determine if the input string is valid.
   * Input: s = "()[]{}"
   * Output: true
   * Input: s = "(]"
   * Output: false
   */

  public boolean isValid(String s) {
    int n = s.length();
    Stack<Character> st = new Stack<>();

    for (int i = 0; i < n; i++) {
      char ch = s.charAt(i);
      if (ch == '(' || ch == '[' || ch == '{') {
        st.push(ch);
      } else {
        if (st.isEmpty()) {
          return false;
        }
        if (ch == ')' && st.peek() == '(' || st.peek() == '[' && ch == ']' || st.peek() == '{' && ch == '}') {
          st.pop();
        } else {
          return false;
        }
      }
    }
    if (st.isEmpty()) {
      return true;
    } else {
      return false;
    }
  }

  /*
   * 155. Min Stack
   * Design a stack that supports push, pop, top, and retrieving the minimum
   * element in constant time.
   */
  class MinStack {
    int min = Integer.MAX_VALUE;
    Stack<Integer> stack = new Stack<Integer>();

    public void push(int val) {
      if (val <= min) {
        stack.push(min);
        min = val;
      }
      stack.push(val);
    }

    public void pop() {
      if (stack.pop() == min)
        min = stack.pop();
    }

    public int top() {
      return stack.peek();
    }

    public int getMin() {
      return min;
    }
  }

  /**
   * Your MinStack object will be instantiated and called as such:
   * MinStack obj = new MinStack();
   * obj.push(val);
   * obj.pop();
   * int param_3 = obj.top();
   * int param_4 = obj.getMin();
   */

  /*
   * 496. Next Greater Element I
   * The next greater element of some element x in an array is the first greater
   * element that is to the right of x in the same array.
   * 
   * Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
   * Output: [-1,3,-1]
   * 
   * Input: nums1 = [2,4], nums2 = [1,2,3,4]
   * Output: [3,-1]
   */
  public int[] nextGreaterElement(int[] nums1, int[] nums2) {

    int n = nums2.length;
    Map<Integer, Integer> nge = new HashMap<>();
    Stack<Integer> st = new Stack<>();

    for (int i = n - 1; i >= 0; i--) {
      while (!st.isEmpty() && nums2[i] >= st.peek()) {
        st.pop();
      }
      if (st.isEmpty()) {
        nge.put(nums2[i], -1);
      } else {
        nge.put(nums2[i], st.peek());
      }
      st.push(nums2[i]);
    }
    for (int i = 0; i < nums1.length; i++) {
      nums1[i] = nge.get(nums1[i]);
    }
    return nums1;
  }

  /*
   * Sort a Stack
   * Sample Input: 5 -2 9 -7 3
   * Sample Output: 9 5 3 -2 -7
   */
  public static void sortedInsert(Stack<Integer> stack, int n) {
    if (stack.isEmpty() || stack.peek() <= n) {
      stack.push(n);
      return;
    }

    int removeBig = stack.peek();
    stack.pop();

    sortedInsert(stack, n);
    stack.push(removeBig);
  }

  public static void sortStack(Stack<Integer> stack) {
    // Write your code here.
    if (stack.isEmpty()) {
      return;
    }

    int top = stack.peek();
    stack.pop();

    sortStack(stack);

    sortedInsert(stack, top);
  }

  /*
   * Nearest Smaller Element
   * input 1: A = [4, 5, 2, 10, 8]
   * Output 1: G = [-1, 4, -1, 2, 2]
   */
  public int[] prevSmaller(int[] A) {
    Stack<Integer> st = new Stack<>();
    int arr[] = new int[A.length];

    for (int i = 0; i < A.length; i++) {
      while (!st.isEmpty() && st.peek() >= A[i]) {
        st.pop();
      }

      if (!st.isEmpty()) {
        arr[i] = st.peek();
      } else {
        arr[i] = -1;
      }

      st.push(A[i]);
    }
    return arr;
  }
}

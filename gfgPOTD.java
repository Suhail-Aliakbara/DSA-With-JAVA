import java.util.ArrayList;
import java.util.Stack;

public class gfgPOTD {
  /*
   * Longest valid Parentheses
   * Given a string s consisting of opening and closing parenthesis '(' and ')'.
   * Find the length of the longest valid parenthesis substring.
   * 
   * A parenthesis string is valid if:
   * 
   * For every opening parenthesis, there is a closing parenthesis.
   * The closing parenthesis must be after its opening parenthesi
   * 
   * Input: s = ")()())"
   * Output: 4
   * 
   */

  static void invalidParenthesisIndex(String s, Stack<Integer> st) {
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      if (ch == '(') {
        st.push(i);
      } else {
        if (st.isEmpty() || s.charAt(st.peek()) == ')') {
          st.push(i);
        } else {
          st.pop();
        }
      }
    }
  }

  static void formArrayofInvalid(String s, Stack<Integer> st, ArrayList<Integer> arr) {
    arr.add(s.length());
    while (!st.isEmpty()) {
      arr.add(0, st.pop());
    }
    arr.add(0, -1);
  }

  static int maxLength(String s) {
    // code here
    Stack<Integer> st = new Stack<>();
    invalidParenthesisIndex(s, st);

    ArrayList<Integer> arr = new ArrayList<>();
    formArrayofInvalid(s, st, arr);

    int maxValue = 0;
    for (int i = 1; i < arr.size(); i++) {
      int prev = arr.get(i - 1);
      maxValue = Math.max(maxValue, arr.get(i) - prev - 1);
    }

    return maxValue;
  }

  // OPTIMAL APPROACH
  static int maxLength1(String s) {
    // code here
    Stack<Integer> st = new Stack<>();
    st.push(-1);

    int maxValue = 0;
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      if (ch == '(') {
        st.push(i);
      } else {
        st.pop();
        if (st.isEmpty()) {
          st.push(i);
        } else {
          maxValue = Math.max(maxValue, i - st.peek());
        }
      }
    }

    return maxValue;
  }
}

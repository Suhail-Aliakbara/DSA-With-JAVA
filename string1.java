public class string1 {
  /*
   * 1021. Remove Outermost Parentheses
   * Input: s = "(()())(())(()(()))"
   * Output: "()()()()(())"
   */
  public String removeOuterParentheses(String s) {
    int n = s.length();
    int cnt = 0;
    String ans = "";
    for (int i = 0; i < n; i++) {
      char ch = s.charAt(i);
      if (ch == '(') {
        cnt++;
        if (cnt != 1) {
          ans += ch;
        }
      } else {
        cnt--;
        if (cnt != 0) {
          ans += ch;
        }
      }
    }
    return ans;
  }

}

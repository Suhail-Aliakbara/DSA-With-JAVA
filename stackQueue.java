import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class stackQueue {

  /*
   * Implementing Stack using Queues
   */
  // Using 1 queue
  Queue<Integer> queue = new LinkedList<>();

  public void push(int x) {
    queue.add(x);
    for (int i = 0; i < queue.size() - 1; i++) {
      queue.add(queue.poll());
    }
  }

  public int pop() {
    return queue.poll();
  }

  public int top() {
    return queue.peek();
  }

  public boolean empty() {
    return queue.isEmpty();
  }

  //

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
}

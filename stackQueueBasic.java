import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class stackQueueBasic {

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

  // Using 2 queue

  // using two queue. The push is inefficient.
  private Queue<Integer> q1 = new LinkedList<Integer>();
  private Queue<Integer> q2 = new LinkedList<Integer>();

  public void pushed(int x) {
    if (q1.isEmpty()) {
      q1.add(x);
      for (int i = 0; i < q2.size(); i++)
        q1.add(q2.poll());
    } else {
      q2.add(x);
      for (int i = 0; i < q1.size(); i++)
        q2.add(q1.poll());
    }
  }

  public void poped() {
    if (!q1.isEmpty())
      q1.poll();
    else
      q2.poll();
  }

  public int toped() {
    return q1.isEmpty() ? q2.peek() : q1.peek();
  }

  public boolean emptyy() {
    return q1.isEmpty() && q2.isEmpty();
  }
  /*
   * 232. Implement Queue using Stacks
   * 
   */

  Stack<Integer> s1 = new Stack<>();
  Stack<Integer> s2 = new Stack<>();

  public void push1(int x) {
    s1.push(x);
  }

  public int pop1() {
    // peek(); //important
    return s2.pop();
  }

  public int peek1() {
    if (s2.isEmpty()) {
      while (!s1.isEmpty()) {
        s2.add(s1.pop());
      }
    }
    return s2.peek();

  }

  public boolean empty1() {
    return s1.isEmpty() && s2.isEmpty();
  }

  /*
   * Infix to Postfix
   * Input: str = "a+b*(c^d-e)^(f+g*h)-i"
   * Output: abcd^e-fgh*+^*+i-
   */
  public static int priority(char ch) {
    if (ch == '^')
      return 3;
    else if (ch == '*' || ch == '/')
      return 2;
    else if (ch == '+' || ch == '-')
      return 1;
    return -1;
  }

  // Function to convert an infix expression to a postfix expression.
  public static String infixToPostfix(String exp) {
    // Your code here
    int n = exp.length();
    StringBuilder ans = new StringBuilder();
    Stack<Character> s = new Stack<>();
    int i = 0;

    while (i < n) {
      char ch = exp.charAt(i);
      // if(ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <= 'z' || ch >= '0' && ch <=
      // '9'){
      if (Character.isLetterOrDigit(ch)) {
        ans.append(ch);
      } else if (ch == '(') {
        s.push(ch);
      } else if (ch == ')') {
        while (!s.isEmpty() && s.peek() != '(') {
          ans.append(s.pop());
        }
        s.pop();
      } else {
        while (!s.isEmpty() && priority(ch) <= priority(s.peek())) {
          ans.append(s.pop());
        }
        s.push(ch);
      }
      i++;
    }
    while (!s.isEmpty()) {
      ans.append(s.pop());
    }
    return ans.toString();
  } // TC(O(n)) SC(O(n))

  /*
   * Postfix to Infix Conversion
   * Input: ab*c+
   * Output: ((a*b)+c)
   */
  static String postToInfix(String exp) {
    // code here
    int n = exp.length();
    Stack<String> s = new Stack<>();
    int i = 0;

    while (i < n) {
      char ch = exp.charAt(i);
      if (Character.isLetterOrDigit(ch)) {
        s.push(ch + "");
      } else {
        String t1 = s.pop();
        String t2 = s.pop();
        s.push("(" + t2 + ch + t1 + ")");
      }
      i++;
    }
    return s.peek();
  }
}
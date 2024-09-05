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

  /*
   * Prefix to Infix Conversion
   * Input: *-A/BC-/AKL
   * Output: ((A-(B/C))*((A/K)-L))
   */
  static String preToInfix(String pre_exp) { // Almost same as postToInfix conversion
    // code here
    int n = pre_exp.length();
    int i = n - 1;
    Stack<String> s = new Stack<>();

    while (i >= 0) { // loop backward
      char ch = pre_exp.charAt(i);
      if (Character.isLetterOrDigit(ch)) {
        s.push(ch + "");
      } else {
        String t1 = s.pop();
        String t2 = s.pop();
        s.push("(" + t1 + ch + t2 + ")"); // second element then first element from stack
      }
      i--;
    }
    return s.peek();
  }

  /*
   * Postfix to Prefix Conversion
   * Input: ABC/-AK/L-*
   * Output: *-A/BC-/AKL
   */
  public static String postToPre(String post_exp) {
    // code here
    int i = 0;
    int n = post_exp.length();
    Stack<String> s = new Stack<>();

    while (i < n) {
      char ch = post_exp.charAt(i);
      if (Character.isLetterOrDigit(ch)) {
        s.push(ch + "");
      } else {
        String t1 = s.pop();
        String t2 = s.pop();
        s.push(ch + t2 + t1);
      }
      i++;
    }
    return s.peek();
  }

  /*
   * Prefix to Postfix Conversion
   * Input: *-A/BC-/AKL
   * Output: ABC/-AK/L-*
   */
  static String preToPost(String pre_exp) {
    // code here
    int n = pre_exp.length();
    int i = n - 1;
    Stack<String> s = new Stack<>();

    while (i >= 0) {
      char ch = pre_exp.charAt(i);
      if (Character.isLetterOrDigit(ch)) {
        s.push(ch + "");
      } else {
        String t1 = s.pop();
        String t2 = s.pop();
        s.push(t1 + t2 + ch);
      }
      i--;
    }
    return s.peek();
  }

  /*
   * convert infix to prefix
   * Input: x+y*z/w+u
   * Output: ++x/*yzwu
   */

  static boolean isalpha(char c) {
    if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
      return true;
    }
    return false;
  }

  static boolean isdigit(char c) {
    if (c >= '0' && c <= '9') {
      return true;
    }
    return false;
  }

  static boolean isOperator(char c) {
    return (!isalpha(c) && !isdigit(c));
  }

  static int getPriority(char C) {
    if (C == '-' || C == '+')
      return 1;
    else if (C == '*' || C == '/')
      return 2;
    else if (C == '^')
      return 3;

    return 0;
  }

  // Reverse the letters of the word
  static String reverse(char str[], int start, int end) {
    // Temporary variable to store character
    char temp;
    while (start < end) {
      // Swapping the first and last character
      temp = str[start];
      str[start] = str[end];
      str[end] = temp;
      start++;
      end--;
    }
    return String.valueOf(str);
  }

  static String infixToPostfix(char[] infix1) {
    System.out.println(infix1);
    String infix = '(' + String.valueOf(infix1) + ')';

    int l = infix.length();
    Stack<Character> char_stack = new Stack<>();
    String output = "";

    for (int i = 0; i < l; i++) {

      // If the scanned character is an operand, add it to output.
      if (isalpha(infix.charAt(i)) || isdigit(infix.charAt(i)))
        output += infix.charAt(i);

      // If the scanned character is an ‘(‘, push it to the stack.
      else if (infix.charAt(i) == '(')
        char_stack.add('(');

      // If the scanned character is an ‘)’, pop and output from the stack
      // until an ‘(‘ is encountered.
      else if (infix.charAt(i) == ')') {
        while (char_stack.peek() != '(') {
          output += char_stack.peek();
          char_stack.pop();
        }

        // Remove '(' from the stack
        char_stack.pop();
      }

      // Operator found
      else {
        if (isOperator(char_stack.peek())) {
          while ((getPriority(infix.charAt(i)) < getPriority(char_stack.peek()))
              ||
              (getPriority(infix.charAt(i)) <= getPriority(char_stack.peek()) &&
                  infix.charAt(i) == '^')) {
            output += char_stack.peek();
            char_stack.pop();
          }

          // Push current Operator on stack
          char_stack.add(infix.charAt(i));
        }
      }
    }
    while (!char_stack.empty()) {
      output += char_stack.pop();
    }
    return output;
  }

  static String infixToPrefix(char[] infix) {
    // Reverse String Replace ( with ) and vice versa Get Postfix Reverse Postfix *

    int l = infix.length;
    // Reverse infix
    String infix1 = reverse(infix, 0, l - 1);
    infix = infix1.toCharArray();

    // Replace ( with ) and vice versa
    for (int i = 0; i < l; i++) {

      if (infix[i] == '(') {
        infix[i] = ')';
        i++;
      } else if (infix[i] == ')') {
        infix[i] = '(';
        i++;
      }
    }
    String prefix = infixToPostfix(infix);
    // Reverse postfix
    prefix = reverse(prefix.toCharArray(), 0, l - 1);
    return prefix;
  }
}
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

  /*
   * LRU cache
   * Design a data structure that follows the constraints of a Least Recently Used
   * (LRU) cache.
   * 
   * Implement the LRUCache class:
   * 
   * //LRUCache(int capacity) Initialize the LRU cache with positive size
   * capacity.
   * //int get(int key) Return the value of the key if the key exists, otherwise
   * return -1.
   * //void put(int key, int value) Update the value of the key if the key exists.
   * Otherwise, add the key-value pair to the cache. If the number of keys exceeds
   * the capacity from this operation, evict the least recently used key.
   * 
   * The functions get and put must each run in O(1) average time complexity.
   */
  class LRUCache {

    Node head = new Node(0, 0);
    Node tail = new Node(0, 0);
    Map<Integer, Node> map = new HashMap<>();
    int capacity = 0;

    public LRUCache(int _capacity) {
      capacity = _capacity;
      head.next = tail;
      tail.prev = head;
    }

    public int get(int key) {
      if (!map.containsKey(key))
        return -1;
      Node node = map.get(key);
      deleteNode(node);
      insertAtFront(node);

      return node.value;
    }

    public void put(int key, int value) {
      if (map.containsKey(key))
        deleteNode(map.get(key));
      else if (map.size() == capacity)
        deleteNode(tail.prev);
      insertAtFront(new Node(key, value));
    }

    public void deleteNode(Node node) {
      map.remove(node.key);
      node.prev.next = node.next;
      node.next.prev = node.prev;
    }

    public void insertAtFront(Node node) {
      map.put(node.key, node);
      node.next = head.next;
      head.next.prev = node;
      head.next = node;
      node.prev = head;
    }
  }

  public class Node {
    int key, value;
    Node next, prev;

    Node(int key, int value) {
      this.key = key;
      this.value = value;
      this.next = null;
      this.prev = null;
    }
  }
}

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

  /*
   * 460. LFU Cache
   * Design and implement a data structure for a Least Frequently Used (LFU)
   * cache.
   * 
   * Implement the LFUCache class:
   *
   * //LFUCache(int capacity) Initializes the object with the capacity of the data
   * structure.
   * // int get(int key) Gets the value of the key if the key exists in the cache.
   * Otherwise, returns -1.
   * // void put(int key, int value) Update the value of the key if present, or
   * inserts the key if not already present. When the cache reaches its capacity,
   * it should invalidate and remove the least frequently used key before
   * inserting a new item. For this problem, when there is a tie (i.e., two or
   * more keys with the same frequency), the least recently used key would be
   * invalidated.
   */
  class LFUCache {
    int capacity;
    int currSize;
    int minFrequency;
    Map<Integer, DLLNode> cache;
    Map<Integer, DoubleLinkedlist> frequencyMap;

    public LFUCache(int capacity) {
      this.capacity = capacity;
      this.currSize = 0;
      this.minFrequency = 0;

      this.cache = new HashMap<>();
      this.frequencyMap = new HashMap<>();
    }

    public int get(int key) {
      DLLNode currNode = cache.get(key);
      if (currNode == null)
        return -1;
      updateNode(currNode);
      return currNode.val;
    }

    public void put(int key, int value) {
      if (capacity == 0) {
        return;
      }

      if (cache.containsKey(key)) {
        DLLNode currNode = cache.get(key);
        currNode.val = value;
        updateNode(currNode);
      } else {
        currSize++;
        if (currSize > capacity) {
          DoubleLinkedlist minFreqList = frequencyMap.get(minFrequency);
          cache.remove(minFreqList.tail.prev.key);
          minFreqList.removeNode(minFreqList.tail.prev);
          currSize--;
        }

        minFrequency = 1;
        DLLNode newNode = new DLLNode(key, value);
        DoubleLinkedlist newList = frequencyMap.getOrDefault(1, new DoubleLinkedlist());
        newList.addNodeAtFirst(newNode);
        frequencyMap.put(1, newList);
        cache.put(key, newNode);
      }
    }

    public void updateNode(DLLNode currNode) {
      int currFreq = currNode.frequency;
      DoubleLinkedlist currList = frequencyMap.get(currFreq);
      currList.removeNode(currNode);

      if (currFreq == minFrequency && currList.listSize == 0) {
        minFrequency++;
      }
      currNode.frequency++;

      DoubleLinkedlist newList = frequencyMap.getOrDefault(currNode.frequency, new DoubleLinkedlist());
      newList.addNodeAtFirst(currNode);
      frequencyMap.put(currNode.frequency, newList);

    }
  }

  class DLLNode {
    int key;
    int val;
    int frequency;
    DLLNode next;
    DLLNode prev;

    DLLNode(int key, int val) {
      this.key = key;
      this.val = val;
      frequency = 1;
    }
  }

  class DoubleLinkedlist {
    int listSize;
    DLLNode head;
    DLLNode tail;

    DoubleLinkedlist() {
      this.head = new DLLNode(0, 0);
      this.tail = new DLLNode(0, 0);
      listSize = 0;
      head.next = tail;
      tail.prev = head;
    }

    public void addNodeAtFirst(DLLNode currNode) {
      DLLNode nextNode = head.next;
      currNode.next = nextNode;
      currNode.prev = head;
      head.next = currNode;
      nextNode.prev = currNode;
      listSize++;
    }

    public void removeNode(DLLNode currNode) {
      DLLNode prevNode = currNode.prev;
      DLLNode nextNode = currNode.next;
      prevNode.next = nextNode;
      nextNode.prev = prevNode;
      listSize--;

    }
  }

  /*
   * 84. Largest Rectangle in Histogram
   * Given an array of integers heights representing the histogram's bar height
   * where the width of each bar is 1, return the area of the largest rectangle in
   * the histogram.
   * Input: heights = [2,1,5,6,2,3]
   * Output: 10
   * Explanation: The above is a histogram where width of each bar is 1.
   * The largest rectangle is shown in the red area, which has an area = 10 units.
   */

  public int largestRectangleArea(int[] heights) {
    int n = heights.length;
    Stack<Integer> st = new Stack<>();
    int leftSmall[] = new int[n];
    int rightSmall[] = new int[n];

    for (int i = 0; i < n; i++) {
      while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
        st.pop();
      }
      if (st.isEmpty())
        leftSmall[i] = 0;
      else
        leftSmall[i] = st.peek() + 1;

      st.push(i);
    }

    while (!st.isEmpty())
      st.pop();

    for (int i = n - 1; i >= 0; i--) {
      while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
        st.pop();
      }
      if (st.isEmpty())
        rightSmall[i] = n - 1;
      else
        rightSmall[i] = st.peek() - 1;

      st.push(i);
    }

    int maxRect = 0;

    for (int i = 0; i < n; i++) {
      maxRect = Math.max(maxRect, heights[i] * (rightSmall[i] - leftSmall[i] + 1));
    }

    return maxRect;
  }
}

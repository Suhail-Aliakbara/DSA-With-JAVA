import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
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
   * Next Greater Element I
   * Input: N = 11, A[] = {3,10,4,2,1,2,6,1,7,2,9}
   * Output: 10,-1,6,6,2,6,7,7,9,9,10
   */
  public static int[] nextGreaterElements(int[] nums) {
    int n = nums.length;
    int nge[] = new int[n];
    Stack<Integer> st = new Stack<>();
    for (int i = 2 * n - 1; i >= 0; i--) {
      while (!st.isEmpty() && st.peek() <= nums[i % n]) {
        st.pop();
      }

      if (i < n) {
        if (st.isEmpty() == false)
          nge[i] = st.peek();
        else
          nge[i] = -1;
      }

      st.push(nums[i % n]);
    }
    return nge;
  }

  /*
   * 503. Next Greater Element II
   * Input: nums = [1,2,1]
   * Output: [2,-1,2]
   */

  public int[] nextGreaterElements2(int[] nums) {
    int n = nums.length;

    // Result array to store the next greater elements
    int[] nge = new int[n];

    // Stack to keep track of elements for comparison
    Stack<Integer> st = new Stack<>();

    // Iterate backwards twice to simulate circular traversal
    for (int i = 2 * n - 1; i >= 0; i--) {
      // Pop elements that are smaller or equal to the current element
      while (!st.isEmpty() && st.peek() <= nums[i % n]) {
        st.pop();
      }
      // Assign the next greater element for the first pass
      if (i < n) {
        nge[i] = st.isEmpty() ? -1 : st.peek();
      }
      // Push the current element into the stack
      st.push(nums[i % n]);
    }
    return nge;
  }

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
  public int[] nextGreaterElement1(int[] nums1, int[] nums2) {

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
   * Sort a Stack
   * Sample Input: 5 -2 9 -7 3
   * Sample Output: 9 5 3 -2 -7
   */
  public static void sortedInsert(Stack<Integer> stack, int n) {
    if (stack.isEmpty() || stack.peek() <= n) {
      stack.push(n);
      return;
    }

    int removeBig = stack.pop();

    sortedInsert(stack, n);
    stack.push(removeBig);
  }

  public static void sortStack(Stack<Integer> stack) {
    // Write your code here.
    if (stack.isEmpty()) {
      return;
    }
    int top = stack.pop();

    sortStack(stack);

    sortedInsert(stack, top);
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

  public int largestRectangleArea(int[] heights) { // TC(O(N)) SC(O(3N));
    int n = heights.length;
    Stack<Integer> st = new Stack<>();
    int leftSmall[] = new int[n];
    int rightSmall[] = new int[n];

    // preveous Smallest Element
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

    // Reset the Stack
    while (!st.isEmpty())
      st.pop();

    // Next smallest Element
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

    // Calculate Max Rectangle with NSE and PSE
    int maxRect = 0;
    for (int i = 0; i < n; i++) {
      maxRect = Math.max(maxRect, heights[i] * (rightSmall[i] - leftSmall[i] + 1));
    }
    return maxRect;
  }

  // OPTIMAL 2 --TC(O(N)) SC(0(N))
  public int largestRectangleArea1(int[] heights) {
    int n = heights.length;
    Stack<Integer> st = new Stack<>();
    int maxRect = 0;

    for (int i = 0; i <= n; i++) {
      while (!st.isEmpty() && ((i == n) || heights[st.peek()] >= heights[i])) {
        int height = heights[st.pop()];

        int width = 0;
        if (st.isEmpty())
          width = i;
        else
          width = i - st.peek() - 1;
        maxRect = Math.max(maxRect, width * height);
      }
      st.push(i);
    }
    return maxRect;
  }

  /*
   * 239. Sliding Window Maximum
   * 
   * Return the max sliding window.
   * 
   * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
   * Output: [3,3,5,5,6,7]
   * Explanation:
   * Window position -------Max-------
   * [1 3 -1] -3 5 3 6 7 -----3
   * 1 [3 -1 -3] 5 3 6 7 -----3
   * 1 3 [-1 -3 5] 3 6 7 -----5
   * 1 3 -1 [-3 5 3] 6 7 -----5
   * 1 3 -1 -3 [5 3 6] 7 -----6
   * 1 3 -1 -3 5 [3 6 7] -----7
   */

  static void getMax(int arr[], int l, int r, ArrayList<Integer> maxx) {
    int i, maxi = Integer.MIN_VALUE;
    for (i = l; i <= r; i++)
      maxi = Math.max(maxi, arr[i]);
    maxx.add(maxi);
  }

  static ArrayList<Integer> maxSlidingWindow(int arr[], int k) {
    int left = 0, right = k - 1;
    ArrayList<Integer> maxx = new ArrayList<>();

    while (right < arr.length) {
      getMax(arr, left, right, maxx);
      left++;
      right++;
    }
    return maxx;
  }
  /*
   * Time Complexity: O(N^2)
   * Reason: One loop for traversing and another to findMax
   * 
   * Space Complexity: O(K)
   * Reason: No.of windows
   */

  public int[] maxSlidingWindow1(int[] nums, int k) { // OPTIMAL- TC:O(N) SC:O(K)

    int n = nums.length;
    int arr[] = new int[n - k + 1];
    int ri = 0;

    Deque<Integer> dq = new ArrayDeque<>();
    for (int i = 0; i < n; i++) {
      while (!dq.isEmpty() && dq.peek() == i - k) {
        dq.poll();
      }
      while (!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]) {
        dq.pollLast();
      }

      dq.offer(i);
      if (i >= k - 1) {
        arr[ri++] = nums[dq.peek()];
      }
    }
    return arr;
  }

  /*
   * 901. Online Stock Span
   * Design an algorithm that collects daily price quotes for some stock and
   * returns the span of that stock's price for the current day.
   * 
   * Input
   * ["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
   * [[], [100], [80], [60], [70], [60], [75], [85]]
   * Output
   * [null, 1, 1, 1, 2, 1, 4, 6]
   */

  class StockSpanner {

    Stack<int[]> st;

    public StockSpanner() {
      st = new Stack<>();
    }

    public int next(int price) {
      int val = 1;
      while (!st.isEmpty() && st.peek()[0] <= price) {
        int prevDays = st.pop()[1];
        val = val + prevDays;
      }
      st.push(new int[] { price, val });

      return val;
    }
  }

  /*
   * 994. Rotting Oranges
   * You are given an m x n grid where each cell can have one of three values:
   * 
   * 0 representing an empty cell,
   * 1 representing a fresh orange, or
   * 2 representing a rotten orange.
   * 
   * Every minute, any fresh orange that is 4-directionally adjacent to a rotten
   * orange becomes rotten.
   * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
   * Output: 4
   */

  public int orangesRotting(int[][] grid) {
    if (grid == null || grid.length == 0)
      return 0;

    int rows = grid.length;
    int cols = grid[0].length;
    Queue<int[]> queue = new LinkedList<>();
    int freshOranges = 0;

    // Put the position of all rotten oranges in queue
    // count the number of fresh oranges
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == 2) {
          queue.offer(new int[] { i, j });
        }
        if (grid[i][j] == 1) {
          freshOranges++;
        }
      }
    }

    if (freshOranges == 0)
      return 0;

    int minutesElapsed = 0;
    int[] rowDirections = { -1, 0, 1, 0 };
    int[] colDirections = { 0, 1, 0, -1 };

    // BFS starting from initially rotten oranges
    while (!queue.isEmpty()) {
      int size = queue.size();
      boolean hasNewRotten = false;

      for (int i = 0; i < size; i++) {
        int[] point = queue.poll();
        int row = point[0];
        int col = point[1];

        for (int j = 0; j < 4; j++) {
          int newRow = row + rowDirections[j];
          int newCol = col + colDirections[j];

          if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols || grid[newRow][newCol] != 1)
            continue;

          grid[newRow][newCol] = 2;
          queue.offer(new int[] { newRow, newCol });
          freshOranges--;
          hasNewRotten = true;
        }
      }

      if (hasNewRotten)
        minutesElapsed++;

    }

    return freshOranges == 0 ? minutesElapsed : -1;
  }

  /*
   * Maximum of minimum for every window size
   * 
   * input: arr:[10 20 15 50 10 70 30] N:7
   * output: ans:[70,30,15,10,10,10,10]
   */

  public static int[] maxMinWindow(int[] arr, int n) {
    int ans[] = new int[n];
    Stack<Integer> st = new Stack<>();

    // Initialize ans array to Integer.MIN_VALUE
    for (int i = 0; i < n; i++) {
      ans[i] = Integer.MIN_VALUE;
    }
    // Process elements
    for (int i = 0; i < n; i++) {
      while (!st.isEmpty() && arr[st.peek()] > arr[i]) {
        int range = 0;
        int idx = st.pop();
        if (st.isEmpty()) {
          range = i;
          ans[range - 1] = Math.max(ans[range - 1], arr[idx]);
        } else {
          range = i - st.peek() - 1;
          ans[range - 1] = Math.max(ans[range - 1], arr[idx]);
        }
      }
      st.push(i);
    }
    // Process remaining elements in the stack
    while (!st.isEmpty()) {
      int range = 0;
      int idx = st.pop();
      if (st.isEmpty()) {
        range = n;
        ans[range - 1] = Math.max(ans[range - 1], arr[idx]);
      } else {
        range = n - st.peek() - 1;
        ans[range - 1] = Math.max(ans[range - 1], arr[idx]);
      }
    }
    // Propagate maximum values backward
    for (int i = n - 2; i >= 0; i--) {
      ans[i] = Math.max(ans[i], ans[i + 1]);
    }

    return ans;
  }

  /*
   * the celebrity Problem
   * celebrity:- is the on who knows no one and everyone know's him
   * input: arr = {{0,1,1,0},{0,0,0,0},{0,1,0,0},{1,1,0,0}}
   * output: 1
   * 
   */
  public int celebrity(int matrix[][]) {
    int top = 0;
    int down = matrix.length - 1;
    while (top < down) {
      if (matrix[top][down] == 1)
        top++;
      else if (matrix[down][top] == 1)
        down--;
      else {
        top++;
        down--;
      }
    }
    if (top > down)
      return -1;

    for (int i = 0; i < matrix.length; i++) {
      if (i == top)
        continue;
      if (matrix[top][i] == 0 && matrix[i][top] == 1) {
        return top;
      }
    }
    return -1;
  }

  //
  public int celebrity1(int matrix[][]) {
    int n = matrix.length;
    int candidate = 0;

    // Step 1: Find the candidate
    for (int i = 1; i < n; i++) {
      if (matrix[candidate][i] == 1) {
        candidate = i;
      }
    }

    // Step 2: Verify the candidate
    for (int i = 0; i < n; i++) {
      if (i != candidate) {
        if (matrix[candidate][i] == 1 || matrix[i][candidate] == 0) {
          return -1;
        }
      }
    }

    return candidate;
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

}

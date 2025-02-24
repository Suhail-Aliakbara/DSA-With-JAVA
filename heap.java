import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Arrays;
import java.util.HashMap;

public class heap {
  public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  /*
   * 215. Kth Largest Element in an Array
   * Given an integer array nums and an integer k, return the kth
   * largest element in the array.
   * Input: nums = [3,2,1,5,6,4], k = 2
   * Output: 5
   */
  public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    for (int i = 0; i < nums.length; i++) {
      pq.add(nums[i]);
      if (pq.size() > k) {
        pq.poll();
      }
    }
    return pq.poll();
  }

  /*
   * Kth Smallest
   * Given an array arr[] and an integer k where k is smaller than the
   * size of the array, the task is to find the kth smallest element
   * in the given array.
   * Input: arr[] = [7, 10, 4, 3, 20, 15], k = 3
   * Output: 7
   */
  public static int kthSmallest(int[] arr, int k) {
    // Your code here
    PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> Integer.compare(y, x));
    for (int i = 0; i < arr.length; i++) {
      pq.add(arr[i]);
      if (pq.size() > k) {
        pq.poll();
      }
    }
    return pq.poll();
  }

  /*
   * Merge k Sorted Arrays
   * Given k sorted arrays arranged in the form of a matrix of size
   * k * k. The task is to merge them into one sorted array.
   * Return the merged sorted array
   * 
   * Input: k = 4, arr[][]={{1,2,3,4},{2,2,3,4},{5,5,6,6},{7,8,9,9}}
   * Output: 1 2 2 2 3 3 4 4 5 5 6 6 7 8 9 9
   */
  public static ArrayList<Integer> mergeKArrays(int[][] arr, int K) {
    // Write your code here.
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        pq.add(arr[i][j]);
      }
    }
    ArrayList<Integer> ans = new ArrayList<>();
    while (!pq.isEmpty()) {
      ans.add(pq.poll());
    }
    return ans;
  }

  /*
   * Given an array of points where each point is represented as
   * points[i] = [xi, yi] on the X-Y plane and an integer k,
   * return the k closest points to the origin (0, 0).
   * 
   * The distance between two points on the X-Y plane is the
   * Euclidean distance, defined as:
   * sqrt( (x2 - x1)2 + (y2 - y1)2 )
   * 
   * Input: k = 2, points[] = [[1, 3], [-2, 2], [5, 8], [0, 1]]
   * Output: [[-2, 2], [0, 1]]
   */
  public int[][] kClosest(int[][] points, int k) {
    // Your code here
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[2], a[2]));

    for (int i = 0; i < points.length; i++) {

      int x = points[i][0];
      int y = points[i][1];
      int dist = x * x + y * y;
      pq.add(new int[] { x, y, dist });
      if (pq.size() > k) {
        pq.poll();
      }
    }

    int[][] ans = new int[k][2];

    for (int i = 0; i < k; i++) {
      int[] arr = pq.poll();
      ans[i][0] = arr[0];
      ans[i][1] = arr[1];
    }

    return ans;
  }

  /*
   * 23. Merge k Sorted Lists
   * You are given an array of k linked-lists lists, each linked-list is
   * sorted in ascending order.
   * Merge all the linked-lists into one sorted linked-list and return it.
   * Input: lists = [[1,4,5],[1,3,4],[2,6]]
   * Output: [1,1,2,3,4,4,5,6]
   * Explanation: The linked-lists are:
   * [
   * 1->4->5,
   * 1->3->4,
   * 2->6
   * ]
   * merging them into one sorted list:
   * 1->1->2->3->4->4->5->6
   */
  class Pair {
    int num;
    ListNode node;

    Pair(int num, ListNode node) {
      this.num = num;
      this.node = node;
    }
  }

  public ListNode mergeKLists(ListNode[] lists) {
    PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> Integer.compare(x.num, y.num));
    int n = lists.length;

    for (int i = 0; i < n; i++) {
      if (lists[i] != null) {
        pq.add(new Pair(lists[i].val, lists[i]));
      }
    }
    ListNode dummy = new ListNode(-1);
    ListNode temp = dummy;
    while (!pq.isEmpty()) {
      Pair p = pq.remove();
      ListNode node = p.node;
      temp.next = node;
      if (node.next != null) {
        pq.add(new Pair(node.next.val, node.next));
      }
      temp = temp.next;
    }
    return dummy.next;
  }

  /*
   * 295. Find Median from Data Stream
   * The median is the middle value in an ordered integer list. If the size
   * of the list is even, there is no middle value, and the median is the
   * mean of the two middle values.
   * 
   */
  class MedianFinder {

    PriorityQueue<Integer> leftPq;
    PriorityQueue<Integer> rightPq;

    public MedianFinder() {
      leftPq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
      rightPq = new PriorityQueue<>();

    }

    public void addNum(int num) {
      if (leftPq.size() == rightPq.size()) {
        rightPq.add(num);
        leftPq.add(rightPq.remove());
      } else {
        leftPq.add(num);
        rightPq.add(leftPq.remove());
      }
    }

    public double findMedian() {
      if (leftPq.size() == rightPq.size()) {
        return (leftPq.peek() + rightPq.peek()) / 2.0;
      } else {
        return leftPq.peek() * 1.0;
      }
    }
  }

  /*
   * Maximum Sum Combination
   * Given two integer array A and B of size N each.
   * A sum combination is made by adding one element from array A and another
   * element of array B.
   * Input:
   * N = 2
   * K = 2
   * A [ ] = {3, 2}
   * B [ ] = {1, 4}
   * Output: {7, 6}
   * Explanation:
   * 7 -> (A : 3) + (B : 4)
   * 6 -> (A : 2) + (B : 4)
   */
  static List<Integer> maxCombinations(int N, int K, int A[], int B[]) {
    // code here
    Arrays.sort(A);
    Arrays.sort(B);

    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
    Set<String> visited = new HashSet<>();
    List<Integer> ans = new ArrayList<>();

    pq.add(new int[] { A[N - 1] + B[N - 1], N - 1, N - 1 });
    visited.add((N - 1) + "," + (N - 1));

    while (!pq.isEmpty() && K-- > 0) {
      int sum = pq.peek()[0];
      int idx1A = pq.peek()[1];
      int idx2B = pq.peek()[2];
      pq.poll();

      ans.add(sum);

      if (idx1A - 1 >= 0 && !visited.contains((idx1A - 1) + "," + idx2B)) {
        pq.add(new int[] { A[idx1A - 1] + B[idx2B], idx1A - 1, idx2B });
        visited.add((idx1A - 1) + "," + idx2B);

      }
      if (idx2B - 1 >= 0 && !visited.contains(idx1A + "," + (idx2B - 1))) {
        pq.add(new int[] { A[idx1A] + B[idx2B - 1], idx1A, idx2B - 1 });
        visited.add(idx1A + "," + (idx2B - 1));
      }
    }
    return ans;
  }

  /*
   * Convert Min Heap to Max Heap
   * You are given an array arr of N integers representing a min Heap. The task is
   * to convert it to max Heap.
   * 
   * Input:
   * N = 5
   * arr = [3, 4, 8, 11, 13]
   * Output:
   * [13, 11, 8, 3, 4]
   */
  static void convertMinToMaxHeap(int N, int[] arr) {
    // Start from the last non-leaf node and move up.
    for (int i = (N / 2) - 1; i >= 0; i--) {
      // Perform heapify operation on each non-leaf node.
      maxHeapify(arr, N, i);
    }
  }

  public static void maxHeapify(int[] arr, int N, int index) {
    int largest = index; // Initialize largest as the current node.
    int leftChild = 2 * index + 1;
    int rightChild = 2 * index + 2;

    if (leftChild < N && arr[leftChild] > arr[largest]) {
      largest = leftChild;
    }

    if (rightChild < N && arr[rightChild] > arr[largest]) {
      largest = rightChild;
    }

    if (largest != index) {
      int temp = arr[index];
      arr[index] = arr[largest];
      arr[largest] = temp;
      maxHeapify(arr, N, largest);
    }
  }

  /*
   * 347. Top K Frequent Elements
   * Given an integer array nums and an integer k, return the k most frequent
   * elements. You may return the answer in any order.
   * Input: nums = [1,1,1,2,2,3], k = 2
   * Output: [1,2]
   */
  public int[] topKFrequent(int[] nums, int k) {
    HashMap<Integer, Integer> map = new HashMap<>();
    int n = nums.length;

    for (int i = 0; i < n; i++) {
      map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
    }

    PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>((a, b) -> (b.getValue() - a.getValue()));
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      maxHeap.add(entry);
    }

    int[] ans = new int[k];
    int i = 0;
    while (i < k) {
      Map.Entry<Integer, Integer> entry = maxHeap.poll();
      ans[i] = entry.getKey();
      i++;
    }
    return ans;
  }

  /////////////////////////////////////////////////////////////////
  class MinHeap {
    int[] heapArray;
    int capacity;
    int size;

    MinHeap(int capacity) {
      this.size = 0;
      this.capacity = capacity;
      this.heapArray = new int[capacity];
    }

    int parent(int index) {
      return (index - 1) / 2;
    }

    int left(int index) {
      return 2 * index + 1;
    }

    int right(int index) {
      return 2 * index + 2;
    }

    // Function to extract the minimum element (root) from the heap
    int extractMin() {
      if (size < 1)
        return -1; // If heap is empty, return -1

      int minValue = heapArray[0]; // The root is the smallest element
      heapArray[0] = heapArray[size - 1]; // Move the last element to root
      size--; // Reduce the heap size
      MinHeapify(0); // Restore heap property

      return minValue;
    }

    // Function to insert a new key into the heap
    void insertKey(int k) {
      if (size == capacity)
        return; // If heap is full, do nothing

      size++; // Increase the heap size
      decreaseKey(size - 1, k); // Insert at the last index and adjust
    }

    // Function to delete a key at a specific index
    void deleteKey(int i) {
      if (i < size) {
        decreaseKey(i, Integer.MIN_VALUE); // Reduce key to the smallest value
        extractMin(); // Extract min to remove it
      }
    }

    // Function to decrease the value at index 'i' and move it up if necessary
    void decreaseKey(int i, int new_val) {
      heapArray[i] = new_val; // Update the value at index 'i'

      // Move the updated node upwards if it violates heap property
      while (i != 0 && heapArray[parent(i)] > heapArray[i]) {
        // Swap heapArray[i] with its parent
        int temp = heapArray[i];
        heapArray[i] = heapArray[parent(i)];
        heapArray[parent(i)] = temp;

        i = parent(i); // Move up in the heap
      }
    }

    // Function to maintain the MinHeap property (push elements downward if needed)
    void MinHeapify(int index) {

      int smallest = index; // Assume current node is the smallest
      int leftChild = left(index); // Get the left child index
      int rightChild = right(index); // Get the right child index

      // If left child exists and is smaller, update smallest
      if (leftChild < size && heapArray[leftChild] < heapArray[index])
        smallest = leftChild;

      // If right child exists and is smaller than smallest, update smallest
      if (rightChild < size && heapArray[rightChild] < heapArray[smallest])
        smallest = rightChild;

      // If smallest is not the current node, swap and continue heapifying
      if (smallest != index) {
        int temp = heapArray[index];
        heapArray[index] = heapArray[smallest];
        heapArray[smallest] = temp;
        MinHeapify(smallest); // Recursively heapify the affected subtree
      }
    }
  }

}

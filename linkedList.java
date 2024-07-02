import java.util.LinkedList;

class Node {
  // Data stored in the node
  int data;
  // Pointer to the next
  // node in the list
  Node next;

  // Constructor with both data
  // and next node as parameters
  Node(int data, Node next) {
    this.data = data;
    this.next = next;
  }

  // Constructor with only data as
  // a parameter, sets next to null
  Node(int data) {
    this.data = data;
    this.next = null;
  }
}

public class linkedList {

  // Function to print the linked list
  public static void printLinkedList(Node head) {
    Node temp = head;
    while (temp != null) {
      System.out.print(temp.data + " ");
      temp = temp.next;
    }
    System.out.println();
  }

  /*
   * 206. Reverse Linked List
   * Input: head = [1,2,3,4,5]
   * Output: [5,4,3,2,1]
   */

  public static Node ReverseLinkedListUsingStack(Node head) { // TC(O(N)) SC(O(1))

    // Initialize'temp' at head of linked list
    Node temp = head;

    // Initialize pointer 'prev' to NULL, representing the previous node
    Node prev = null;

    // Traverse the list, continue till 'temp' reaches the end (NULL)
    while (temp != null) {
      // Store the next node in 'front' to preserve the reference
      Node front = temp.next;

      // Reverse the direction of the current node's 'next' pointer
      // to point to 'prev'
      temp.next = prev;

      // Move 'prev' to the current node for the next iteration
      prev = temp;

      // Move 'temp' to the 'front' node advancing the traversal
      temp = front;
    }

    // Return the new head of the reversed linked list
    return prev;
  }

  public static Node reverseLinkedList(Node head) {

    if (head == null || head.next == null) {
      return head;
    }

    // Recursive step: Reverse the linked list starting
    // from the second node (head.next).
    Node newHead = reverseLinkedList(head.next);

    // Save a reference to the node following the current 'head' node.
    Node front = head.next;

    // Make the 'front' node point to the current 'head' node in the reversed order.
    front.next = head;

    // Break the link from the current 'head' node to the 'front' node to avoid
    // cycles.
    head.next = null;

    // Return the 'newHead,' which is the new head of the reversed linked list.
    return newHead;
  }

  /*
   * 21. Merge Two Sorted Lists
   * You are given the heads of two sorted linked lists list1 and list2.
   * Merge the two lists into one sorted list. The list should be made by splicing
   * together the nodes of the first two lists.
   * 
   * Input: list1 = [1,2,4], list2 = [1,3,4]
   * Output: [1,1,2,3,4,4]
   */
  static Node sortTwoLinkedLists(Node list1, Node list2) {
    // Create a dummy node to serve as the head of the merged list
    Node dummyNode = new Node(-1);
    Node temp = dummyNode;

    // Traverse both lists simultaneously
    while (list1 != null && list2 != null) {
      // Compare elements of both lists and
      // link the smaller node to the merged list
      if (list1.data <= list2.data) {
        temp.next = list1;
        list1 = list1.next;
      } else {
        temp.next = list2;
        list2 = list2.next;
      }
      // Move the temporary pointer to the next node
      temp = temp.next;
    }

    // If any list still has remaining elements, append them to the merged list
    if (list1 != null) {
      temp.next = list1;
    } else {
      temp.next = list2;
    }
    // Return the merged list starting from the next of the dummy node
    return dummyNode.next;
  }

  /*
   * 19. Remove Nth Node From End of List
   * Given the head of a linked list, remove the nth node from the end of the list
   * and return its head.
   * Input: head = [1,2,3,4,5], n = 2
   * Output: [1,2,3,5]
   */
  public Node removeNthFromEnd(Node head, int n) {
    int size = 0;
    Node temp = head;
    while (temp != null) {
      size++;
      temp = temp.next;
    }

    // If the node to remove is the head, update head to the next node
    if (size == n) {
      head = head.next;
      return head;
    }

    // Initialize counter to traverse the list up to the node before the one to be
    // removed
    int i = 1;
    // Calculate the position of the node just before the one to be removed
    int iToFind = size - n;
    Node prev = head;

    // Traverse the list until reaching the node just before the one to be removed
    while (i < iToFind) {
      i++;
      prev = prev.next;
    }
    // Remove the nth node from the end by skipping over it
    prev.next = prev.next.next;
    // Return the head of the modified list
    return head;
  }

  // OPTIMAL SOLUTION
  // Function to delete the Nth node from the end of the linked list
  public static Node DeleteNthNodefromEnd(Node head, int N) {
    // Create two pointers, fastp and slowp
    Node fastp = head;
    Node slowp = head;

    // Move the fastp pointer N nodes ahead
    for (int i = 0; i < N; i++)
      fastp = fastp.next;

    // If fastp becomes null, the Nth node from the end is the head
    if (fastp == null)
      return head.next;

    // Move both pointers until fastp reaches the end
    while (fastp.next != null) {
      fastp = fastp.next;
      slowp = slowp.next;
    }

    // Delete the Nth node from the end
    Node delNode = slowp.next;
    slowp.next = slowp.next.next;
    delNode = null;
    return head;
  }
}

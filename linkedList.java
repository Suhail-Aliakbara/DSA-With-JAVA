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
}

package istanbul_moovit;

/**
 * A basic custom queue class to implement a breadth first search algorithm.
 * A queue is a data structure which obeys the first in first out rule.
 * It is an ordered list of elements for inserting them to the end or deleting them from start.
 *
 * The time complexity of inserting an element to the top is O(1)
 * The time complexity of deleting element from bottom is O(1)
 * The time complexity of getting the top element is O(1)
 * The space complexity is O(N)
 *
 * @implNote Queue interface of the java.util package can be used but I wasn't sure additional packages are allowed in the assignment
 *
 *
 * @author  Muhittin Köybaşı
 * @since   13-03-2023
 * @version 1.0
 * @see Node
 */

public class Queue {

  private int length;
  private Node head, tail;

  public Queue() {
    length = 0;
    head = tail = null;
  }

  public void push(Node node) {
    if (empty()) {
      head = node;
    } else {
      tail.setNext(node);
    }
    tail = node;
    length += 1;
  }

  public void pop() {
    if (empty()) return;

    head = head.next();
    length -= 1;
    if (empty()) {
      tail = null;
    }
  }

  public Node top() {
    if (empty()) {
      return null;
    }
    return head;
  }

  public int size() {
    return length;
  }

  public boolean empty() {
    return length == 0;
  }
}

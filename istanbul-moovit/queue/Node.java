package queue;

import java.util.ArrayList;

/**
 * A basic custom linked list class to build a custom queue from stracht.
 * A linked list is a data structure which contains connected nodes like a chain.
 * In a linked list each node points out the next node of the list by doing so they form a chain.
 *
 * The time complexity of getting the element of the current point is O(1)
 * The time complexity of adding an element to the current point is O(1)
 * The time complexity of traversing all list is O(N)
 *
 *
 * @author  Muhittin Köybaşı
 * @since   13-03-2023
 * @version 1.0
 */

public class Node {

  public int id;
  public Node nextNode;
  public ArrayList<Integer> path = new ArrayList<Integer>();

  public Node(int _id) {
    id = _id;
  }

  public int get() {
    return id;
  }

  public void set(int _id) {
    id = _id;
  }

  public Node next() {
    return nextNode;
  }

  public void setNext(Node _nextNode) {
    nextNode = _nextNode;
  }
}

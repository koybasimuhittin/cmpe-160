/**
 * @author  Muhittin Köybaşı
 * @since   13-03-2023
 * @version 1.0
 */

public class Main {

  static Queue queue = new Queue();

  /**
   * The main method of the Main class to make sure that custom queue's push, empty, pop, and top methods are working properly
   * Pushing the Node(0) and Node(1) to the queue and listing the elements of the queue by the first in first out rule until the queue becomes empty
   * @param args
   * @return null
   * @see Queue
   */
  public static void main(String[] args) {
    Node node = new Node(0);
    queue.push(node);
    Node node1 = new Node(1);
    queue.push(node1);
    while (!queue.empty()) {
      System.out.println(queue.top().get());
      queue.pop();
    }
  }
}

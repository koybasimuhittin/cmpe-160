/**
 * @author  Muhittin Köybaşı
 * @since   13-03-2023
 * @version 1.0
 */

import java.util.ArrayList;
import map.DrawMap;
import map.Map;
import queue.Queue;

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
    Map map = new Map();
    DrawMap canvas = new DrawMap(map);
    canvas.init();
    try {
      map.readCoordinatesFile();
      map.createGraph();
      ShortestPath shortestPath = new ShortestPath(
        map,
        "IstanbulHavalimani",
        "SabihaGokcenHavalimani"
      );
      ArrayList<Integer> path = shortestPath.findShortestPath();
      for (int i = 0; i < path.size(); i += 1) {
        System.out.println(map.stations.get(path.get(i)).name);
      }
    } catch (Exception exception) {
      System.out.println(exception);
    }
  }
}

import java.util.ArrayList;
import map.Map;
import queue.Node;
import queue.Queue;

public class ShortestPath {

  Map map;
  String start, destination;
  int startIndex, destinationIndex;
  Queue queue = new Queue();
  ArrayList<Boolean> visited = new ArrayList<Boolean>();

  public ShortestPath(Map _map, String _start, String _destination)
    throws Exception {
    map = _map;
    start = _start;
    destination = _destination;
    try {
      destinationIndex = map.stationIndex.get(destination);
      startIndex = map.stationIndex.get(start);
      for (int i = 0; i < map.stationCount; i += 1) {
        visited.add(false);
      }
    } catch (Exception exception) {
      throw new Exception(
        "The station names provided are not present in this map."
      );
    }
  }

  public ArrayList<Integer> findShortestPath() throws Exception {
    Node startNode = new Node(startIndex);
    startNode.path.add(startIndex);
    queue.push(startNode);

    while (!queue.empty()) {
      Node current = queue.top();
      queue.pop();
      visited.set(current.id, true);
      //System.out.println(map.stations.get(current.id).name);
      if (current.id == destinationIndex) {
        return current.path;
      }
      //System.out.println(map.graph.get(current.id).size());
      for (int i = 0; i < map.graph.get(current.id).size(); i += 1) {
        int neighbourStationIndex = map.graph.get(current.id).get(i);
        //System.out.println(neighbourStationIndex);
        if (!visited.get(neighbourStationIndex)) {
          Node neighbourNode = new Node(neighbourStationIndex);
          neighbourNode.path = new ArrayList<Integer>(current.path);
          neighbourNode.path.add(neighbourStationIndex);
          queue.push(neighbourNode);
        }
      }
    }
    throw new Exception("These two stations are not connected");
  }
}

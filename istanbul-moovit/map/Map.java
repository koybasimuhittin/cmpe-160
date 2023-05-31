package map;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Map {

  private static int LINE_COUNT = 10;
  public int stationCount = 0;

  public ArrayList<Line> lines = new ArrayList<Line>();
  public ArrayList<Station> stations = new ArrayList<Station>();
  public ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
  public HashMap<String, Integer> stationIndex = new HashMap<String, Integer>();

  public void readCoordinatesFile() {
    Scanner scanner;
    try {
      String filePath = new File("").getAbsolutePath();
      File file = new File(filePath + "/map/coordinates.txt");
      scanner = new Scanner(file);
      for (int i = 0; i < LINE_COUNT; i += 1) {
        String lineString = scanner.nextLine().toString();
        String stationsString = scanner.nextLine().toString();

        String[] lineInformation = lineString.split("[\s,,]");
        Double red = Double.parseDouble(lineInformation[1]);
        Double green = Double.parseDouble(lineInformation[2]);
        Double blue = Double.parseDouble(lineInformation[3]);
        Line _line = new Line(lineInformation[0], red, green, blue);
        
        String[] stationsInformation = stationsString.split("[\s,\s]");
        for(int j = 0; j < stationsInformation.length; j+= 3){
            String stationName = stationsInformation[j];
            double x = Double.parseDouble(stationsInformation[j + 1]);
            double y = Double.parseDouble(stationsInformation[j + 2]);
            boolean isNamed = false;
            
            if(stationName.startsWith("*")) {
                isNamed = true;
                stationName = stationName.substring(1);
            }

            Station _station;
            if(stationIndex.containsKey(stationName)){
                _station = new Station(x, y, stationIndex.get(stationName), stationName, isNamed);
            }
            else {
                _station = new Station(x, y, stationCount, stationName, isNamed);
                stationIndex.put(stationName, stationCount);
                stations.add(_station);
                stationCount += 1;   
            }
           _line.stations.add(_station);
        }
        lines.add(_line);
      }
      scanner.close();
    } catch (Exception exception) {
        System.out.println(exception);
    } 
  }

  public void createGraph() {
    for(int i = 0; i < stationCount; i+= 1) {
        graph.add(new ArrayList<Integer>());
    }
    for(int i = 0; i < lines.size(); i+= 1) {
        Line line = lines.get(i);
        for(int j = 0; j < line.stations.size(); j+= 1) {
            int currentId = line.stations.get(j).id;
            if(j == 0 && line.stations.size() > 1) {
                graph.get(currentId).add(line.stations.get(j + 1).id);
            }
            else if(j == line.stations.size() - 1 && j > 0) {
                graph.get(currentId).add(line.stations.get(j - 1).id);
            }
            else if(j > 0 && j < line.stations.size() - 1){
                graph.get(currentId).add(line.stations.get(j - 1).id);
                graph.get(currentId).add(line.stations.get(j + 1).id);
            }
        }
    }
  }
}

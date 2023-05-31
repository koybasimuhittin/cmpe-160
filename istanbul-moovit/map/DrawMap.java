package map;

import helpers.StdDraw;
import java.awt.Font;

public class DrawMap {

  public static Map map;
  private static int CANVAS_HEIGHT = 482;
  private static int CANVAS_WIDTH = 1024;
  private static double STATION_RADIUS = 0.012;
  private static double LINE_RADIUS = 0.01;
  private static Font FONT = new Font("Helvetica", Font.BOLD, 8);
  private static double STATION_NAME_X_OFFSET = 0.0;
  private static double STATION_NAME_Y_OFFSET = 5;

  public DrawMap(Map _map) {
    map = _map;
  }

  public void init() {
    StdDraw.setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
    StdDraw.setXscale(0, CANVAS_WIDTH);
    StdDraw.setYscale(0, CANVAS_HEIGHT);
    StdDraw.picture(0.5, 0.5, "../assets/images/background.jpg");
  }

  public static void drawLines() {
    for (int i = 0; i < map.lines.size(); i += 1) {
      Line line = map.lines.get(i);
      for (int j = 0; j < line.stations.size(); j += 1) {}
    }
  }
}

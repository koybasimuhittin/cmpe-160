import java.util.ArrayList;

public class Table extends Square {

  public static class Box {

    int x, y, size;
    int isFulfilled = 0;
  }

  static ArrayList<Box> boxes = new ArrayList<>();

  static int gameZoneWidth = 1500;
  static int gameZoneHeight = 600;
  static int gameInfoWidth = 1500;
  static int gameInfoHeight = 180;

  public static void drawLine(int x0, int y0, int x1, int y1) {
    StdDraw.setPenColor(StdDraw.GREEN);
    StdDraw.setPenRadius(0.050); // TODO: add soft coded radius
    StdDraw.line(x0, y0, x1, y1);
    setDefaultPen();
  }

  public static void drawTable(
    int tableColumnCount,
    int tableRowCount,
    int boxSize
  ) {
    int xStart = (gameZoneWidth - boxSize * tableColumnCount + boxSize) / 2;
    int yStart =
      (gameZoneHeight - boxSize * tableRowCount + boxSize) / 2 + gameInfoHeight;

    int tableY = yStart;
    for (int rowCount = 0; rowCount < tableRowCount; rowCount += 1) {
      int tableX = xStart;
      for (
        int columnCount = 0;
        columnCount < tableColumnCount;
        columnCount += 1
      ) {
        drawSquare(tableX, tableY, boxSize);

        Box box = new Box();
        box.x = tableX;
        box.y = tableY;
        box.size = boxSize;
        boxes.add(box);

        tableX += boxSize;
      }
      tableY += boxSize;
    }
  }
}

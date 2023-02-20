public class App {

  static int width = 1500;
  static int height = 780;
  static int gameZoneWidth = 1500;
  static int gameZoneHeight = 500;
  static int gameInfoWidth = 1500;
  static int gameInfoHeight = 280;

  static double[][] coordinatesOfBoxes;

  public static void setDefaultPen() {
    StdDraw.setPenRadius(0.004);
    StdDraw.setPenColor(StdDraw.BLACK);
  }

  public static void init() {
    StdDraw.setTitle("StdDraw XOX");
    StdDraw.setCanvasSize(width, height);
    StdDraw.setXscale(0.0, width * 1.0);
    StdDraw.setYscale(0.0, height * 1.0);
    StdDraw.line(
      0.0,
      gameInfoHeight * 1.0,
      gameInfoWidth * 1.0,
      gameInfoHeight * 1.0
    );
    setDefaultPen();
  }

  public static void drawSquare(int x, int y, int size) {
    StdDraw.square(x, y, size / 2.0);
  }

  public static void drawCircle(int x, int y, int radius) { // preferred radius is 30 for 100 * 100 box
    StdDraw.setPenRadius(0.035);
    StdDraw.setPenColor(StdDraw.BLUE);
    StdDraw.circle(x * 1.0, y * 1.0, radius);
    setDefaultPen();
  }

  public static void drawPoint(int x, int y) {
    StdDraw.point(x, y);
  }

  public static void drawCross(int x, int y, int size) { // size = halfSize of vertical or horizantal length, preferred 30 for 100 * 100 box
    StdDraw.setPenRadius(0.035);
    StdDraw.setPenColor(StdDraw.RED);
    StdDraw.line(x - size, y - size, x + size, y + size);
    StdDraw.line(x - size, y + size, x + size, y - size);
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
    System.out.println(xStart);
    System.out.println(yStart);

    int tableY = yStart;
    for (int rowCount = 0; rowCount < tableRowCount; rowCount += 1) {
      int tableX = xStart;
      for (
        int columnCount = 0;
        columnCount < tableColumnCount;
        columnCount += 1
      ) {
        drawSquare(tableX, tableY, boxSize);
        tableX += boxSize;
      }
      tableY += boxSize;
    }
  }

  public static void main(String[] args) {
    init();
    drawTable(3, 3, 100);
  }
}

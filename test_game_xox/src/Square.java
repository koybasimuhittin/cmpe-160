public class Square {

  public static void setDefaultPen() {
    StdDraw.setPenRadius(0.004);
    StdDraw.setPenColor(StdDraw.BLACK);
  }

  public static void drawSquare(int x, int y, int size) {
    StdDraw.square(x, y, size / 2.0);
  }

  public static void drawCircle(int x, int y, int radius) { // preferred radius is 30 for 150 * 150 box
    StdDraw.setPenRadius(0.030); // TODO: make the radius soft-coded for every boxSize
    StdDraw.setPenColor(StdDraw.BLUE);
    StdDraw.circle(x * 1.0, y * 1.0, radius);
    // TODO: implement an animation
    setDefaultPen();
  }

  public static void drawCross(int x, int y, int size) { // size = halfSize of vertical or horizantal length, preferred 30 for 150 * 150 box
    StdDraw.setPenRadius(0.030); // TODO: make the radius soft-coded for every boxSize
    StdDraw.setPenColor(StdDraw.RED);
    StdDraw.line(x - size, y - size, x + size, y + size);
    StdDraw.line(x - size, y + size, x + size, y - size);
    // TODO: implement an animation
    setDefaultPen();
  }
}

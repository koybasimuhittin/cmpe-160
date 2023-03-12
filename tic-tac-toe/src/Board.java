import java.util.ArrayList;

public class Board extends Table {

  static int width = 1500;
  static int height = 780;
  static int gameInfoWidth = 1500;
  static int gameInfoHeight = 180;

  public static void init() {
    boxes = new ArrayList<>();
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
    drawTable(3, 3, 150); //TODO: make the boxSize soft-coded for every box count
    StdDraw.text(100, 760, "Player1:");
    StdDraw.text(1340, 760, "Player2:");
  }

  public static void drawPoint(int x, int y) {
    StdDraw.point(x, y);
  }

  public static void main(String[] args) {}
}

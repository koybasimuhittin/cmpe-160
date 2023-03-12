import java.awt.Font;

public class Game extends Board {

  static boolean turn = false;
  static int gameCount = 0;
  static int mouseX, mouseY;
  static String winner;
  static int winCountOfPlayer1 = 0;
  static int winCountOfPlayer2 = 0;

  public static void writeWinCounts() {
    StdDraw.text(160, 760, String.valueOf(winCountOfPlayer1)); // TODO: make the coordinates soft-coded
    StdDraw.text(1400, 760, String.valueOf(winCountOfPlayer2)); // TODO: make the coordinates soft-coded
  }

  public static void setMouseCoordinates() {
    mouseX = (int) StdDraw.mouseX();
    mouseY = (int) StdDraw.mouseY();
  }

  public static boolean isClickedInThisArea(int x, int y, int size) {
    int left, right, top, bottom;
    left = x - size / 2;
    right = x + size / 2;
    top = y + size / 2;
    bottom = y - size / 2;
    if (mouseX > left && mouseX < right && mouseY < top && mouseY > bottom) {
      return true;
    }

    return false;
  }

  public static boolean isGameFinished() {
    for (int i = 0; i < 3; i++) {
      boolean ans = true;
      for (int j = 0; j < 3; j++) {
        if (boxes.get(i * 3 + j).isFulfilled != -1) {
          ans = false;
        }
      }
      if (ans) {
        drawLine(
          boxes.get(i * 3).x - 18,
          boxes.get(i * 3).y,
          boxes.get(i * 3 + 2).x + 18,
          boxes.get(i * 3 + 2).y
        );
        return true;
      }

      ans = true;
      for (int j = 0; j < 3; j++) {
        if (boxes.get(i * 3 + j).isFulfilled != 1) {
          ans = false;
        }
      }
      if (ans) {
        drawLine(
          boxes.get(i * 3).x - 18,
          boxes.get(i * 3).y,
          boxes.get(i * 3 + 2).x + 18,
          boxes.get(i * 3 + 2).y
        );
        return true;
      }
      ans = true;
      for (int j = 0; j < 3; j++) {
        if (boxes.get(j * 3 + i).isFulfilled != -1) {
          ans = false;
        }
      }
      if (ans) {
        drawLine(
          boxes.get(i).x,
          boxes.get(i).y - 18,
          boxes.get(i + 6).x,
          boxes.get(i + 6).y + 18
        );
        return true;
      }

      ans = true;
      for (int j = 0; j < 3; j++) {
        if (boxes.get(j * 3 + i).isFulfilled != 1) {
          ans = false;
        }
      }
      if (ans) {
        drawLine(
          boxes.get(i).x,
          boxes.get(i).y - 18,
          boxes.get(i + 6).x,
          boxes.get(i + 6).y + 18
        );
        return true;
      }
    }
    if (
      boxes.get(0).isFulfilled == boxes.get(4).isFulfilled &&
      boxes.get(4).isFulfilled == boxes.get(8).isFulfilled &&
      boxes.get(0).isFulfilled != 0
    ) {
      drawLine(
        boxes.get(0).x - 18,
        boxes.get(0).y - 18,
        boxes.get(8).x + 18,
        boxes.get(8).y + 18
      );
      return true;
    }
    if (
      boxes.get(2).isFulfilled == boxes.get(4).isFulfilled &&
      boxes.get(4).isFulfilled == boxes.get(6).isFulfilled &&
      boxes.get(2).isFulfilled != 0
    ) {
      drawLine(
        boxes.get(2).x + 18,
        boxes.get(2).y - 18,
        boxes.get(6).x - 18,
        boxes.get(6).y + 18
      );
      return true;
    }
    return false;
  }

  public static void handlesMouseInAnyBox() {
    if (winner == null) {
      for (int i = 0; i < boxes.size(); i += 1) {
        Box box = boxes.get(i);
        if (
          isClickedInThisArea(box.x, box.y, box.size) && box.isFulfilled == 0
        ) {
          if (turn == false) {
            drawCross(box.x, box.y, 30);
            box.isFulfilled = 1;
          } else {
            drawCircle(box.x, box.y, 30);
            box.isFulfilled = -1;
          }
          turn = !turn;
          if (isGameFinished()) {
            Font font = new Font("Sans Serif", Font.BOLD, 18);
            StdDraw.setFont(font);
            StdDraw.text(710, 720, "The winner is: ");
            if (turn) {
              winner = "Player1";
              winCountOfPlayer1 += 1;
              StdDraw.setPenColor(StdDraw.RED);
            } else {
              winner = "Player2";
              winCountOfPlayer2 += 1;
              StdDraw.setPenColor(StdDraw.BLUE);
            }
            gameCount += 1;
            turn = gameCount % 2 == 1 ? true : false;
            StdDraw.text(820, 720, winner);
            return;
          }
        }
      }
    }
  }

  public static void handleMouseInAnyButton() {
    // TODO: implement new game button, next game & previous game buttons.
  }

  public static void main(String[] args) {
    init();
    writeWinCounts();

    boolean isMousePressed = false;
    boolean isPlaying = true;

    while (isPlaying) {
      if (StdDraw.isKeyPressed(27) || StdDraw.isKeyPressed(81)) {
        System.exit(0);
      }
      if (StdDraw.isKeyPressed(67) || StdDraw.isKeyPressed(8)) {
        StdDraw.clear();
        init();
        winner = null;
        writeWinCounts();
      }
      if (StdDraw.isMousePressed() && isMousePressed == false) {
        setMouseCoordinates();
        isMousePressed = true;
        handlesMouseInAnyBox();
        handleMouseInAnyButton();
      }
      if (!StdDraw.isMousePressed()) {
        isMousePressed = false;
      }
    }
  }
}

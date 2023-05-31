import java.awt.Font;
import java.io.Console;
import java.util.ArrayList;

public class Environment {

  private static final int CANVAS_WIDTH = 800;
  private static final int CANVAS_HEIGHT = 500;

  public static final int PAUSE_DURATION = 15;
  public static final int TOTAL_GAME_DURATION = 40000;
  public static final double X_SCALE = 16.0;
  public static final double Y_SCALE = 10.0;

  // Game end section fonts
  private static Font HEADER_FONT = new Font("Heletica", Font.BOLD, 30);
  private static Font MAIN_FONT = new Font("Heletica", Font.ITALIC, 15);

  public static boolean hasGameEnded = false; // to keep the information has game ended in any condition
  public static ArrayList<Ball> balls; // list of the all balls for just one game

  public static void setHasGameEnded(boolean val) {
    hasGameEnded = val;
  }

  public static void canvasInit() {
    // Initialize the canvas with given constants
    StdDraw.setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
    StdDraw.setXscale(0.0, X_SCALE);
    StdDraw.setYscale(-1.0, Y_SCALE - 1.0);
    StdDraw.enableDoubleBuffering();
  }

  public static void backgroundInit() {
    // Initialize the background image
    StdDraw.picture(8.0, 4.0, "background.png", 16.0, 10.0);
  }

  public static void playGame() {
    // creates a new bar, player, and arrow objects and then initialize the bar(isAlive = false)
    Bar bar = new Bar();
    bar.setStartingTime();
    Player player = new Player();
    Arrow arrow = new Arrow();
    arrow.init();

    // clears the balls array before every game and initialize it with the given 3 balls
    balls = new ArrayList<Ball>();
    balls.clear();
    Ball a = new Ball(X_SCALE / 4, 0.5, 1.0, 0);
    Ball b = new Ball(X_SCALE / 3, 0.5, -1.0, 1);
    Ball c = new Ball(X_SCALE / 4, 0.5, 1.0, 2);

    balls.add(a);
    balls.add(b);
    balls.add(c);

    // make sure the hasGameEnded = false
    hasGameEnded = false;

    while (!hasGameEnded) {
      // Clear the canvas
      StdDraw.clear();

      // Draw every object
      backgroundInit();
      bar.drawBar();
      arrow.drawArrow();
      player.drawPlayer();

      // Check all of the balls are alive or not
      boolean allBallsCleared = true;
      for (int i = 0; i < balls.size(); i++) {
        // Check balls and arrow or balls and player intersects
        balls.get(i).setIsBallAlive(arrow);
        balls.get(i).setIsPlayerTouching(player);
        if (balls.get(i).isAlive) {
          allBallsCleared = false;
        }
        balls.get(i).drawBall();
      }
      if (allBallsCleared) {
        // if there is no ball alive it means the game ended
        setHasGameEnded(true);
      }

      // To player movements
      if (StdDraw.isKeyPressed(37)) {
        player.setX(-1);
      }
      if (StdDraw.isKeyPressed(39)) {
        player.setX(+1);
      }
      if (StdDraw.isKeyPressed(32) && !arrow.isArrowAlive) {
        arrow.setArrow(player.xCoordinate);
      }

      // if the game ended draw the game board
      if (hasGameEnded) {
        StdDraw.picture(
          X_SCALE / 2,
          Y_SCALE / 2.18,
          "game_screen.png",
          X_SCALE / 3.8,
          Y_SCALE / 4
        );
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setFont(HEADER_FONT);
        if (balls.size() == 11) {
          StdDraw.text(X_SCALE / 2, Y_SCALE / 2, "You Won!");
        } else {
          StdDraw.text(X_SCALE / 2, Y_SCALE / 2, "Game Over!");
        }
        StdDraw.setFont(MAIN_FONT);
        StdDraw.text(X_SCALE / 2, Y_SCALE / 2.3, "To Replay Click “Y”");
        StdDraw.text(X_SCALE / 2, Y_SCALE / 2.6, "To Quit Click “N”");
      }
      StdDraw.show();
      StdDraw.pause(PAUSE_DURATION);
    }
  }
}

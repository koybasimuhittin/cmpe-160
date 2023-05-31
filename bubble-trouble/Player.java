public class Player {

  public double PERIOD_OF_PLAYER = 6000;
  private static double PLAYER_HEIGHT_WIDTH_RATE = 37.0 / 27.0;
  public static double PLAYER_WIDTH = 6.0;
  public static double PLAYER_SCALE_RATE = 1.0 / 8.0;
  public static double PLAYER_HEIGHT =
    PLAYER_HEIGHT_WIDTH_RATE * PLAYER_WIDTH * PLAYER_SCALE_RATE;

  public double xCoordinate = Environment.X_SCALE / 2; // initial x coordinate
  public double yCoordinate = PLAYER_HEIGHT / 2; // initital y coordinate

  public void setX(double x) {
    // calculate the x position of the player
    if (Environment.hasGameEnded == false) {
      xCoordinate += x * (Environment.PAUSE_DURATION / PERIOD_OF_PLAYER) * 45.0;
      if (xCoordinate >= Environment.X_SCALE) {
        xCoordinate = Environment.X_SCALE;
      } else if (xCoordinate < 0.0) {
        xCoordinate = 0.0;
      }
    }
  }

  public void drawPlayer() {
    // draw the player to the canvas with calculated values
    StdDraw.picture(
      xCoordinate,
      PLAYER_HEIGHT / 2,
      "player_back.png",
      PLAYER_WIDTH * PLAYER_SCALE_RATE,
      PLAYER_HEIGHT
    );
  }
}

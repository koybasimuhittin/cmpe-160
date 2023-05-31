public class Bar {

  private long passedTime = 0; // passed time from game start
  private double xAxisCoefficient = 1.0; // x axis coefficient to make more realiable calculations
  private static long startingTime = System.currentTimeMillis(); // starting time of the current game

  public void setStartingTime() {
    startingTime = System.currentTimeMillis();
  }

  private void setBackGround() {
    // set the background of the bar with the given image
    StdDraw.picture(
      Environment.X_SCALE / 2,
      -0.5,
      "bar.png",
      Environment.X_SCALE,
      1.0
    );
  }

  public void drawBar() {
    // draw the bar according to game status
    if (
      startingTime +
      Environment.TOTAL_GAME_DURATION >
      System.currentTimeMillis() &&
      !Environment.hasGameEnded
    ) {
      passedTime = System.currentTimeMillis() - startingTime;
      xAxisCoefficient =
        (double) (Environment.TOTAL_GAME_DURATION - passedTime) /
        (double) Environment.TOTAL_GAME_DURATION;
    } else {
      Environment.setHasGameEnded(true);
    }
    setBackGround();

    StdDraw.setPenColor(255, (int) (255 * xAxisCoefficient), 0);
    StdDraw.filledRectangle(
      Environment.X_SCALE * xAxisCoefficient / 2,
      -0.5,
      Environment.X_SCALE * xAxisCoefficient / 2,
      0.25
    );
  }
}

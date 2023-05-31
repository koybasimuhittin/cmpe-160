public class Arrow {

  private static final double PERIOD_OF_ARROW = 1500; //Period for calculate arrow speed
  public double yCoordinate = 0.0; // Initial y coordinate of the arrow
  public double xCoordinate;
  public boolean isArrowAlive = true; // Keep the arraow outside the canvas or encounterd with any ball

  public void setArrow(double x) {
    // initialize the arrow
    xCoordinate = x;
    yCoordinate = 0.0;
    isArrowAlive = true;
  }

  public void init() {
    // to start the first arrow of the game
    this.isArrowAlive = false;
  }

  public void killTheArrow() {
    // when arraow outside the canvas or encounterd with any ball make it unvisible
    this.isArrowAlive = false;
  }

  private void incrementArrowY() {
    // calculate the new locations of the arrow
    if (yCoordinate < Environment.Y_SCALE - 1) {
      yCoordinate += (Environment.Y_SCALE - 1) * 45.0 / PERIOD_OF_ARROW;
    } else {
      killTheArrow();
    }
  }

  public void drawArrow() {
    // if the arrow is visible draw the arrow according to its locations and then calculate new position
    if (isArrowAlive) {
      StdDraw.picture(
        xCoordinate,
        yCoordinate / 2,
        "arrow.png",
        0.2,
        yCoordinate
      );
      incrementArrowY();
    }
  }
}

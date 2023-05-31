public class Ball {

  private final int PERIOD_OF_BALL = 15000; // period constant to calculate the x speed of the ball
  private final double HEIGHT_MULTIPLIER = 1.75; // a constant to calculate the ball's maximum height from ground and initial y speed
  private final double RADIUS_MULTIPLIER = 2.0; // a constant to calculate the ball's radius
  private final double MIN_POSSIBLE_HEIGHT = Player.PLAYER_HEIGHT * 1.4; // a calculate the ball's maximum height from ground and initial y speed
  private final double MIN_POSSIBLE_RADIUS = Environment.Y_SCALE * 0.0175;
  private final double GRAVITY = 0.000003 * Environment.Y_SCALE;

  private int ballLevel;
  private double yCoordinateStarting; // to make projectile motion after every ball creation

  public double xCoordinate;
  public double yCoordinate;
  public double xSpeed;
  public double ySpeed;
  public double radius;
  public boolean isAlive;

  public Ball(double x, double y, double vx, int level) {
    xCoordinate = x;
    yCoordinate = yCoordinateStarting = y;

    // x speed of the ball derived from given constants
    xSpeed =
      vx *
      Environment.X_SCALE *
      Environment.PAUSE_DURATION *
      2 /
      PERIOD_OF_BALL;
    ballLevel = level;

    // initial y speed of the ball derived from given constants
    ySpeed = MIN_POSSIBLE_HEIGHT * HEIGHT_MULTIPLIER * (level + 2) / 90.0;

    // radius of the ball derived from given constants
    radius = MIN_POSSIBLE_RADIUS * RADIUS_MULTIPLIER * (level + 1);
    isAlive = true;
  }

  public void setIsBallAlive(Arrow arrow) {
    // check if arrow and ball is intersects
    if (
      arrow.xCoordinate - 0.1 <= xCoordinate + radius / 2 &&
      arrow.xCoordinate + 0.1 >= xCoordinate - radius / 2 &&
      arrow.yCoordinate - radius / 3.8 >= yCoordinate - radius / 2 &&
      arrow.isArrowAlive &&
      isAlive
    ) {
      // make this ball and the arrow unvisible
      isAlive = false;
      arrow.isArrowAlive = false;

      if (ballLevel > 0) {
        // if it is not a level 0 ball create new two ball
        Ball a = new Ball(xCoordinate, yCoordinate, 1.0, ballLevel - 1);
        Ball b = new Ball(xCoordinate, yCoordinate, -1.0, ballLevel - 1);
        Environment.balls.add(a);
        Environment.balls.add(b);
      }
    }
  }

  private boolean isBallTouchingThePlayer(Player player) {
    // check is ball and player intersects ball calculating the distance between them
    double xLen =
      (xCoordinate - player.xCoordinate) * (xCoordinate - player.xCoordinate);
    double yLen =
      (yCoordinate - player.yCoordinate) * (yCoordinate - player.yCoordinate);

    if ((Math.sqrt(xLen + yLen)) <= Player.PLAYER_HEIGHT / 2 + radius / 2) {
      return true;
    }
    return false;
  }

  public void setIsPlayerTouching(Player player) {
    if (isBallTouchingThePlayer(player)) {
      Environment.setHasGameEnded(true);
    }
  }

  public void setxCoordinate() {
    // calculate the x coordinate of the ball and do an elastic collision with the left, right walls.
    xCoordinate += xSpeed;
    if (
      xCoordinate + radius / 2 >= Environment.X_SCALE ||
      xCoordinate - radius / 2 <= 0
    ) {
      xSpeed *= -1;
    }
  }

  public void setyCoordinate() {
    // calculate the x coordinate of the ball and do an elastic collision with the bottom walls.
    ySpeed -= GRAVITY * Environment.PAUSE_DURATION * 3;
    yCoordinate += ySpeed;
    if (
      // if it reaches the its limit drop the ball from the limit.
      yCoordinate >=
      MIN_POSSIBLE_HEIGHT *
      HEIGHT_MULTIPLIER *
      (ballLevel + 1) +
      yCoordinateStarting
    ) {
      ySpeed = 0;
    }
    if (yCoordinate - radius / 2 <= 0) {
      // if it hits the ground do an elastic collision
      yCoordinateStarting = 0;
      ySpeed = MIN_POSSIBLE_HEIGHT * HEIGHT_MULTIPLIER * (ballLevel + 2) / 80.0;
      yCoordinate = radius / 2;
    }
  }

  public void drawBall() {
    // if the ball is alive draw it and calculate the next coordinates
    if (isAlive) {
      StdDraw.picture(xCoordinate, yCoordinate, "ball.png", radius, radius);
      setxCoordinate();
      setyCoordinate();
    }
  }
}

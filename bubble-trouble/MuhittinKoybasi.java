public class MuhittinKoybasi {

  public static void main(String[] args) {
    // start a while loop while player press "N" after games
    while (true) {
      Environment.canvasInit();
      Environment.playGame();
      while (true) {
        if (StdDraw.isKeyPressed(78)) {
          System.exit(0);
        }
        if (StdDraw.isKeyPressed(89)) {
          // if player press "Y" break this while loop and continue with outer
          Environment.setHasGameEnded(false);
          break;
        }
      }
    }
  }
}

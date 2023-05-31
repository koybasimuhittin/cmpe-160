import java.util.Formatter;
import java.util.Scanner;

public class MuhittinKoybasi {

  public static void main(String[] args) {
    TerrainHelper.readTerrain();
    TerrainHelper.printTerrain();

    Scanner scanner = new Scanner(System.in);
    for (int i = 1; i <= 10; i++) {
      TerrainHelper.modifyTerrain(i, scanner);
    }
    scanner.close();

    PoolFinder.initializePools();
    //PoolFinder.printPoolsOfTerrain();
    PoolFinder.updatePools();
    PoolFinder.calculateScore();
    TerrainHelper.printTerrain();
    Formatter fm = new Formatter();
    fm.format("%.2f", PoolFinder.score);
    System.out.println("Final score: " + fm);
    fm.close();
  }
}

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class PoolFinder {

  public static int[][] pools = new int[TerrainHelper.n][TerrainHelper.m];
  public static int[][] poolsOfTerrain = new int[TerrainHelper.n][TerrainHelper.m];
  public static final int[] dx = { 1, 0, -1 };
  public static final int[] dy = { 1, 0, -1 };
  public static double score = 0;
  private static Queue<Node> queue = new LinkedList<Node>();

  public static void initializePools() {
    for (int i = 0; i < TerrainHelper.n; i++) {
      for (int j = 0; j < TerrainHelper.m; j++) {
        pools[i][j] = 0;
        poolsOfTerrain[i][j] = TerrainHelper.terrain[i][j];
      }
    }
  }

  public static void printPools() {
    for (int i = 0; i < TerrainHelper.n; i++) {
      for (int j = 0; j < TerrainHelper.m; j++) {
        System.out.print(pools[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static void printPoolsOfTerrain() {
    for (int i = 0; i < TerrainHelper.n; i++) {
      for (int j = 0; j < TerrainHelper.m; j++) {
        System.out.print(poolsOfTerrain[i][j] + " ");
      }
      System.out.println();
    }
  }

  private static boolean isValidCoordinate(int x, int y) {
    return x > 0 && x < TerrainHelper.m - 1 && y > 0 && y < TerrainHelper.n - 1;
  }

  private static void updatePoolsForLayer(int layer) {
    ArrayList<Node> currentPool = new ArrayList<Node>();
    boolean[][] visited = new boolean[TerrainHelper.n][TerrainHelper.m];
    for (int i = 0; i < TerrainHelper.n; i++) {
      for (int j = 0; j < TerrainHelper.m; j++) {
        visited[i][j] = false;
      }
    }
    for (int i = 0; i < TerrainHelper.n; i++) {
      for (int j = 0; j < TerrainHelper.m; j++) {
        if (poolsOfTerrain[i][j] == layer && !visited[i][j]) {
          queue.clear();
          currentPool.clear();
          boolean isValidPool = true;
          queue.add(new Node(j, i));
          while (!queue.isEmpty()) {
            Node node = queue.remove();

            visited[node.y][node.x] = true;
            currentPool.add(node);
            if (!isValidCoordinate(node.x, node.y)) {
              isValidPool = false;
              queue.clear();
              currentPool.clear();
              break;
            }

            for (int k = 0; k < 3; k++) {
              for (int l = 0; l < 3; l++) {
                if (k == 1 && l == 1) {
                  continue;
                }
                int x = node.x + dx[k];
                int y = node.y + dy[l];
                if (poolsOfTerrain[y][x] == layer && !visited[y][x]) {
                  queue.add(new Node(x, y));
                }
              }
            }
          }

          if (isValidPool) {
            for (Node node : currentPool) {
              pools[node.y][node.x] += 1;
            }
          }
        }
      }
    }
    for (int i = 0; i < TerrainHelper.n; i++) {
      for (int j = 0; j < TerrainHelper.m; j++) {
        if (poolsOfTerrain[i][j] == layer) {
          poolsOfTerrain[i][j] += 1;
        }
      }
    }
  }

  public static void updatePools() {
    for (int i = 0; i < TerrainHelper.maxElement; i++) {
      updatePoolsForLayer(i);
    }
  }

  private static String getPoolName(int count) {
    String result = "";
    if (count >= 26) {
      result += (char) ((int) (count / 26) + 'A' - 1);
    } else {
      result += " ";
    }
    result += (char) ((int) (count % 26) + 'A');
    return result;
  }

  public static void calculateScore() {
    queue.clear();
    boolean[][] visited = new boolean[TerrainHelper.n][TerrainHelper.m];
    for (int i = 0; i < TerrainHelper.n; i++) {
      for (int j = 0; j < TerrainHelper.m; j++) {
        visited[i][j] = false;
      }
    }
    int poolCount = 0;

    for (int i = 0; i < TerrainHelper.n; i++) {
      for (int j = 0; j < TerrainHelper.m; j++) {
        if (pools[i][j] > 0 && !visited[i][j]) {
          int currentScore = 0;
          queue.add(new Node(j, i));
          while (!queue.isEmpty()) {
            Node node = queue.remove();
            visited[node.y][node.x] = true;
            if (!isValidCoordinate(node.x, node.y)) {
              continue;
            }
            TerrainHelper.finalTerrain[node.y][node.x] = getPoolName(poolCount);
            currentScore += pools[node.y][node.x];
            for (int k = 0; k < 3; k++) {
              for (int l = 0; l < 3; l++) {
                if (k == 1 && l == 1) {
                  continue;
                }
                int x = node.x + dx[k];
                int y = node.y + dy[l];
                if (pools[y][x] > 0 && !visited[y][x]) {
                  queue.add(new Node(x, y));
                }
              }
            }
          }
          score += Math.sqrt(currentScore);
          poolCount += 1;
        }
      }
    }
  }
}

import java.io.File;
import java.util.Scanner;

public class TerrainHelper {

  public static int n, m;
  public static int[][] terrain;
  public static String[][] finalTerrain;
  public static int maxElement = 0;

  private static int[] resolveModification(String modification) {
    try {
      if (modification.length() == 2) {
        return new int[] {
          modification.charAt(0) - 'a',
          modification.charAt(1) - '0',
        };
      } else if (modification.length() == 3) {
        if (modification.charAt(1) <= '9' && modification.charAt(1) >= '0') {
          return new int[] {
            modification.charAt(0) - 'a',
            (modification.charAt(1) - '0') * 10 + modification.charAt(2) - '0',
          };
        } else {
          return new int[] {
            (modification.charAt(0) - 'a' + 1) *
            26 +
            modification.charAt(1) -
            'a',
            modification.charAt(2) - '0',
          };
        }
      } else {
        return new int[] {
          (modification.charAt(0) - 'a' + 1) *
          26 +
          modification.charAt(1) -
          'a',
          (modification.charAt(2) - '0') * 10 + modification.charAt(3) - '0',
        };
      }
    } catch (Exception e) {
      throw new IllegalArgumentException(
        "Invalid modification: " + modification
      );
    }
  }

  public static void readTerrain() {
    Scanner scanner = null;
    try {
      String filePath = new File("").getAbsolutePath();
      File file = new File(filePath + "/input.txt");
      scanner = new Scanner(file);
      m = scanner.nextInt();
      n = scanner.nextInt();
      terrain = new int[n][m];
      finalTerrain = new String[n][m];
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          terrain[i][j] = scanner.nextInt();
          finalTerrain[i][j] = String.valueOf(terrain[i][j]);
          maxElement = Math.max(maxElement, terrain[i][j]);
        }
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      if (scanner != null) scanner.close();
    }
  }

  private static String getRowCoordinate(int coordinate) {
    String result = "";
    while (result.length() < 3) {
      result += " ";
    }
    result += String.valueOf(coordinate);
    return result.substring(result.length() - 3, result.length());
  }

  private static String getColumnCoordinate(int coordinate) {
    String result = "";
    if (coordinate >= 26) {
      result += (char) ((int) (coordinate / 26) + 'a' - 1);
    } else {
      result += " ";
    }
    result += (char) ((int) (coordinate % 26) + 'a');
    return result;
  }

  private static String getElement(int x, int y) {
    return ((finalTerrain[y][x].length() == 1 ? " " : "") + finalTerrain[y][x]);
  }

  public static void printTerrain() {
    for (int i = 0; i < n; i++) {
      System.out.print(getRowCoordinate(i) + " ");
      for (int j = 0; j < m; j++) {
        System.out.print(getElement(j, i) + " ");
      }
      System.out.println();
    }
    String leftPad = "";
    while (leftPad.length() < 4) {
      leftPad += " ";
    }
    System.out.print(leftPad);
    for (int i = 0; i < m; i++) {
      System.out.print(getColumnCoordinate(i) + " ");
    }
    System.out.println();
  }

  public static void modifyTerrain(int step, Scanner scanner) {
    System.out.println("Add stone " + step + " / 10 to coordinate:");
    int[] modification = null;
    do {
      try {
        String modificationStr = scanner.next();
        if (!isValidModification(modificationStr)) throw new Exception(
          "Invalid modification"
        );
        modification = resolveModification(modificationStr);
        if (modification[0] >= m || modification[1] >= n) throw new Exception(
          "Invalid modification"
        );
      } catch (Exception e) {
        modification = null;
        System.out.println(e.getMessage());
      }
    } while (modification == null);
    terrain[modification[1]][modification[0]] += 1;
    finalTerrain[modification[1]][modification[0]] =
      String.valueOf(terrain[modification[1]][modification[0]]);
    printTerrain();
    System.out.println("---------------");
  }

  private static boolean isValidModification(String modificationStr) {
    if (
      modificationStr.length() < 2 || modificationStr.length() > 4
    ) return false;

    if (
      modificationStr.charAt(0) < 'a' || modificationStr.charAt(0) > 'z'
    ) return false;
    if (
      modificationStr.charAt(modificationStr.length() - 1) < '0' ||
      modificationStr.charAt(modificationStr.length() - 1) > '9'
    ) return false;

    int letterCount = 0;
    int numberCount = 0;
    for (int i = 0; i < modificationStr.length(); i++) {
      if (
        modificationStr.charAt(i) >= 'a' && modificationStr.charAt(i) <= 'z'
      ) {
        letterCount++;
      } else if (
        modificationStr.charAt(i) >= '0' && modificationStr.charAt(i) <= '9'
      ) {
        numberCount++;
      }
    }
    if (letterCount > 2 || numberCount > 2) return false;
    if (letterCount < 1 || numberCount < 1) return false;
    return true;
  }
}

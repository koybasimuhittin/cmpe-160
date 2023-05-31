package map;

/**
 * @author  Muhittin Köybaşı
 * @since   13-03-2023
 * @version 1.0
 */

public class Station {

  public double x, y;
  public int id;
  public String name;
  boolean isNamed;

  public Station(
    double _x,
    double _y,
    int _id,
    String _name,
    boolean _isNamed
  ) {
    x = _x;
    y = _y;
    id = _id;
    name = _name;
    isNamed = _isNamed;
  }
}

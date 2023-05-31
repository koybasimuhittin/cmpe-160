package map;

import java.util.ArrayList;

public class Line {

  String name;
  Double red, green, blue;
  ArrayList<Station> stations = new ArrayList<Station>();

  public Line(String _name, Double _red, Double _green, Double _blue) {
    name = _name;
    red = _red;
    green = _green;
    blue = _blue;
  }
}

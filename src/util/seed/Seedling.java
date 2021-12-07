package util.seed;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.Posn;

/**
 * Class to represent a seedling.
 */
public class Seedling implements ISeedling{
  private Posn position;
  private Map<Posn, Color> containedPixels;

  /**
   * Creates a seedling around a seed in a given position.
   * @param position position of the center seed in the seedling.
   */
  public Seedling(Posn position) {
    this.position = position;
    this.containedPixels = new HashMap<>();
  }

  @Override
  public void addPixel(Color color, Posn posn) {
    containedPixels.put(posn, color);
  }

  @Override
  public boolean containsPosn(Posn posn) {
    return containedPixels.containsKey(posn);
  }

  @Override
  public int getLength() {
    return containedPixels.size();
  }

  @Override
  public void colorSeedling() {
    List<Color> colors = this.getColors();
    List<Integer> redComps = new ArrayList<>();
    List<Integer> greenComps = new ArrayList<>();
    List<Integer> blueComps = new ArrayList<>();
    for (int i = 0; i < colors.size(); i++) {
      Color current = colors.get(i);
      int redComp = current.getRed();
      int greenComp = current.getGreen();
      int blueComp = current.getBlue();
      redComps.add(redComp);
      greenComps.add(greenComp);
      blueComps.add(blueComp);
    }
    int avgRed = avg(redComps);
    int avgGreen = avg(greenComps);
    int avgBlue = avg(blueComps);
    for (int i = 0; i < colors.size(); i++) {
      Color current = colors.get(i);
      current = new Color(avgRed, avgGreen, avgBlue);
    }
  }

  private int avg(List<Integer> ints) {
    int sum = 0;
    int length = ints.size();
    if (length == 0) {
      return 0;
    }
    else {
      for (int i = 0; i < length; i++) {
        sum = sum + ints.get(i);
      }
      return sum/length;
    }
  }

  private List<Color> getColors() {
    List<Color> base = new ArrayList<>();
    Collection<Color> colors = containedPixels.values();
    Object[] colorArray = colors.toArray();
    for (int i = 0; i < colorArray.length; i++) {
      Color current = (Color) colorArray[i];
      base.add(current);
    }
    return base;
  }

  public void addColors(Color[][] base) {
    Set<Posn> posns = containedPixels.keySet();
    for (Posn x : posns) {
      Posn currentPosn = x;
      int curX = currentPosn.getX();
      int curY = currentPosn.getY();
      base[curY][curX] = this.getColors().get(0);
    }
  }

  public Posn getPosn() {
    return this.position;
  }
}
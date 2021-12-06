package util.seed;

import java.awt.Color;
import java.util.Collection;
import java.util.Collections;
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
  public boolean containsColor(Color color) {
    return containedPixels.containsValue(color);
  }

  @Override
  public int getLength() {
    return containedPixels.size();
  }

  @Override
  public void colorSeedling() {
    List<Color> colors = this.getColors();
    List<Integer> redComps = Collections.emptyList();
    List<Integer> greenComps = Collections.emptyList();
    List<Integer> blueComps = Collections.emptyList();
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
    for (int i = 0; i < length; i++) {
      sum = sum + ints.get(i);
    }
    return sum;
  }

  private List<Color> getColors() {
    List<Color> base = Collections.emptyList();
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
    Posn[] posnArray = (Posn[]) posns.toArray();
    for (int i = 0; i < this.getLength(); i++) {
      Posn currentPosn = posnArray[i];
      int curX = currentPosn.getX();
      int curY = currentPosn.getY();
      base[curX][curY] = this.getColors().get(1);
    }
  }

  public Posn getPosn() {
    return this.position;
  }
}
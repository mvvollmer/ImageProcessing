package util.seed;

import java.awt.*;
import java.util.Collections;
import java.util.List;

import util.Posn;

/**
 * interface representing a Seedling
 */
public interface ISeedling {
  /**
   * adds a Pixel to a seedling.
   * @param color color of pixel.
   * @param posn position of pixel.
   */
  public void addPixel(Color color, Posn posn);

  /**
   * determines whether a seedling contains a Pixel.
   * @return if Seedling contains given Color or not.
   */
  public boolean containsPosn(Posn pos);

  /**
   * gets the number of Pixels contained in a seedling.
   * @return number of pixels in seedling.
   */
  public int getLength();

  /**
   * colors the seedling based on the colors of the pixels in it.
   */
  public void colorSeedling();
}

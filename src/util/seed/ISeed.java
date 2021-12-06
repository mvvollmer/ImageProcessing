package util.seed;

import java.awt.Color;

/**
 * Interface representing a seed.
 */
public interface ISeed {
  /**
   * Method to build a seed.
   */
  public void createSeed();

  /**
   * Method that creates a mosaic image from a seed.
   * @return color array representing seed mosaic image.
   */
  public Color[][] createSeedImage();
}

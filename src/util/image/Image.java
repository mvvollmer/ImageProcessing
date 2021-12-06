package util.image;

import java.awt.Color;

/**
 * Representing an Image as a 2D array of Pixels and their supporting operations.
 */
public interface Image extends ImageState {

  /**
   * Creates a new matrix of pixels that represents
   * the brightened version of this image and returns it.
   *
   * @param increment the value that the user wants to change the brightness by.
   * @return a 2D array of pixels that represents the brightened version of this image.
   * @throws IllegalArgumentException when the given increment value is invalid.
   */
  Color[][] brightenImage(int increment) throws IllegalArgumentException;

  /**
   * Creates a new matrix of pixels that represents
   * the greyscale version of this image and returns it.
   *
   * @param component the method that should be employed to create a greyscale version of an image.
   *                  Valid methods include red, blue, green, value, intensity and luma.
   * @return a 2D array of pixels that represents the greyscale version of this image.
   * @throws IllegalArgumentException when the given component is invalid.
   */
  Color[][] displayGreyscale(String component) throws IllegalArgumentException;

  /**
   * Rearranges the pixels of an image to flip them over a given axis.
   *
   * @param axis the axis that the image will be flipped across.
   * @throws IllegalArgumentException when the given String is not
   *                                  a valid axis to flip the image across.
   */
  Color[][] flipImage(String axis) throws IllegalArgumentException;


  /**
   * Creates a new Matrix of colors that represents
   * an image after it has undergone a supported transformation.
   *
   * @param type the methods that will be used to transform the image.
   * @return an image that reflects a post-filtered image.
   * @throws IllegalArgumentException if the transformation type is invalid.
   */
  Color[][] transformImage(String type) throws IllegalArgumentException;

  /**
   * Creates a new Matrix of colors that represents an image after a filter has been applied to it.
   *
   * @param type the filtering methods that will be applied to the image.
   * @return an image that reflects a post-filtered image.
   * @throws IllegalArgumentException if the filter type is invalid.
   */
  Color[][] filterImage(String type) throws IllegalArgumentException;
}

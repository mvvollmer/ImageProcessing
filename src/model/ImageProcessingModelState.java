package model;

import java.awt.Color;

import util.image.Image;

/**
 * This interface represents operations that can be used to monitor the state of an
 * ImageProcessingModel without changing it.
 */
public interface ImageProcessingModelState {


  Image getImage(String imageName) throws IllegalArgumentException;

  Color getPixelAt(String imageName, int row, int col) throws IllegalArgumentException;

  /**
   * Gets the height of an image associated with the given imageName.
   *
   * @return an int that represents the height of an image.
   * @throws IllegalArgumentException when the given imageName is not associated with an image.
   */
  int getImageHeight(String imageName) throws IllegalArgumentException;

  /**
   * Gets the width of an image associated with the given imageName.
   *
   * @return an int that represents the width of an image.
   * @throws IllegalArgumentException when the given imageName is not associated with an image.
   */
  int getImageWidth(String imageName) throws IllegalArgumentException;
}

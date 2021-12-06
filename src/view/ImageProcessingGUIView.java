package view;

import controller.Features;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Representing a GUI View.
 */
public interface ImageProcessingGUIView {

  /**
   * Displays a given image to the user.
   * @param image a BufferedImage that will be displayed to the user.
   */
  void displayImage(BufferedImage image);

  /**
   * Given lists of integers that represent the frequency that pixels of an
   * image display color components
   * of varying greyscale values, creates a histogram line chart
   * so that the user can visual the value disparity.
   * @param histogram lists of integers tht represent the frequency that
   *                 values of color components are used in an image.
   */
  void displayHistogram(List<List<Integer>> histogram);

  /**
   * Adds the supported features of the controller to the view.
   * @param features the features tht are supported by the view.
   */
  void addFeatures(Features features);

  /**
   * Given an error message, displays the message to the user.
   * @param message a string that represents an error image.
   */
  void displayMessage(String message);
}

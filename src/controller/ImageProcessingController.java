package controller;

/**
 * Representing a Controller for an Image Processing program,
 * utilized for delegating operations to the model/view
 * based on user inputs.
 */
public interface ImageProcessingController {

  /**
   * Processes an image based on user inputs.
   *
   * @throws IllegalArgumentException if a user input is invalid
   */
  void processImage() throws IllegalArgumentException;

}

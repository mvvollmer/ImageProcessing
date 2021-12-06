package view;

import java.io.IOException;

/**
 * Representing the View of an Image Processor, utilized for displaying
 * Images and messages within the program.
 */
public interface ImageProcessingView {

  /**
   * Display an image.
   */
  void displayImage();

  /**
   * Display a given message.
   *
   * @param message String of the message
   */
  void renderMessage(String message) throws IOException;

}

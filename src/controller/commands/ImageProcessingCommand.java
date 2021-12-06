package controller.commands;

import model.ImageProcessingModel;

/**
 * Representing the commands that an ImageProcessingController
 * can execute.
 */
public interface ImageProcessingCommand {

  /**
   * Executes a command method based on user input.
   *
   * @param model ImageProcessingModel
   */
  void apply(ImageProcessingModel model) throws IllegalArgumentException;

}

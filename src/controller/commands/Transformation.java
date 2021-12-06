package controller.commands;

import model.ImageProcessingModel;

/**
 * Command class utilized for Transforming an image.
 */
public class Transformation implements ImageProcessingCommand {

  private String type;
  private String imageName;
  private String desiredName;

  /**
   * Constructs a Transformation object.
   *
   * @param type        filter type
   * @param imageName   name of the image to perform filter command on
   * @param desiredName desired name of the image
   * @throws IllegalArgumentException if any inputs are null
   */
  public Transformation(String type, String imageName, String desiredName)
          throws IllegalArgumentException {
    if (type == null || imageName == null || desiredName == null) {
      throw new IllegalArgumentException("Inputs may not be null.");
    }
    this.type = type;
    this.imageName = imageName;
    this.desiredName = desiredName;
  }

  @Override
  public void apply(ImageProcessingModel model)
          throws IllegalArgumentException {

    switch (this.type) {
      case "greyscale":
        model.transformImage("greyscale", imageName, desiredName);
        break;
      case "sepia":
        model.transformImage("sepia", imageName, desiredName);
        break;
      default:
        throw new IllegalArgumentException("Invalid command.");
    }
  }
}

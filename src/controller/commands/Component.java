package controller.commands;

import model.ImageProcessingModel;

/**
 * Command class used for methods pertaining to a "component" input,
 * where a user visualizes a certain component of an image.
 */
public class Component implements ImageProcessingCommand {

  private String type;
  private String imageName;
  private String desiredName;

  /**
   * Constructs a Component object.
   *
   * @param type        String of the component type
   * @param imageName   name of the image user would like to visualize a component of
   * @param desiredName desired name of the visualized image
   * @throws IllegalArgumentException if any fields are null.
   */
  public Component(String type, String imageName, String desiredName)
          throws IllegalArgumentException {
    if (type == null) {
      throw new IllegalArgumentException("Type cannot be null.");
    }
    if (imageName == null) {
      throw new IllegalArgumentException("Image name cannot be null.");
    }
    if (desiredName == null) {
      throw new IllegalArgumentException("Desired name cannot be null.");
    }

    this.type = type;
    this.imageName = imageName;
    this.desiredName = desiredName;
  }

  @Override
  public void apply(ImageProcessingModel model) {

    switch (this.type) {
      case "value":
        model.displayGreyscale("value", imageName, desiredName);
        break;
      case "intensity":
        model.displayGreyscale("intensity", imageName, desiredName);
        break;
      case "luma":
        model.displayGreyscale("luma", imageName, desiredName);
        break;
      case "red":
        model.displayGreyscale("red", imageName, desiredName);
        break;
      case "green":
        model.displayGreyscale("green", imageName, desiredName);
        break;
      case "blue":
        model.displayGreyscale("blue", imageName, desiredName);
        break;
      default:
        throw new IllegalArgumentException("Invalid input.");
    }
  }
}

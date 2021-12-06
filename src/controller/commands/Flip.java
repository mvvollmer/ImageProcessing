package controller.commands;

import model.ImageProcessingModel;

/**
 * Command class used to execute flip operations.
 */
public class Flip implements ImageProcessingCommand {

  private String type;
  private String imageName;
  private String desiredName;

  /**
   * Constructs a Flip object.
   *
   * @param type        flip type (vertical or horizontal)
   * @param imageName   the name of the image
   * @param desiredName the desired name of the image
   * @throws IllegalArgumentException if fields are null
   */
  public Flip(String type, String imageName, String desiredName) throws IllegalArgumentException {
    if (type == null) {
      throw new IllegalArgumentException("Type cannot be null.");
    }
    this.type = type;
    this.imageName = imageName;
    this.desiredName = desiredName;
  }

  @Override
  public void apply(ImageProcessingModel model) {
    String in = this.type;
    switch (in) {
      case "horizontal":
        model.flipImage("horizontal", this.imageName, this.desiredName);
        break;
      case "vertical":
        model.flipImage("vertical", this.imageName, this.desiredName);
        break;
      default:
        throw new IllegalArgumentException("Invalid type entered.");
    }
  }
}

package controller.commands;

import model.ImageProcessingModel;

/**
 * Command class used for brightening an image.
 */
public class Brighten implements ImageProcessingCommand {

  private int increment;
  private String imageName;
  private String desiredName;

  /**
   * Constructs a Brighten object.
   *
   * @param increment   the increment by which an image is brightened/darkened
   * @param imageName   the name of the image
   * @param desiredName the desired name of the image
   * @throws IllegalArgumentException if fields are null
   */
  public Brighten(int increment, String imageName, String desiredName)
          throws IllegalArgumentException {
    if (imageName == null || desiredName == null) {
      throw new IllegalArgumentException("imageName and/or desired name are null");
    }
    this.increment = increment;
    this.imageName = imageName;
    this.desiredName = desiredName;
  }

  @Override
  public void apply(ImageProcessingModel model) {
    model.brightenImage(this.increment, this.imageName, this.desiredName);
  }
}

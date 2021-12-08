package controller.commands;

import model.ImageProcessingModel;

/**
 * Command class for creating a Mosaic of an Image.
 */
public class Mosaic implements ImageProcessingCommand {
  private int seedNum;
  private String imageName;
  private String desiredName;

  /**
   * Constructs a Mosaic Command with a given seedNum, imageName, desiredName.
   * @param seedNum desired number of seeds.
   * @param imageName name of desired image.
   * @param desiredName desired image name.
   * @throws IllegalArgumentException if either imageName or desiredName are null.
   */
  public Mosaic(int seedNum, String imageName, String desiredName)
          throws IllegalArgumentException {
    if (imageName == null || desiredName == null) {
      throw new IllegalArgumentException("imageName and/or desired name are null");
    }
    this.seedNum = seedNum;
    this.imageName = imageName;
    this.desiredName = desiredName;
  }

  @Override
  public void apply(ImageProcessingModel model) throws IllegalArgumentException {
    model.mosaicImage(imageName, desiredName, seedNum);
  }
}

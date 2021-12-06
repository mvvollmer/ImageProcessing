package controller.commands;

import model.ImageProcessingModel;

public class Mosaic implements ImageProcessingCommand {
  private int seedNum;
  private String imageName;
  private String desiredName;

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

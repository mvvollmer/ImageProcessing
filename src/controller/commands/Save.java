package controller.commands;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import model.ImageProcessingModel;
import util.ImageUtil;

/**
 * Command class utilized for saving an image.
 */
public class Save implements ImageProcessingCommand {

  private String fileName;
  private String imageName;

  /**
   * Constructing a Save object.
   *
   * @param fileName  the location where the file is to be saved
   * @param imageName the name of the file to be saved.
   */
  public Save(String fileName, String imageName) {
    if (fileName == null || imageName == null) {
      throw new IllegalArgumentException("File name and image name cannot be null.");
    }
    this.fileName = fileName;
    this.imageName = imageName;
  }

  @Override
  public void apply(ImageProcessingModel model) {
    if (fileName == null) {
      throw new IllegalArgumentException("The filename is null.");
    }

    String[] splitAtPeriods = fileName.split("\\.");
    if (splitAtPeriods.length <= 1) {
      throw new IllegalArgumentException("Filepath does not contain a file type.");
    }
    int indexOfType = splitAtPeriods.length - 1;

    if (splitAtPeriods[indexOfType].equals("ppm")) {
      ImageUtil.writePPM(fileName, model.getImage(imageName));
    } else {
      String[] supportedSuffixes = ImageIO.getWriterFileSuffixes();
      if (!(Arrays.asList(supportedSuffixes).contains(splitAtPeriods[indexOfType]))) {
        throw new IllegalArgumentException("The given file is not supported for writing.");
      }

      int i;
      int j;
      try {
        int height = model.getImageHeight(imageName);
        int width = model.getImageWidth(imageName);
        BufferedImage savedImage = model.toBufferedImage(imageName);

        File newFile = new File(fileName);
        ImageIO.write(savedImage, splitAtPeriods[indexOfType], newFile);
      } catch (IOException e) {
        throw new IllegalArgumentException(e);
      }
    }
  }
}

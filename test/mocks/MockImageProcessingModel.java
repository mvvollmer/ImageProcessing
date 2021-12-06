package mocks;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.ImageProcessingModel;
import util.image.Image;
import util.image.PixelImage;

/**
 * Mock model class utilized for testing.
 */
public class MockImageProcessingModel implements ImageProcessingModel {
  private HashMap<String, Image> loadMap;
  private StringBuilder log;

  /**
   * Constructing a MockImageProcessingModel object.
   *
   * @param log StringBuilder
   */
  public MockImageProcessingModel(StringBuilder log) {
    this.loadMap = new HashMap<>();

    Color[][] colorfulImage = {
            {new Color(123, 92, 23), new Color(25, 215, 205), new Color(215, 205, 21)},
            {new Color(0, 34, 1), new Color(0, 252, 50), new Color(20, 50, 250)},
            {new Color(111, 16, 100), new Color(16, 200, 15), new Color(11, 240, 50)},
            {new Color(255, 0, 0), new Color(0, 255, 0), new Color(111, 24, 5)},
            {new Color(2, 90, 195), new Color(100, 60, 0), new Color(80, 20, 100)}};

    loadMap.put("colorful", new PixelImage(colorfulImage));
    this.log = log;
  }

  /**
   * Load an image from a file path to be used internally within the model.
   *
   * @param imageName the name of the image
   * @param image     the image will be stored in the model.
   * @throws IllegalArgumentException if the given parameters are null.
   */
  @Override
  public void load(String imageName, Image image) throws IllegalArgumentException {
    log.append(String.format("The image was processed in the controller, " +
            "and the following imageName: %s was passed to the model, " +
            "along with an Image that contains the following color mapping:\n", imageName));
    for (int row = 0; row < image.getImageHeight(); row++) {
      for (int col = 0; col < image.getImageWidth(); col++) {
        Color pixel = image.getPixelAt(row, col);
        log.append(pixel.getRed()).append(" ");
        log.append(pixel.getGreen()).append(" ");
        log.append(pixel.getBlue()).append(" ");
      }
    }
  }

  @Override
  public void brightenImage(int increment, String imageName, String desiredName)
          throws IllegalArgumentException {

    log.append(String.format("brightenImage method called with parameters: %d, %s, %s",
            increment, imageName, desiredName));
  }

  @Override
  public void displayGreyscale(String component, String imageName, String desiredName)
          throws IllegalArgumentException {
    log.append(String.format("displayGreyscale method called with parameters: %s, %s, %s",
            component, imageName, desiredName));
  }

  @Override
  public void flipImage(String axis, String imageName, String desiredName)
          throws IllegalArgumentException {
    log.append(String.format("flipImage method called with parameters: %s, %s, %s",
            axis, imageName, desiredName));
  }

  @Override
  public void filterImage(String filterType, String imageName, String desiredName)
          throws IllegalArgumentException {
    log.append(String.format("filterImage method called with parameters: %s, %s, %s",
            filterType, imageName, desiredName));
  }

  @Override
  public void transformImage(String transformType, String imageName, String desiredName)
          throws IllegalArgumentException {
    log.append(String.format("transformImage method called with parameters: %s, %s, %s",
            transformType, imageName, desiredName));
  }

  /**
   * Creates a histogram from an image with the a given imageName.
   *
   * @param imageName the name of the image that will be used to create a representative histogram.
   * @throws IllegalArgumentException if the imageName does not correspond to an image stored in the
   *                                  image processor.
   */
  @Override
  public List<List<Integer>> createHistogram(String imageName) throws IllegalArgumentException {
    return new ArrayList<>();
  }

  @Override
  public BufferedImage toBufferedImage(String imageName) {
    return null;
  }

  @Override
  public Image getImage(String imageName) throws IllegalArgumentException {
    return loadMap.get(imageName);
  }

  @Override
  public Color getPixelAt(String imageName, int row, int col) throws IllegalArgumentException {
    return loadMap.get(imageName).getPixelAt(row, col);
  }

  @Override
  public int getImageHeight(String imageName) throws IllegalArgumentException {
    if (imageName == null) {
      throw new IllegalArgumentException("The image name is null");
    }
    return loadMap.get(imageName).getImageHeight();

  }

  @Override
  public int getImageWidth(String imageName) throws IllegalArgumentException {
    return loadMap.get(imageName).getImageWidth();
  }
}
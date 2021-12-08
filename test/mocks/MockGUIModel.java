package mocks;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.ImageProcessingModel;
import util.image.Image;
import util.image.PixelImage;
import util.seed.PixelSeed;

/**
 * Mock model class utilized for testing.
 */
public class MockGUIModel implements ImageProcessingModel {

  private HashMap<String, Image> loadMap;
  private StringBuilder log;

  /**
   * Constructing a MockImageProcessingModel object.
   *
   * @param log StringBuilder
   */
  public MockGUIModel(StringBuilder log) {
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
    loadMap.put(imageName, image);
    log.append(String.format("The image was processed in the controller, "
        +
        "and the following imageName: %s was passed to the model, "
        +
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
    Color[][] brightened = getImage(imageName).brightenImage(increment);
    loadMap.put(desiredName, new PixelImage(brightened));

    log.append(String.format("brightenImage method called with parameters: %d, %s, %s",
        increment, imageName, desiredName));
  }

  @Override
  public void mosaicImage(String imageName, String desiredName, int seedNum) {
    Image start = getImage(imageName);
    PixelSeed cur = new PixelSeed(seedNum, (PixelImage) start);
    cur.createSeed();
    Color[][] fin = cur.createSeedImage();

    loadMap.put(desiredName, new PixelImage(fin));

    log.append(String.format("mosaicImage method called with parameters: %s, %s, %d",
            imageName, desiredName, seedNum));
  }

  @Override
  public void displayGreyscale(String component, String imageName, String desiredName)
      throws IllegalArgumentException {
    if (component == null) {
      throw new IllegalArgumentException("component cannot be null.");
    }
    if (!(component.equals("red") || component.equals("green")
        || component.equals("blue")
        || component.equals("value")
        || component.equals("intensity")
        || component.equals("luma"))) {
      throw new IllegalArgumentException("The given component is invalid.");
    }
    Color[][] greyscale = getImage(imageName).displayGreyscale(component);
    loadMap.put(desiredName, new PixelImage(greyscale));

    log.append(String.format("displayGreyscale method called with parameters: %s, %s, %s",
        component, imageName, desiredName));
  }

  @Override
  public void flipImage(String axis, String imageName, String desiredName)
      throws IllegalArgumentException {
    if (axis == null) {
      throw new IllegalArgumentException("axis cannot be null.");
    }
    if (!(axis.equals("horizontal") || axis.equals("vertical"))) {
      throw new IllegalArgumentException("The given axis was invalid.");
    }
    Color[][] flippedImage = getImage(imageName).flipImage(axis);
    loadMap.put(desiredName, new PixelImage(flippedImage));
    log.append(String.format("flipImage method called with parameters: %s, %s, %s",
        axis, imageName, desiredName));
  }

  @Override
  public void filterImage(String filterType, String imageName, String desiredName)
      throws IllegalArgumentException {
    if (!(filterType.equals("blur") || filterType.equals("sharpen"))) {
      throw new IllegalArgumentException("Invalid filter type entered.");
    }
    Color[][] filtered = getImage(imageName).filterImage(filterType);
    loadMap.put(desiredName, new PixelImage(filtered));

    log.append(String.format("filterImage method called with parameters: %s, %s, %s",
        filterType, imageName, desiredName));
  }

  @Override
  public void transformImage(String transformType, String imageName, String desiredName)
      throws IllegalArgumentException {
    if (!transformType.equals("greyscale") && !transformType.equals("sepia")) {
      throw new IllegalArgumentException("The given transformation type was invalid.");
    }
    Color[][] transformed = getImage(imageName).transformImage(transformType);
    loadMap.put(desiredName, new PixelImage(transformed));

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
    int i;
    int j;
    int height = getImageHeight(imageName);
    int width = getImageWidth(imageName);
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (i = 0; i < width; i++) {
      for (j = 0; j < height; j++) {
        int pixel = (getPixelAt(imageName, j, i).getRed() << 16)
            | (getPixelAt(imageName, j, i).getGreen() << 8)
            | (getPixelAt(imageName, j, i).getBlue());
        bufferedImage.setRGB(i, j, pixel);
      }
    }
    return bufferedImage;
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
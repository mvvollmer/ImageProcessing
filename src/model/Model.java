package model;

import java.awt.Color;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.image.Image;
import util.image.PixelImage;
import util.seed.PixelSeed;

/**
 * Representing an ImageProcessingModel that contains the versions of an Image and their supporting
 * operations.
 */
public class Model implements ImageProcessingModel {

  private Map<String, Image> loadMap;

  /**
   * Instantiates a Map of Image-names and Images that stores images. Images can be retrieved by
   * their names.
   */
  public Model() {
    this.loadMap = new HashMap<>();
  }

  //Getters
  @Override
  public Color getPixelAt(String imageName, int row, int col) throws IllegalArgumentException {
    return getImage(imageName).getPixelAt(row, col);
  }

  @Override
  public int getImageHeight(String imageName) throws IllegalArgumentException {
    return getImage(imageName).getImageHeight();
  }

  @Override
  public int getImageWidth(String imageName) throws IllegalArgumentException {
    return getImage(imageName).getImageWidth();
  }

  @Override
  public void load(String imageName, Image image) throws IllegalArgumentException {
    if (imageName == null || image == null) {
      throw new IllegalArgumentException("The imageName and/or image are null");
    }
    loadMap.put(imageName, image);
  }

  @Override
  public Image getImage(String imageName) throws IllegalArgumentException {
    if (!(loadMap.containsKey(imageName)) || loadMap.get(imageName) == null) {
      throw new IllegalArgumentException("Image name is not associated with an image.");
    }
    return loadMap.get(imageName);
  }

  //Functionality
  @Override
  public void brightenImage(int increment, String imageName, String desiredName)
      throws IllegalArgumentException {
    validateNames(imageName, desiredName);

    Color[][] brightened = getImage(imageName).brightenImage(increment);
    load(desiredName, new PixelImage(brightened));
  }

  @Override
  public void displayGreyscale(String component, String imageName, String desiredName)
      throws IllegalArgumentException {
    validateNames(imageName, desiredName);
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
    load(desiredName, new PixelImage(greyscale));
  }

  @Override
  public void flipImage(String axis, String imageName, String desiredName)
      throws IllegalArgumentException {
    validateNames(imageName, desiredName);
    if (axis == null) {
      throw new IllegalArgumentException("axis cannot be null.");
    }
    if (!(axis.equals("horizontal") || axis.equals("vertical"))) {
      throw new IllegalArgumentException("The given axis was invalid.");
    }
    Color[][] flippedImage = getImage(imageName).flipImage(axis);
    load(desiredName, new PixelImage(flippedImage));
  }

  @Override
  public void filterImage(String filterType, String imageName, String desiredName)
      throws IllegalArgumentException {
    validateNames(imageName, desiredName);
    if (!(filterType.equals("blur") || filterType.equals("sharpen"))) {
      throw new IllegalArgumentException("Invalid filter type entered.");
    }
    Color[][] filtered = getImage(imageName).filterImage(filterType);
    load(desiredName, new PixelImage(filtered));
  }

  @Override
  public void transformImage(String transformType, String imageName, String desiredName)
      throws IllegalArgumentException {
    validateNames(imageName, desiredName);
    if (!transformType.equals("greyscale") && !transformType.equals("sepia")) {
      throw new IllegalArgumentException("The given transformation type was invalid.");
    }
    Color[][] transformed = getImage(imageName).transformImage(transformType);
    load(desiredName, new PixelImage(transformed));
  }

  @Override
  public List<List<Integer>> createHistogram(String imageName) throws IllegalArgumentException {
    if (imageName == null) {
      throw new IllegalArgumentException("The given image name is null.");
    }
    if (!(loadMap.containsKey(imageName)) || loadMap.get(imageName) == null) {
      throw new IllegalArgumentException("The given image name isn't associated with an image.");
    }

    //Grab the Image, height, and width.
    Image image = loadMap.get(imageName);
    int height = getImageHeight(imageName);
    int width = getImageWidth(imageName);

    List<List<Integer>> frequencies = new ArrayList<>();

    List<Integer> frequencyOfRed = new ArrayList<>();
    List<Integer> frequencyOfGreen = new ArrayList<>();
    List<Integer> frequencyOfBlue = new ArrayList<>();
    List<Integer> frequencyOfIntensity = new ArrayList<>();

    for (int i = 0; i < 256; i++) {
      frequencyOfRed.add(0);
      frequencyOfGreen.add(0);
      frequencyOfBlue.add(0);
      frequencyOfIntensity.add(0);
    }
    //Iterate through all pixels and add their component values to the representative lists.
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int redComponent = image.getPixelAt(row, col).getRed();
        int greenComponent = image.getPixelAt(row, col).getGreen();
        int blueComponent = image.getPixelAt(row, col).getBlue();
        int intensity = (redComponent + greenComponent + blueComponent) / 3;

        frequencyOfRed.set(redComponent, frequencyOfRed.get(redComponent) + 1);
        frequencyOfGreen.set(greenComponent, frequencyOfGreen.get(greenComponent) + 1);
        frequencyOfBlue.set(blueComponent, frequencyOfBlue.get(blueComponent) + 1);
        frequencyOfIntensity.set(intensity, frequencyOfIntensity.get(intensity) + 1);
      }
    }

    frequencies.add(frequencyOfIntensity);
    frequencies.add(frequencyOfRed);
    frequencies.add(frequencyOfGreen);
    frequencies.add(frequencyOfBlue);
    return frequencies;
  }

  // Helper methods
  private void validateNames(String imageName, String desiredImage)
      throws IllegalArgumentException {
    if (imageName == null || desiredImage == null) {
      throw new IllegalArgumentException("The given image name or desired image name are null.");
    }
    if (!(loadMap.containsKey(imageName)) || loadMap.get(imageName) == null) {
      throw new IllegalArgumentException("The given image name isn't associated with an image.");
    }
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
  public void mosaicImage(String imageName, String desiredName, int seedNum) {
    validateNames(imageName, desiredName);
    Image start = getImage(imageName);
    PixelSeed cur = new PixelSeed(seedNum, (PixelImage) start);
    cur.createSeed();
    Color[][] fin = cur.createSeedImage();
    load(desiredName, new PixelImage(fin));
  }
}


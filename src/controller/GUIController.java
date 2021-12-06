package controller;

import controller.commands.ImageProcessingCommand;
import controller.commands.Load;
import controller.commands.Save;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import model.ImageProcessingModel;
import view.ImageProcessingGUIView;

/**
 * Class that represents a controller for a Graphic User Interface.
 */
public class GUIController implements Features {

  private final ImageProcessingModel model;
  private ImageProcessingGUIView view;
  private List<String> imageNames;
  private String desiredName;

  /**
   * Given a model for an Image processor, constructs a GUI that
   * passes information to the given model.
   * @param model the model that information from the controller will be passed to.
   */
  public GUIController(ImageProcessingModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model may not be null");
    }
    this.model = model;
    this.imageNames = new ArrayList<>();
  }

  @Override
  public void setView(ImageProcessingGUIView view) {
    if (view == null) {
      throw new IllegalArgumentException("View may not be null.");
    }
    this.view = view;
    this.view.addFeatures(this);
    view.displayMessage("Welcome! Please load an image using the Load New... button below.");
  }

  @Override
  public void load(String filePath) {
    ImageProcessingCommand load = new Load(filePath, "default");
    load.apply(this.model);
    this.imageNames.add("default");
    this.view.displayHistogram(model.createHistogram("default"));
  }

  // Getting most recent image in Map.
  private String getLatestImage() {
    return this.imageNames.get(imageNames.size() - 1);
  }

  // If no image is loaded, throw an error when a button is pressed.
  private boolean throwErrorIfNoImageLoaded() {
    if ((imageNames.size()) == 0) {
      view.displayMessage("Please load an image before performing an operation.");
      return false;
    }
    return true;
  }

  @Override
  public void save(String filePath) {
    throwErrorIfNoImageLoaded();
    ImageProcessingCommand save = new Save(filePath, getLatestImage());
    save.apply(this.model);
  }

  @Override
  public void exit() {
    System.exit(0);
  }

  @Override
  public void filter(String type) {
    throwErrorIfNoImageLoaded();
    desiredName = getLatestImage() + "-" + type;
    this.model.filterImage(type, getLatestImage(), desiredName);
    BufferedImage image = model.toBufferedImage(desiredName);
    List<List<Integer>> histogram = model.createHistogram(desiredName);
    this.view.displayImage(image);
    //this.view.displayHistogram(model.createHistogram(desiredName));
    this.view.displayHistogram(histogram);
    this.imageNames.add(desiredName);
  }

  @Override
  public void transform(String type) {
    throwErrorIfNoImageLoaded();
    desiredName = getLatestImage() + "-" + type;
    this.model.transformImage(type, getLatestImage(), desiredName);
    BufferedImage image = model.toBufferedImage(desiredName);
    List<List<Integer>> histogram = model.createHistogram(desiredName);
    this.view.displayImage(image);
    this.view.displayHistogram(histogram);
    this.imageNames.add(desiredName);
  }

  @Override
  public void brighten(int increment) {
    throwErrorIfNoImageLoaded();
    desiredName = getLatestImage() + "-" + ("brightened-by-" + increment);
    this.model.brightenImage(increment, getLatestImage(), desiredName);
    BufferedImage image = model.toBufferedImage(desiredName);
    List<List<Integer>> histogram = model.createHistogram(desiredName);
    this.view.displayImage(image);
    this.view.displayHistogram(histogram);
    this.imageNames.add(desiredName);
  }

  @Override
  public void flip(String axis) {
    throwErrorIfNoImageLoaded();
    desiredName = getLatestImage() + "-" + axis;
    this.model.flipImage(axis, getLatestImage(), desiredName);
    BufferedImage image = model.toBufferedImage(desiredName);
    List<List<Integer>> histogram = model.createHistogram(desiredName);
    this.view.displayImage(image);
    this.view.displayHistogram(histogram);
    this.imageNames.add(desiredName);
  }

  @Override
  public void component(String type) {
    throwErrorIfNoImageLoaded();
    desiredName = getLatestImage() + "-" + type;
    this.model.displayGreyscale(type, getLatestImage(), desiredName);
    BufferedImage image = model.toBufferedImage(desiredName);
    List<List<Integer>> histogram = model.createHistogram(desiredName);
    this.view.displayImage(image);
    this.view.displayHistogram(histogram);
    this.imageNames.add(desiredName);
  }

  @Override
  public void mosaic(int numSeed) {
    throwErrorIfNoImageLoaded();
    desiredName = getLatestImage() + "-" + ("mosaiced-with-" + numSeed + "-seeds");
    this.model.mosaicImage(getLatestImage(), desiredName, numSeed);
    BufferedImage image = model.toBufferedImage(desiredName);
    List<List<Integer>> histogram = model.createHistogram(desiredName);
    this.view.displayImage(image);
    this.view.displayHistogram(histogram);
    this.imageNames.add(desiredName);
  }
}
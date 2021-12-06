package controller;

import view.ImageProcessingGUIView;

/**
 * Representing the features that our graphical user interface supports.
 */
public interface Features {

  /**
   * Given a file path, will create a load function object that
   * will apply the load method in the model.
   * The given filepath will be ued to retrieve a supported image file,
   * and load the image to the program.
   *
   * @param filePath a string that represents the filepath used to retrieve an imagefile.
   */
  void load(String filePath);

  /**
   * Given a view, sets the view of a controller. the features that this view
   * can react accordingly to, will be instantiated as well.
   * @param view the view that will be associated with this controller.
   */
  void setView(ImageProcessingGUIView view);

  /**
   * Given a desired filepath, will create a save function object that will apply
   * the save method in the model.
   * The filepath will be used as a save location for an image.
   *
   * @param filePath a string that represents the filepath that an image will be saved to.
   */
  void save(String filePath);

  /**
   * Creates a quit function object that will allow the user to exit the program.
   */
  void exit();

  /**
   * Given a filter type, will create a function object that will be passed to the model in
   * order to apply a filter
   * to an image.
   * @param type the type of filter that will be applied to an image.
   */
  void filter(String type);

  /**
   * Given a transformation type, will create a function object that will be passed to the
   * model in order to apply
   * a transformation to an image.
   *
   * @param type the type of transformation that will be applied to an image.
   */
  void transform(String type);

  /**
   * Given an integer that represents the increment that an image will be brightened to,
   * creates a brighten function
   * object that will be passed to the model in order to brighten an image.
   * @param increment the integer value that an image will be brightened
   */
  void brighten(int increment);

  /**
   * Given an axis, creates a function object that will be passed to the model in order to flip
   * an image across the
   * specified axis.
   * @param axis the axis that an image will be flipped across.
   */
  void flip(String axis);

  /**
   * Given a component type, will create a function object that will produce the greyscale version
   * of an image within
   * the model.
   * @param type the type of component of an pixel's color that
   *            will be used to create a greyscale image.
   */
  void component(String type);

  /**
   * Given a number of seeds, creates a Mosaic of the Image.
   * @param numSeed number of seeds used.
   */
  void mosaic(int numSeed);
}

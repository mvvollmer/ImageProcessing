package util;

import controller.Controller;
import controller.GUIController;
import controller.ImageProcessingController;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

import model.ImageProcessingModel;
import model.Model;
import view.GUIView;
import view.ImageProcessingGUIView;
import view.ImageProcessingView;
import view.View;

/**
 * Class utilized for running the ImageProcessingProgram.
 */
public final class ImageProcessingProgram {

  /**
   * Main method used to instantiate the class objects and run the controller's processImage()
   * method.
   *
   * @param args user inputs
   */
  public static void main(String[] args) {

    Readable read = null;
    Appendable appendable = new StringBuilder();
    ImageProcessingModel model = new Model();
    ImageProcessingView textView = new View(appendable);
    ImageProcessingGUIView guiView = new GUIView();

    if (args.length == 2) {
      if (args[0].equals("-file")) {
        try {
          read = new FileReader(args[1]);
        } catch (FileNotFoundException e) {
          throw new IllegalArgumentException("Invalid file.");
        }
        ImageProcessingController controller = new Controller(model, textView, read);
        controller.processImage();
      }
    }
    if (args.length == 1) {
      if (args[0].equals("-text")) {
        read = new InputStreamReader(System.in);
        ImageProcessingController controller = new Controller(model, textView, read);
        controller.processImage();
      }
    }
    if (args.length == 0) {
      GUIController controller = new GUIController(model);
      controller.setView(guiView);
    }
    else {
      System.out.print("Invalid command entered.");
      System.exit(-1);
    }
  }
}

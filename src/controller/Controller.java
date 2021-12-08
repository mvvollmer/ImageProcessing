package controller;

import controller.commands.Brighten;
import controller.commands.Component;
import controller.commands.Filter;
import controller.commands.Flip;
import controller.commands.ImageProcessingCommand;
import controller.commands.Load;
import controller.commands.Mosaic;
import controller.commands.Quit;
import controller.commands.Save;
import controller.commands.Transformation;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import model.ImageProcessingModel;
import view.ImageProcessingView;

/**
 * Representing an implementation of the ImageProcessorController interface,
 * utilized for delegating operations to the model/view based on user input.
 */
public class Controller implements ImageProcessingController {

  private final ImageProcessingModel model;
  //private final ImageProcessingView view;
  private final Readable input;

  /**
   * Constructs a Controller object.
   *
   * @param model ImageProcessingModel
   * @param view  ImageProcessingView
   * @param input Readable
   * @throws IllegalArgumentException if any fields are null.
   */
  public Controller(ImageProcessingModel model, ImageProcessingView view, Readable input)
          throws IllegalArgumentException {

    if (model == null || view == null || input == null) {
      throw new IllegalArgumentException("Inputs may not be null.");
    }

    this.model = model;
    //this.view = view;
    this.input = input;

  }

  @Override
  public void processImage() throws IllegalArgumentException {

    Scanner scan = new Scanner(this.input);

    Map<String, Function<Scanner, ImageProcessingCommand>> knownCommands;

    knownCommands = new HashMap<>();
    knownCommands.put("load", s -> new Load(s.next(), s.next()));
    knownCommands.put("save", s -> new Save(s.next(), s.next()));
    knownCommands.put("brighten", s -> new Brighten(s.nextInt(), s.next(), s.next()));
    knownCommands.put("horizontal-flip", s -> new Flip("horizontal", s.next(), s.next()));
    knownCommands.put("vertical-flip", s -> new Flip("vertical", s.next(), s.next()));
    knownCommands.put("value-component", s -> new Component("value", s.next(), s.next()));
    knownCommands.put("intensity-component", s -> new Component("intensity", s.next(), s.next()));
    knownCommands.put("luma-component", s -> new Component("luma", s.next(), s.next()));
    knownCommands.put("red-component", s -> new Component("red", s.next(), s.next()));
    knownCommands.put("green-component", s -> new Component("green", s.next(), s.next()));
    knownCommands.put("blue-component", s -> new Component("blue", s.next(), s.next()));
    knownCommands.put("blur", s -> new Filter("blur", s.next(), s.next()));
    knownCommands.put("sharpen", s -> new Filter("sharpen", s.next(), s.next()));
    knownCommands.put("greyscale", s -> new Transformation("greyscale", s.next(), s.next()));
    knownCommands.put("sepia", s -> new Transformation("sepia", s.next(), s.next()));
    knownCommands.put("mosaic", s -> new Mosaic(s.nextInt(), s.next(), s.next()));
    knownCommands.put("q", s -> new Quit());
    knownCommands.put("Q", s -> new Quit());
    knownCommands.put("Quit", s -> new Quit());
    knownCommands.put("quit", s -> new Quit());

    while (scan.hasNext()) {
      ImageProcessingCommand c;
      String in = scan.next();
      Function<Scanner, ImageProcessingCommand> cmd =
              knownCommands.getOrDefault(in, null);

      if (cmd == null) {
        throw new IllegalArgumentException("Invalid command entered.");
      } else {
        c = cmd.apply(scan);
        c.apply(this.model);
      }
    }
  }
}

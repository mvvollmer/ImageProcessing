package mocks;

import controller.Features;
import view.ImageProcessingGUIView;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Mock GUI View Class.
 */
public class MockGUIView implements ImageProcessingGUIView {

  private StringBuilder log;

  /**
   * Constructing a MockGUIView object.
   *
   * @param log StringBuilder
   */
  public MockGUIView(StringBuilder log) {
    this.log = log;
  }


  @Override
  public void displayImage(BufferedImage image) {
    log.append(String.format("displayImage method called."));
  }

  @Override
  public void displayHistogram(List<List<Integer>> histogram) {
    int numberOfList = histogram.size();
    int numberOfItemsInLists = 0;

    for (List<Integer> list : histogram) {
      for (Integer i : list) {
        numberOfItemsInLists++;
      }
    }
    log.append(String.format("Number Lists: " + numberOfList + "\n"
        + "NumberOfItems: " + numberOfItemsInLists));
  }

  @Override
  public void addFeatures(Features features) {
    log.append(String.format("Features have been added to the GUI."));
  }

  @Override
  public void displayMessage(String message) {
    log.append(String.format(message));
  }
}

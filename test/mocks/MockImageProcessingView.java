package mocks;

import view.ImageProcessingView;

/**
 * Mock View utilized for testing.
 */
public class MockImageProcessingView implements ImageProcessingView {

  private StringBuilder log;

  /**
   * Constructing a MockImageProcessingView object.
   *
   * @param log StringBuilder
   */
  public MockImageProcessingView(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void displayImage() {
    log.append(String.format("displayImage method called."));
  }

  @Override
  public void renderMessage(String message) {
    log.append(String.format("renderMessage method called with message: %s", message));
  }
}

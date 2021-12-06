package view;

import java.io.IOException;

/**
 * View class utilized for displaying Images and messages within the program.
 */
public class View implements ImageProcessingView {

  private Appendable appendable;

  /**
   * Constructs a View object.
   *
   * @param appendable an Appendable object that carries Strings.
   * @throws IllegalArgumentException if the input is null.
   */
  public View(Appendable appendable) throws IllegalArgumentException {
    if (appendable == null) {
      throw new IllegalArgumentException("Inputs cannot be null.");
    }
    this.appendable = appendable;
  }

  /**
   * Constructs a View object and defaults the Appendable to System.out.
   */
  public View() {
    this.appendable = System.out;
  }

  @Override
  public void displayImage() {
    // NOT NEEDED YET
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.appendable.append(message);
  }

}

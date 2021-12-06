import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import view.ImageProcessingView;
import view.View;

/**
 * Class utilized for testing the ImageProcessingView.
 */
public class ImageProcessingViewTest {

  Appendable append = new StringBuilder();
  ImageProcessingView view = new View(append);

  @Test(expected = IllegalArgumentException.class)
  public void constTest1() {
    new View(null);
  }

  @Test
  public void renderMessageTest() {
    Appendable a = new StringBuilder();

    try {
      a.append("testing");
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      view.renderMessage("testing");
    } catch (IOException e) {
      e.printStackTrace();
    }

    assertEquals(a.toString(), append.toString());
  }

}

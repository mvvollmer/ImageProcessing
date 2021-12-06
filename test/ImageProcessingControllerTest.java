import static org.junit.Assert.assertEquals;

import controller.Controller;
import controller.ImageProcessingController;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import javax.imageio.ImageIO;
import mocks.MockImageProcessingModel;
import mocks.MockImageProcessingView;
import model.ImageProcessingModel;
import model.Model;

import org.junit.Test;


import view.ImageProcessingView;
import view.View;

/**
 * Class utilized for testing the ImageProcessingController.
 */
public class ImageProcessingControllerTest {

  ImageProcessingModel model = new Model();
  Appendable appendable = new StringBuilder();
  ImageProcessingView view = new View(appendable);
  Readable readable = new StringReader("");
  ImageProcessingController controller = new Controller(model, view, readable);
  ImageProcessingModel mockModel = new MockImageProcessingModel(new StringBuilder());
  ImageProcessingView mockView = new MockImageProcessingView(new StringBuilder());

  // Testing invalid constructors
  @Test(expected = IllegalArgumentException.class)
  public void testConst1() {
    ImageProcessingController c = new Controller(model, view, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConst2() {
    ImageProcessingController c = new Controller(null, view, readable);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConst3() {
    ImageProcessingController c = new Controller(model, null, readable);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCommand1() {
    Readable read = new StringReader("loading Koala.ppm koala");
    ImageProcessingController c = new Controller(mockModel, mockView, read);
    c.processImage();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCommand2() {
    Readable read = new StringReader("Koala.ppm koala load");
    ImageProcessingController c = new Controller(mockModel, mockView, read);
    c.processImage();
  }

  // Testing load command
  @Test
  public void testLoad() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockImageProcessingModel(mLog);
    ImageProcessingView mockView = new MockImageProcessingView(vLog);
    Readable read = new StringReader("load images/dumby.ppm dumby");
    ImageProcessingController c = new Controller(mockModel, mockView, read);
    c.processImage();

    String message = "The image was processed in the controller,"
        +
        " and the following imageName: dumby "
        +
        "was passed to the model, along with an Image that contains the following "
        +
        "color mapping:\n"
        + "0 0 0 255 255 255 255 255 255 "
        + "5 5 5 250 250 250 250 250 250 "
        + "100 100 100 100 100 100 100 100 100 "
        + "250 250 250 5 5 5 5 5 5 "
        + "255 255 255 0 0 0 0 0 0 ";

    assertEquals(message, mLog.toString());
  }

  // Testing load command
  @Test
  public void testLoadJPEG() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockImageProcessingModel(mLog);
    ImageProcessingView mockView = new MockImageProcessingView(vLog);
    Readable read = new StringReader("load images/colorful.jpeg colorful");
    ImageProcessingController c = new Controller(mockModel, mockView, read);
    c.processImage();

    String message = "The image was processed in the controller,"
        +
        " and the following imageName: colorful "
        +
        "was passed to the model, along with an Image that contains the following "
        +
        "color mapping:\n"
        + "47 108 75 137 198 165 157 173 170 "
        + "0 52 19 94 155 122 61 77 74 "
        + "0 110 0 58 177 33 112 186 71 "
        + "6 125 0 79 198 54 12 86 0 "
        + "87 70 114 67 50 94 95 31 105 ";

    assertEquals(message, mLog.toString());
  }

  // Testing save command
  @Test
  public void testSavetoPPM() throws IOException {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockImageProcessingModel(mLog);
    ImageProcessingView mockView = new MockImageProcessingView(vLog);
    Readable read = new StringReader("save images/colorful.ppm colorful");
    ImageProcessingController c = new Controller(mockModel, mockView, read);
    c.processImage();

    String expected =
        "P3\n3 5\n255\n123\n92\n23\n25\n215\n205\n215\n205\n21\n0\n34\n1\n0\n252\n50\n20\n"
            + "50\n250\n111\n16\n100\n16\n200\n15\n11\n240\n50\n255\n0\n0\n0\n255\n"
            + "0\n111\n24\n5\n2\n90\n195\n100\n60\n0\n80\n20\n100";

    BufferedReader reader = new BufferedReader(new FileReader("images/colorful.ppm"));
    StringBuilder sb = new StringBuilder();
    String line;
    String ls = System.getProperty("line.separator");
    while ((line = reader.readLine()) != null) {
      sb.append(line);
      sb.append(ls);
    }

    sb.deleteCharAt(sb.length() - 1);
    reader.close();

    assertEquals(expected, sb.toString());
  }

  // Testing save command
  @Test
  public void testSavetoBufferedImageFile() throws IOException {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockImageProcessingModel(mLog);
    ImageProcessingView mockView = new MockImageProcessingView(vLog);
    Readable read = new StringReader("save images/colorful.jpg colorful");
    ImageProcessingController c = new Controller(mockModel, mockView, read);
    c.processImage();

    BufferedImage image = ImageIO.read(new File("images/colorful.jpg"));

    assertEquals(mockModel.getImageHeight("colorful"), image.getHeight());
    assertEquals(mockModel.getImageWidth("colorful"), image.getWidth());
  }

  // Testing brighten command
  @Test
  public void testBrighten() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockImageProcessingModel(mLog);
    ImageProcessingView mockView = new MockImageProcessingView(vLog);
    Readable read = new StringReader("brighten 10 koala koala-brighten");
    ImageProcessingController c = new Controller(mockModel, mockView, read);
    c.processImage();

    String message = "brightenImage method called with parameters: 10, koala, koala-brighten";

    assertEquals(message, mLog.toString());
  }

  // Testing horizontal-flip command
  @Test
  public void testHorizontalFlip() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockImageProcessingModel(mLog);
    ImageProcessingView mockView = new MockImageProcessingView(vLog);
    Readable read = new StringReader("horizontal-flip koala koala-horizontal");
    ImageProcessingController c = new Controller(mockModel, mockView, read);
    c.processImage();

    String message = "flipImage method called with parameters: horizontal, koala, koala-horizontal";

    assertEquals(message, mLog.toString());
  }

  // Testing vertical-flip command
  @Test
  public void testVerticalFlip() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockImageProcessingModel(mLog);
    ImageProcessingView mockView = new MockImageProcessingView(vLog);
    Readable read = new StringReader("vertical-flip koala koala-vertical");
    ImageProcessingController c = new Controller(mockModel, mockView, read);
    c.processImage();

    String message = "flipImage method called with parameters: vertical, koala, koala-vertical";

    assertEquals(message, mLog.toString());
  }

  // Testing value-component command
  @Test
  public void testValueComponent() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockImageProcessingModel(mLog);
    ImageProcessingView mockView = new MockImageProcessingView(vLog);
    Readable read = new StringReader("value-component koala koala-value");
    ImageProcessingController c = new Controller(mockModel, mockView, read);
    c.processImage();

    String message = "displayGreyscale method called with parameters: value, koala, koala-value";

    assertEquals(message, mLog.toString());
  }

  // Testing intensity-component command
  @Test
  public void testIntensityComponent() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockImageProcessingModel(mLog);
    ImageProcessingView mockView = new MockImageProcessingView(vLog);
    Readable read = new StringReader("intensity-component koala koala-intensity");
    ImageProcessingController c = new Controller(mockModel, mockView, read);
    c.processImage();

    String message =
        "displayGreyscale method called with parameters: intensity, koala, koala-intensity";

    assertEquals(message, mLog.toString());

  }

  // Testing luma-component command
  @Test
  public void testLumaComponent() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockImageProcessingModel(mLog);
    ImageProcessingView mockView = new MockImageProcessingView(vLog);
    Readable read = new StringReader("luma-component koala koala-luma");
    ImageProcessingController c = new Controller(mockModel, mockView, read);
    c.processImage();

    String message = "displayGreyscale method called with parameters: luma, koala, koala-luma";

    assertEquals(message, mLog.toString());
  }

  // Testing red-component command
  @Test
  public void testRedComponent() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockImageProcessingModel(mLog);
    ImageProcessingView mockView = new MockImageProcessingView(vLog);
    Readable read = new StringReader("red-component koala koala-red");
    ImageProcessingController c = new Controller(mockModel, mockView, read);
    c.processImage();

    String message = "displayGreyscale method called with parameters: red, koala, koala-red";

    assertEquals(message, mLog.toString());

  }

  // Testing green-component command
  @Test
  public void testGreenComponent() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockImageProcessingModel(mLog);
    ImageProcessingView mockView = new MockImageProcessingView(vLog);
    Readable read = new StringReader("green-component koala koala-green");
    ImageProcessingController c = new Controller(mockModel, mockView, read);
    c.processImage();

    String message = "displayGreyscale method called with parameters: green, koala, koala-green";

    assertEquals(message, mLog.toString());
  }

  // Testing blue-component command
  @Test
  public void testBlueComponent() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockImageProcessingModel(mLog);
    ImageProcessingView mockView = new MockImageProcessingView(vLog);
    Readable read = new StringReader("blue-component koala koala-blue");
    ImageProcessingController c = new Controller(mockModel, mockView, read);
    c.processImage();

    String message = "displayGreyscale method called with parameters: blue, koala, koala-blue";

    assertEquals(message, mLog.toString());

  }

  // Testing sharpen command
  @Test
  public void testSharpenType() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockImageProcessingModel(mLog);
    ImageProcessingView mockView = new MockImageProcessingView(vLog);
    Readable read = new StringReader("sharpen koala koala-sharpen");
    ImageProcessingController c = new Controller(mockModel, mockView, read);
    c.processImage();

    String message = "filterImage method called with parameters: sharpen, koala, koala-sharpen";

    assertEquals(message, mLog.toString());
  }

  // Testing sharpen command
  @Test
  public void testBlurType() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockImageProcessingModel(mLog);
    ImageProcessingView mockView = new MockImageProcessingView(vLog);
    Readable read = new StringReader("blur koala koala-blur");
    ImageProcessingController c = new Controller(mockModel, mockView, read);
    c.processImage();

    String message = "filterImage method called with parameters: blur, koala, koala-blur";

    assertEquals(message, mLog.toString());
  }

  // Testing sharpen command
  @Test
  public void testSepiaType() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockImageProcessingModel(mLog);
    ImageProcessingView mockView = new MockImageProcessingView(vLog);
    Readable read = new StringReader("sepia koala koala-sepia");
    ImageProcessingController c = new Controller(mockModel, mockView, read);
    c.processImage();

    String message = "transformImage method called with parameters: sepia, koala, koala-sepia";

    assertEquals(message, mLog.toString());
  }

  @Test
  public void testGreyscaleType() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockImageProcessingModel(mLog);
    ImageProcessingView mockView = new MockImageProcessingView(vLog);
    Readable read = new StringReader("greyscale koala koala-greyscale");
    ImageProcessingController c = new Controller(mockModel, mockView, read);
    c.processImage();

    String message = "transformImage method called with parameters: "
        + "greyscale, koala, koala-greyscale";

    assertEquals(message, mLog.toString());
  }

}

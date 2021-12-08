import controller.Controller;
import controller.Features;
import controller.GUIController;
import controller.ImageProcessingController;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import mocks.MockGUIModel;
import mocks.MockGUIView;
import model.ImageProcessingModel;
import model.Model;
import org.junit.Test;
import view.ImageProcessingGUIView;
import view.ImageProcessingView;
import view.View;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import static org.junit.Assert.assertEquals;

/**
 * Class utilized for testing the GUIController.
 */
public class GUIControllerTest {

  ImageProcessingModel model = new Model();
  Appendable appendable = new StringBuilder();
  ImageProcessingView view = new View(appendable);
  Readable readable = new StringReader("");
  Features controller = new GUIController(model);
  ImageProcessingModel mockModel = new MockGUIModel(new StringBuilder());
  ImageProcessingGUIView mockView = new MockGUIView(new StringBuilder());

  // Testing invalid constructors
  @Test(expected = IllegalArgumentException.class)
  public void testConst1() {
    Features c = new GUIController(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConst3() {
    ImageProcessingController c = new Controller(model, null, readable);
  }

  // Null model
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCommand1() {
    Features c = new GUIController(null);
    c.setView(mockView);
    c.filter(null);
  }

  // Null view
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCommand2() {
    Features c = new GUIController(mockModel);
    c.setView(null);
  }

  // Testing load command
  @Test
  public void testLoad() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockGUIModel(mLog);
    ImageProcessingGUIView mockView = new MockGUIView(vLog);
    Features c = new GUIController(mockModel);
    c.setView(mockView);
    c.load("images/colorful.jpeg");

    String modelMessage = "The image was processed in the controller," +
        " and the following imageName: default " +
        "was passed to the model, along with an Image that contains the following " +
        "color mapping:\n"
        + "47 108 75 137 198 165 157 173 170 "
        + "0 52 19 94 155 122 61 77 74 0 110 "
        + "0 58 177 33 112 186 71 6 125 0 79 "
        + "198 54 12 86 0 87 70 114 67 50 94 95 31 105 ";
    String viewMessage = "Number Lists: " + 0 + "\n"
        + "NumberOfItems: " + 0;

    assertEquals(modelMessage, mLog.toString());
  }

  // Testing save command
  @Test
  public void testSavetoPPM() throws IOException {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockGUIModel(mLog);
    ImageProcessingGUIView mockView = new MockGUIView(vLog);
    Features c = new GUIController(mockModel);
    c.setView(mockView);
    c.load("images/colorful.jpeg");
    c.save("images/colorful.ppm");

    String expected =
        "P3\n3 5\n255\n47\n108\n75\n137\n198\n165\n157\n173\n170\n0\n52\n19\n"
            + "94\n155\n122\n61\n77\n74\n0\n110\n0\n58\n177\n33\n112\n186\n"
            + "71\n6\n125\n0\n79\n198\n54\n12\n86\n0\n87\n70\n114\n67\n50\n94\n95\n31\n105";

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
    ImageProcessingModel mockModel = new MockGUIModel(mLog);
    ImageProcessingGUIView mockView = new MockGUIView(vLog);
    Features c = new GUIController(mockModel);
    c.setView(mockView);
    c.load("images/colorful.jpeg");
    c.save("images/colorful.jpg");
    BufferedImage image = ImageIO.read(new File("images/colorful.jpg"));

    assertEquals(mockModel.getImageHeight("colorful"), image.getHeight());
    assertEquals(mockModel.getImageWidth("colorful"), image.getWidth());
  }

  // Testing brighten command
  @Test
  public void testBrighten() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockGUIModel(mLog);
    ImageProcessingGUIView mockView = new MockGUIView(vLog);
    Features c = new GUIController(mockModel);
    c.setView(mockView);
    c.load("images/colorful.jpeg");

    c.brighten(10);

    String message = "The image was processed in the controller, and the following imageName: "
        + "default was passed to the model, along with an Image that contains the following color"
        + " mapping:\n"
        + "47 108 75 137 198 165 157 173 170 0 52 19 94 155 122 61 77 74 0 110 0 58 177 33"
        + " 112 186 71 "
        + "6 125 0 79 198 54 12 86 0 87 70 114 67 50 94 95 31 105 brightenImage method called"
        + " with parameters:"
        + " 10, default, default-brightened-by-10";

    assertEquals(message, mLog.toString());
  }

  // Testing mosaic command
  @Test
  public void testMosaic() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockGUIModel(mLog);
    ImageProcessingGUIView mockView = new MockGUIView(vLog);
    Features c = new GUIController(mockModel);
    c.setView(mockView);
    c.load("images/colorful.jpeg");

    c.mosaic(10);

    String message = "The image was processed in the controller, and the following imageName: "
            + "default was passed to the model, along with an Image that contains the "
            + "following color mapping:\n"
            + "47 108 75 137 198 165 157 173 170 0 52 19 94 155 122 61 77 74 0 110 0 58 177 33"
            + " 112 186 71 "
            + "6 125 0 79 198 54 12 86 0 87 70 114 67 50 94 95 31 105 brightenImage method called"
            + " with parameters:"
            + " 10, default, default-mosaiced-with-10-seeds";

    assertEquals(message, mLog.toString());
  }

  // Testing horizontal-flip command
  @Test
  public void testHorizontalFlip() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockGUIModel(mLog);
    ImageProcessingGUIView mockView = new MockGUIView(vLog);
    Features c = new GUIController(mockModel);
    c.setView(mockView);
    c.load("images/colorful.jpeg");
    c.flip("horizontal");
    String message = "The image was processed in the controller, and the following imageName:"
        + " default was passed to the model, along with an Image that contains the following "
        + "color mapping:\n47 108 75 137 198 165 157 173 170 0 52 19 94 155 122 61 77 74 0 110"
        + " 0 58 177 33 112 186 71 6 125 0 79 198 54 12 86 0 87 70 114 67 50 94 95 31 105"
        + " flipImage"
        +
        " method called with parameters: horizontal, default, default-horizontal";

    assertEquals(message, mLog.toString());
  }

  // Testing vertical-flip command
  @Test
  public void testVerticalFlip() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockGUIModel(mLog);
    ImageProcessingGUIView mockView = new MockGUIView(vLog);
    Features c = new GUIController(mockModel);
    c.setView(mockView);
    c.load("images/colorful.jpeg");
    c.flip("vertical");
    String message = "The image was processed in the controller, and the following imageName:"
        + " default was passed to the model, along with an Image that contains the following "
        + "color mapping:\n47 108 75 137 198 165 157 173 170 0 52 19 94 155 122 61 77 74 0 110"
        + " 0 58 177 33 112 186 71 6 125 0 79 198 54 12 86 0 87 70 114 67 50 94 95 31 105"
        + " flipImage"
        +
        " method called with parameters: vertical, default, default-vertical";

    assertEquals(message, mLog.toString());
  }

  // Testing value-component command
  @Test
  public void testValueComponent() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockGUIModel(mLog);
    ImageProcessingGUIView mockView = new MockGUIView(vLog);
    Features c = new GUIController(mockModel);
    c.setView(mockView);
    c.load("images/colorful.jpeg");
    c.component("value");
    String message = "The image was processed in the controller, and the following imageName: " +
        "default was passed to the model, along with an Image that contains the following"
        + " color mapping:\n"
        +
        "47 108 75 137 198 165 157 173 170 0 52 19 94 155 122 61 77 74 0 110 0 58 177 33"
        + " 112 186 71"
        +
        " 6 125 0 79 198 54 12 86 0 87 70 114 67 50 94 95 31 105 displayGreyscale method called" +
        " with parameters: value, default, default-value";

    assertEquals(message, mLog.toString());
  }

  // Testing intensity-component command
  @Test
  public void testIntensityComponent() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockGUIModel(mLog);
    ImageProcessingGUIView mockView = new MockGUIView(vLog);
    Features c = new GUIController(mockModel);
    c.setView(mockView);
    c.load("images/colorful.jpeg");
    c.component("intensity");
    String message = "The image was processed in the controller, and the following imageName: " +
        "default was passed to the model, along with an Image that contains the following"
        + " color mapping:\n"
        +
        "47 108 75 137 198 165 157 173 170 0 52 19 94 155 122 61 77 74 0 110 0 58 177 33 "
        + "112 186 71"
        +
        " 6 125 0 79 198 54 12 86 0 87 70 114 67 50 94 95 31 105 displayGreyscale method called" +
        " with parameters: intensity, default, default-intensity";

    assertEquals(message, mLog.toString());
  }

  // Testing luma-component command
  @Test
  public void testLumaComponent() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockGUIModel(mLog);
    ImageProcessingGUIView mockView = new MockGUIView(vLog);
    Features c = new GUIController(mockModel);
    c.setView(mockView);
    c.load("images/colorful.jpeg");
    c.component("luma");
    String message = "The image was processed in the controller, and the following imageName: " +
        "default was passed to the model, along with an Image that contains the "
        + "following color mapping:\n"
        +
        "47 108 75 137 198 165 157 173 170 0 52 19 94 155 122 61 77 74 0 110 0 58 177 "
        + "33 112 186 71"
        +
        " 6 125 0 79 198 54 12 86 0 87 70 114 67 50 94 95 31 105 displayGreyscale method called" +
        " with parameters: luma, default, default-luma";

    assertEquals(message, mLog.toString());
  }

  // Testing red-component command
  @Test
  public void testRedComponent() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockGUIModel(mLog);
    ImageProcessingGUIView mockView = new MockGUIView(vLog);
    Features c = new GUIController(mockModel);
    c.setView(mockView);
    c.load("images/colorful.jpeg");
    c.component("red");
    String message = "The image was processed in the controller, and the following imageName: " +
        "default was passed to the model, along with an Image that contains the"
        + " following color mapping:\n"
        +
        "47 108 75 137 198 165 157 173 170 0 52 19 94 155 122 61 77 74 0 110 0 58 177 "
        + "33 112 186 71"
        +
        " 6 125 0 79 198 54 12 86 0 87 70 114 67 50 94 95 31 105 displayGreyscale method called" +
        " with parameters: red, default, default-red";

    assertEquals(message, mLog.toString());
  }

  // Testing green-component command
  @Test
  public void testGreenComponent() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockGUIModel(mLog);
    ImageProcessingGUIView mockView = new MockGUIView(vLog);
    Features c = new GUIController(mockModel);
    c.setView(mockView);
    c.load("images/colorful.jpeg");
    c.component("green");
    String message = "The image was processed in the controller, and the following imageName: " +
        "default was passed to the model, along with an Image that contains the "
        + "following color mapping:\n"
        +
        "47 108 75 137 198 165 157 173 170 0 52 19 94 155 122 61 77 74 0 110 0 58"
        + " 177 33 112 186 71"
        +
        " 6 125 0 79 198 54 12 86 0 87 70 114 67 50 94 95 31 105 displayGreyscale method called" +
        " with parameters: green, default, default-green";

    assertEquals(message, mLog.toString());
  }

  // Testing blue-component command
  @Test
  public void testBlueComponent() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockGUIModel(mLog);
    ImageProcessingGUIView mockView = new MockGUIView(vLog);
    Features c = new GUIController(mockModel);
    c.setView(mockView);
    c.load("images/colorful.jpeg");
    c.component("blue");
    String message = "The image was processed in the controller, and the following imageName: " +
        "default was passed to the model, along with an Image that contains the "
        + "following color mapping:\n"
        +
        "47 108 75 137 198 165 157 173 170 0 52 19 94 155 122 61 77 74 0 110 0 58 177"
        + " 33 112 186 71"
        +
        " 6 125 0 79 198 54 12 86 0 87 70 114 67 50 94 95 31 105 displayGreyscale method called" +
        " with parameters: blue, default, default-blue";

    assertEquals(message, mLog.toString());

  }

  // Testing sharpen command
  @Test
  public void testSharpenType() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockGUIModel(mLog);
    ImageProcessingGUIView mockView = new MockGUIView(vLog);
    Features c = new GUIController(mockModel);
    c.setView(mockView);
    c.load("images/colorful.jpeg");
    c.filter("sharpen");
    String message = "The image was processed in the controller, and the following imageName: "
        + "default was passed to the model, along with an Image that contains the "
        + "following color mapping:\n"
        + "47 108 75 137 198 165 157 173 170 0 52 19 94 155 122 61 77 74 0 110 0 58 177 33 112"
        + " 186 71 6 125 0 79 198 54 12 86 0 87 70 114 67 50 94 95 31 105 filterImage method"
        + " called with parameters: sharpen, default, default-sharpen";

    assertEquals(message, mLog.toString());
  }

  // Testing sharpen command
  @Test
  public void testBlurType() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockGUIModel(mLog);
    ImageProcessingGUIView mockView = new MockGUIView(vLog);
    Features c = new GUIController(mockModel);
    c.setView(mockView);
    c.load("images/colorful.jpeg");
    c.filter("blur");
    String message = "The image was processed in the controller, and the following imageName: "
        + "default was passed to the model, along with an Image that contains the"
        + " following color mapping:\n"
        + "47 108 75 137 198 165 157 173 170 0 52 19 94 155 122 61 77 74 0 110 0 58 177 33 112"
        + " 186 71 6 125 0 79 198 54 12 86 0 87 70 114 67 50 94 95 31 105 filterImage method"
        + " called with parameters: blur, default, default-blur";

    assertEquals(message, mLog.toString());
  }

  // Testing sharpen command
  @Test
  public void testSepiaType() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockGUIModel(mLog);
    ImageProcessingGUIView mockView = new MockGUIView(vLog);
    Features c = new GUIController(mockModel);
    c.setView(mockView);
    c.load("images/colorful.jpeg");
    c.transform("sepia");
    String message = "The image was processed in the controller, and the following imageName: "
        + "default was passed to the model, along with an Image that contains the "
        + "following color mapping:\n"
        + "47 108 75 137 198 165 157 173 170 0 52 19 94 155 122 61 77 74 0 110 0 58 177 33 112"
        + " 186 71 6 125 0 79 198 54 12 86 0 87 70 114 67 50 94 95 31 105 transformImage method"
        + " called with parameters: sepia, default, default-sepia";

    assertEquals(message, mLog.toString());
  }

  @Test
  public void testGreyscaleType() {
    StringBuilder mLog = new StringBuilder();
    StringBuilder vLog = new StringBuilder();
    ImageProcessingModel mockModel = new MockGUIModel(mLog);
    ImageProcessingGUIView mockView = new MockGUIView(vLog);
    Features c = new GUIController(mockModel);
    c.setView(mockView);
    c.load("images/colorful.jpeg");
    c.transform("greyscale");
    String message = "The image was processed in the controller, and the following imageName: "
        + "default was passed to the model, along with an Image that contains the "
        + "following color mapping:\n"
        + "47 108 75 137 198 165 157 173 170 0 52 19 94 155 122 61 77 74 0 110 0 58 177 33 112"
        + " 186 71 6 125 0 79 198 54 12 86 0 87 70 114 67 50 94 95 31 105 transformImage method"
        + " called with parameters: greyscale, default, default-greyscale";

    assertEquals(message, mLog.toString());
  }
}

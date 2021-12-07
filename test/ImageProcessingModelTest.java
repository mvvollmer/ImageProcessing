import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.ImageProcessingModel;
import model.Model;
import util.image.ImageState;
import util.image.Image;
import util.image.PixelImage;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Class utilized for testing the ImageProcessingModel class.
 */
public class ImageProcessingModelTest {

  ImageProcessingModel testModel;

  @Before
  public void init() {
    this.testModel = new Model();
    Color[][] dumbyImage = {
        {new Color(0, 0, 0), new Color(255, 255, 255), new Color(255, 255, 255)},
        {new Color(5, 5, 5), new Color(250, 250, 250), new Color(250, 250, 250)},
        {new Color(100, 100, 100), new Color(100, 100, 100), new Color(100, 100,
            100)},
        {new Color(250, 250, 250), new Color(5, 5, 5), new Color(5, 5, 5)},
        {new Color(255, 255, 255), new Color(0, 0, 0), new Color(0, 0, 0)}};

    Color[][] colorfulImage = {
        {new Color(123, 92, 23), new Color(25, 215, 205), new Color(215, 205, 21)},
        {new Color(0, 34, 1), new Color(0, 252, 50), new Color(20, 50, 250)},
        {new Color(111, 16, 100), new Color(16, 200, 15), new Color(11, 240, 50)},
        {new Color(255, 0, 0), new Color(0, 255, 0), new Color(111, 24, 5)},
        {new Color(2, 90, 195), new Color(100, 60, 0), new Color(80, 20, 100)}};

    this.testModel.load("dumby", new PixelImage(dumbyImage));
    this.testModel.load("colorful", new PixelImage(colorfulImage));
  }


  @Test(expected = IllegalArgumentException.class)
  public void imageNameNotInMap() {
    this.testModel.brightenImage(10, "notAnImage",
        "notAnImage2");
  }


  @Test(expected = IllegalArgumentException.class)
  public void ImageInMapIsNull() {
    this.testModel.brightenImage(10, "nullImage",
        "nullImage2");
  }


  @Test
  public void add0() {
    this.testModel.brightenImage(0, "dumby", "dumbyBrighten");
    ImageState dumbyBrighten = this.testModel.getImage("dumbyBrighten");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < dumbyBrighten.getImageHeight(); row++) {
      for (int col = 0; col < dumbyBrighten.getImageWidth(); col++) {
        sb1.append(dumbyBrighten.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(dumbyBrighten.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(dumbyBrighten.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    sb2.append("0 0 0 255 255 255 255 255 255 "
        + "5 5 5 250 250 250 250 250 250 "
        + "100 100 100 100 100 100 100 100 100 "
        + "250 250 250 5 5 5 5 5 5 "
        + "255 255 255 0 0 0 0 0 0 ");
    assertEquals(sb1.toString(), sb2.toString());
  }

  @Test
  public void try1SeedMosaic() {
    this.testModel.mosaicImage( "dumby", "dumbyBrighten", 1);
    ImageState dumbyBrighten = this.testModel.getImage("dumbyBrighten");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < dumbyBrighten.getImageHeight(); row++) {
      for (int col = 0; col < dumbyBrighten.getImageWidth(); col++) {
        sb1.append(dumbyBrighten.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(dumbyBrighten.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(dumbyBrighten.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    sb2.append("255 255 255 255 255 255 255 255 255 255 255 255 " +
            "255 255 255 255 255 255 255 255 255 " +
            "255 255 255 255 255 255 255 255 255 255 255 255 " +
            "255 255 255 255 255 255 255 255 255 255 255 255 ");
    assertEquals(sb1.toString(), sb2.toString());
  }

  @Test
  public void try3SeedMosaic() {
    this.testModel.mosaicImage( "dumby", "dumbyBrighten", 3);
    ImageState dumbyBrighten = this.testModel.getImage("dumbyBrighten");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < dumbyBrighten.getImageHeight(); row++) {
      for (int col = 0; col < dumbyBrighten.getImageWidth(); col++) {
        sb1.append(dumbyBrighten.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(dumbyBrighten.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(dumbyBrighten.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    sb2.append("0 0 0 255 255 255 255 255 255 0 0 0 " +
            "255 255 255 255 255 255 0 0 0 255 255 255 " +
            "255 255 255 0 0 0 255 255 255 255 255 " +
            "255 0 0 0 0 0 0 0 0 0 ");
    assertEquals(sb1.toString(), sb2.toString());
  }

  @Test
  public void MaximumRBGComponentIs255() {
    this.testModel.brightenImage(10, "dumby", "dumbyBrighten");
    Image dumbyBrighten = this.testModel.getImage("dumbyBrighten");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < dumbyBrighten.getImageHeight(); row++) {
      for (int col = 0; col < dumbyBrighten.getImageWidth(); col++) {
        sb1.append(dumbyBrighten.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(dumbyBrighten.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(dumbyBrighten.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    sb2.append("10 10 10 255 255 255 255 255 255"
        + " 15 15 15 255 255 255 255 255 255"
        + " 110 110 110 110 110 110 110 110 110"
        + " 255 255 255 15 15 15 15 15 15"
        + " 255 255 255 10 10 10 10 10 10 ");

    assertEquals(sb1.toString(), sb2.toString());
  }

  @Test
  public void TestHistogram() {
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();
    List<List<Integer>> histogram = this.testModel.createHistogram("dumby");

    List<Integer> red = new ArrayList<Integer>(
        Arrays.asList(3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 3, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3));

    List<Integer> green = new ArrayList<Integer>(
        Arrays.asList(3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3));

    List<Integer> blue = new ArrayList<Integer>(Arrays.asList(3, 0, 0, 0, 0, 3, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        3, 0, 0, 0,
        0, 3));

    List<Integer> intensity = new ArrayList<Integer>(
        Arrays.asList(3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 3, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3));

    ArrayList<List<Integer>> expectedHistogram = new ArrayList<>(
        Arrays.asList(red, green, blue, intensity));

    assertEquals(expectedHistogram, histogram);
  }


  @Test
  public void MaximumRBGComponentIs255PNG() {
    this.testModel.brightenImage(40, "dumby", "dumbyBrighten");
    Image dumbyBrighten = this.testModel.getImage("dumbyBrighten");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < dumbyBrighten.getImageHeight(); row++) {
      for (int col = 0; col < dumbyBrighten.getImageWidth(); col++) {
        sb1.append(dumbyBrighten.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(dumbyBrighten.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(dumbyBrighten.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    sb2.append("40 40 40 255 255 255 255 255 255 "
        + "45 45 45 255 255 255 255 255 255 "
        + "140 140 140 140 140 140 140 140 140 "
        + "255 255 255 45 45 45 45 45 45 "
        + "255 255 255 40 40 40 40 40 40 ");

    assertEquals(sb1.toString(), sb2.toString());
  }

  @Test
  public void MinimumRBGComponentIs0() {
    this.testModel.brightenImage(-40, "dumby", "dumbyDarken");
    Image dumbyDarken = this.testModel.getImage("dumbyDarken");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < dumbyDarken.getImageHeight(); row++) {
      for (int col = 0; col < dumbyDarken.getImageWidth(); col++) {
        sb1.append(dumbyDarken.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(dumbyDarken.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(dumbyDarken.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    sb2.append("0 0 0 215 215 215 215 215 215"
        + " 0 0 0 210 210 210 210 210 210"
        + " 60 60 60 60 60 60 60 60 60"
        + " 210 210 210 0 0 0 0 0 0"
        + " 215 215 215 0 0 0 0 0 0 ");

    assertEquals(sb1.toString(), sb2.toString());
  }

  @Test
  public void MinimumRBGComponentIs255PNG() {
    this.testModel.brightenImage(-1000, "dumby", "dumbyDarken");
    Image dumbyDarken = this.testModel.getImage("dumbyDarken");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < dumbyDarken.getImageHeight(); row++) {
      for (int col = 0; col < dumbyDarken.getImageWidth(); col++) {
        sb1.append(dumbyDarken.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(dumbyDarken.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(dumbyDarken.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    sb2.append("0 0 0 0 0 0 0 0 0 "
        + "0 0 0 0 0 0 0 0 0 "
        + "0 0 0 0 0 0 0 0 0 "
        + "0 0 0 0 0 0 0 0 0 "
        + "0 0 0 0 0 0 0 0 0 ");

    assertEquals(sb1.toString(), sb2.toString());
  }

  //Tests the Flip method
  @Test(expected = IllegalArgumentException.class)
  public void imageNameNotInMapFlip() {
    this.testModel.flipImage("vertical", "notAnImage", "notAnImage2");
  }


  @Test(expected = IllegalArgumentException.class)
  public void ImageInMapIsNullFlip() {
    this.testModel.flipImage("horizontal", "nullImage", "nullImage2");
  }


  @Test(expected = IllegalArgumentException.class)
  public void axisIsInvalid() {
    this.testModel.flipImage("fff", "dumby", "dumby2");
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullAxis() {
    this.testModel.flipImage(null, "dumby", "dumby2");
  }

  @Test
  public void vertFlip() {
    this.testModel.flipImage("vertical", "dumby", "dumbyVert");
    ImageState dumbyVert = this.testModel.getImage("dumbyVert");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < dumbyVert.getImageHeight(); row++) {
      for (int col = 0; col < dumbyVert.getImageWidth(); col++) {
        sb1.append(dumbyVert.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(dumbyVert.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(dumbyVert.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    ImageState dumby = this.testModel.getImage("dumby");
    for (int row = 0; row < dumby.getImageHeight(); row++) {
      int height = dumby.getImageHeight();
      for (int col = 0; col < dumby.getImageWidth(); col++) {
        int width = dumby.getImageWidth();
        sb2.append(dumby.getPixelAt(row, width - col - 1).getRed()).append(" ");
        sb2.append(dumby.getPixelAt(row, width - col - 1).getGreen()).append(" ");
        sb2.append(dumby.getPixelAt(row, width - col - 1).getBlue()).append(" ");
      }
    }
    assertEquals(sb1.toString(), sb2.toString());
  }

  @Test
  public void horizontalFlip() {
    this.testModel.flipImage("horizontal", "dumby", "dumbyHorizontal");
    ImageState dumbyHorizontal = this.testModel.getImage("dumbyHorizontal");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < dumbyHorizontal.getImageHeight(); row++) {
      for (int col = 0; col < dumbyHorizontal.getImageWidth(); col++) {
        sb1.append(dumbyHorizontal.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(dumbyHorizontal.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(dumbyHorizontal.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    ImageState dumby = this.testModel.getImage("dumby");
    for (int row = 0; row < dumby.getImageHeight(); row++) {
      int height = dumby.getImageHeight();
      for (int col = 0; col < dumby.getImageWidth(); col++) {
        int width = dumby.getImageWidth();
        sb2.append(dumby.getPixelAt(height - row - 1, col).getRed()).append(" ");
        sb2.append(dumby.getPixelAt(height - row - 1, col).getGreen()).append(" ");
        sb2.append(dumby.getPixelAt(height - row - 1, col).getBlue()).append(" ");
      }
    }
    assertEquals(sb1.toString(), sb2.toString());
  }

  @Test
  public void doubleHorizontalFlip() {
    this.testModel.flipImage("horizontal", "dumby", "dumbyHorizontal");
    ImageState dumbyHorizontal = this.testModel.getImage("dumbyHorizontal");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();
    StringBuilder sb3 = new StringBuilder();
    StringBuilder sb4 = new StringBuilder();

    for (int row = 0; row < dumbyHorizontal.getImageHeight(); row++) {
      for (int col = 0; col < dumbyHorizontal.getImageWidth(); col++) {
        sb1.append(dumbyHorizontal.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(dumbyHorizontal.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(dumbyHorizontal.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    sb2.append("255 255 255 0 0 0 0 0 0" +
        " 250 250 250 5 5 5 5 5 5" +
        " 100 100 100 100 100 100 100 100 100" +
        " 5 5 5 250 250 250 250 250 250" +
        " 0 0 0 255 255 255 255 255 255 ");
    assertEquals(sb1.toString(), sb2.toString());

    this.testModel.flipImage("horizontal", "dumbyHorizontal",
        "dumbyHorizontalHorizontal");
    ImageState dumbyHorizontalHorizontal =
        this.testModel.getImage("dumbyHorizontalHorizontal");

    for (int row = 0; row < dumbyHorizontalHorizontal.getImageHeight(); ++row) {
      for (int col = 0; col < dumbyHorizontalHorizontal.getImageWidth(); ++col) {
        sb3.append(dumbyHorizontalHorizontal.getPixelAt(row, col).getRed()).append(" ");
        sb3.append(dumbyHorizontalHorizontal.getPixelAt(row, col).getGreen()).append(" ");
        sb3.append(dumbyHorizontalHorizontal.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    sb4.append("0 0 0 255 255 255 255 255 255 "
        + "5 5 5 250 250 250 250 250 250 "
        + "100 100 100 100 100 100 100 100 100 "
        + "250 250 250 5 5 5 5 5 5 "
        + "255 255 255 0 0 0 0 0 0 ");
    assertEquals(sb3.toString(), sb4.toString());
  }

  @Test
  public void doubleVertFlip() {
    this.testModel.flipImage("vertical", "dumby", "dumbyVert");
    this.testModel.flipImage("vertical", "dumbyVert", "doubleFlip");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < testModel.getImageHeight("doubleFlip"); row++) {
      for (int col = 0; col < testModel.getImageWidth("doubleFlip"); col++) {
        sb1.append(testModel.getPixelAt("doubleFlip", row, col).getRed()).append(" ");
        sb1.append(testModel.getPixelAt("doubleFlip", row, col).getGreen()).append(" ");
        sb1.append(testModel.getPixelAt("doubleFlip", row, col).getBlue()).append(" ");
      }
    }
    sb2.append("0 0 0 255 255 255 255 255 255 "
        + "5 5 5 250 250 250 250 250 250 "
        + "100 100 100 100 100 100 100 100 100 "
        + "250 250 250 5 5 5 5 5 5 "
        + "255 255 255 0 0 0 0 0 0 ");
    assertEquals(sb1.toString(), sb2.toString());
  }

  @Test
  public void verticalThenHorizontalFlip() {
    this.testModel.flipImage("vertical", "dumby", "dumbyVert");
    this.testModel.flipImage("horizontal", "dumbyVert", "doubleFlip");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < testModel.getImageHeight("doubleFlip"); row++) {
      for (int col = 0; col < testModel.getImageWidth("doubleFlip"); col++) {
        sb1.append(testModel.getPixelAt("doubleFlip", row, col).getRed()).append(" ");
        sb1.append(testModel.getPixelAt("doubleFlip", row, col).getGreen()).append(" ");
        sb1.append(testModel.getPixelAt("doubleFlip", row, col).getBlue()).append(" ");
      }
    }

    sb2.append("0 0 0 0 0 0 255 255 255 "
        + "5 5 5 5 5 5 250 250 250 "
        + "100 100 100 100 100 100 100 100 100 "
        + "250 250 250 250 250 250 5 5 5 "
        + "255 255 255 255 255 255 0 0 0 ");
    assertEquals(sb1.toString(), sb2.toString());
  }


  @Test
  public void horizontalThenVertFlip() {
    this.testModel.flipImage("horizontal", "dumby", "dumbyHorizontal");
    this.testModel.flipImage("vertical", "dumbyHorizontal", "doubleFlip");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < testModel.getImageHeight("doubleFlip"); row++) {
      for (int col = 0; col < testModel.getImageWidth("doubleFlip"); col++) {
        sb1.append(testModel.getPixelAt("doubleFlip", row, col).getRed()).append(" ");
        sb1.append(testModel.getPixelAt("doubleFlip", row, col).getGreen()).append(" ");
        sb1.append(testModel.getPixelAt("doubleFlip", row, col).getBlue()).append(" ");
      }
    }

    sb2.append("0 0 0 0 0 0 255 255 255 "
        + "5 5 5 5 5 5 250 250 250 "
        + "100 100 100 100 100 100 100 100 100 "
        + "250 250 250 250 250 250 5 5 5 "
        + "255 255 255 255 255 255 0 0 0 ");
    assertEquals(sb1.toString(), sb2.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidComponent() {
    testModel.displayGreyscale("invalid", "colorful", "colorfulGreyscale");
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullComponent() {
    testModel.displayGreyscale(null, "dumby", "dumbyGreyscale");
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullImageNameGreyscale() {
    testModel.displayGreyscale("red", null, "dumbyGreyscale");
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullDesiredNameGreyscale() {
    testModel.displayGreyscale("red", "dumby", null);
  }


  @Test
  public void blueGreycale() {
    this.testModel.displayGreyscale("blue", "colorful", "colorfulGreyscale");
    Image colorfulGreyscale = this.testModel.getImage("colorfulGreyscale");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < colorfulGreyscale.getImageHeight(); row++) {
      for (int col = 0; col < colorfulGreyscale.getImageWidth(); col++) {
        sb1.append(colorfulGreyscale.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(colorfulGreyscale.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(colorfulGreyscale.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    sb2.append("23 23 23 205 205 205 21 21 21 "
        + "1 1 1 50 50 50 250 250 250 "
        + "100 100 100 15 15 15 50 50 50 "
        + "0 0 0 0 0 0 5 5 5 "
        + "195 195 195 0 0 0 100 100 100 ");

    assertEquals(sb1.toString(), sb2.toString());
  }

  @Test
  public void redGreycale() {
    this.testModel.displayGreyscale("red", "colorful", "colorfulGreyscale");
    Image colorfulGreyscale = this.testModel.getImage("colorfulGreyscale");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < colorfulGreyscale.getImageHeight(); row++) {
      for (int col = 0; col < colorfulGreyscale.getImageWidth(); col++) {
        sb1.append(colorfulGreyscale.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(colorfulGreyscale.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(colorfulGreyscale.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    sb2.append("123 123 123 25 25 25 215 215 215 "
        + "0 0 0 0 0 0 20 20 20 "
        + "111 111 111 16 16 16 11 11 11 "
        + "255 255 255 0 0 0 111 111 111 "
        + "2 2 2 100 100 100 80 80 80 ");

    assertEquals(sb1.toString(), sb2.toString());
  }

  @Test
  public void greenGreycale() {
    this.testModel.displayGreyscale("green", "colorful", "colorfulGreyscale");
    Image colorfulGreyscale = this.testModel.getImage("colorfulGreyscale");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < colorfulGreyscale.getImageHeight(); row++) {
      for (int col = 0; col < colorfulGreyscale.getImageWidth(); col++) {
        sb1.append(colorfulGreyscale.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(colorfulGreyscale.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(colorfulGreyscale.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    sb2.append("92 92 92 215 215 215 205 205 205 "
        + "34 34 34 252 252 252 50 50 50 "
        + "16 16 16 200 200 200 240 240 240 "
        + "0 0 0 255 255 255 24 24 24 "
        + "90 90 90 60 60 60 20 20 20 ");

    assertEquals(sb1.toString(), sb2.toString());
  }

  @Test
  public void valueGreycale() {
    this.testModel.displayGreyscale("value", "colorful", "colorfulGreyscale");
    Image colorfulGreyscale = this.testModel.getImage("colorfulGreyscale");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < colorfulGreyscale.getImageHeight(); row++) {
      for (int col = 0; col < colorfulGreyscale.getImageWidth(); col++) {
        sb1.append(colorfulGreyscale.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(colorfulGreyscale.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(colorfulGreyscale.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    sb2.append("123 123 123 215 215 215 215 215 215 "
        + "34 34 34 252 252 252 250 250 250 "
        + "111 111 111 200 200 200 240 240 240 "
        + "255 255 255 255 255 255 111 111 111 "
        + "195 195 195 100 100 100 100 100 100 ");

    assertEquals(sb1.toString(), sb2.toString());
  }

  @Test
  public void intensityGreycale() {
    this.testModel.displayGreyscale("intensity", "colorful", "colorfulGreyscale");
    Image colorfulGreyscale = this.testModel.getImage("colorfulGreyscale");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < colorfulGreyscale.getImageHeight(); row++) {
      for (int col = 0; col < colorfulGreyscale.getImageWidth(); col++) {
        sb1.append(colorfulGreyscale.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(colorfulGreyscale.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(colorfulGreyscale.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    sb2.append("79 79 79 148 148 148 147 147 147 "
        + "11 11 11 100 100 100 106 106 106 "
        + "75 75 75 77 77 77 100 100 100 "
        + "85 85 85 85 85 85 46 46 46 "
        + "95 95 95 53 53 53 66 66 66 ");

    assertEquals(sb1.toString(), sb2.toString());
  }

  @Test
  public void lumaGreycale() {
    this.testModel.displayGreyscale("luma", "colorful", "colorfulGreyscale");
    Image colorfulGreyscale = this.testModel.getImage("colorfulGreyscale");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < colorfulGreyscale.getImageHeight(); row++) {
      for (int col = 0; col < colorfulGreyscale.getImageWidth(); col++) {
        sb1.append(colorfulGreyscale.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(colorfulGreyscale.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(colorfulGreyscale.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    sb2.append("94 94 94 174 174 174 195 195 195 "
        + "24 24 24 184 184 184 58 58 58 "
        + "42 42 42 147 147 147 178 178 178 "
        + "54 54 54 182 182 182 41 41 41 "
        + "78 78 78 64 64 64 38 38 38 ");

    assertEquals(sb1.toString(), sb2.toString());
  }

  // Load tests
  @Test(expected = IllegalArgumentException.class)
  public void nullImageName() {
    Color[][] colorfulImage = {
        {new Color(123, 92, 23), new Color(25, 215, 205), new Color(215, 205, 21)},
        {new Color(0, 34, 1), new Color(0, 252, 50), new Color(20, 50, 250)},
        {new Color(111, 16, 100), new Color(16, 200, 15), new Color(11, 240, 50)},
        {new Color(255, 0, 0), new Color(0, 255, 0), new Color(111, 24, 5)},
        {new Color(2, 90, 195), new Color(100, 60, 0), new Color(80, 20, 100)}};

    this.testModel.load(null, new PixelImage(colorfulImage));
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullImage() {
    this.testModel.load("notARealFileName", null);
  }

  @Test
  public void loadDumby() {
    //in init(), the image "dumbyImage" has not yet been loaded into the model.
    Color[][] dumbyImageColor = {
        {new Color(11, 55, 223), new Color(95, 218, 5), new Color(123, 25, 255)},
        {new Color(0, 255, 255), new Color(20, 70, 20), new Color(20, 20, 220)},
        {new Color(150, 200, 70), new Color(10, 180, 110), new Color(110, 110, 170)},
        {new Color(255, 20, 255), new Color(11, 23, 55), new Color(213, 5, 5)},
        {new Color(255, 255, 255), new Color(0, 0, 0), new Color(9, 0, 9)}};
    this.testModel.load("dumbyImageColor", new PixelImage(dumbyImageColor));

    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < testModel.getImageHeight("dumbyImageColor"); row++) {
      for (int col = 0; col < testModel.getImageWidth("dumbyImageColor"); col++) {
        sb1.append(testModel.getPixelAt("dumbyImageColor", row, col).getRed()).append(" ");
        sb1.append(testModel.getPixelAt("dumbyImageColor", row, col).getGreen()).append(" ");
        sb1.append(testModel.getPixelAt("dumbyImageColor", row, col).getBlue()).append(" ");
      }
    }

    sb2.append("11 55 223 95 218 5 123 25 255" +
        " 0 255 255 20 70 20 20 20 220 150" +
        " 200 70 10 180 110 110 110 170 255" +
        " 20 255 11 23 55 213 5 5 255 255" +
        " 255 0 0 0 9 0 9 ");
    assertEquals(sb1.toString(), sb2.toString());
  }

  // Tests for transformation
  @Test(expected = IllegalArgumentException.class)
  public void imageNameNotInMapTransformation() {
    this.testModel.transformImage("sepia", "notAnImage", "notAnImage2");
  }


  @Test(expected = IllegalArgumentException.class)
  public void invalidTransformationType() {
    this.testModel.transformImage("invalid", "colorful", "colorfulSepia");
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullTransformationType() {
    this.testModel.filterImage(null, "notAnImage", "notAnImage2");
  }

  @Test
  public void sepiaTransformation() {
    this.testModel.transformImage("sepia", "colorful", "colorfulSepia");
    Image colorfulSepia = this.testModel.getImage("colorfulSepia");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < colorfulSepia.getImageHeight(); row++) {
      for (int col = 0; col < colorfulSepia.getImageWidth(); col++) {
        sb1.append(colorfulSepia.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(colorfulSepia.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(colorfulSepia.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    sb2.append("123 110 85 214 190 149 246 220 170 "
        + "26 23 18 203 181 142 93 83 65 "
        + "75 67 52 163 146 113 198 177 138 "
        + "100 89 69 196 175 136 63 56 44 "
        + "107 96 75 85 76 59 65 59 46 ");
    assertEquals(sb1.toString(), sb2.toString());
  }

  @Test
  public void greyscaleTransformation() {
    this.testModel.transformImage("greyscale", "colorful", "colorfulGreyscale");
    Image colorfulGreyscale = this.testModel.getImage("colorfulGreyscale");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < colorfulGreyscale.getImageHeight(); row++) {
      for (int col = 0; col < colorfulGreyscale.getImageWidth(); col++) {
        sb1.append(colorfulGreyscale.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(colorfulGreyscale.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(colorfulGreyscale.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    sb2.append("94 94 94 174 174 174 195 195 195 "
        + "24 24 24 184 184 184 58 58 58 "
        + "42 42 42 147 147 147 178 178 178 "
        + "54 54 54 182 182 182 41 41 41 "
        + "78 78 78 64 64 64 38 38 38 ");
    assertEquals(sb1.toString(), sb2.toString());
  }

  //Filter tests
  @Test(expected = IllegalArgumentException.class)
  public void imageNameNotInMapFilter() {
    this.testModel.filterImage("blur", "notAnImage", "notAnImage2");
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullFilterType() {
    this.testModel.filterImage(null, "notAnImage", "notAnImage2");
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidFilterType() {
    this.testModel.transformImage("invalid", "colorful", "colorfulBlur");
  }

  @Test
  public void blurFilter() {
    this.testModel.filterImage("blur", "colorful", "colorfulBlur");
    Image colorfulBlur = this.testModel.getImage("colorfulBlur");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < colorfulBlur.getImageHeight(); row++) {
      for (int col = 0; col < colorfulBlur.getImageWidth(); col++) {
        sb1.append(colorfulBlur.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(colorfulBlur.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(colorfulBlur.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    sb2.append("34 70 35 49 129 79 60 100 65 "
        + "32 81 36 37 160 83 36 127 92 "
        + "62 65 30 43 153 45 22 126 50 "
        + "85 62 38 74 123 30 46 88 21 "
        + "46 47 49 58 63 37 47 32 26 ");
    assertEquals(sb1.toString(), sb2.toString());
  }

  @Test
  public void sharpenFilter() {
    this.testModel.filterImage("sharpen", "colorful", "colorfulSharpen");
    Image colorfulSharpen = this.testModel.getImage("colorfulSharpen");
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();

    for (int row = 0; row < colorfulSharpen.getImageHeight(); row++) {
      for (int col = 0; col < colorfulSharpen.getImageWidth(); col++) {
        sb1.append(colorfulSharpen.getPixelAt(row, col).getRed()).append(" ");
        sb1.append(colorfulSharpen.getPixelAt(row, col).getGreen()).append(" ");
        sb1.append(colorfulSharpen.getPixelAt(row, col).getBlue()).append(" ");
      }
    }

    sb2.append("83 129 33 98 255 255 194 255 125 "
        + "0 131 59 85 255 216 12 255 255 "
        + "95 79 11 77 255 62 0 255 51 255 "
        + "80 22 171 255 80 115 164 0 50 "
        + "107 162 196 101 55 84 37 57 ");
    assertEquals(sb1.toString(), sb2.toString());
  }


}
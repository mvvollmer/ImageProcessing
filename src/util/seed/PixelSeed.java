package util.seed;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


import util.Posn;
import util.image.Image;
import util.image.PixelImage;

/**
 * Class representing a PixelSeed.
 */
public class PixelSeed implements ISeed {
  private int numSeeds;
  private List<Seedling> positions;
  private Image image;

  /**
   * Constructs a PixelSeed with an integer number of seeds and a PixelImage.
   * @param numSeeds desired number of seeds.
   * @param image image which you would like to Mosaic.
   */
  public PixelSeed(int numSeeds, Image image) {
    this.numSeeds = numSeeds;
    this.image = image;
    this.positions = buildSeed(numSeeds);
  }

  @Override
  public void createSeed() {
    int height = image.getImageHeight();
    int width = image.getImageWidth();
    for (int i = 0; i < positions.size(); i++) {
      Seedling current = positions.get(i);
      cluster(current);
    }
    for (int i = 0; i < positions.size(); i++) {
      Seedling current = positions.get(i);
      current.colorSeedling();
    }
  }

  @Override
  public Color[][] createSeedImage() {
    Color[][] base = new Color[image.getImageHeight()][image.getImageWidth()];
    for (int i = 0; i < positions.size(); i++) {
      Seedling current = positions.get(i);
      current.addColors(base);
      int x = current.getPosn().getX();
      int y = current.getPosn().getY();
      base[y - 1][x - 1] = Color.MAGENTA;
    }
    return base;
  }

  private List<Seedling> buildSeed(int numSeeds) {
    List<Seedling> base = new ArrayList<>();
    int height = image.getImageHeight();
    int width = image.getImageWidth();
    for (int i = 0; i < numSeeds; i++) {
      int seedX = (int) (Math.random() * width);
      int seedY = (int) (Math.random() * height);
      Posn seedPosn = new Posn(seedX, seedY);
      Seedling seedling = new Seedling(seedPosn);
      base.add(seedling);
    }
    return base;
  }

  private void cluster(Seedling seedling) {
    int centerX = seedling.getPosn().getX();
    int centerY = seedling.getPosn().getY();
    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        Color current = image.getPixelAt(i, j);
        Posn currentPosn = new Posn(i, j);
        if (!seededPixel(currentPosn)) {
          seedling.addPixel(current, currentPosn);
        }
        else {
          if (closerSeedling(seedling, getSeedling(currentPosn), currentPosn)) {
            seedling.addPixel(current, currentPosn);
          }
        }
      }
    }
  }

  private boolean seededPixel(Posn posn){
    int x = 0;
    for (int i = 0; i < positions.size(); i++) {
      if (positions.get(i).containsPosn(posn)) {
        x = x + 1;
      }
    }
    return x > 0;
  }

  private boolean closerSeedling(Seedling current, Seedling containing, Posn pixel) {
    Posn currentPosn = current.getPosn();
    int currentX = currentPosn.getX();
    int currentY = currentPosn.getY();
    Posn containingposn = containing.getPosn();
    int containingX = containingposn.getX();
    int containingY = containingposn.getY();
    int pixelX = pixel.getX();
    int pixelY = pixel.getY();
    double disFromCur = getDistance(currentX, currentY, pixelX, pixelY);
    double disFromCon = getDistance(containingX, containingY, pixelX, pixelY);
    return disFromCur < disFromCon;
  }

  private double getDistance(int x1, int y1, int x2, int y2) {
    int xDis = Math.abs(x1 - x2);
    int yDis = Math.abs(y1 - y2);
    return Math.sqrt((xDis * xDis) +  (yDis * yDis));
  }

  private Seedling getSeedling(Posn p) {
    for (int i = 0; i < positions.size(); i++) {
      Seedling current = positions.get(i);
      if (current.containsPosn(p)) {
        return current;
      }
    }
    throw new IllegalArgumentException("uh oh");
  }
}

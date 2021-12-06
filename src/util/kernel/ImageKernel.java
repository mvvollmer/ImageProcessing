package util.kernel;

/**
 * Represents a Kernel that is used to apply changes to images.
 */
public class ImageKernel implements Kernel {

  private double[][] matrix;

  /**
   * Given a 2D array of doubles, creates a Kernel.
   *
   * @param matrix a 2D array of doubles that represent the values that will be applied to
   *               Pixels of an image, to create the RGB values of new images.
   */
  public ImageKernel(double[][] matrix) {
    if (matrix.length % 2 != 1 || matrix[0].length % 2 != 1) {
      throw new IllegalArgumentException("Dimensions must be odd.");
    }
    this.matrix = matrix;
  }

  @Override
  public int getHeight() {
    return matrix.length;
  }

  @Override
  public int getWidth() {
    return matrix[0].length;
  }

  @Override
  public double getValueAt(int row, int col) throws IllegalArgumentException {
    if (row > matrix.length || row < 0 || col > matrix[0].length || col < 0) {
      throw new IllegalArgumentException("The given row and/or col are out of bounds.");
    }
    return this.matrix[row][col];
  }
}



package util;

import java.util.Objects;

/**
 * Represents a 2-dimensional coordinate position with an x and y component.
 */
public class Posn {

  private final int x;
  private final int y;

  /**
   * Initializes a new Posn with the given (x,y) coordinate.
   *
   * @param x x coordinate
   * @param y y coordinate
   */
  public Posn(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Return the x coordinate of this Posn.
   *
   * @return x coordinate
   */
  public int getX() {
    return x;
  }

  /**
   * Return the y coordinate of this Posn.
   *
   * @return y coordinate
   */
  public int getY() {
    return y;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }

    if (!(o instanceof Posn)) {
      return false;
    }

    Posn other = (Posn) o;

    return this.x == other.getX()
            && this.y == other.getY();

  }

  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y);
  }

  @Override
  public String toString() {
    return String.format("Posn(%d,%d)", this.x, this.y);
  }
}

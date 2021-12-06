package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JPanel;

/**
 * Representing a histogram.
 */
public final class Histogram extends JPanel {

  private final int height = 300;
  private final int width = 510;
  //private final double unitY; //height / maxFrequency
  private final int unitX = width / 255;
  //private final int maxFreq;
  private final List<List<Line>> lineLists = new ArrayList<>();

  /**
   * Constructing a histogram.
   *
   * @param frequencies list of lists of frequencies.
   */
  public Histogram(List<List<Integer>> frequencies) {
    super();
    super.setPreferredSize(new Dimension(510, 300));

    int maxFreqTemp = 0;

    for (List<Integer> list : frequencies) {
      for (Integer i : list) {
        maxFreqTemp = Math.max(maxFreqTemp, i);
      }
    }
    int maxFreq = maxFreqTemp;
    double unitY = (double) height / maxFreq;

    for (List<Integer> list : frequencies) {
      List<Line> newList = new ArrayList<>();
      for (int i = 0; i < 255; i++) {
        newList.add(new Line(i * unitX, (int) (height - (list.get(i) * unitY)),
            (i + 1) * unitX, (int) (height - (list.get(i + 1) * unitY))));
      }
      lineLists.add(newList);
    }
    setVisible(true);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    List<Color> lineColors = new ArrayList<>(
        Arrays.asList(Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA));
    for (int i = 0; i < lineLists.size(); i++) {
      g.setColor(lineColors.get(i));
      for (Line l : lineLists.get(i)) {
        g.drawLine(l.xFrom, l.yFrom, l.xTo, l.yTo);
      }
    }
  }

  public void repaint() {
    super.repaint();
  }

  /**
   * Representing a line.
   */
  public static class Line {

    public final int xFrom;
    public final int yFrom;
    public final int xTo;
    public final int yTo;

    /**
     * Constructing a line.
     *
     * @param xFrom xfrom
     * @param yFrom yfrom
     * @param xTo xto
     * @param yTo yto
     */
    public Line(int xFrom, int yFrom, int xTo, int yTo) {
      this.xFrom = xFrom;
      this.yFrom = yFrom;
      this.xTo = xTo;
      this.yTo = yTo;
    }
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(400, 510);
  }
}
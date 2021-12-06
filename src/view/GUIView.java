package view;

import controller.Features;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * View for the graphical user interface.
 */
public class GUIView extends JFrame implements ImageProcessingGUIView {

  private JMenuItem blur;
  private JMenuItem sharpen;
  private JMenuItem greyscale;
  private JMenuItem sepia;
  private JMenuItem red;
  private JMenuItem green;
  private JMenuItem blue;
  private JMenuItem value;
  private JMenuItem intensity;
  private JMenuItem luma;
  private JMenuItem horizontal;
  private JMenuItem vertical;
  private JButton brighten;
  private JButton mosaic;

  private JLabel image;
  private JLabel histogramMessage;
  private JPanel histogramPanel;
  private JButton exit;
  private JButton save;
  private JButton load;

  /**
   * Constructing a GUIView object.
   */
  public GUIView() {
    super();
    JMenuBar topMenuBar;
    JMenu filterMenu;
    JMenu transformMenu;
    JMenu componentMenu;
    JMenu flipMenu;

    JPanel imagePanel;
    JLabel imageMessage;
    JScrollPane imageScrollPane;

    JMenuItem menuMessage;

    JMenuBar bottomMenuBar;

    setTitle("Image Processor");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setExtendedState(JFrame.MAXIMIZED_BOTH);

    // setting layout to BorderLayout
    this.setLayout(new BorderLayout());

    // Menu Bar
    topMenuBar = new JMenuBar();
    this.add(topMenuBar, BorderLayout.PAGE_START);

    menuMessage = new JMenuItem("Edit Image:");
    topMenuBar.add(menuMessage);

    // Filter Menu
    filterMenu = new JMenu("Filter");
    // Blur Button
    blur = new JMenuItem("Blur");
    blur.setActionCommand("Blur Button");
    filterMenu.add(blur);
    // Sharpen Button
    sharpen = new JMenuItem("Sharpen");
    sharpen.setActionCommand("Greyscale Button");
    filterMenu.add(sharpen);
    topMenuBar.add(filterMenu);

    // Transformation Menu
    transformMenu = new JMenu("Transform");
    // Sepia Button
    sepia = new JMenuItem("Sepia");
    sepia.setActionCommand("Sepia Button");
    transformMenu.add(sepia);
    // Greyscale Button
    greyscale = new JMenuItem("Greyscale");
    greyscale.setActionCommand("Greyscale Button");
    transformMenu.add(greyscale);
    topMenuBar.add(transformMenu);

    // Component Menu
    componentMenu = new JMenu("Component");
    // Red Component Button
    red = new JMenuItem("Red");
    red.setActionCommand("Red Component Button");
    componentMenu.add(red);
    // Green Component Button
    green = new JMenuItem("Green");
    green.setActionCommand("Green Component Button");
    componentMenu.add(green);
    // Blue Component Button
    blue = new JMenuItem("Blue");
    blue.setActionCommand("Blue Component Button");
    componentMenu.add(blue);
    // Value Component Button
    value = new JMenuItem("Value");
    value.setActionCommand("Value Component Button");
    componentMenu.add(value);
    // Intensity Component Button
    intensity = new JMenuItem("Intensity");
    intensity.setActionCommand("Intensity Component Button");
    componentMenu.add(intensity);
    // Luma Component Button
    luma = new JMenuItem("Luma");
    luma.setActionCommand("Luma Component Button");
    componentMenu.add(luma);
    topMenuBar.add(componentMenu);

    // Flip Menu
    flipMenu = new JMenu("Flip");
    // Vertical Button
    vertical = new JMenuItem("Vertical");
    vertical.setActionCommand("Vertical Button");
    flipMenu.add(vertical);
    // Horizontal Button
    horizontal = new JMenuItem("Horizontal");
    horizontal.setActionCommand("Horizontal Button");
    flipMenu.add(horizontal);
    topMenuBar.add(flipMenu);

    // Brighten Button
    brighten = new JButton("Brighten");
    brighten.setActionCommand("Brighten");
    topMenuBar.add(brighten);

    // Mosaic Button
    mosaic = new JButton("Mosaic");
    mosaic.setActionCommand("Mosaic");
    topMenuBar.add(mosaic);

    // Setting the image panel
    imagePanel = new JPanel();
    imagePanel.setLayout(new CardLayout());
    image = new JLabel();
    image.setIcon(new ImageIcon());
    imageScrollPane = new JScrollPane(image);
    imagePanel.add(imageScrollPane);
    imageMessage = new JLabel("Load an image using the Load New... button below.");
    imagePanel.add(imageMessage);
    imagePanel.setBackground(Color.LIGHT_GRAY);
    this.add(imagePanel, BorderLayout.CENTER);

    // TODO Adding the histogram visualization to the right side of the screen
    histogramPanel = new JPanel();
    histogramPanel.setLayout(new BoxLayout(histogramPanel, BoxLayout.Y_AXIS));
    histogramPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    histogramPanel.setBackground(Color.LIGHT_GRAY);
    histogramMessage = new JLabel("Histogram of RBG Value Frequencies");
    histogramPanel.add(histogramMessage);
    this.add(histogramPanel, BorderLayout.AFTER_LINE_ENDS);

    // Bottom menu bar with load/save/exit buttons
    bottomMenuBar = new JMenuBar();
    this.add(bottomMenuBar, BorderLayout.PAGE_END);
    // Load Button
    load = new JButton("Load New...");
    load.setActionCommand("Load");
    bottomMenuBar.add(load);
    // Save Button
    save = new JButton("Save As...");
    save.setActionCommand("Save");
    bottomMenuBar.add(save);
    // Exit Button
    exit = new JButton("EXIT");
    exit.setActionCommand("Exit");
    bottomMenuBar.add(exit);

    pack();
    setVisible(true);
  }

  @Override
  public void addFeatures(Features features) {
    blur.addActionListener(evt -> features.filter("blur"));
    sharpen.addActionListener(evt -> features.filter("sharpen"));
    sepia.addActionListener(evt -> features.transform("sepia"));
    greyscale.addActionListener(evt -> features.transform("greyscale"));
    red.addActionListener(evt -> features.component("red"));
    green.addActionListener(evt -> features.component("green"));
    blue.addActionListener(evt -> features.component("blue"));
    value.addActionListener(evt -> features.component("value"));
    intensity.addActionListener(evt -> features.component("intensity"));
    luma.addActionListener(evt -> features.component("luma"));
    vertical.addActionListener(evt -> features.flip("vertical"));
    horizontal.addActionListener(evt -> features.flip("horizontal"));
    brighten.addActionListener(evt -> features.brighten(getBrightness()));
    mosaic.addActionListener(evt -> features.mosaic(getSeedNum()));
    load.addActionListener(evt -> features.load(loadImage()));
    save.addActionListener(evt -> features.save(saveImage()));
    exit.addActionListener(evt -> features.exit());
  }

  // load file path, retrieve string of file path from user clicks.
  private String loadImage() {
    System.out.print("loadImage: Been Here.\n");
    JFileChooser fileChooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "Images", "jpg", "jpeg", "bmp", "png");
    fileChooser.setFileFilter(filter);
    int returnValue = fileChooser.showOpenDialog(GUIView.this);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      String filePath = file.getAbsolutePath();
      try {
        BufferedImage newImage = ImageIO.read(file);
        image.setIcon(new ImageIcon(newImage));
      } catch (IOException e) {
        displayMessage("Unable to load file. Please try again.");
        loadImage();
      }
      return filePath;
    }
    return "";
  }

  // save file path, retrieve string of file path from user clicks.
  private String saveImage() {
    final JFileChooser fileChooser = new JFileChooser(".");
    int returnValue = fileChooser.showSaveDialog(GUIView.this);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      return file.getAbsolutePath();
    }
    return "";
  }

  // getting the brightness increment value from user input.
  private int getBrightness() {
    JOptionPane optionPane = new JOptionPane();
    String input = optionPane.showInputDialog("Enter a value between (-250,250)"
        + " to brighten/darken the image.");
    int value = Integer.parseInt(input);
    if (value > 250 || value < -250) {
      displayMessage("Brightness input out of range. Please re-enter.");
      getBrightness();
      return 0;
    }
    return value;
  }

  private int getSeedNum() {
    JOptionPane optionPane = new JOptionPane();
    String input = optionPane.showInputDialog("Enter a seed number to create mosaic");
    int value = Integer.parseInt(input);
    return value;
  }

  @Override
  public void displayImage(BufferedImage newImage) {
    image.setIcon(new ImageIcon(newImage));
  }

  @Override
  public void displayHistogram(List<List<Integer>> lists) {
    histogramPanel.removeAll();
    histogramPanel.add(histogramMessage, BoxLayout.X_AXIS);
    histogramPanel.add(new Histogram(lists), BoxLayout.Y_AXIS);
    //histogram.setVisible(false);
    histogramPanel.validate();
    histogramPanel.repaint();
    this.validate();
    this.repaint();
  }

  @Override
  public void displayMessage(String message) {
    JOptionPane.showMessageDialog(this, message);
  }
}
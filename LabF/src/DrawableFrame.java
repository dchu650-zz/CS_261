import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawableFrame extends JFrame
{
  private DrawableFrame.DrawingPanel panel;
  private Image image;

  public DrawableFrame(String title, Image drawing)
  {
    super(title);
    setDefaultCloseOperation(3);

    this.image = drawing;
    this.panel = new DrawableFrame.DrawingPanel();
    this.panel.setPreferredSize(new Dimension(drawing.getWidth(null), drawing.getHeight(null)));
    this.panel.setBackground(Color.WHITE);
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(this.panel, "Center");

    pack();
    setVisible(true);
  }

  public void refresh()
  {
    this.panel.repaint();
  }

  public void updateDrawing(Image drawing)
  {
    if ((drawing.getWidth(null) != this.image.getWidth(null)) || (drawing.getHeight(null) != drawing.getHeight(null))) {
      throw new IllegalArgumentException("Given image has different size");
    }
    this.image = drawing;
    this.panel.repaint();
  }

  private class DrawingPanel extends JPanel
  {
    private DrawingPanel()
    {
    }

    public void paintComponent(Graphics g) {
      g.drawImage(DrawableFrame.this.image, 0, 0, null);
    }
  }
}
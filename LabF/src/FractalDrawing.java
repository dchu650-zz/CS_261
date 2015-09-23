import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class FractalDrawing
{
  public DrawableFrame frame;
  public BufferedImage fractalImage;
  protected Graphics2D graphics;
  protected int width;
  protected int height;

  public FractalDrawing(String title, int width, int height)
  {
    this.width = width;
    this.height = height;
    this.fractalImage = new BufferedImage(width, height, 2);
    this.graphics = this.fractalImage.createGraphics();
    this.frame = new DrawableFrame(title, this.fractalImage);
    clearDrawing();
    this.frame.refresh();

    drawFractal();
    show();
  }

  public abstract void drawFractal();

  public void clearDrawing()
  {
    this.graphics.setColor(Color.WHITE);
    this.graphics.fillRect(0, 0, this.width, this.height);
    this.graphics.setColor(Color.BLACK);
    this.frame.refresh();
  }

  public void show()
  {
    this.frame.refresh();
  }

  public void wait(int milliseconds)
  {
    try
    {
      Thread.sleep(milliseconds);
    }
    catch (InterruptedException localInterruptedException)
    {
    }
    this.frame.refresh();
  }
}
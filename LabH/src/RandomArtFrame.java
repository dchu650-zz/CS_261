import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * A window for generating and displaying random artwork.
 * @author joel
 * @version Sp 2013
 */
public class RandomArtFrame extends JFrame implements ActionListener
{
	//instance variables
	private ImagePanel panel;
	private BufferedImage image;
	private JButton colorButton, bwButton, saveButton;
	
	/**
	 * Creates a new RandomArtFrame
	 * @param title the title for the frame
	 * @param width the width of the artwork
	 * @param height the height of the artwork
	 */
	public RandomArtFrame(String title, int width, int height)
	{
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		image = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		createArtwork(true);
		
		panel = new ImagePanel();
		panel.setPreferredSize(new Dimension(width,height));
		panel.setBackground(Color.WHITE);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(panel, BorderLayout.CENTER);
		
		bwButton = new JButton("New B/W Image");
		bwButton.addActionListener(this);
		colorButton = new JButton("New Color Image");
		colorButton.addActionListener(this);
		saveButton = new JButton("Save Image");
		saveButton.addActionListener(this);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(bwButton);
		buttonPanel.add(colorButton);
		buttonPanel.add(saveButton);
		this.getContentPane().add(buttonPanel,BorderLayout.SOUTH);
		
		this.pack();
		this.setVisible(true);
		
	}

	/**
	 * Makes a new RandomArtFrame with default parameters. title="Random Artwork"; width=500; height=500;
	 */
	public RandomArtFrame()
	{
		this("Random Artwork",500,500);
	}
	
	/**
	 * Creates a new piece of random artwork to display
	 * @param bw Whether the artwork should be in black-and-white (grayscale)
	 */
	public void createArtwork(boolean bw)
	{
		Expression expR = null; //expressions for possible color channels
		Expression expG = null;
		Expression expB = null;
		Expression expW = null;

		if(bw)
			expW = new RandomExpression(); //single expression
		else
		{
			expR = new RandomExpression(); //one expression for each color channel
			expG = new RandomExpression();
			expB = new RandomExpression();
		}

		double width = image.getWidth(); //to speed up method calling, slightly
		double height = image.getHeight();
		Color c;
		for(int i=0; i<width; i++)
		{
			for(int j=0; j<height; j++)
			{
				double w = (i/width-.5)*2; //scale to between -1 and 1
				double h = (j/height-.5)*2;
				if(bw)
				{
					float co = (float)(expW.evaluate(w,h)*.5+.5); //evaluate at w,h, then scale back to 0-1
					c = new Color(co,co,co); //could be more efficient, but easy to read
				}
				else
				{
					float r = (float)(expR.evaluate(w,h)*.5+.5); //evaluate at w,h, then scale back to 0-1
					float g = (float)(expG.evaluate(w,h)*.5+.5);
					float b = (float)(expB.evaluate(w,h)*.5+.5);
					c = new Color(r,g,b); //could be more efficient, but easy to read
				}
				image.setRGB(i,j,c.getRGB()); //color the pixel
			}
		}
		
		
	}
	
	/**
	 * Respond to buttons
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == bwButton)
			createArtwork(true);
		else if(e.getSource() == colorButton)
			createArtwork(false);
		else if(e.getSource() == saveButton)
			saveImage();
		panel.repaint();		
	}
	
	/**
	 * A private inner class that contains the panel that we're drawing on.
	 * We've overwritten paintComponent to specify our own painting.
	 */
	private class ImagePanel extends JPanel {
		public void paintComponent(Graphics g)
		{
			g.drawImage(image, 0, 0, null); //just draw the image on the canvas
		}
	}

	
    /** 
     * Saves the image to disk (in png format). The user is prompted for the save location.
     * @return true if successful, false if not
     */
    public boolean saveImage()
    {
        JFileChooser chooser = new JFileChooser();     // make a file chooser
        File imageFile = null;                         // name of image file
        FileNameExtensionFilter filter = new FileNameExtensionFilter(   // filter only for image files
        	"Graphic files (*.png)", "png");
        chooser.setFileFilter(filter);
        chooser.setCurrentDirectory(new File("C:\\"));                  // initial directory
        int returnVal = chooser.showSaveDialog(null);                   // let the user search for an image file
        if(returnVal == JFileChooser.APPROVE_OPTION)                    // if one is selected
        {
        	imageFile = chooser.getSelectedFile();
            // System.out.println("Trying to open : " + imageFile);
        	try{
        		ImageIO.write(image, "png", imageFile); 
        		return true;
        	}
        	catch (IOException e)
        	{
        		JOptionPane.showMessageDialog(null,  " File write error: " + e.getMessage());
        	}

        }
        return false;
      }

	/**
	 * A main method to create the frame
	 */
	public static void main(String[] args)
	{
		new RandomArtFrame();
	}

	
	
}

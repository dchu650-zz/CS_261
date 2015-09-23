import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

/**
 * A simple frame with two buttons that can be pressed, as a demonstration of GUI buttons.
 * 
 * @author joel
 * @version sp13
 */
public class ButtonFrame extends JFrame implements ActionListener
{
	//instance variables
	private JButton button1;
	private JButton button2;
	
	/**
	 * The constructor for the ButtonFrame
	 */
	public ButtonFrame()
	{
		super("Buttons!"); //call parent's constructor (give us a title)
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit on close

		//make the buttons, and add actionListener (me!) to them
		button1 = new JButton("One");
		button1.addActionListener(this);
		button2 = new JButton("Two");
		button2.addActionListener(this);
		
		JPanel buttonPanel = new JPanel(new GridLayout(1,2)); //a panel to hold and layout the buttons
		buttonPanel.setPreferredSize(new Dimension(300,200)); //how big we want the button panel to be
		buttonPanel.add(button1);
		buttonPanel.add(button2);
		getContentPane().add(buttonPanel); //add the panel to the frame's contentPane
		
		pack(); //squeeze everything to try and fit the preferred sizes
		setVisible(true); //show the frame
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == button1) //check which button was the source of the ActionEvent
		{
			//display a message
			JOptionPane.showMessageDialog(this, "Button One is clearly the superior button.");
		}
		else if(e.getSource() == button2)
		{
			JOptionPane.showMessageDialog(this, "Button Two: the choice of button pressers everywhere.");
		}
	}
	
	//to run the test
	public static void main(String[] args)
	{
		new ButtonFrame();
	}
}

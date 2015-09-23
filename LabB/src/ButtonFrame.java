import javax.swing.*;
import java.lang.Double;
import java.awt.*;
import java.awt.event.*;

/**
 * A simple frame with two buttons that can be pressed, as a demonstration of GUI buttons.
 * 
 * @author Darren Chu & Andrew Graham
 * DID NOT USE THE CALCULATOR.JAVA CLASS******
 * @version sp13
 */
public class ButtonFrame extends JFrame implements ActionListener
{
	//instance variables
	private boolean isAdded;
	private boolean isSubtracted;
	private boolean isMultiplied;
	private boolean isDivided;
	private String temp;
	private String tempSec;
	private String tempString;
	private String tempString2;
	private Double theDouble;
	private Double theDouble2;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;
	private JButton button7;
	private JButton button8;
	private JButton button9;
	private JButton button10;
	private JButton button11;
	private JButton button12;
	private JButton button13;
	private JButton button14;
	private JButton button15;
	private JButton button16;
	private JTextField text;
	
	/**
	 * The constructor for the ButtonFrame
	 */
	public ButtonFrame()
	{
		
		super("Buttons!"); //call parent's constructor (give us a title)
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit on close

		//Set the boolean control variables to false
		isAdded = false;
		isSubtracted = false;
		isMultiplied = false;
		isDivided = false;
		
		text = new JTextField();
		text.setHorizontalAlignment(JTextField.CENTER);
		text.setVisible(true);
		
		
		
		//make the buttons, and add actionListener (me!) to them
		button1 = new JButton("1");
		button1.addActionListener(this);
		
		button2 = new JButton("2");
		button2.addActionListener(this);
		
		button3 = new JButton("3");
		button3.addActionListener(this);
		
		button4 = new JButton("4");
		button4.addActionListener(this);
		
		button5 = new JButton("5");
		button5.addActionListener(this);
		
		button6 = new JButton("6");
		button6.addActionListener(this);
		
		button7 = new JButton("7");
		button7.addActionListener(this);
		
		button8 = new JButton("8");
		button8.addActionListener(this);
		
		button9 = new JButton("9");
		button9.addActionListener(this);
		
		button10 = new JButton("0");
		button10.addActionListener(this);
		
		button11 = new JButton("+");
		button11.addActionListener(this);
		
		button12 = new JButton("-");
		button12.addActionListener(this);
		
		button13 = new JButton("*");
		button13.addActionListener(this);
		
		button14 = new JButton("/");
		button14.addActionListener(this);
		
		button15 = new JButton("=");
		button15.addActionListener(this);
		
		button16 = new JButton(".");
		button16.addActionListener(this);
		
		JPanel buttonPanel = new JPanel(new GridLayout(3,3)); //a panel to hold and layout the buttons
		buttonPanel.setPreferredSize(new Dimension(300,200)); //how big we want the button panel to be
		//Adds a button onto the display
		buttonPanel.add(button1);
		buttonPanel.add(button2);
		buttonPanel.add(button3);
		buttonPanel.add(button4);
		buttonPanel.add(button5);
		buttonPanel.add(button6);
		buttonPanel.add(button7);
		buttonPanel.add(button8);
		buttonPanel.add(button9);	
		buttonPanel.add(button10);
		buttonPanel.add(button11);
		buttonPanel.add(button12);
		buttonPanel.add(button13);
		buttonPanel.add(button14);
		buttonPanel.add(button15);
		buttonPanel.add(button16);
		buttonPanel.add(text);
		
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
		//Take in the inputs from the action and displays it in the text box
		{	
				temp = text.getText();
				temp = temp + "1";
				text.setText(temp);	
		}
		else if(e.getSource() == button2)
		{			
				temp = text.getText();
				temp = temp + "2";
				text.setText(temp);
		}
		else if(e.getSource() == button3)
		{
				temp = text.getText();
				temp = temp + "3";
				text.setText(temp);
		}
		
		else if(e.getSource() == button4)
		{
				temp = text.getText();
				temp = temp + "4";
				text.setText(temp);
		}	
		else if(e.getSource() == button5)
			{
				temp = text.getText();
				temp = temp + "5";
				text.setText(temp);
			}	
		else if(e.getSource() == button6)
		
			{
				temp = text.getText();
				temp = temp + "6";
				text.setText(temp);
			}	
		else if(e.getSource() == button7)
			{
				temp = text.getText();
				temp = temp + "7";
				text.setText(temp);
			}	
		else if(e.getSource() == button8)
			{
				temp = text.getText();
				temp = temp + "8";
				text.setText(temp);
			}	
		else if(e.getSource() == button9)
			{
				temp = text.getText();
				temp = temp + "9";
				text.setText(temp);
			}	
		else if(e.getSource() == button10)
			{
				temp = text.getText();
				temp = temp + "0";
				text.setText(temp);
			}	
		else if(e.getSource() == button11)
		{
			temp = text.getText();
			theDouble = Double.parseDouble(temp);
			text.setText("");	
			isAdded=true; //Boolean control for the equal button. Tells equal button to add.
		}	
		else if(e.getSource() == button12)
		{
			temp = text.getText();
			theDouble = Double.parseDouble(temp);
			text.setText("");	
			isSubtracted=true;//Boolean control for the equal button. Tells equal button to subtract.
		}
		else if(e.getSource() == button13)
		{
			temp = text.getText();
			theDouble = Double.parseDouble(temp);
			text.setText("");	
			isMultiplied=true;//Boolean control for the equal button. Tells equal button to multiply.
		}
		else if(e.getSource() == button14)
		{
			temp = text.getText();
			theDouble = Double.parseDouble(temp);
			text.setText("");	
			isDivided=true; //Boolean control for the equal button. Tells equal button to divide.
		}
		
		else if(e.getSource() == button15)
		{
		
			if(isAdded == true)
			{
				tempSec = text.getText();
				theDouble = (theDouble + Double.parseDouble(tempSec));
				text.setText("" +theDouble);
				isAdded=false;	
			}
			
			else if(isSubtracted == true)
			{
				tempSec = text.getText();
				Double a = (theDouble - Double.parseDouble(tempSec));
				text.setText("" +a);
				isSubtracted=false;
			}
			else if(isMultiplied == true)
			{
				tempSec = text.getText();
				
				theDouble = (theDouble * Double.parseDouble(tempSec));
				text.setText("" +theDouble);
				isMultiplied=false;
			}
			else if(isDivided == true)
			{
				tempSec = text.getText();
				if(Double.parseDouble(tempSec) == 0)
				{
					text.setText("");
				}	
				theDouble = (theDouble / Double.parseDouble(tempSec));
				text.setText("" +theDouble);
				isDivided=false;
			}
		}
			
		else if(e.getSource() == button16)
		{
			text.setText(text.getText() + ".");
		}
	}
	
	
	//to run the test
	public static void main(String[] args)
	{
		new ButtonFrame();
	}
	

}

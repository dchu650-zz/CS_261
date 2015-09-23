import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.util.Random;

/**
 * A tester for the Necklass class
 * 
 * @author Joel Ross (adapted from Chuck Hommel)
 * @version Sp13
 */
public class Tester
{
    Necklace neck;
    NecklaceFrame frame;
    
    public static void main(String[] args)
    {
        Tester t = new Tester();
        String[] opts = {" Create new necklace ",
                " Remove from front ",
                " Random remove ",
                " Show Spectrum ",
                " Quit ",
            };
        int messageType = JOptionPane.QUESTION_MESSAGE;  // type of dialog box
        int choice;  // user choice
        do  // keep asking until user wants to quit
        {
            choice = JOptionPane.showOptionDialog (null, "Choose", "CS261 Necklace tester", 0, messageType, null, opts, opts[0]);
            switch (choice)
            {
                case 0: 		t.makeNewNecklace();
                break;
                case 1:         t.removeFromFront();
                break;
                case 2:         t.randomRemove();
                break;
                case 3:         t.showPalette();
                break;
            }
        } while (choice != 4);
    }

    /**
     * Constructor for objects of class Tester
     */
    public Tester() {}

    /**
     * run test to add Beads to a necklace
     */
    public void makeNewNecklace()
    {
        // test 1: add beads to necklace and display
        ArrayList<Bead> theBeads = new ArrayList<Bead>();
        for (int i = 0; i< 20; i++)
        { 
            Bead b = new Bead();
            theBeads.add(b);
        }
   
        neck = new Necklace();
        if(frame!=null)
        {
        	frame.setVisible(false);
        	frame.dispose();
        }
        frame = new NecklaceFrame(neck);
        System.out.println("Running tests on " + theBeads.size() + " Beads.");
        System.out.println("*********************************\n");
        System.out.println("Test: Adding Beads...");
        System.out.println("*********************************\n");
        for (Bead b : theBeads)
        {
            System.out.println("Adding a Bead with color " + b.getColor());
            neck.addBead(b);
            frame.refreshNecklace();
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {

            }
        }
        System.out.print("Expected number of Beads in necklace: " + theBeads.size() + " ...");

        if (neck.size() == theBeads.size())
        {
            System.out.println("Pass.");
        }
        else
        {
            System.out.println("Necklace reports " + neck.size() + ". Fail.");
        }
    }

    /**
     * Create a necklace and remove beads from front until empty.
     */
    public void removeFromFront()
    {
        // test 2: remove some beads, displaying
        neck = new Necklace();
        if(frame!=null)
        {
        	frame.setVisible(false);
        	frame.dispose();
        }
        frame = new NecklaceFrame(neck);
        ArrayList<Bead> theBeads = new ArrayList<Bead>();
        for (int i = 0; i< 20; i++)
        { 
            Bead b = new Bead();
            theBeads.add(b);
        }
        System.out.println("*********************************\n");
        System.out.println("Test: Removing Beads...");
        System.out.println("*********************************\n");
        for (Bead b : theBeads)  // make a necklace 
        {
            neck.addBead(b);
        }
        
        frame.refreshNecklace();
        System.out.println("Testing removing all Beads from front.");
        Color[] testColors = Palette.getPalette();
        int numRemoved = 0;
        int expectedCount = theBeads.size();
        for (Color c : testColors)
        {
            int count = 0;
            System.out.println("Removing Beads of color : " + c + " ...");
            Bead b = neck.removeBead(c);
            while (b != null)
            {
                if (b.getColor().getRGB() != c.getRGB())
                {
                    System.out.println("Incorrect color Bead returned.");
                }
                else
                {
                    System.out.println("Correct Bead returned.");
                    count++;
                }
                frame.refreshNecklace();
                try
                {
                    Thread.sleep(1000);
                }
                catch(InterruptedException e)
                {
                }
                b = neck.removeBead(c);
            }
            System.out.println("Done. " + count + " Beads removed.");
            numRemoved += count;
        }
        System.out.println("Expected to remove " + expectedCount + " Beads.");
        System.out.print("Actually removed " + numRemoved + " Beads...");
        if (expectedCount == numRemoved)
        {
            System.out.println("Pass. ");
        }
        else
        {
            System.out.println("Fail. ");
        }
    }

    /**
     * Randomly remove Beads from necklace
     */
    private void randomRemove()
    {
        // test 3. Remove beads randomly by selecting colors to remove
        neck = new Necklace();
        if(frame!=null)
        {
        	frame.setVisible(false);
        	frame.dispose();
        }
        frame = new NecklaceFrame(neck);
        ArrayList<Bead> theBeads = new ArrayList<Bead>();
        for (int i = 0; i< 20; i++)
        { 
            Bead b = new Bead();
            theBeads.add(b);
        }
        System.out.println("*********************************\n");
        System.out.println("Test: Randomly Removing Beads...");
        System.out.println("*********************************\n");
        for (Bead b : theBeads)  // make a necklace
        {
            neck.addBead(b);
        }

        frame.refreshNecklace();
        System.out.println("Testing removing Beads randomly.");
        Color[] testColors = Palette.getPalette();
        int numRemoved = 0;
        int startingCount = neck.size();
        Random randGen = new Random();   // generate random indices to testColors
        for (int i = 0; i < 20; i++ )  // run 20 tests)
        {
            int count = 0;
            Color c = testColors[randGen.nextInt(testColors.length)];
            System.out.println("Removing Bead of color : " + c + " ...");
            Bead b = neck.removeBead(c);
            if (b != null)
            {
                if (b.getColor().getRGB() != c.getRGB())
                {
                    System.out.println("Incorrect color Bead returned.");
                }
                else
                {
                    System.out.println("Correct Bead returned.");
                    count++;
                }
            }
            else
            {
                System.out.println("No Bead returned.");
            }
            frame.refreshNecklace();
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {
            }
            System.out.println("Done. " + count + " Beads removed.");
            numRemoved += count;
        }

        System.out.println("Initial necklace size: " + startingCount);
        System.out.println("Final necklace size:  " + neck.size());
        System.out.print("Actually removed " + numRemoved + " Beads...");        

        if (numRemoved + neck.size()  == startingCount)
        {
            System.out.println("Pass. ");
        }
        else
        {
            System.out.println("Fail. ");
        }

    }


    /**
     *  Show the palette for testing
     *  
     */
    private void showPalette()
    {
        Palette.showPalette();
    }
}

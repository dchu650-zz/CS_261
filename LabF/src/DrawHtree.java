
/**
 * Class that draws an "H-Tree" fractal
 * @author Darren Chu
 * @version 3.05.13
 */

public class DrawHtree extends FractalDrawing  {


	public DrawHtree(String title, int width, int height) {
		super(title, width, height); //uses the title and the dimensions of the super class
		drawHFractal(400, 300, 500, 8, .5); //hfractal, for demonstration purposes the iterations is set at 8

	}

	public void drawHFractal(int x1, int y1, int y2, int iterator, double ratio) {

		if(iterator != 0){ // anything but the first case

			super.show(); //display the final iteration
			iterator --; //decrease the iterator each time


			int legx1 = (int) (x1 - ((y2-y1)*ratio)); // first x coordinate for the legs of H, its length is half of that of the original vertical line
			int legx2 = (int) (x1 + ((y2-y1)*ratio));  //second x coordinate for the legs of H

			graphics.drawLine(x1, y1, x1, y2); //draw the vertical line
			graphics.drawLine(legx1, y1, legx2, y1); //draw the top leg
			graphics.drawLine(legx1, y2,legx2, y2); //draw the bottom leg

			int halfLeg = (int)((y2-y1)*ratio); // half leg that is used to find the y coordinates of the next legs

			drawHFractal((int) (x1 - halfLeg), y1 - halfLeg/2, y1 + halfLeg/2, iterator, ratio); //draw top left H
			
			drawHFractal((int) (x1 + halfLeg), y1 - halfLeg/2, y1 + halfLeg/2, iterator, ratio); //draw top right H
			
			drawHFractal((int) (x1 - halfLeg), y2 - halfLeg/2, y2 + halfLeg/2, iterator, ratio); //draw bottom left H
			
			drawHFractal((int) (x1 + halfLeg), y2 - halfLeg/2 ,y2 + halfLeg/2, iterator, ratio); //draw bottom right H
			
		}


	}

	@Override
	public void drawFractal() {


	}


}

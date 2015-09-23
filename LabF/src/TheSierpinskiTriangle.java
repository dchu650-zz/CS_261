
public class TheSierpinskiTriangle extends FractalDrawing
{
	

	public TheSierpinskiTriangle(String title, int width, int height) 
	{
		super(title, width, height);
	
		drawSierpinski(0,500,500,0,1000,500, 0);

	}

	public void drawSierpinski(int x1, int y1, int x2, int y2, int x3, int y3, int counter)
	{
		if(counter<=1)
		{
			super.show();
			graphics.drawLine(x1, y1, x2, y2);
			graphics.drawLine(x2, y2, x3, y3);
			graphics.drawLine(x3, y3, x1, y1);
			counter++;
			drawSierpinski((x3)*1/4, (y1)/2, x2, y2, (x3)*3/4, (y3)/2, counter);
			drawSierpinski(x1, y1, x2, y2, x3, y3, counter);
		}
	}

		
	
	@Override
	public void drawFractal() {
		// TODO Auto-generated method stub

	}

}

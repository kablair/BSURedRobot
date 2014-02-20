package scanning;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Point;
import java.awt.Robot;

/**This is the class that reads pixels from the screen. Other classes interpret the color, but this one gets it*/
public class ColorScanner {

	public static Color scanPixelColor(Point p)
	{
		try {
			Robot robot=new Robot();
			int x= (int) p.getX();
			int y= (int) p.getY();
			Color color = robot.getPixelColor(x,y);
			return color;
		} catch (AWTException e) {
			e.printStackTrace();
		}
		return null;
		
	
	}
	
}

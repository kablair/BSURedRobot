package scanning;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

/**Reads Pixels from screen, or captures images*/
public class ScreenReader {

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
	
	public static BufferedImage createScreenCapture(Rectangle screenRect)
	{
		try {
			Robot robot=new Robot();
			BufferedImage screenCapture;
			screenCapture= robot.createScreenCapture(screenRect);
			return screenCapture;
		} catch (AWTException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}

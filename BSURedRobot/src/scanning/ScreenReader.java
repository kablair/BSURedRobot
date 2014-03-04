package scanning;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

import exceptions.InvalidTileException;

/**Reads Pixels from screen, or captures images*/
public class ScreenReader {

//**************************************************************************************
//Screencap or ScreenRectCap
//**************************************************************************************	
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
	
//*************************************************************************************
//ScreenTiles
//*************************************************************************************
	
	public static BufferedImage readTileImage(int tileRow, int tileCol) throws InvalidTileException
	{
		Rectangle tileRect = ScreenTile.getTileRect(tileRow, tileCol);
		BufferedImage tileImage = ScreenReader.createScreenCapture(tileRect);
		return tileImage;
	}
	
	public static ScreenTile readScreenTile(int tileRow, int tileCol) throws InvalidTileException
	{
		BufferedImage tileImage = readTileImage(tileRow, tileCol);
		ScreenTile screenTile = new ScreenTile(tileImage);
		return screenTile;
	}
	
	public static ScreenTile[][] readScreenTiles() throws InvalidTileException
	{
		int maxCol= ScreenTile.getMaxscreentilecol();
		int maxRow = ScreenTile.getMaxscreentilerow();
		ScreenTile[][] screenTiles = new ScreenTile[maxCol][maxRow];
		for(int y=0; y<maxCol-1; y++)
		{
			for(int x=0; x<maxRow-1; x++)
			{
				screenTiles[y][x]=readScreenTile(x, y);
			}
		}
		return screenTiles;
	}
	
	
//******************************************************************************************
//Basic Testing Methods
//******************************************************************************************
	public static Point getPointerLocation()
	{
		Point p = MouseInfo.getPointerInfo().getLocation();
		return p;
	}
	public static Color findColorAtMouse()
	{
			Point p =getPointerLocation();
			Color color = ScreenReader.scanPixelColor(p);
			return color;
	}
	
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

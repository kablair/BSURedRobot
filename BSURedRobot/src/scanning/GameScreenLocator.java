package scanning;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import exceptions.GameScreenNotFoundException;

/**This class is capable of locating Pokemon Red on the screen. 
 * If the game is not on the screen or is only partially onscreen, it will display a message*/
public class GameScreenLocator {

	private static boolean isAlive;
	
	private static final int gameWidth=ScannerMain.gameWidth;
	private static final int gameHeight=ScannerMain.gameHeight;

	private static Dimension screenSize;
	private static int screenWidth;
	private static int screenHeight;
	private static Point gameLocation;
	private static boolean isGameFound;
	
	private static int xcount=0; //used for testing algorithm efficiency
	private static int ycount=0;
	
	public static void initialize()
	{
		isAlive=true;
		setGameFound(false);
		
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	
		gameLocation = new Point();
		xcount=0;
		ycount=0;
	}
	
	public static void kill()
	{
		isAlive=false;
	}

	private static void setGameLocation(Point location)
	{
		gameLocation.setLocation(location);
	}
	public static Point getGameLocation()
	{
		return gameLocation;
	}
	//works!
	public static void findTopLeftOfGameScreen() throws GameScreenNotFoundException
	{
		Point partOfScreen =findPartOfGameScreen();
		if(isGameFound())
		{
			System.out.println("found");
			Point topleft= new Point();
			Point botright= new Point();
			int y =findTopOfGameScreen(partOfScreen);
			int x= findLeftOfGameScreen(partOfScreen);
			topleft.setLocation(x, y);
			boolean isBotRightOnScreen;
			botright.setLocation(x+gameWidth-1, y-gameHeight+1);
			
			if(GameColor.isGameColor(ScreenReader.scanPixelColor(topleft)))
			{
				System.out.println(topleft);
			}
			if(GameColor.isGameColor(ScreenReader.scanPixelColor(botright)))
			{
				System.out.println(botright);
				isBotRightOnScreen=true;
				System.out.println("Entire game found");
			}
			else 
				{
					isBotRightOnScreen=false;
					System.out.println("Partial game found");
					throw new GameScreenNotFoundException("Partial game found. Game is obstructed.");
				}
			
			if(isBotRightOnScreen)
			{
				gameLocation.setLocation(topleft);
			}
		}
		else
			System.out.println("not found");
		
	
	
	}
		
	private static int findTopOfGameScreen(Point partOfScreen)
	{
		int highestY;
		Point highestPoint= new Point();
		highestPoint.setLocation(partOfScreen);
		Point testPoint =  new Point();
		testPoint.setLocation(partOfScreen);
		
		int manualSteps=12;
		int divider=6;
		int verticalStep=gameWidth/divider;
		
		while(verticalStep>manualSteps)
		{
			//while testPoint is a game color
			while(GameColor.isGameColor(ScreenReader.scanPixelColor(testPoint)))
			{
				highestPoint.setLocation(testPoint);
				testPoint.setLocation(highestPoint.getX(), highestPoint.getY()+verticalStep);
				ycount++;
			}
			testPoint.setLocation(highestPoint);
			divider=divider*2;
			verticalStep=gameWidth/divider;
		}
	
		
		while(GameColor.isGameColor(ScreenReader.scanPixelColor(testPoint)))
		{
			highestPoint.setLocation(testPoint);
			testPoint.setLocation(highestPoint.getX(), highestPoint.getY()+1);
			ycount++;
		}
		
		highestY= (int) (highestPoint.getY());
		return highestY;
	}
	
	private static int findLeftOfGameScreen(Point partOfScreen)
	{
		int leftmostX;
		Point leftmost= new Point();
		leftmost.setLocation(partOfScreen);
		Point testPoint = new Point();
		testPoint.setLocation(partOfScreen);
		
		int manualSteps=10;
		int divider=4;
		int horizontalStep=gameWidth/divider;
		while(horizontalStep>manualSteps)//horizontalStep>manualSteps);
		{
			
			//while testPoint is a game color
			while(GameColor.isGameColor(ScreenReader.scanPixelColor(testPoint)))
			{
				
				leftmost.setLocation(testPoint);
				testPoint.setLocation(leftmost.getX()-horizontalStep, leftmost.getY());
				xcount++;
			}
			testPoint.setLocation(leftmost);
			divider=divider*3;
			horizontalStep=gameWidth/divider;
		}
	
		while(GameColor.isGameColor(ScreenReader.scanPixelColor(testPoint)))
		{
			leftmost.setLocation(testPoint);
			testPoint.setLocation(leftmost.getX()-1, leftmost.getY());
			xcount++;
		}
		System.out.println(xcount);
		
		leftmostX= (int) (leftmost.getX());
		return leftmostX;
		
	}
	
		private static Point findPartOfGameScreen() throws GameScreenNotFoundException
		{
			Point p= new Point();
			int x=0;
			int y=0;
			int horizontalStep=gameWidth/2;
			int verticalStep=gameHeight/2;
			
			while(y<screenHeight)
			{
				while(x<screenWidth)
				{
					p.setLocation(x, y);
					Color color =ScreenReader.scanPixelColor(p);
					System.out.println(color);
					if(GameColor.isGameColor(color))
					{
						setGameFound(true);
						return p;
					}
					x+=horizontalStep;
					System.out.println(color);
				}
				x=0;
				y+=verticalStep;
			
			}
			//if you get here, there is something wrong
			p.setLocation(-1, -1);
			setGameFound(false);
			throw new GameScreenNotFoundException("Game is not on screen or is obstructed");
			
		}
	

	
	//****************************************************************
	//Determines the screen size to search
	//****************************************************************
	private static Dimension getScreenSize()
	{
		return Toolkit.getDefaultToolkit().getScreenSize();
	}

	private static int getScreenWidth()
	{
		int width = (int) getScreenSize().getWidth();
		return width;
	}
	
	private static int getScreenHeight()
	{
		int height = (int) getScreenSize().getHeight();
		return height;
	}
	
	//**************************************************************************
	//Mouse Stuff for Testing
	//**************************************************************************

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

	public static boolean isGameFound() {
		return isGameFound;
	}

	private static void setGameFound(boolean isGameFound) {
		GameScreenLocator.isGameFound = isGameFound;
	}
	
	
}
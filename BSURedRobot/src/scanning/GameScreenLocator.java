package scanning;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import exceptions.GameScreenNotFoundException;

public class GameScreenLocator {

	//Another thing to do: if only a partial screen is discovered, try to find how it is being 
	//blocked. Maybe we can still use some data.
		private static BufferedImage screen;
		private static int width;
		private static int height;
		static boolean gameFound;
		
		public static void initialize()
		{
			gameFound=false;
			//Reading screensize
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			width= (int) screenSize.getWidth();
			height= (int) screenSize.getHeight();
			captureScreen();
			
		}
		
		private static void captureScreen()
		{
			Rectangle screenRect= new Rectangle();
			screenRect.setBounds(0, 0, width, height);
			screen=ScreenReader.createScreenCapture(screenRect);
		}
		public static Point findGameLocation() throws GameScreenNotFoundException
		{
			
			captureScreen();
			Point topleft = new Point();
			Point botright= new Point();
			Point partOfGame = findPartOfGame();
			int y= findTopOfGameScreen(partOfGame);
			int x= findLeftOfGameScreen(partOfGame);
			topleft.setLocation(x, y);
			System.out.println("Screen Size: " +Toolkit.getDefaultToolkit().getScreenSize());
			botright.setLocation(x+ScannerMain.gameWidth-1, y+ScannerMain.gameHeight-1);
			System.out.println("TopLeft "+topleft);
			System.out.println("BotRight "+botright);
			/* Sometimes if the screen is partial, the predicted value of botright 
			 * will be outside of screen. This is why we check here; it prevents an
			 * out of bounds exception
			 */
			if(botright.x<=screen.getWidth() && botright.y <=screen.getHeight())
			{
				
				if(GameColor.isGameColor(getColorFromScreenCoord(botright.x, botright.y)))
				{
					System.out.println("Screen Found");
					return topleft;
				}
				else
					throw new GameScreenNotFoundException("Partial Screen Only");
			}
			
			else
			throw new GameScreenNotFoundException("Partial Screen Only, off screen");
		}
		
		private static int findTopOfGameScreen(Point partOfGame) {
			
			int x = partOfGame.x;
			int highestY= partOfGame.y;
			int testY= highestY;
			
			while(GameColor.isGameColor(getColorFromScreenCoord(x,testY)))
			{
				highestY=testY;
				testY--;
			}
			return highestY;
		}
		
		private static int findLeftOfGameScreen(Point partOfGame) {
			
			int leftmostX = partOfGame.x;
			int y= partOfGame.y;
			int testX= leftmostX;
			
			while(GameColor.isGameColor(getColorFromScreenCoord(testX,y)))
			{
				leftmostX=testX;
				testX--;
			}
			return leftmostX;
		}
		
		

		private static Point findPartOfGame() throws GameScreenNotFoundException
		{
			int horizontalSteps = ScannerMain.gameWidth/2;
			int verticalSteps = ScannerMain.gameHeight/2;
			
			//int htests = width/horizontalSteps;
			//int vtests = height/verticalSteps;
			
			Point gamePoint = new Point();
			Color testPixel;
			String hexString;
			for(int y=0; y<height; y+=verticalSteps)
			{
				for(int x=0; x<width; x+=horizontalSteps)
				{
					
					testPixel=getColorFromScreenCoord(x,y);
					if(GameColor.isGameColor(testPixel))
						{
							gamePoint.setLocation(x, y);
							return gamePoint;
						}
				}
			}
			throw new GameScreenNotFoundException("Game is missing or obstructed");
		}
		
		private static Color getColorFromScreenCoord(int x, int y)
		{
			
			String hexString= String.format("%06d",screen.getRGB(x, y));
			Color screenColor=Color.decode(hexString);
			return screenColor;
		}
}

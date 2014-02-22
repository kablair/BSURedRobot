package scanningtest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import scanning.GameColor;
import scanning.ScannerMain;
import scanning.ScreenReader;
import exceptions.GameScreenNotFoundException;

public class GameScreenLocator3 {

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
			botright.setLocation(x+ScannerMain.gameWidth, y+ScannerMain.gameHeight);
			String hexString =String.format("%06d", screen.getRGB(botright.x-1, botright.y-1));
			if(GameColor.isGameColor(Color.decode(hexString)))
			{
				System.out.println(topleft);
				return topleft;
			}

			else
			throw new GameScreenNotFoundException("Partial Screen Only");
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
			System.out.println(highestY);
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
			System.out.println(leftmostX);
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

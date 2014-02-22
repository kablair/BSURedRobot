package scanningtest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;

import exceptions.GameScreenNotFoundException;
import scanning.GameColor;
import scanning.ScannerMain;
import scanning.ScreenReader;

public class OtherGameScreenLocator {
	private static BufferedImage screen;
	private static int width;
	private static int height;
	static int pixels[][];
	static boolean gameFound;
	
	public static void initialize()
	{
		gameFound=false;
		//Reading screensize
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle screenRect= new Rectangle();
		width= (int) screenSize.getWidth();
		height= (int) screenSize.getHeight();
		screenRect.setBounds(0, 0, width, height);
		
		//captures screen
		pixels = new int [height][width];
		screen=ScreenReader.createScreenCapture(screenRect);
	}
	
	public static Point findGameLocation() throws GameScreenNotFoundException
	{
		Point topleft = new Point();
		Point botright= new Point();
		Point partOfGame = findPartOfGame();
		int y= findTopOfGameScreen(partOfGame);
		int x= findLeftOfGameScreen(partOfGame);
		
		topleft.setLocation(x, y);
		botright.setLocation(x+ScannerMain.gameWidth, y+ScannerMain.gameHeight);
		String hexString =String.format("%06d", screen.getRGB(botright.x, botright.y));
		if(GameColor.isGameColor(Color.decode(hexString)))
		{
			System.out.println(topleft.getX());
			return topleft;
		}

		else
		throw new GameScreenNotFoundException("Partial Screen Only");
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
				hexString= String.format("%06d",screen.getRGB(x, y));
				testPixel=Color.decode(hexString);
				if(GameColor.isGameColor(testPixel))
					{
						gamePoint.setLocation(x, y);
						return gamePoint;
					}
			}
		}
		
		
	
		//width * verticalSteps*v +h*horizontalSteps
		//
//		System.out.println(screen.getRaster().getDataBuffer().getSize() +""+width*height);
//		for(int col=0; col<height-1; col++)
//		{
//			for (int row=0; row<width-1; row++)
//			pixels[col][row] = (screen.getRaster().getDataBuffer().getElem(col*width +row));
//			
//		}
		
//		for(int vcount=0; vcount<vtests; vcount++)
//		{
//			for (int hcount=0; hcount<htests; hcount++)
//			{
//				//int index = (width * verticalSteps*vcount) +(hcount*horizontalSteps);
//				//pixels = (screen.getRaster().getDataBuffer()).getElem(400);
//				//BufferedImage.
//			}
//		}

//		System.out.println(pixels);
//		
		
		gameFound=false;
		throw new GameScreenNotFoundException("Game is not on screen or is obstructed");
	}
	private static int findTopOfGameScreen(Point partOfScreen)
	{
		int highestY=(int) partOfScreen.getY();
		int x= (int) partOfScreen.getX();
		
		int manualSteps=12;
		int divider=6;
		int verticalStep=ScannerMain.gameHeight/divider;
		int testY=highestY;
		
		String hexString;
		Color testColor;
		
		while(verticalStep>manualSteps)
		{
			hexString= String.format("%06d",screen.getRGB(x, highestY));
			testColor=Color.decode(hexString);
			while(GameColor.isGameColor(testColor))
				
			{
				highestY= testY; 
				testY= highestY -verticalStep;
				hexString= String.format("%06d",screen.getRGB(x, highestY));
				testColor=Color.decode(hexString);
				
			}
			testY=highestY;
			divider=divider*2;
			verticalStep=ScannerMain.gameHeight/divider;
		}
	
			hexString = String.format("%06d",screen.getRGB(x, testY));
		while(GameColor.isGameColor(Color.decode(hexString)) && testY>1)
		{
			highestY=testY;
			System.out.println(testY);
			//String representation of Color
			testY--;
			hexString= String.format("%06d",screen.getRGB(x, testY));
		}
		return highestY;
	}

	private static int findLeftOfGameScreen(Point partOfScreen)
	{
		int y=(int) partOfScreen.getY();
		int leftMostX= (int) partOfScreen.getX();
		
		int manualSteps=12;
		int divider=6;
		int horizontalStep=ScannerMain.gameWidth/divider;
		int testX=leftMostX;
		
		String hexString;
		Color testColor;
		
		while(horizontalStep>manualSteps)
		{
			hexString= String.format("%06d",screen.getRGB(leftMostX, y));
			testColor=Color.decode(hexString);
			while(GameColor.isGameColor(testColor))
			{
				leftMostX= testX; 
				testX= leftMostX -horizontalStep;
				hexString= String.format("%06d",screen.getRGB(leftMostX, y));
				testColor=Color.decode(hexString);
			}
			testX=leftMostX;
			divider=divider*2;
			horizontalStep=ScannerMain.gameWidth/divider;
		}
		hexString = String.format("%06d",screen.getRGB(testX, y));
		while(GameColor.isGameColor(Color.decode(hexString)))
		{
			leftMostX=testX;
			testX--;
			//String representation of Color
			hexString= String.format("%06d",screen.getRGB(testX, y));
			
		}
		
		return leftMostX;
	}
	

}

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
		return findPartOfGame();
	}
	
	private static Point findPartOfGame() throws GameScreenNotFoundException
	{
		int horizontalSteps = ScannerMain.gameWidth/2;
		int verticalSteps = ScannerMain.gameHeight/2;
		
		int htests = width/horizontalSteps;
		int vtests = height/verticalSteps;
		
		Color testPixel;
		String hexString;
		for(int y=0; y<height; y+=verticalSteps)
		{
			for(int x=0; x<width; x+=horizontalSteps)
			{
				hexString= String.format("%06d",screen.getRGB(x, y));
				testPixel=Color.decode(hexString);
				if(GameColor.isGameColor(testPixel))
				System.out.println(testPixel);
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

	

}

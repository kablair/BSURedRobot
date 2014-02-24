package scanning;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import data_storage.TileLoader;
import data_storage.TileWriter;
import exceptions.GameScreenNotFoundException;
import exceptions.InvalidTileException;
import exceptions.IsDisabledException;

public class ScannerMain {

	public static final int gameWidth=640;
	public static final int gameHeight=576;
	public static final int tileSize = 64;
	
	private static Point gameScreenLocation;
	private static boolean enabled;
	public static void initialize()
	{
		gameScreenLocation = new Point();
		enabled=true;
		try 
		{
			GameScreenLocator.initialize();
			setGameScreenLocation();
			ScreenTile tile1 = new ScreenTile(4,2);
			//TileWriter.writeTile(tile1, "oakhead");
			String tileDataOak[][] =TileLoader.loadTile("oakhead");
			if(tile1.isTileDataEqual(tileDataOak))
			{
				System.out.println("oak is on screen");
			}
			else
			{
				System.out.println("oakhead not found");
			}
			
		} 
		catch (GameScreenNotFoundException e) 
		{	
			System.out.println(e.getMessage());
		} 
		catch (InvalidTileException e) {
			System.out.println(e.getMessage());
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deactivate()
	{
		enabled=false;
	}
	
	public static boolean isEnabled()
	{
		return enabled;
	}

	
//	public static BufferedImage captureGameScreen() throws IsDisabledException
//	{
//		BufferedImage screenCapture;
//		if(enabled)
//		{
//			screenCapture = ScreenReader.createScreenCapture(getScreenRect());
//			return screenCapture;
//		}
//		else throw new IsDisabledException();
//	}

	/**There are 10 hTiles and 9 yTiles
	 * @param xTile = a value ranging from 0 to 9
	 * @param ytile = a value ranging from 0 to 8*/
//	public static BufferedImage captureGameTile(int xTile, int yTile) throws IsDisabledException
//	{
//		BufferedImage tileCapture;
//		if(enabled)
//		{
//			tileCapture = ScreenReader.createScreenCapture(getTileRect(xTile, yTile));
//			return tileCapture;
//		}
//	 else throw new IsDisabledException();
//	}
	
//	private static Rectangle getScreenRect()
//	{
//			Rectangle gameScreenRect = new Rectangle();
//			int x = (int) gameScreenLocation.getX();
//			int y = (int) gameScreenLocation.getY();
//			gameScreenRect.setBounds(x, y, gameWidth, gameHeight);
//			return gameScreenRect;
//
//	}

//	private static Rectangle getTileRect(int xTile, int yTile)
//	{
//		Rectangle tileRect = new Rectangle();
//		int x = (int) gameScreenLocation.getX()+xTile*tileSize;
//		int y = (int) gameScreenLocation.getY()+yTile*tileSize;
//		tileRect.setBounds(xTile, yTile, tileSize, tileSize);
//		return tileRect;
//	}
	


	public static void setGameScreenLocation() throws GameScreenNotFoundException
	{
		Point location =GameScreenLocator.findGameLocation(); 
			gameScreenLocation.setLocation(location);
	}
	
	public static Point getGameScreenLocaton()
	{
		return gameScreenLocation;
	}
	
	public static int getTileSize()
	{
		return tileSize;
	}
	
}

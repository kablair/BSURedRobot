package scanning;

import java.awt.Point;
import exceptions.GameScreenNotFoundException;
import exceptions.InvalidTileException;
import exceptions.ScanningDisabledException;

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
		GameScreenLocator.initialize();
		setGameScreenLocation();
	}
	
	public static void deactivate()
	{
		enabled=false;
	}
	
	public static boolean isEnabled()
	{
		return enabled;
	}

	public static void setGameScreenLocation()
	{
		try 
		{
			Point location = GameScreenLocator.findGameLocation();
			gameScreenLocation.setLocation(location);
		} 
		catch (GameScreenNotFoundException e) 
		{
			deactivate();
			e.printStackTrace();
			System.exit(0);
		} 
		
	}
	
	public static ScreenTile[][] getScreenTiles() throws InvalidTileException, ScanningDisabledException
	{
		if(isEnabled())
		{
			ScreenTile [][] screenTiles = ScreenReader.readScreenTiles();
			return screenTiles;
		}
		else throw new ScanningDisabledException("Error: Tried to read screenTiles when ScannerMain was disabled.");
				
	}

	public static ScreenTile getScreenTile(int screenRow, int screenCol) throws InvalidTileException, ScanningDisabledException
	{
		if(isEnabled())
		{
			ScreenTile screenTile = ScreenReader.readScreenTile(screenRow, screenCol);
			return screenTile;
		}
		else throw new ScanningDisabledException("Error: Tried to read screenTiles when ScannerMain was disabled.");
				
	}
	public static Point getGameScreenLocaton() throws ScanningDisabledException
	{
		if(isEnabled())
		return gameScreenLocation;
		else throw new ScanningDisabledException("Error: Tried to get screen location when ScannerMain was disabled.");
	}
	
	public static long sampleScreenTile(int tileRow, int tileCol) throws InvalidTileException, ScanningDisabledException
	{
		if(isEnabled())
		return ScreenReader.sampleScreenTile(tileRow, tileCol);
		else throw new ScanningDisabledException("Error: Tried to get screen data when ScannerMain was disabled.");
	}
	
	public static long[][] sampleScreenTiles() throws InvalidTileException, ScanningDisabledException
	{
		if(isEnabled())
		return ScreenReader.sampleScreenTiles();
		else throw new ScanningDisabledException("Error: Tried to get screen data when ScannerMain was disabled.");
	}

	
}

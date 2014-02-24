package scanning;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import exceptions.InvalidTileException;

public class ScreenTileReader {
	
	public static ScreenTile readScreenTile(int screenTileX, int screenTileY) throws InvalidTileException
	{
		ScreenTile screenTile = new ScreenTile(screenTileX, screenTileY);
		//ScreenTile.getTileLocation(screenTileX, screenTileY);
		Rectangle tileRect = ScreenTile.getTileRect(screenTileX, screenTileY);
		//Rectangle tileRect =screenTile.getTileRectangle();
		BufferedImage tileImage = ScreenReader.createScreenCapture(tileRect);
		screenTile.setImage(tileImage);
		//screenTile.getIdentity()
		return null;
	}

}

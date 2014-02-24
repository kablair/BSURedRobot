package scanning;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ScreenTileInterpretter {

public String[][] getTileData(ScreenTile tile)
{
	String tileArray[][] = new String[ScreenTile.getTilesize()][ScreenTile.getTilesize()];
	BufferedImage tileImage =tile.getImage();
	for(int y=0; y<tileImage.getHeight(); y++)
	{
		for(int x=0; x<tileImage.getWidth(); x++)
		{
			tileArray[y][x]=String.format("%06d",tile.getImage().getRGB(x, y));
		}
	}
	return tileArray;
}
	
	
}

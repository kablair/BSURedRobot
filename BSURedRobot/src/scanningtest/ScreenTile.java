package scanningtest;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import scanning.ScannerMain;
import exceptions.InvalidTileException;
public class ScreenTile {

	private static final int tileXMax=9;
	private static final int tileYMax=8;
	private static final int tileSize=64;
	private BufferedImage image;
	private int tileX;
	private int tileY;
	public ScreenTile (int tileX, int tileY) throws InvalidTileException
	{
		if (isOutOfBounds(tileX, tileY))
			throw (new InvalidTileException());
		else
		{
			this.tileX=this.tileY;
			this.tileY=this.tileX;
		}
	}
	
	public ScreenTile (BufferedImage image, int tileX, int tileY) throws InvalidTileException
	{
		if (isOutOfBounds(tileX, tileY))
			throw (new InvalidTileException());
		else
		{
			this.tileX=this.tileY;
			this.tileY=this.tileX;
			this.image=image;
		}
	}
	
	
	
	private static boolean isOutOfBounds(int tileX, int tileY)
	{
		if(tileX<0)
			return true;
		else if(tileX>tileXMax)
			return true;
		else if(tileY<0)
			return true;
		else if(tileY<tileYMax)
			return true;
		else
			return false;
	}
	
	public Rectangle getTileRect()
	{
		Rectangle tileRect = new Rectangle();
		int x = (int) ScannerMain.getGameScreenLocaton().getX()+tileX*tileSize;
		int y = (int) ScannerMain.getGameScreenLocaton().getY()+tileY*tileSize;
		tileRect.setBounds(x, y, tileSize, tileSize);
		return tileRect;
	}
	
	
	
}

package scanning;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import exceptions.InvalidTileException;

public class ScreenTile {

	private static final int maxScreenTileRow=9;
	private static final int maxScreenTileCol=8;
	private static final int tileSize=64;
	private int screenTileRow;
	private int screenTileCol;
	private BufferedImage image;
	String tileArray[][];
	//Null image exception?
	

	//*****************************************************************************
//Tile Setup
//*****************************************************************************
	public ScreenTile(int screenTileRow, int screenTileCol) throws InvalidTileException {
		setScreenTileRow(screenTileRow);
		setScreenTileCol(screenTileCol);
		setImage(ScreenReader.getTileImage(this));
	}

	public ScreenTile(int screenTileRow, int screenTileCol, BufferedImage image) throws InvalidTileException
	{
		setScreenTileRow(screenTileRow);
		setScreenTileCol(screenTileCol);
		setImage(image);
	}
	
	public void setImage(BufferedImage image) throws InvalidTileException
	{
		if(image.getWidth()==tileSize &&image.getHeight()==tileSize)
		{
			this.image=image;
		}
		else throw new InvalidTileException("Tile image has invalid dimensions");
	}
	
	public void setScreenTileRow(int screenTileRow) throws InvalidTileException
	{
		if(isInRowBounds(screenTileRow))
		{
			this.screenTileRow=screenTileRow;
		}
		else throw new InvalidTileException("Tile row is out of bounds");
	}
	
	public void setScreenTileCol(int screenTileCol) throws InvalidTileException
	{
		if(isInColBounds(screenTileCol))
		{
			this.screenTileCol=screenTileCol;
		}
		else throw new InvalidTileException("Tile column is out of bounds");
	}
//**********************************************************************************
//Get Tile Location
//**********************************************************************************
	//TODO Check
	public static Point getTileLocation(int screenTileRow, int screenTileCol)
	{
		Point start = ScannerMain.getGameScreenLocaton();

		Point tileLocation= new Point();
		int deltaX = screenTileRow*tileSize;
		int deltaY= screenTileCol*tileSize;
		tileLocation.setLocation(start.x+ deltaX, start.y +deltaY);
		return tileLocation;
	}
	
	public Point getTileLocation()
	{
		return ScreenTile.getTileLocation(getScreenTileRow(), getScreenTileCol());
	}
//************************************************************************************
//Get Tile Rect
//************************************************************************************
	public Rectangle getTileRect()
	{
		Rectangle tileRect = new Rectangle();
		Point tilePoint=this.getTileLocation();
		int screenX= tilePoint.x;
		int screenY= tilePoint.y;
		tileRect.setBounds(screenX, screenY, tileSize, tileSize);
		return tileRect;
	}
	
	public static Rectangle getTileRect(int screenTileRow, int screenTileCol) throws InvalidTileException
	{
		if(isInRowBounds(screenTileRow) && isInColBounds(screenTileCol))
		{
			Rectangle tileRect = new Rectangle();
			Point tilePoint=getTileLocation(screenTileRow, screenTileCol);
			int screenX= tilePoint.x;
			int screenY= tilePoint.y;
			tileRect.setBounds(screenX, screenY, tileSize, tileSize);
			return tileRect;
		}
		else throw new InvalidTileException("Invalid tile dimensions");
	}
	
//****************************************************************************************
//Get tileData[][]
//****************************************************************************************
	public static String[][] getTileData(ScreenTile tile)
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
	
	public String[][] getTileData()
	{
		String tileArray[][] = new String[ScreenTile.getTilesize()][ScreenTile.getTilesize()];
		BufferedImage tileImage =this.getImage();
		for(int y=0; y<tileImage.getHeight(); y++)
		{
			for(int x=0; x<tileImage.getWidth(); x++)
			{
				tileArray[y][x]=String.format("%06d",this.getImage().getRGB(x, y));
			}
		}
		return tileArray;
	}
//********************************************************************************************
//Compare Tile Data
//********************************************************************************************
	public static boolean isRightSize(String tileData[][])
	{
		boolean isRightSize= false;
		if(tileData.length ==tileSize && tileData[tileSize-1].length==tileSize)
		{
			isRightSize=true;
		}
		return isRightSize;
	}
	
	public boolean isTileDataEqual(String tileData1[][]) throws InvalidTileException
	{
		String tileData2[][]=this.getTileData();
		return isTileDataEqual(tileData1, tileData2);
			
	}
	
	public boolean isTileDataEqual(ScreenTile tile) throws InvalidTileException
	{
		String tileData1[][] = this.getTileData();
		String tileData2[][]= tile.getTileData();
		return isTileDataEqual(tileData1, tileData2);	
	}
	
	public static boolean isTileDataEqual(String[][] tileData1, String[][] tileData2) throws InvalidTileException
	{
		boolean equals = true;
		if (isRightSize(tileData1)&&isRightSize(tileData2))
		{
			for(int y=0; y<tileData1.length; y++)
			{
				for(int x=0; x<tileData1[tileSize-1].length; x++)
				{
					if(!tileData1[y][x].equals(tileData2[y][x]))
						equals=false;
				}
			}
			return equals;
		}
		else throw new InvalidTileException("Tile data is the wrong size");
			
	}
	
//****************************************************************************************
//Row and Col Checkers
//*****************************************************************************************
	private static boolean isInRowBounds(int tileRow)
	{
		if(tileRow<0) 
			return false;
		else if(tileRow>maxScreenTileRow)
			return false;
		else return true;
	}
	
	private static boolean isInColBounds(int tileCol)
	{
		if(tileCol<0) 
			return false;
		else if(tileCol>maxScreenTileCol)
			return false;
		else return true;
	}
	
//***************************************************************************************
//Getters and Setters
//***************************************************************************************
	public int getScreenTileRow()
	{
		return screenTileRow;
	}
	
	public int getScreenTileCol()
	{
		return screenTileCol;
	}
	public BufferedImage getImage()
	{
		return image;
	}
	public static int getTilesize() {
		return tileSize;
	}
}

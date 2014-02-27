package scanning;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import exceptions.InvalidTileException;
import exceptions.ScanningDisabledException;

/**A class that represents an in-game tile. Contains an image, row, and col.**/
public class ScreenTile {

	private static final int maxScreenTileRow=9;
	private static final int maxScreenTileCol=8;
	private static final int tileSize=64;
	private static final int arraySize=tileSize/4;
	private int screenTileRow;
	private int screenTileCol;
	private BufferedImage image;
	private int tileData[][];
	private int tileId[];
	//Null image exception?
	

//*****************************************************************************
//Tile Setup
//*****************************************************************************
/**A class that represents an in-game tile. Contains an image, row, and col.
	 * @param BufferedImage image*/
	public ScreenTile(BufferedImage image) throws InvalidTileException {
		setImage(image);
	}
	
/**A class that represents an in-game tile. Contains an image, row, and col.
	 * @param BufferedImage image*/
	public ScreenTile(int[][] tileData) throws InvalidTileException {
		if(isRightSize(tileData))
			this.tileData=tileData;
		else
		{
			throw new InvalidTileException("Tile data is the wrong size.");
		}
	}
	
	/**A class that represents an in-game tile. Contains an image, row, and col.
	 * @param int screenTileRow
	 * @param int screenTileCol**/
	public ScreenTile(int screenTileRow, int screenTileCol) throws InvalidTileException {
		setScreenTileRow(screenTileRow);
		setScreenTileCol(screenTileCol);
		setImage(ScreenReader.readTileImage(this));
	}

	/**A class that represents an in-game tile. Contains an image, row, and col.
	 * @param int screenTileRow
	 * @param int screenTileCol
	 * @param BufferedImage image**/
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
		Point tileLocation= new Point();
		try {
			
			Point start = ScannerMain.getGameScreenLocaton();
			int deltaX = screenTileRow*tileSize;
			int deltaY= screenTileCol*tileSize;
			tileLocation.setLocation(start.x+ deltaX, start.y +deltaY);
			return tileLocation;
		} 
		catch (ScanningDisabledException e) {
			e.printStackTrace();
		}

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
	public static int[][] getTileData(ScreenTile tile)
	{
		if(tile.tileData==null)
		{
			tile.tileData=findTileData(tile);
		}
		return tile.tileData;
	}
	
	public int[][] getTileData()
	{
		if(this.tileData==null)
		{
			this.tileData=findTileData(this);
		}
		return this.tileData;
	}
	
	private static int[][] findTileData(ScreenTile tile)
	{
		int tileArray[][] = new int[ScreenTile.getArraysize()][ScreenTile.getArraysize()];
		BufferedImage tileImage =tile.getImage();
		for(int y=0; y<tileImage.getHeight(); y+=4)
		{
			for(int x=0; x<tileImage.getWidth(); x+=4)
			{
				String tileString=String.format("%06d",tile.getImage().getRGB(x, y));
				System.out.println(tileString);
				int tileInt=Integer.parseInt(tileString);
				tileArray[y/4][x/4]= tileInt;
			}
		}
		return tileArray;
	}
	
//********************************************************************************************
//Compare Tile Data
//********************************************************************************************
	//TODO divide by 4 arraySize
	public static boolean isRightSize(int tileData[][])
	{
		boolean isRightSize= false;
		if(tileData.length ==getArraysize() && tileData[getArraysize()-1].length==getArraysize())
		{
			isRightSize=true;
		}
		return isRightSize;
	}
	
	public boolean isTileDataEqual(int tileData1[][]) throws InvalidTileException
	{
		int tileData2[][]=this.getTileData();
		return isTileDataEqual(tileData1, tileData2);
			
	}
	
	public boolean isTileDataEqual(ScreenTile tile) throws InvalidTileException
	{
		int tileData1[][] = this.getTileData();
		int tileData2[][]= tile.getTileData();
		return isTileDataEqual(tileData1, tileData2);	
	}
	
	
	//TODO divide by 4
	public static boolean isTileDataEqual(int[][] tileData1, int[][] tileData2) throws InvalidTileException
	{
		boolean equals = true;
		if (isRightSize(tileData1)&&isRightSize(tileData2))
		{
			for(int y=0; y<tileData1.length; y++)
			{
				for(int x=0; x<tileData1[getArraysize()-1].length; x++)
				{
					if(!(tileData1[y][x]==tileData2[y][x]))
						equals=false;
				}
			}
			return equals;
		}
		else throw new InvalidTileException("Tile data is the wrong size");
			
	}
	
//********************************************************************************************
//SoftCompare Tile Data
//********************************************************************************************
	public boolean isTileDataSoftEqual(int tileData1[][]) throws InvalidTileException
	{
		int tileData2[][]=this.getTileData();
			return isTileDataSoftEqual(tileData1, tileData2);
				
	}
		
		public boolean isTileDataSoftEqual(ScreenTile tile) throws InvalidTileException
		{
			int tileData1[][] = this.getTileData();
			int tileData2[][]= tile.getTileData();
			return isTileDataEqual(tileData1, tileData2);	
		}
		
		//TODO divide by 4
		public static boolean isTileDataSoftEqual(int[][] tileData1, int[][] tileData2) throws InvalidTileException
		{
			boolean equals = true;
			if (isRightSize(tileData1)&&isRightSize(tileData2))
			{
				int y=0; int x=0;
				
				while(y<tileData1.length)
				{
					outerloop:
					while(x<tileData1[getArraysize()-1].length)
					{
						if(!(tileData1[y][x]==tileData2[y][x]))
						{
							equals=false;	
							break outerloop;
						}
						x++;
					}
					y++;
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

	public static int getArraysize() {
		return arraySize;
	}
	public static int getMaxscreentilerow() {
		return maxScreenTileRow;
	}

	public static int getMaxscreentilecol() {
		return maxScreenTileCol;
	}
	@Override
	public String toString()
	{
		String string="Tile";
		return string;	
		
	}

	
}

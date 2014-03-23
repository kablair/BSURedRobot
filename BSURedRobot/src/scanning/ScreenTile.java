package scanning;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import data_storage.ImageWriter;
import exceptions.InvalidGameColorException;
import exceptions.InvalidTileException;
import exceptions.ScanningDisabledException;

/**A class that represents an in-game tile. Contains an image and its tileData**/
public class ScreenTile {

	private static final int maxScreenTileRow=9;
	private static final int maxScreenTileCol=8;
	private static final int tileSize=64;
	private static final int arraySize=tileSize/4;
	private BufferedImage image;
	private int tileData[][];
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
			{
				this.tileData=tileData;
				image=null;
			}
		else
		{
			throw new InvalidTileException("Tile data is the wrong size.");
		}
	}

	public void setImage(BufferedImage image) throws InvalidTileException
	{
		if(image.getWidth()==tileSize &&image.getHeight()==tileSize)
		{
			this.image=image;
		}
		else throw new InvalidTileException("Tile image has invalid dimensions");
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
	
//************************************************************************************
//Get Tile Rect
//************************************************************************************
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
		for(int y=0; y<tileImage.getHeight()/4; y++)
		{
			for(int x=0; x<tileImage.getWidth()/4; x++)
			{
				String tileString=String.format("%06d",tile.getImage().getRGB(x*4, y*4));
				int tileInt=Integer.parseInt(tileString);
				tileArray[y][x]= tileInt;
			}
		}
		return tileArray;
	}
	
//********************************************************************************************
//Compare Tile Data
//********************************************************************************************
	public static boolean isRightSize(int tileData[][])
	{
		boolean isRightSize= false;
		if(tileData.length ==getArraysize() && tileData[getArraysize()-1].length==getArraysize())
		{
			isRightSize=true;
		}
		return isRightSize;
	}
	
//*******************************************************************************************
//Compare to other ScreenTiles
//*******************************************************************************************
	public static boolean equals(ScreenTile tile, ScreenTile tile2) throws InvalidTileException
	{
		int[][] tileData1 = tile.getTileData();
		int[][] tileData2 = tile2.getTileData();
		return equals(tileData1, tileData2);
	}
	
	public static boolean equals(ScreenTile tile1, int[][] tileData2) throws InvalidTileException
	{
		int[][] tileData1 = tile1.getTileData();
		return equals(tileData1, tileData2);
	}
	
	public static boolean equals(int[][] tileData1, int[][] tileData2) throws InvalidTileException
	{
		boolean equals = true;
		if (ScreenTile.isRightSize(tileData1)&&ScreenTile.isRightSize(tileData2))
		{
			for(int y=0; y<tileData1.length && equals; y++)
			{
				for(int x=0; x<tileData1[ScreenTile.getArraysize()-1].length &&equals; x++)
				{
					if(!(tileData1[y][x]==tileData2[y][x]))
						equals=false;
				}
			}
			return equals;
		}
		else throw new InvalidTileException("Tile data is the wrong size");
			
	}
	
	public boolean equals(ScreenTile tile) throws InvalidTileException
	{
		int[][] tileData1 = this.getTileData();
		int[][] tileData2 = tile.getTileData();
		return equals(tileData1, tileData2);
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
	
	public BufferedImage getImage()
	{
		if(image==(null))
		{
			image = ImageWriter.writeImage(tileData, true);
		}
		
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

	public int getId() {
		int id=0;
		//sample points
		int row[] = {11, 2, 7, 8, 10, 5, 0, 2, 9, 4, 6};
		int col[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
		for(int n=0; n<11; n++)
		{
			try 
			{
				int colNum= col[n];
				int rowNum= row[n];
				
				int colorNum = GameColor.getGameColor(tileData[colNum][rowNum]).getId();
				System.out.println(colorNum);
				id+=(colorNum)*Math.pow(4, n);
				//System.out.println(id);
				System.out.println(colorNum);
			
			} 
			catch (InvalidGameColorException e) {
				
			}
			
		
		}
		return id;
	}

	
}

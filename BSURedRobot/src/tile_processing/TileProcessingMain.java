package tile_processing;

import java.io.IOException;

import data_storage.TileReader;
import exceptions.InvalidTileException;
import exceptions.ScanningDisabledException;
import scanning.ScannerMain;
import scanning.ScreenTile;

public class TileProcessingMain {

	
	public static void checkTile()
	{
		
		try {
			ScreenTile tile = TileReader.loadTile("oakhead");
			boolean equals = false;
			long startTime = System.nanoTime();
//			for(int y=0; y<ScreenTile.getMaxscreentilecol(); y++)
//			{
//				for(int x=0; x<ScreenTile.getMaxscreentilerow(); x++)
//				{
//					ScreenTile tile2= ScannerMain.getScreenTile(x, y);
//					if(tile.isTileDataEqual(tile2))
//					{
//						equals=true;
//						break;
//					}
//					
//				}
//			}
			ScreenTile[][]screenTiles =  ScannerMain.getScreenTiles();
			int maxRow = ScreenTile.getMaxscreentilerow();
			int maxCol= ScreenTile.getMaxscreentilecol();
			//TODO the tileData compare methods aren't working.
			for(int y=0; y<maxCol-1; y++)
			{
				for(int x=0; x<maxRow-1; x++)
				{
					if (tile.isTileDataEqual(screenTiles[y][x]));
					{
						equals =true;
						System.out.println(x +" " + y);
					}
					
				}
			}
			
			
			long endTime = System.nanoTime();
			System.out.println("Took "+(endTime - startTime)/1000000 + " ms"); 
			System.out.println(equals);
		}
		 catch (IOException | InvalidTileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ScanningDisabledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

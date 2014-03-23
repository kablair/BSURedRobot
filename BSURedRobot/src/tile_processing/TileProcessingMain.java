package tile_processing;

import java.io.IOException;
import java.util.ArrayList;

import data_storage.TileReader;
import exceptions.InvalidTileException;
import exceptions.ScanningDisabledException;
import scanning.ScannerMain;
import scanning.ScreenReader;
import scanning.ScreenTile;

public class TileProcessingMain {

	public static ArrayList<ScreenTile> tileList = new ArrayList<ScreenTile>();
	
	public static void initialize()
	{
		TileLoader.loadTiles();
	}
	
	
	public static void checkTiles() throws InvalidTileException, ScanningDisabledException
	{
		long[][] tiles =ScannerMain.sampleScreenTiles();
		for(int y=0; y<tiles.length; y++)
		{
			for(int x=0; x<tiles[y].length; x++)
			{
				System.out.println(tiles[y][x]);
			}
		}
		
	}
	
	public static void checkTile(ScreenTile tile) throws IOException, InvalidTileException, ScanningDisabledException
	{
		
			long startTime = System.nanoTime();
			long id = tile.getId();
			boolean found = false;
			int n=0;
			while(!found && n<tileList.size())
			{
				ScreenTile other= tileList.get(n);
				if (id==other.getId())
				{
						found=true;
				}
				n++;
			}
			
			long endTime = System.nanoTime();
			System.out.println("Took "+(endTime - startTime)/1000000 + " ms"); 

	}	
}

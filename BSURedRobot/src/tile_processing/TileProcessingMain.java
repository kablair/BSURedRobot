package tile_processing;

import java.io.IOException;

import data_storage.TileReader;
import exceptions.InvalidTileException;
import exceptions.ScanningDisabledException;
import scanning.ScannerMain;
import scanning.ScreenTile;

public class TileProcessingMain {

	
	public static void checkTile() throws IOException, InvalidTileException, ScanningDisabledException
	{
		
			long startTime = System.nanoTime();
			ScreenTile tile = TileReader.loadTile("sample");
			ScreenTile tile2 = ScannerMain.getScreenTile(5, 5);
			ScreenTile[][] tiles = ScannerMain.getScreenTiles();
			System.out.println(tiles[4][4].isTileDataEqual(tile2));
			System.out.println(tiles[4][4].isTileDataEqual(tile));
			
			
			
			boolean equals = false;
			long endTime = System.nanoTime();
			System.out.println("Took "+(endTime - startTime)/1000000 + " ms"); 

	}	
}

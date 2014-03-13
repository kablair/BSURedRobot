package tile_processing;

import java.io.IOException;
import java.util.ArrayList;

import data_storage.TileReader;
import exceptions.InvalidTileException;
import exceptions.ScanningDisabledException;
import scanning.ScannerMain;
import scanning.ScreenTile;

public class TileProcessingMain {

	public static ArrayList<ScreenTile> tileList = new ArrayList<ScreenTile>();
	
	public static void initialize()
	{
		TileLoader.loadTiles();
	}
	
	
	
	public static void checkTile() throws IOException, InvalidTileException, ScanningDisabledException
	{
		
			long startTime = System.nanoTime();

			long endTime = System.nanoTime();
			System.out.println("Took "+(endTime - startTime)/1000000 + " ms"); 

	}	
}

package world_tiles;

import java.io.IOException;
import java.util.ArrayList;

import data_storage.TileReader;
import data_storage.TileWriter;
import exceptions.InvalidTileException;
import exceptions.ScanningDisabledException;
import scanning.ScannerMain;
import scanning.ScreenTile;

public class WorldTilesMain {

	public static ArrayList<WorldTile> worldTiles= new ArrayList<WorldTile>();

	public static void initialize()
	{
		try {
			TileReader.loadWorldTileData();
		} catch (IOException | InvalidTileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			ArrayList<ScreenTile> tileList= TileReader.loadAllTileData();
//			for(ScreenTile screenTile:tileList)
//			{
//				WorldTile worldTile =new WorldTile(screenTile);
//				worldTiles.add(worldTile);
//			}
//		} catch (IOException | InvalidTileException e) {
//			e.printStackTrace();
//		}
		
	
	}

	public static void addNewTiles() throws InvalidTileException, ScanningDisabledException, IOException
	{
		ScreenTile tiles [][] = ScannerMain.getScreenTiles();
		for(int y=0; y<tiles.length; y++)
		{
			for(int x=0; x<tiles[0].length; x++)
			{
				System.out.println(x+" "+ y);
				
				addNewTile(tiles[y][x]);
			}
		}
	}
	
	public static void addNewTile(ScreenTile tile) throws InvalidTileException, IOException
	{
		boolean equals = false;
		
		for(WorldTile worldTile: worldTiles)
		{
			if (worldTile.getScreenTile().getId() == tile.getId())
			{
				equals=true;
			}
		}
		if(!equals)
		{
			worldTiles.add(new WorldTile(tile));
			TileWriter.writeTile(tile, true);
		}
		
		System.out.println(equals);
		
	}
	
}

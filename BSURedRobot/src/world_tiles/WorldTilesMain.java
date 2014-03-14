package world_tiles;

import java.io.IOException;
import java.util.ArrayList;

import data_storage.TileReader;
import exceptions.InvalidTileException;
import scanning.ScreenTile;

public class WorldTilesMain {

	static ArrayList<WorldTile> worldTiles= new ArrayList<WorldTile>();

	public static void initialize()
	{
		try {
			ArrayList<ScreenTile> tileList= TileReader.loadAllTileData();
			for(ScreenTile screenTile:tileList)
			{
				worldTiles.add(new WorldTile(screenTile));
				//addNewTile(screenTile);
			}
		} catch (IOException | InvalidTileException e) {
			e.printStackTrace();
		}
		
	
	}

	
	
	public static void addNewTile(ScreenTile tile) throws InvalidTileException
	{
		boolean equals = false;
		
		for(WorldTile worldTile: worldTiles)
		{
			if (worldTile.getScreenTile().equals(tile))
			{
				equals=true;
			}
		}
		if(!equals)
		{
			worldTiles.add(new WorldTile(tile));
		}
		
	}
	
}

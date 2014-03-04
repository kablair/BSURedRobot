package world_tiles;

import java.util.ArrayList;

import exceptions.InvalidTileException;
import scanning.ScreenTile;

public class WorldTilesMain {

	static ArrayList<WorldTile> worldTiles= new ArrayList<WorldTile>();

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

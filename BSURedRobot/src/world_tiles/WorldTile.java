package world_tiles;

import maze.MazeTile;
import scanning.ScreenTile;

public class WorldTile {

	private ScreenTile screenTile;
	private MazeTile tileType;
	WorldTile(ScreenTile screenTile)
	{
		this.screenTile=screenTile;
	}

	public ScreenTile getScreenTile()
	{
		return screenTile;
	}
}

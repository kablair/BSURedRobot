package tile_processing;

import maze.MazeTile;
import scanning.ScreenTile;

public class WorldTile {

	private ScreenTile screenTile;
	private MazeTile mazeTile;
	public WorldTile(ScreenTile screenTile)
	{
		this.screenTile=screenTile;
	}
}

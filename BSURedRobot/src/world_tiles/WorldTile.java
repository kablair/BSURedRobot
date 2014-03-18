package world_tiles;

import maze.MazeTile;
import scanning.ScreenTile;

public class WorldTile {

	private ScreenTile screenTile;
	private MazeTile tileType;
	private String name;
	
	WorldTile(ScreenTile screenTile)
	{
		this.screenTile=screenTile;
	}

	void setName(String name)
	{
		this.name=name;
	}
	
	String getName()
	{
		return name;
	}
	
	public ScreenTile getScreenTile()
	{
		return screenTile;
	}
}

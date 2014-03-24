package world_tiles;

import scanning.ScreenTile;

public class WorldTile {
	public static final String[] tileTypes = {"Wall","Path","Door","Grass", "Ledge", "Person", "Red", "Other"};
	public static final String obstruction= "ob";
	
	private ScreenTile screenTile;
	private int tileType;
	private boolean obstructed;
	
	public WorldTile(ScreenTile screenTile)
	{
		this.screenTile=screenTile;
		this.tileType=tileTypes.length-1;
		this.obstructed=false;
	}

	public WorldTile(ScreenTile screenTile, String name)
	{
		this.screenTile=screenTile;
		identify(name);
	}
	
	public void identify(String name)
	{
		//default: "other" type
		tileType=tileTypes.length-1;
		System.out.println("bob".contains("bo")+ " hi");
		for(int n=0; n<tileTypes.length; n++)
		{
			if(name.contains(tileTypes[n]))
			{
				tileType=n;
			}
		}
		
		if(name.contains(obstruction))
		{
				obstructed=true;
		}
		else
		{
			obstructed= false;
		}
	}
	

	
	public ScreenTile getScreenTile()
	{
		return screenTile;
	}

	public int getTileType() {
		return tileType;
	}

	public boolean isObstructed() {
		return obstructed;
	}

}

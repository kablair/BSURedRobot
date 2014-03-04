package maze;

import java.awt.Color;
import java.awt.Graphics;


public enum MazeTile {

	//assume path then grass
	//assume ledge then wall
	wall(Color.red, true, true), 
	path(Color.yellow, false, false), 
	grass(Color.green, false, false), 
	ledge(Color.orange, false, true),// if y<ledge it is passable, otherwise it is not
	unknown(Color.darkGray), 
	unknownTopBlocked(Color.darkGray, true),
	unseen(Color.black);
	
	private int tileId;

	private static final int tileSize=16;
	private static final int mazeInset= MazePanel.getInset();
	private boolean topBlocked;
	private boolean bottomBlocked;
	private boolean visited;
	private Color color;
	
	MazeTile(Color color)
	{
		this.color=color;
		visited=false;
	}
	
	MazeTile(Color color, boolean topBlocked)
	{
		this.color=color;
		visited=false;
		this.topBlocked=topBlocked;
	}
	
	MazeTile(Color color, boolean topBlocked, boolean bottomBlocked)
	{
		this.color=color;
		visited=false;
		this.topBlocked=topBlocked;
		this.bottomBlocked=bottomBlocked;
	}

	
//*******************************************************************************************
//Getters and Setters
//*******************************************************************************************
	public void visit()
	{
		
		visited=true;
	}

	public boolean wasVisited()
	{
		return visited;
	}
	
	public Color getColor() {
		return this.color;
	}

	public static int getTilesize() {
		return tileSize;
	}
	
	public void setTileId(int tileId)
	{
		this.tileId=tileId;
	}
	
	public int getTileID()
	{
		return tileId;
	}
	
//**********************************************************************************************
//Paint
//**********************************************************************************************	
	public void paintTile(Graphics g, int row, int col) {
		
		int x= tileSize*row+mazeInset;
		int y= tileSize*col+mazeInset;
		g.setColor(this.getColor());
		g.fillRect(x, y, tileSize, tileSize);
		g.setColor(Color.black);
		g.drawRect(x, y, tileSize, tileSize);
		
		
	}

	public static void paintRobot(Graphics g, int row, int col)
	{
		int x= tileSize*row+mazeInset;
		int y= tileSize*col+mazeInset;
		g.setColor(Color.green);
		g.fillRect(x, y, tileSize, tileSize);
		g.setColor(Color.black);
		g.drawRect(x, y, tileSize, tileSize);
	}	
	
//********************************************************************************************
//Get random MazeTile	
//********************************************************************************************	
	public static MazeTile generateRandom()
	{
		MazeTile tile;
		if (Math.random()>0.8)
		{
			tile=MazeTile.wall;
		}
		else
		{
				tile=MazeTile.path;
		}
		return tile;
	}
	
}

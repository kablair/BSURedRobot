package maze;

import java.awt.Graphics;

import main.StartClass;

public class MazeMain {

	public static int mazeHeight=20;
	public static int mazeWidth=20;
	public static MazeTile[][] maze = new MazeTile[mazeHeight][mazeWidth];
	public static final MazeTile[][] hiddenMaze = new MazeTile[mazeHeight][mazeWidth];
	public static MazeFrame mazeFrame;
	public static void initialize()
	{
		for(int y=0; y<mazeHeight; y++)
		{
			for(int x=0; x<mazeWidth; x++)
			{
				maze [y][x]=MazeTile.unknown;
				
				if(Math.random()<0.8)
				hiddenMaze [y][x]=MazeTile.path;
				else
				hiddenMaze [y][x]=MazeTile.wall;
			}
		}
		MazeSolver.initialize(maze);
	}
	
	public static void openMazeFrame()
	{
		mazeFrame= new MazeFrame();
	}
	
	public static MazeTile randomTile()
	{
		MazeTile tile;
		if(Math.random()>0.8)
			tile=MazeTile.wall;
		else
			tile=MazeTile.path;
		return tile;
	}
	
	public static void paintMaze(Graphics g)
	{
		for (int col=0; col<mazeHeight; col++)
			for (int row=0; row<mazeWidth; row++)
			{
				paintMazeTile(g, maze[col][row], row, col);
			}
		
		MazeTile.paintRobot(g, MazeSolver.getXPos(), MazeSolver.getYPos());
	}
	
	private static void paintMazeTile(Graphics g, MazeTile tile, int row, int col)
	{
		tile.paintTile(g, row, col);
	}

	public static void shiftMaze() {
		// TODO Auto-generated method stub
		
	}
	
	public static void move(Direction direction)
	{
		MazeSolver.move(direction);
		mazeFrame.repaint();
		
	}
}

package maze;

public class MazeSolver {

	private static int xpos;
	private static int ypos;
	private static MazeTile[][] maze;
	
	
	static void initialize(MazeTile[][] maze)
	{
		MazeSolver.maze=maze;
		xpos=0;
		ypos=0;
	}
	
	static int getXPos()
	{
		return xpos;
	}
	
	static int getYPos()
	{
		return ypos;
	}
	
	static void explore()
	{
		Direction direction = chooseDirection();
		move(direction);
	
	}
	
	static void move(Direction direction)
	{
		int xposTest = xpos +direction.getDeltaX();
		int yposTest = ypos + direction.getDeltaY();
		
		if(xposTest<0 || yposTest<0 || yposTest>MazeMain.mazeHeight-1||xposTest>MazeMain.mazeWidth-1)
		{
			MazeMain.shiftMaze();
		}
		else
		{
			if(!maze[yposTest][xposTest].wasVisited())
			{
				MazeTile tile = MazeTile.generateRandom();
				maze[yposTest][xposTest]=tile;
				maze[yposTest][xposTest].visit();
				
			}
			
			 if(maze[yposTest][xposTest]!=MazeTile.wall)
			 {
				 xpos=xposTest;
				 ypos=yposTest;
			 }
		}
		
	}
	
	//TODO
	private static Direction chooseDirection()
	{
		Direction possibleDirections[];
		int count=0;
		
		if((xpos+=Direction.LEFT.getDeltaX())>=0)
		{
			
		}
		if((xpos+=Direction.RIGHT.getDeltaX())>=0)
		{
			
		}
		if((ypos+=Direction.UP.getDeltaY())>=0)
		{
			
		}
		if((ypos+=Direction.DOWN.getDeltaY())>=0)
		{
			
		}
		
		
		
		return Direction.LEFT;
	}
	
	//TODO
	private static void resetExploreMap()
	{
		
	}
}

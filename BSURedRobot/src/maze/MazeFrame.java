package maze;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.border.Border;

import main.StartClass;

public class MazeFrame extends JFrame implements KeyListener{
	
	private static MazePanel mazePanel;
	
	MazeFrame()
	{
		mazePanel = new MazePanel();
		this.setTitle("Maze View");
		this.setPreferredSize(mazePanel.getPreferredSize());
		this.add(mazePanel);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setFocusable(true);
		addKeyListener(this);
	}

	
	public static void setMaze(MazeTile[][] maze)
	{
		MazePanel.setMaze(maze);
		
	}
	
	public static void drawMaze()
	{
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
			
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		//System.out.println("hi");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			MazeMain.move(Direction.UP);
			System.out.println("UP");
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			MazeMain.move(Direction.DOWN);
			System.out.println("DOWN");
		}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			MazeMain.move(Direction.LEFT);
			System.out.println("LEFT");
		}
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			MazeMain.move(Direction.RIGHT);
			System.out.println("RIGHT");
		}
	
		
	}

	
}

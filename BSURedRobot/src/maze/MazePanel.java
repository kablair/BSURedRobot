package maze;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class MazePanel extends JPanel {

	//private static final int iniMazeHeight=500;
	//private static final int iniMazeWidth=600;
	private static final int inset= 10;
	private static final int defaultMazeRows=MazeMain.mazeWidth;
	private static final int defaultMazeCols=MazeMain.mazeHeight;
	private static final int panelWidth=3*inset+ MazeTile.getTilesize()*defaultMazeRows+6;
	private static final int panelHeight=4*inset+MazeTile.getTilesize()*defaultMazeCols+15;
	private static final Dimension defaultSize = new Dimension(panelWidth,panelHeight);
	
	private static MazeTile[][] maze= new MazeTile[defaultMazeCols][defaultMazeRows];


	public MazePanel()
	{
		System.out.println(panelHeight);
		this.setPreferredSize(defaultSize);
		MazeMain.initialize();
	}
	
	public static void setMaze(MazeTile[][] maze) {
		MazePanel.maze= maze;
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		MazeMain.paintMaze(g);
	}
	
	private void setMaze()
	{
		maze=MazeMain.maze;
	}
	

	public static int getInset()
	{
		return inset;
	}


	
	
}

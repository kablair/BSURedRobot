package frame;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

/**
 * @author Kelly Blair
 * @about
 *	A frame that allows the user to scroll through tiles. Only one frame can be open at a time.
 */
public class TileFrame extends JFrame implements WindowListener{

	private static final long serialVersionUID = 4069028082432333403L;
	private static final Dimension preferredSize= new Dimension(300,200);
	private static boolean isOpen=false;
	private static TileFrame frame;
	private static TilePanel tilePanel;
	private static int index;
	private static boolean initialized;
	
	private static void initialize()
	{
		index=0;
		initialized=true;
		System.out.println("initialize");
	}
	
	
	private TileFrame()
	{
		if(!initialized)
		{
			initialize();
		}
		frame=this;
		this.setPreferredSize(preferredSize);
		this.setTitle("Tile Explorer");
		this.setVisible(true);
		tilePanel=getTilePanel(index);
		this.add(tilePanel);
		this.pack();
		TileFrame.isOpen=true;
		this.addWindowListener(this);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	private static TilePanel getTilePanel(int index)
	{
		TilePanel tilePanel = new TilePanel();
		return tilePanel;
		
	}
	static int getIndex()
	{
		return index;
	}
	
	static void setIndex(int index)
	{
		TileFrame.index=index;
		
	}
	static void openTileFrame()
	{
		if(TileFrame.isOpen)
		{
			frame.requestFocus();
		}
		
		else
		{
			new TileFrame();
		}
		
	}


	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		TileFrame.isOpen=false;
		index=0;
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}



	

	
	
}

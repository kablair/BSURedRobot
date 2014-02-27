package frame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import data_storage.ImageWriter;
import data_storage.TileReader;

public class FrameMain extends JFrame implements WindowListener{
	private static final long serialVersionUID = 157572953151107392L;
	private static TextPanel textPanel = new TextPanel();
	//Goals: Rename tiles from default
	//Choose AI pattern
	//Pause/Start Actions
	//Access Data it gathered
	//View desision making pattern current: what is making it choose this way?
	public FrameMain()
	{
		this.setTitle("PokeFrame");
		this.setLayout(new GridLayout(2,1));
		this.setPreferredSize(new Dimension(400,500));
		this.add(textPanel);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			TileDialog dialog = new TileDialog(this, ImageWriter.writeImage(TileReader.loadTileData("sample")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
	}
	
	public void addText(String newText)
	{
		textPanel.addText(newText);
	}
	
	public void addTextLine(String newText)
	{
		textPanel.addTextLine(newText);
	}
	
	public void clearText()
	{
		textPanel.clearText();
	}
	
	public void pause()
	{
		//KeyboardRobot.pause();
	}
	
	@Override
	public void paintComponents(Graphics g)
	{
		super.paintComponents(g);
		
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		//KeyboardRobot.destroy();
		
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

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


}


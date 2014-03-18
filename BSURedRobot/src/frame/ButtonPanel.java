package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel implements ActionListener {

	private static final String VIEWTILES = "view tiles";
	ButtonPanel()
	{
		JButton button = new JButton("View Tiles");
		button.addActionListener(this);
		button.setActionCommand(ButtonPanel.VIEWTILES);
		
		this.add(button);
	}
	
	public static void openTileFrame()	
	{
		TileFrame.openTileFrame();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals(ButtonPanel.VIEWTILES))
		{
			openTileFrame();
		}
		
	}
}

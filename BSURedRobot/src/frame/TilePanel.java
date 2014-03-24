package frame;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import scanning.ScreenTile;
import world_tiles.WorldTile;
import world_tiles.WorldTilesMain;

public class TilePanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = -7730709789353061729L;
	private static final String forwardCommand="forward";
	private static final String backwardCommand="backward";
	private static final String bigForwardCommand="bigForward";
	private static final String bigBackwardCommand="bigBackward";
	
	private BoxLayout layout;
	private BufferedImage image;
	private JTextField name;
	private JTextField tileId;
	private JLabel count;
	private JButton forward;
	private JButton backward;
	private JButton bigForward;
	private JButton bigBackward;
	private ScreenTile tile;
	private String nameText;

	

	TilePanel()
	{
		loadPanel();
	
		
	}
	
	private void loadPanel()
	{
		layout= new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
		loadTile();
		tileId=new JTextField(String.valueOf(tile.getId()));
		tileId.setEditable(false);
		tileId.setBorder(null);
		tileId.setHorizontalAlignment(JTextField.CENTER);
		this.add(tileId);
		image= tile.getImage();
		this.add(new ImagePanel(image));
		name= new JTextField(nameText);
		name.setEditable(false);
		name.setBorder(null);
		name.setHorizontalAlignment(JTextField.CENTER);
		this.add(name, this);
		this.add(createButtonPanel());
	}
	
	private void loadTile()
	{
		tile=WorldTilesMain.worldTiles.get(TileFrame.getIndex()).getScreenTile();
		int tileType=WorldTilesMain.worldTiles.get(TileFrame.getIndex()).getTileType();
		nameText= WorldTile.tileTypes[tileType];
	}
	
	
	private JPanel createButtonPanel()
	{
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		forward = new JButton(">");
		forward.addActionListener(this);
		forward.setActionCommand(forwardCommand);
		backward= new JButton("<");
		backward.addActionListener(this);
		backward.setActionCommand(backwardCommand);
		count = new JLabel("    "+(TileFrame.getIndex()+1)+"    ");
		bigForward = new JButton(">>");
		bigForward.addActionListener(this);
		bigForward.setActionCommand(bigForwardCommand);
		bigBackward= new JButton("<<");
		bigBackward.addActionListener(this);
		bigBackward.setActionCommand(bigBackwardCommand);
		
		buttonPanel.add(bigBackward);
		buttonPanel.add(backward);
		buttonPanel.add(count);
		buttonPanel.add(forward);
		buttonPanel.add(bigForward);
		return buttonPanel;
	}

	private static void setIndex(int delta)
	{
		int size =WorldTilesMain.worldTiles.size();
		int newIndex=(TileFrame.getIndex()+delta)%size;
		if(newIndex<0)
		{
			newIndex+=size;
		}
		
		TileFrame.setIndex(newIndex);
		System.out.println(newIndex);
		
		
	}
	

	private void reload()
	{
		this.removeAll();
		loadPanel();
		this.getRootPane().revalidate();
//		this.invalidate();
//		this.validate();
//		this.repaint();
		//SwingUtilities.updateComponentTreeUI(this);
		//removeAll();
		//loadPanel();
		//repaint();
		//this.getParent().repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(bigBackwardCommand))
		{
			setIndex(-5);
			reload();
			
		}
		else if(e.getActionCommand().equals(backwardCommand))
		{
			setIndex(-1);
			reload();
		}
		else if(e.getActionCommand().equals(forwardCommand))
		{
			setIndex(1);
			reload();
			
		}
		else if(e.getActionCommand().equals(bigForwardCommand))
		{
			setIndex(5);
			reload();
		}
		
	}
	
	
	
}

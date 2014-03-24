package data_storage;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import scanning.ScreenTile;
import world_tiles.WorldTile;
import frame.ImagePanel;




/**Pops up a menu for the user to enter a tile name.<p>
 * If the user submits a name, the tile will be saved with that name. <br>
 * If the name already exists, the tile will NOT be saved<p>
 * If the user submits a blank name, the tile will be saved with a generated name <p>
 * The user can choose to NOT save the tile by clicking the exit button.<br>*/
//TODO
//Add a warning message if the tile name is taken. Allow overwrite
public class TileDialog extends JDialog implements ActionListener{

	private static final long serialVersionUID = 732283671303623728L;
	private static final String title= "Tile Discovery";
	private static final String submitAction= "Submit";
	private static Dimension preferredSize = new Dimension(320,120);

	private ImagePanel imagePanel;
	private JPanel dialogPanel;
	JPanel textPanel;
	
	private ScreenTile screenTile;
	
	private BufferedImage image;
	//TODO try getting rid of this
	private String tileName;
	//private JTextField dialogTextField;
	private JButton submitButton;
	private JComboBox<String> comboBox;
	private JCheckBox checkbox;

	public TileDialog(ScreenTile tile)
	{
		setUp(tile);
	}
	
	
	public TileDialog(JFrame frame, ScreenTile tile)
	{
		super(frame, title, false);
		setUp(tile);
        setLocationRelativeTo(frame);
	}

	/**Initializes dialog. Can be used with all constructors.*/
	private void setUp(ScreenTile tile)
	{
		this.setTitle(title);
		super.setPreferredSize(preferredSize);
		this.screenTile=tile;
		this.image=tile.getImage();
		tileName="";
		setDialogPanel();
		getContentPane().add(dialogPanel);
        pack();
        setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	
	/**Arranges components on dialogPanel*/
	private void setDialogPanel()
	{
		dialogPanel = new JPanel();
		dialogPanel.setLayout(null);
		JLabel dialogLabel = new JLabel("Identify this tile.");
		dialogLabel.setBounds(77, 5, 200, 30);
		imagePanel= new ImagePanel(image);
		imagePanel.setBounds(3,5, 65,70 );
		String comboStrings[] = WorldTile.tileTypes;
		comboBox = new JComboBox<String>(comboStrings);
		comboBox.setBounds(78, 55, 122, 20);
		//{"Wall","Path","Door","Grass", "Ledge"}
		JLabel dialogLabel2 = new JLabel("Is partially obstructed");
		dialogLabel2.setBounds(100, 30, 150, 20);
		dialogLabel2.setFont(new Font(dialogLabel2.getFont().getName(), Font.PLAIN, 11));
		checkbox= new JCheckBox();
		checkbox.setBounds(76, 31, 18, 18);
		//dialogTextField = new JTextField();
		//dialogTextField.setBounds();
		//(78, 55, 122, 20);
		submitButton = new JButton("Submit");
		submitButton.setBounds(220, 55, 75, 20);
		//(220, 55, 75, 20);
		submitButton.addActionListener(this);
		submitButton.setActionCommand(submitAction);
		dialogPanel.add(dialogLabel);
		dialogPanel.add(dialogLabel2);
		dialogPanel.add(imagePanel);
		//dialogPanel.add(dialogTextField);
		dialogPanel.add(submitButton);
		dialogPanel.add(comboBox);
		dialogPanel.add(checkbox);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals(submitAction))
		{
			tileName= (String) comboBox.getSelectedItem();
			
			if(checkbox.isSelected())
			{
				tileName = tileName + WorldTile.obstruction;
			}
			
			
			System.out.println(tileName);
			try 
			{
				if(!tileName.isEmpty())
				{
					TileWriter.writeTile(screenTile, tileName);
				
				}
				else
				{
					TileWriter.writeTile(screenTile, false);
				}
			} 
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}
			
			this.setVisible(false);
			this.dispose();
			
		}
		
	}
}
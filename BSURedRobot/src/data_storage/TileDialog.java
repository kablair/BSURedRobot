package data_storage;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import scanning.ScreenTile;
import exceptions.InvalidTileException;
import frame.ImagePanel;

public class TileDialog extends JDialog implements ActionListener{

	private static final long serialVersionUID = 732283671303623728L;
	private static final String submitAction= "Submit";
	private static Dimension preferredSize = new Dimension(320,120);
	//private BorderLayout borderLayout;
	private ImagePanel imagePanel;
	private JPanel dialogPanel;
	JPanel textPanel;
	private BufferedImage image;
	private String tileName;
	
	private JTextField dialogTextField;
	private JButton submitButton;

	
	public TileDialog(BufferedImage image)
	{
		

		super.setPreferredSize(preferredSize);
		tileName="";
		this.image=image;
		setDialogPanel();
		getContentPane().add(dialogPanel);
        pack();
        setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	
	public TileDialog(JFrame frame, BufferedImage image)
	{
		
		super(frame, "Tile Discovery", false);
		super.setPreferredSize(preferredSize);
		tileName="";
		this.image=image;
		setDialogPanel();
		getContentPane().add(dialogPanel);
        pack();
        setLocationRelativeTo(frame);
        setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	
	public void setDialogPanel()
	{
		dialogPanel = new JPanel();
		dialogPanel.setLayout(null);
		JLabel dialogLabel = new JLabel("Enter a name for this tile.");
		dialogLabel.setBounds(77, 5, 200, 30);
		imagePanel= new ImagePanel(image);
		imagePanel.setBounds(3,5, 65,70 );
		dialogTextField = new JTextField();
		dialogTextField.setBounds(78, 55, 122, 20);
		submitButton = new JButton("Submit");
		submitButton.setBounds(220, 55, 75, 20);
		submitButton.addActionListener(this);
		submitButton.setActionCommand(submitAction);
		dialogPanel.add(dialogLabel);
		dialogPanel.add(imagePanel);
		dialogPanel.add(dialogTextField);
		dialogPanel.add(submitButton);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(submitAction))
		{
			tileName=dialogTextField.getText();
				System.out.println(tileName);
				try 
				{
					if(!tileName.isEmpty())
					{
						
						TileWriter.writeTile(new ScreenTile(image), tileName);
				
					}
					else
					{
						TileWriter.writeTile(new ScreenTile(image), false);
					}
				} 
				catch (IOException | InvalidTileException e1) 
				{
					e1.printStackTrace();
				}
			this.setVisible(false);
			this.dispose();
		
		
		}
		
	}
}
package data_storage;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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

public class TileDialog extends JDialog implements WindowListener, ActionListener{

	private static final long serialVersionUID = 732283671303623728L;
	private static final String submitAction= "Submit";
	private static Dimension preferredSize = new Dimension(320,120);
	//private BorderLayout borderLayout;
	private ImagePanel imagePanel;
	private JPanel dialogPanel;
	JPanel textPanel;
	private BufferedImage image;
	private String tileName;
	private int closeCount=0; // windowClosed is firing twice for some reason; this is meant to make sure closing code is used once only.
	
	private JTextField dialogTextField;
	private JButton dialogButton;
	
	public TileDialog(BufferedImage image)
	{
		

		super.setPreferredSize(preferredSize);
		tileName="";
		this.image=image;
		setDialogPanel();
		getContentPane().add(dialogPanel);
        pack();
        setVisible(true);
        this.addWindowListener(this);
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
		dialogButton = new JButton("Submit");
		dialogButton.setBounds(220, 55, 75, 20);
		dialogButton.addActionListener(this);
		dialogButton.setActionCommand(submitAction);
		dialogPanel.add(dialogLabel);
		dialogPanel.add(imagePanel);
		dialogPanel.add(dialogTextField);
		dialogPanel.add(dialogButton);
		
	}


	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosing(WindowEvent e) {
	}


	@Override
	public void windowClosed(WindowEvent e) {
		
		if(tileName.isEmpty()&&closeCount==0)
		{
			try {
				TileWriter.writeTile(new ScreenTile(image), false);
			} 
			catch (IOException | InvalidTileException e1) {
				e1.printStackTrace();
			}
		}
		closeCount++;
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
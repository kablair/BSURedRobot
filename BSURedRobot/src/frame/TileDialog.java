package frame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TileDialog extends JDialog{

	private static final long serialVersionUID = 732283671303623728L;
	private static Dimension preferredSize = new Dimension(320,120);
	//private BorderLayout borderLayout;
	private ImagePanel imagePanel;
	private JPanel dialogPanel;
	JPanel textPanel;
	private BufferedImage image;
	
	private JTextField dialogTextField;
	private JButton dialogButton;
	
	public TileDialog(JFrame frame, BufferedImage image)
	{
		
		super(frame, "Tile Discovery", false);
		super.setPreferredSize(preferredSize);
		this.image=image;
		setDialogPanel();
		getContentPane().add(dialogPanel);
        pack();
        setLocationRelativeTo(frame);
        setVisible(true);
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
		dialogPanel.add(dialogLabel);
		dialogPanel.add(imagePanel);
		dialogPanel.add(dialogTextField);
		dialogPanel.add(dialogButton);
		
	}
}
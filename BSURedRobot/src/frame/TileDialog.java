package frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TileDialog extends JDialog{

	private static final long serialVersionUID = 732283671303623728L;
	private static Dimension preferredSize = new Dimension(300,150);
	private BorderLayout borderLayout;
	private ImagePanel imagePanel;
	private JPanel dialoguePanel;
	private JLabel dialogLabel;
	private JTextField dialogTextField;
	private JButton dialogButton;
	
	public TileDialog(JFrame frame, BufferedImage image)
	{
		super(frame, "Tile Discovery", false);
		super.setPreferredSize(preferredSize);
		borderLayout = new BorderLayout();
		dialoguePanel = new JPanel();
		dialoguePanel.setLayout(borderLayout);
		
		dialogLabel = new JLabel("          A new tile was discovered! Enter a name");
		dialogLabel.setAlignmentX(CENTER_ALIGNMENT);
		imagePanel= new ImagePanel(image);
		//dialoguePanel.add(imagePanel);
		dialoguePanel.add(dialogLabel, borderLayout.PAGE_START);
		dialoguePanel.add(imagePanel, borderLayout.LINE_START);
        getContentPane().add(dialoguePanel);
        pack();
        setLocationRelativeTo(frame);
        setVisible(true);
	}
	
}

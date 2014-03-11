package frame;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{
	
	private static final long serialVersionUID = -1611420697035533174L;
	private ImageIcon imageIcon;

    public ImagePanel(BufferedImage image) {
    	imageIcon = new ImageIcon(image);
    	JLabel label = new JLabel("", imageIcon, JLabel.CENTER);
    	this.add(label);
    
    }
    
}


package frame;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    private ImageIcon imageIcon;

    public ImagePanel(BufferedImage image) {
    	imageIcon = new ImageIcon(image);
    	JLabel label = new JLabel("", imageIcon, JLabel.CENTER);
    	this.add(label);
    
    }
    
}


package frame;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextPanel extends JPanel {
	private static final long serialVersionUID = -514877044964482643L;
		
	private JTextArea textArea = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane(textArea);
	BoxLayout boxLayout = new BoxLayout(this, BoxLayout.PAGE_AXIS);

		
	public TextPanel()
	{
		this.setLayout(boxLayout);
		this.setPreferredSize(new Dimension(400,400));
		textArea.setEditable(false);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		JLabel label = new JLabel("Action Log");
		label.setAlignmentX(CENTER_ALIGNMENT);
		this.setBorder(BorderFactory.createEmptyBorder(7, 3, 0, 7));
		this.add((label));
		this.add(new JLabel(" "));
		this.add(scrollPane);
		this.add(new JLabel( " "));
			
	}
		
	public void addText(String newText)
	{
		textArea.insert(newText, 0);
	}
		
	public void addTextLine(String newText)
	{
		textArea.insert(newText+"\n", 0);
	}
		
	public void clearText()
	{
		textArea.setText("");
	}
		
	public String getText()
	{
		return textArea.getText();
	}
		
		
}


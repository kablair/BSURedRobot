package frame;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TextPanel extends JPanel {
	private static final long serialVersionUID = -514877044964482643L;
		
	private JTextArea textArea = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane(textArea);
	private JTextField modeField = new JTextField();
	private JTextField modeField2 = new JTextField();
	private JPanel modePanel = new JPanel();
	BoxLayout boxLayout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
	FlowLayout flowLayout = new FlowLayout(FlowLayout.LEADING);
	
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
		this.add(modePanel);
		createModePanel();
		this.add(modePanel);
			
	}
	
	private void createModePanel()
	{
		modeField.setText(("Status:        "));
		modeField.setHorizontalAlignment(JTextField.CENTER);
		modeField.setEditable(false);
		modeField.setBorder(null);
		modeField2.setText(("Exploring Overworld "));
		modeField2.setHorizontalAlignment(JTextField.CENTER);
		modeField2.setEditable(false);
		modeField2.setBorder(null);
		modePanel.setLayout(flowLayout);
		modePanel.add(modeField);
		modePanel.add(modeField2);
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


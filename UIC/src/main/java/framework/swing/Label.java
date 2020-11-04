package framework.swing;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class Label extends JLabel {

	private static final long serialVersionUID = 1L;

	private Color defaultColor = ComponentFactory.newDefaultColor();
	private Font defaultFont   = ComponentFactory.newDefaultFont();
	
	
	public Label() {
		super();
		
		setForeground(defaultColor);
		setFont(defaultFont);
	}

	public Label(String text) {
		super(text);
		
		setForeground(defaultColor);
		setFont(defaultFont);
	}


}

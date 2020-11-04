package framework.swing;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class Button extends JButton {

	private static final long serialVersionUID = 1L;

	private Color defaultColor = ComponentFactory.newDefaultColor();
	private Font defaultFont   = ComponentFactory.newDefaultFont();
	
	
	
	public Button() {
		super();
		
		setForeground(defaultColor);
		setFont(defaultFont);
	}

	public Button(String text) {
		super(text);
		
		setForeground(defaultColor);
		setFont(defaultFont);
	}

}

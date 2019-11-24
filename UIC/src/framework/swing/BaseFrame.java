package framework.swing;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;

public class BaseFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	
	public BaseFrame() {
		super();
	}


	@Override
	public void setContentPane(Container contentPane) {
		contentPane.setBackground(Color.WHITE);		
		super.setContentPane(contentPane);
	}
	
	
	
}

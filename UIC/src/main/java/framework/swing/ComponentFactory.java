package framework.swing;

import java.awt.Color;
import java.awt.Font;

public class ComponentFactory {

	private ComponentFactory() {
	}

	
	public static Color newDefaultColor() {
		return new Color(25, 25, 112);
	}
	
	public static Font newDefaultFont() {
		return new Font("Tahoma", Font.BOLD, 14);
	}

	
}

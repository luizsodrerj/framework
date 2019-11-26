package framework.presentation.swing;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * Created on 23/05/2006
 *
 * @author Luiz Alberto
 */
public class Window {

    /**
     * Construtor privado para evitar instâncias
     * desnecessárias da classe
     */
    private Window() {
    }

    
    
    public static void setNimbusLookAndFeel() {
    	try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Centraliza uma janela
     *
     * @param window janela a ser centralizada
     */
    public static final void centralizeWindow(Component window) {
        Dimension screenSize    = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension componentSize = window.getSize();

        if (componentSize.height > screenSize.height) {
            componentSize.height = screenSize.height;
        }

        if (componentSize.width > screenSize.width) {
            componentSize.width = screenSize.width;
        }

        int x = (screenSize.width - componentSize.width) / 2;
        int y = (screenSize.height - componentSize.height) / 2;

        window.setLocation(x, y);
    }

    public static final WindowAdapter getDefaultWindowClosingEvent() {
        WindowAdapter windowAdapter = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };

        return windowAdapter;
    }

}







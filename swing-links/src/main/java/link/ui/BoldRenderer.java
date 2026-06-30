package link.ui;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class BoldRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;
	
	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
            boolean isSelected, boolean hasFocus, int row, int column) {
        
        // Call super to preserve default selections, borders, and backgrounds
		 Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        // Derive a bold version of the current table font
		 cellComponent.setFont(cellComponent.getFont().deriveFont(Font.BOLD)); 
        
        return cellComponent;
    }	

}

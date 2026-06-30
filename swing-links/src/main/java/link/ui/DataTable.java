package link.ui;

import java.awt.Component;
import java.awt.Font;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class DataTable extends JTable {

	private static final long serialVersionUID = 1L;
	
	private List<? extends Object>data;
	
	
	@Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component component = super.prepareRenderer(renderer, row, column);
        
        component.setFont(component.getFont().deriveFont(Font.BOLD));
        
        return component;
    }	

	
	public void setData(List<? extends Object> data) {
		this.data = data;
	}
	public <T>List<T> getData() {
		return (List<T>) data;
	}
}

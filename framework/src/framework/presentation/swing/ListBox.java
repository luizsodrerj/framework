package framework.presentation.swing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListModel;

/**
 * @author luiz.sodre
 */
public class ListBox extends JList {
    
    private List<Object>   objects = new ArrayList<Object>();
    private Vector<Object> values  = new Vector<Object>();
    
    
    /**
     * Construtor
     */
    public ListBox() {
        super();
    }
    
    /**
     * Construtor
     *
     * @param dataModel modelo de lista
     */
    public ListBox(ListModel dataModel) {
        super(dataModel);
    }
    
    /**
     * Construtor
     *
     * @param listData array de dados
     */
    public ListBox(Object[] listData) {
        super(listData);
    }
    
    /**
     * Construtor
     *
     * @param listData lista de dados
     */
    public ListBox(Vector<?> listData) {
        super(listData);
    }
    
    public Object getSelectedValueObject() {
        return objects.get(getSelectedIndex());
    }

    
    public List getSelectedValueObjects() {
        List<Object>    selectedValues  = new ArrayList<Object>();
        int[]           indices         = this.getSelectedIndices();
         
        for (int i = 0; i < indices.length; i++) {
            selectedValues.add(objects.get(indices[i]));
        }
        return selectedValues;
    }
    
    public void addValues(Collection<Object> values, Collection<Object> objects)  {
        this.values.clear();
        this.objects.clear();
        
        this.values.addAll(values);
        this.objects.addAll(objects);
        setListData(this.values);
    }
    
    public void addItem(String value, Object object) {
        values.add(value);
        objects.add(object);
        setListData(values);
    }
    
    public void setValue(int index, String value) {
        values.set(index, value);
        setListData(values);
    }
    
}











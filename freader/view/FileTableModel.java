
package freader.view;

import freader.model.Field;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

/**
 * Modèle du tableau pour la description du fichier.
 * @author chris-scientist
 */
public class FileTableModel extends AbstractTableModel {
    
    private List<Field> fieldSet = new ArrayList<Field>();
    
    private String[] header = {
        "Nom",
        "Taille"
    };
    
    public List<Field> getFieldSet() {
        return this.fieldSet;
    }
    
    @Override
    public int getRowCount() {
        return this.fieldSet.size();
    }

    @Override
    public int getColumnCount() {
        return this.header.length;
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return this.header[columnIndex];
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return this.fieldSet.get(rowIndex).getName();
            case 1:
                return this.fieldSet.get(rowIndex).getSize();
            default:
                return null;
        }
    }
    
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(aValue != null) {
            Field field = this.fieldSet.get(rowIndex);
            
            switch(columnIndex) {
                case 0:
                    field.setName((String)aValue);
                    break;
                case 1:
                    field.setSize((Integer)aValue);
                    break;
            }
        }
    }
    
    @Override
    public Class getColumnClass(int columnIndex) {
        switch(columnIndex) {
            case 0:
                return String.class;
            case 1:
                return Integer.class;
            default:
                return Object.class;
        }
    }
    
    public boolean haveNoData() {
        return this.fieldSet.isEmpty();
    }
    
    public void addRow(Field aField) {
        this.fieldSet.add(aField);
        
        fireTableRowsInserted(this.fieldSet.size() - 1, this.fieldSet.size() - 1);
    }
    
    public void removeRow(int rowIndex) {
        this.fieldSet.remove(rowIndex);
        
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
}

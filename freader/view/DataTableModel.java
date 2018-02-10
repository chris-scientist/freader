
package freader.view;

import freader.model.Data;
import freader.model.DataLine;
import freader.model.Field;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Modèle du tableau pour la lecture du fichier.
 * @author chris-scientist
 */
public class DataTableModel  extends AbstractTableModel {
    
    private List<DataLine> data = new ArrayList<DataLine>();
    
    private File file;
    
    private String[] header;
    
    private Integer[] size;
    
    public DataTableModel(FileTableModel fileModel, File aFile) throws IOException {
        
        this.header = new String[fileModel.getFieldSet().size()];
        this.size = new Integer[fileModel.getFieldSet().size()];
        
        int i = 0;
        for(Field field : fileModel.getFieldSet()) {
            this.header[i] = field.getName();
            this.size[i] = field.getSize();
            i++;
        }
        
        this.file = aFile;
        
        this.readFile();
    }
    
    private void readFile() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(this.file));
        try {
            String line = br.readLine();

            while (line != null) {
                int beginIndex = 0;
                DataLine dataLine = new DataLine();
                for(int i = 0 ; i<this.size.length ; i++) {
                    int stringSize = this.size[i];
                    dataLine.getDataLine().add(new Data(line.substring(beginIndex, beginIndex + stringSize)));
                    beginIndex += stringSize;
                }
                this.data.add(dataLine);
                line = br.readLine();
            }
        } finally {
            br.close();
        }
    }
    
    @Override
    public int getRowCount() {
        return this.data.size();
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
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.data.get(rowIndex).getDataLine().get(columnIndex).getValue();
    }
    
    @Override
    public Class getColumnClass(int columnIndex) {
        return String.class;
    }
    
}
